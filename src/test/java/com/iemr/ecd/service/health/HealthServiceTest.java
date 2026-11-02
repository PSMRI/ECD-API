/*
* AMRIT – Accessible Medical Records via Integrated Technology
* Integrated EHR (Electronic Health Records) Solution
*
* Copyright (C) "Piramal Swasthya Management and Research Institute"
*
* This file is part of AMRIT.
*
* This program is free software: you can redistribute it and/or modify
* it under the terms of the GNU General Public License as published by
* the Free Software Foundation, either version 3 of the License, or
* (at your option) any later version.
*
* This program is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
* GNU General Public License for more details.
*
* You should have received a copy of the GNU General Public License
* along with this program.  If not, see https://www.gnu.org/licenses/.
*/
package com.iemr.ecd.service.health;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import javax.sql.DataSource;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.util.ReflectionTestUtils;

import com.zaxxer.hikari.HikariDataSource;
import com.zaxxer.hikari.HikariPoolMXBean;

class HealthServiceTest {

	private DataSource dataSource;
	private RedisTemplate<String, Object> redisTemplate;
	private Connection connection;
	private HealthService service;

	@SuppressWarnings("unchecked")
	@BeforeEach
	void setUp() throws Exception {
		dataSource = mock(DataSource.class);
		redisTemplate = mock(RedisTemplate.class);
		connection = mock(Connection.class);
		when(dataSource.getConnection()).thenReturn(connection);
		when(redisTemplate.execute(any(RedisCallback.class))).thenReturn("PONG");
		service = new HealthService(dataSource, redisTemplate);
	}

	@AfterEach
	void tearDown() {
		service.shutdown();
	}

	/** Wires the health-check query plus both diagnostic queries. */
	private void stubQueries(boolean healthCheckHasRow, int lockWaits, int slowQueries) throws SQLException {
		PreparedStatement healthStatement = mock(PreparedStatement.class);
		ResultSet healthResult = mock(ResultSet.class);
		when(healthResult.next()).thenReturn(healthCheckHasRow);
		when(healthStatement.executeQuery()).thenReturn(healthResult);
		when(connection.prepareStatement("SELECT 1 as health_check")).thenReturn(healthStatement);

		PreparedStatement diagnosticStatement = mock(PreparedStatement.class);
		ResultSet lockResult = mock(ResultSet.class);
		when(lockResult.next()).thenReturn(true);
		when(lockResult.getInt(1)).thenReturn(lockWaits, slowQueries);
		when(diagnosticStatement.executeQuery()).thenReturn(lockResult);
		when(connection.prepareStatement(org.mockito.ArgumentMatchers
				.argThat(sql -> sql != null && sql.contains("PROCESSLIST")))).thenReturn(diagnosticStatement);
	}

	@SuppressWarnings("unchecked")
	private Map<String, Object> component(Map<String, Object> response, String name) {
		return ((Map<String, Map<String, Object>>) response.get("components")).get(name);
	}

	@Test
	void checkHealthReportsUpWhenBothComponentsRespond() throws Exception {
		stubQueries(true, 0, 0);

		Map<String, Object> response = service.checkHealth();

		assertEquals("UP", response.get("status"));
		assertNotNull(response.get("timestamp"));
		assertEquals("UP", component(response, "mysql").get("status"));
		assertEquals("OK", component(response, "mysql").get("severity"));
		assertEquals("UP", component(response, "redis").get("status"));
		assertNotNull(component(response, "redis").get("responseTimeMs"));
	}

	@Test
	void checkHealthReportsDownWhenHealthQueryReturnsNoRow() throws Exception {
		stubQueries(false, 0, 0);

		Map<String, Object> response = service.checkHealth();

		assertEquals("DOWN", response.get("status"));
		assertEquals("No result from health check query", component(response, "mysql").get("error"));
		assertEquals("CRITICAL", component(response, "mysql").get("severity"));
	}

	@Test
	void checkHealthReportsDownWhenConnectionFails() throws Exception {
		when(dataSource.getConnection()).thenThrow(new SQLException("no connection"));

		Map<String, Object> response = service.checkHealth();

		assertEquals("DOWN", response.get("status"));
		assertEquals("MySQL connection failed", component(response, "mysql").get("error"));
	}

	@Test
	void checkHealthReportsDegradedWhenLockWaitsDetected() throws Exception {
		stubQueries(true, 4, 0);

		Map<String, Object> response = service.checkHealth();

		assertEquals("DEGRADED", response.get("status"));
		assertEquals("DEGRADED", component(response, "mysql").get("status"));
		assertEquals("WARNING", component(response, "mysql").get("severity"));
	}

	@Test
	void checkHealthReportsDegradedWhenSlowQueriesDetected() throws Exception {
		stubQueries(true, 0, 9);

		assertEquals("DEGRADED", service.checkHealth().get("status"));
	}

	@Test
	void checkHealthSkipsRedisWhenNotConfigured() throws Exception {
		service.shutdown();
		service = new HealthService(dataSource, null);
		stubQueries(true, 0, 0);

		Map<String, Object> response = service.checkHealth();

		assertEquals("UP", response.get("status"));
		assertEquals("Redis not configured — skipped", component(response, "redis").get("message"));
	}

	@SuppressWarnings("unchecked")
	@Test
	void checkHealthReportsDownWhenRedisDoesNotPong() throws Exception {
		stubQueries(true, 0, 0);
		when(redisTemplate.execute(any(RedisCallback.class))).thenReturn("NOPE");

		Map<String, Object> response = service.checkHealth();

		assertEquals("DOWN", response.get("status"));
		assertEquals("Redis PING failed", component(response, "redis").get("error"));
	}

	@SuppressWarnings("unchecked")
	@Test
	void checkHealthReportsDownWhenRedisThrows() throws Exception {
		stubQueries(true, 0, 0);
		when(redisTemplate.execute(any(RedisCallback.class))).thenThrow(new IllegalStateException("redis down"));

		Map<String, Object> response = service.checkHealth();

		assertEquals("DOWN", response.get("status"));
		assertEquals("Redis connection failed", component(response, "redis").get("error"));
	}

	@Test
	void checkHealthMarksComponentsDownWhenExecutorIsShutdown() {
		service.shutdown();

		Map<String, Object> response = service.checkHealth();

		assertEquals("DOWN", response.get("status"));
		assertTrue(((String) component(response, "mysql").get("error")).contains("did not complete in time"));
		assertTrue(((String) component(response, "redis").get("error")).contains("did not complete in time"));
	}

	@Test
	void advancedChecksAreThrottledBetweenCalls() throws Exception {
		stubQueries(true, 0, 0);

		service.checkHealth();
		Object firstCache = ReflectionTestUtils.getField(service, "cachedAdvancedCheckResult");
		service.checkHealth();

		assertNotNull(firstCache);
		assertEquals(firstCache, ReflectionTestUtils.getField(service, "cachedAdvancedCheckResult"));
	}

	@Test
	void shutdownIsIdempotent() {
		service.shutdown();
		service.shutdown();

		Object executor = ReflectionTestUtils.getField(service, "executorService");
		assertTrue(((java.util.concurrent.ExecutorService) executor).isShutdown());
	}

	@Test
	void determineSeverityRanksHealthResponseTimeAndDegradation() {
		assertEquals("CRITICAL", ReflectionTestUtils.invokeMethod(service, "determineSeverity", false, 5L, false));
		assertEquals("WARNING", ReflectionTestUtils.invokeMethod(service, "determineSeverity", true, 5L, true));
		assertEquals("WARNING", ReflectionTestUtils.invokeMethod(service, "determineSeverity", true, 5000L, false));
		assertEquals("OK", ReflectionTestUtils.invokeMethod(service, "determineSeverity", true, 5L, false));
	}

	@Test
	void computeOverallStatusPrefersCriticalThenDegraded() {
		assertEquals("DOWN", ReflectionTestUtils.invokeMethod(service, "computeOverallStatus",
				Map.of("mysql", Map.of("status", "DOWN", "severity", "CRITICAL"))));
		assertEquals("DOWN", ReflectionTestUtils.invokeMethod(service, "computeOverallStatus",
				Map.of("mysql", Map.of("status", "UP", "severity", "CRITICAL"))));
		assertEquals("DEGRADED", ReflectionTestUtils.invokeMethod(service, "computeOverallStatus",
				Map.of("mysql", Map.of("status", "DEGRADED", "severity", "OK"))));
		assertEquals("DEGRADED", ReflectionTestUtils.invokeMethod(service, "computeOverallStatus",
				Map.of("mysql", Map.of("status", "UP", "severity", "WARNING"))));
		assertEquals("UP", ReflectionTestUtils.invokeMethod(service, "computeOverallStatus",
				Map.of("mysql", Map.of("status", "UP", "severity", "OK"))));
	}

	@Test
	void lockWaitCheckReportsFalseWhenQueryFails() throws Exception {
		when(connection.prepareStatement(anyString())).thenThrow(new SQLException("denied"));

		assertFalse(Boolean.TRUE.equals(ReflectionTestUtils.invokeMethod(service, "hasLockWaits", connection)));
		assertFalse(Boolean.TRUE.equals(ReflectionTestUtils.invokeMethod(service, "hasSlowQueries", connection)));
	}

	@Test
	void lockWaitCheckReportsFalseWhenNoRowReturned() throws Exception {
		PreparedStatement statement = mock(PreparedStatement.class);
		ResultSet resultSet = mock(ResultSet.class);
		when(resultSet.next()).thenReturn(false);
		when(statement.executeQuery()).thenReturn(resultSet);
		when(connection.prepareStatement(anyString())).thenReturn(statement);

		assertFalse(Boolean.TRUE.equals(ReflectionTestUtils.invokeMethod(service, "hasLockWaits", connection)));
		assertFalse(Boolean.TRUE.equals(ReflectionTestUtils.invokeMethod(service, "hasSlowQueries", connection)));
	}

	@Test
	void poolExhaustionUsesHikariMetricsWhenAvailable() {
		HikariDataSource hikariDataSource = mock(HikariDataSource.class);
		HikariPoolMXBean poolMXBean = mock(HikariPoolMXBean.class);
		when(hikariDataSource.getHikariPoolMXBean()).thenReturn(poolMXBean);
		when(hikariDataSource.getMaximumPoolSize()).thenReturn(10);
		when(poolMXBean.getActiveConnections()).thenReturn(9);
		HealthService hikariService = new HealthService(hikariDataSource, redisTemplate);

		try {
			assertTrue(Boolean.TRUE
					.equals(ReflectionTestUtils.invokeMethod(hikariService, "hasConnectionPoolExhaustion")));
		} finally {
			hikariService.shutdown();
		}
	}

	@Test
	void poolExhaustionFallsBackToJmxWhenHikariMetricsMissing() {
		HikariDataSource hikariDataSource = mock(HikariDataSource.class);
		when(hikariDataSource.getHikariPoolMXBean()).thenReturn(null);
		HealthService hikariService = new HealthService(hikariDataSource, redisTemplate);

		try {
			assertFalse(Boolean.TRUE
					.equals(ReflectionTestUtils.invokeMethod(hikariService, "hasConnectionPoolExhaustion")));
		} finally {
			hikariService.shutdown();
		}
	}

	@Test
	void poolExhaustionSwallowsHikariMetricFailures() {
		HikariDataSource hikariDataSource = mock(HikariDataSource.class);
		when(hikariDataSource.getHikariPoolMXBean()).thenThrow(new IllegalStateException("no bean"));
		HealthService hikariService = new HealthService(hikariDataSource, redisTemplate);

		try {
			assertFalse(Boolean.TRUE
					.equals(ReflectionTestUtils.invokeMethod(hikariService, "hasConnectionPoolExhaustion")));
		} finally {
			hikariService.shutdown();
		}
	}

	@Test
	void poolMetricsViaJmxReturnsFalseWhenNoPoolRegistered() {
		assertFalse(Boolean.TRUE.equals(ReflectionTestUtils.invokeMethod(service, "checkPoolMetricsViaJMX")));
	}

	@Test
	void evaluatePoolMetricsReturnsFalseWhenAttributesUnavailable() throws Exception {
		javax.management.MBeanServer mBeanServer = mock(javax.management.MBeanServer.class);
		javax.management.ObjectName objectName = new javax.management.ObjectName("com.zaxxer.hikari:type=Pool (x)");
		when(mBeanServer.getAttribute(objectName, "ActiveConnections"))
				.thenThrow(new javax.management.AttributeNotFoundException("missing"));

		assertFalse(Boolean.TRUE.equals(
				ReflectionTestUtils.invokeMethod(service, "evaluatePoolMetrics", mBeanServer, objectName)));
	}

	@Test
	void evaluatePoolMetricsFlagsExhaustedPool() throws Exception {
		javax.management.MBeanServer mBeanServer = mock(javax.management.MBeanServer.class);
		javax.management.ObjectName objectName = new javax.management.ObjectName("com.zaxxer.hikari:type=Pool (x)");
		when(mBeanServer.getAttribute(objectName, "ActiveConnections")).thenReturn(9);
		when(mBeanServer.getAttribute(objectName, "MaximumPoolSize")).thenReturn(10);

		assertTrue(Boolean.TRUE.equals(
				ReflectionTestUtils.invokeMethod(service, "evaluatePoolMetrics", mBeanServer, objectName)));
	}

	@Test
	void advancedCheckFutureTreatsTimeoutAsDegraded() {
		Future<?> future = new CompletableFuture<>();

		Object result = ReflectionTestUtils.invokeMethod(service, "handleAdvancedChecksFuture", future);

		assertTrue((Boolean) ReflectionTestUtils.getField(result, "isDegraded"));
	}

	@Test
	void advancedCheckFutureTreatsExecutionFailureAsDegraded() {
		CompletableFuture<Object> future = new CompletableFuture<>();
		future.completeExceptionally(new IllegalStateException("boom"));

		Object result = ReflectionTestUtils.invokeMethod(service, "handleAdvancedChecksFuture", future);

		assertTrue((Boolean) ReflectionTestUtils.getField(result, "isDegraded"));
	}

	@Test
	void advancedCheckFutureTreatsInterruptedCauseAsDegraded() {
		CompletableFuture<Object> future = new CompletableFuture<>();
		future.completeExceptionally(new InterruptedException("interrupted"));

		Object result = ReflectionTestUtils.invokeMethod(service, "handleAdvancedChecksFuture", future);

		assertTrue((Boolean) ReflectionTestUtils.getField(result, "isDegraded"));
		Thread.interrupted();
	}

	@Test
	void advancedCheckFutureTreatsInterruptionAsDegraded() throws Exception {
		@SuppressWarnings("unchecked")
		Future<Object> future = mock(Future.class);
		when(future.get(org.mockito.ArgumentMatchers.anyLong(), any(TimeUnit.class)))
				.thenThrow(new InterruptedException("interrupted"));

		Object result = ReflectionTestUtils.invokeMethod(service, "handleAdvancedChecksFuture", future);

		assertTrue((Boolean) ReflectionTestUtils.getField(result, "isDegraded"));
		Thread.interrupted();
	}

	@Test
	void advancedChecksMarkDegradedWhenConnectionUnavailable() throws Exception {
		when(dataSource.getConnection()).thenThrow(new SQLException("down"));

		Object result = ReflectionTestUtils.invokeMethod(service, "performAdvancedMySQLChecks");

		assertTrue((Boolean) ReflectionTestUtils.getField(result, "isDegraded"));
	}
}

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
package com.iemr.ecd.utils.aop.logging_advice;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.junit.jupiter.api.Test;

class LoggingAdviceTest {

	private ProceedingJoinPoint joinPoint(Object target) {
		ProceedingJoinPoint pjp = mock(ProceedingJoinPoint.class);
		Signature signature = mock(Signature.class);
		when(signature.getName()).thenReturn("someMethod");
		when(pjp.getSignature()).thenReturn(signature);
		when(pjp.getTarget()).thenReturn(target);
		when(pjp.getArgs()).thenReturn(new Object[] { "arg" });
		return pjp;
	}

	@Test
	void applicationLoggerReturnsProceedResult() throws Throwable {
		ProceedingJoinPoint pjp = joinPoint(this);
		when(pjp.proceed()).thenReturn("result");

		assertEquals("result", new LoggingAdvice().applicationLogger(pjp));
		verify(pjp).proceed();
	}

	@Test
	void applicationLoggerPropagatesFailure() throws Throwable {
		ProceedingJoinPoint pjp = joinPoint(this);
		when(pjp.proceed()).thenThrow(new IllegalStateException("boom"));

		assertThrows(IllegalStateException.class, () -> new LoggingAdvice().applicationLogger(pjp));
	}
}

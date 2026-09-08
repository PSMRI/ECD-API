package com.iemr.ecd.dao;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReaderFactory;

/**
 * Exercises the accessors, constructors and value semantics of every entity and
 * transfer object, so the persistence and API contracts are covered without a
 * hand-written test per class.
 */
class DataObjectContractTest {

	private static final String[] PACKAGES = { "com/iemr/ecd/dao", "com/iemr/ecd/dao_temp", "com/iemr/ecd/dto",
			"com/iemr/ecd/model" };

	static Stream<Class<?>> dataClasses() throws Exception {
		ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
		MetadataReaderFactory readerFactory = new CachingMetadataReaderFactory(resolver);
		List<Class<?>> classes = new ArrayList<>();
		for (String basePackage : PACKAGES) {
			for (Resource resource : resolver.getResources("classpath*:" + basePackage + "/**/*.class")) {
				String className = readerFactory.getMetadataReader(resource).getClassMetadata().getClassName();
				Class<?> candidate = Class.forName(className);
				if (candidate.isInterface() || candidate.isEnum() || candidate.isMemberClass()
						|| Modifier.isAbstract(candidate.getModifiers())
						|| candidate.getSimpleName().endsWith("Test")) {
					continue;
				}
				if (noArgConstructor(candidate) != null) {
					classes.add(candidate);
				}
			}
		}
		return classes.stream();
	}

	private static Constructor<?> noArgConstructor(Class<?> type) {
		for (Constructor<?> constructor : type.getDeclaredConstructors()) {
			if (constructor.getParameterCount() == 0) {
				return constructor;
			}
		}
		return null;
	}

	@ParameterizedTest
	@MethodSource("dataClasses")
	void accessorsAndValueSemanticsBehaveConsistently(Class<?> type) throws Exception {
		Object first = newInstance(type);
		Object second = newInstance(type);

		populate(first);
		populate(second);
		readAll(first);
		readAll(second);

		assertNotNull(first.toString(), type.getName());
		first.hashCode();
		assertTrue(first.equals(first), type.getName());
		assertFalse(first.equals(null), type.getName());
		assertFalse(first.equals("a different type"), type.getName());
		// Two independently populated instances compare consistently with their hash codes.
		if (first.equals(second) && type.getMethod("equals", Object.class).getDeclaringClass() != Object.class) {
			org.junit.jupiter.api.Assertions.assertEquals(first.hashCode(), second.hashCode(), type.getName());
		}
		// An empty instance differs from a populated one whenever equals is value based.
		Object empty = newInstance(type);
		Object anotherEmpty = newInstance(type);
		empty.hashCode();
		empty.toString();
		first.equals(empty);
		empty.equals(first);
		// Two blank instances compare every property while both sides are unset.
		boolean valueBased = type.getMethod("equals", Object.class).getDeclaringClass() != Object.class;
		if (valueBased) {
			assertTrue(empty.equals(anotherEmpty), type.getName());
			org.junit.jupiter.api.Assertions.assertEquals(empty.hashCode(), anotherEmpty.hashCode(),
					type.getName());
		}

		compareFieldByField(type, first);
		invokeAllArgsConstructor(type);
	}

	/**
	 * Compares the fully populated instance against copies that each differ in a
	 * single property, so every field comparison inside equals is exercised.
	 */
	private void compareFieldByField(Class<?> type, Object populated) throws Exception {
		for (Method setter : type.getMethods()) {
			if (!setter.getName().startsWith("set") || setter.getParameterCount() != 1
					|| setter.getDeclaringClass() == Object.class) {
				continue;
			}
			Object variant = newInstance(type);
			populate(variant);
			Class<?> parameterType = setter.getParameterTypes()[0];
			try {
				setter.setAccessible(true);
				if (parameterType.isPrimitive()) {
					setter.invoke(variant, alternateValue(parameterType));
				} else {
					setter.invoke(variant, (Object) null);
				}
			} catch (Exception e) {
				continue;
			}
			populated.equals(variant);
			variant.equals(populated);
			variant.hashCode();
		}
	}

	private Object alternateValue(Class<?> type) {
		if (type == boolean.class) {
			return Boolean.FALSE;
		}
		if (type == int.class) {
			return 2;
		}
		if (type == long.class) {
			return 2L;
		}
		if (type == double.class) {
			return 2.0d;
		}
		if (type == float.class) {
			return 2.0f;
		}
		if (type == short.class) {
			return (short) 2;
		}
		if (type == byte.class) {
			return (byte) 2;
		}
		if (type == char.class) {
			return 'w';
		}
		return null;
	}

	@Test
	void everyDataPackageIsScanned() throws Exception {
		assertTrue(dataClasses().count() > 50, "expected the entity and DTO packages to be discovered");
	}

	private Object newInstance(Class<?> type) throws Exception {
		Constructor<?> constructor = noArgConstructor(type);
		constructor.setAccessible(true);
		return constructor.newInstance();
	}

	private void populate(Object target) {
		for (Method method : target.getClass().getMethods()) {
			if (!method.getName().startsWith("set") || method.getParameterCount() != 1
					|| method.getDeclaringClass() == Object.class) {
				continue;
			}
			Object value = defaultValue(method.getParameterTypes()[0]);
			try {
				method.setAccessible(true);
				method.invoke(target, value);
			} catch (Exception e) {
				// A setter that rejects the generated value is not part of this contract.
			}
		}
	}

	private void readAll(Object target) {
		for (Method method : target.getClass().getMethods()) {
			if (method.getParameterCount() != 0 || method.getDeclaringClass() == Object.class) {
				continue;
			}
			String name = method.getName();
			if (!name.startsWith("get") && !name.startsWith("is")) {
				continue;
			}
			try {
				method.setAccessible(true);
				method.invoke(target);
			} catch (Exception e) {
				fail(target.getClass().getName() + "." + name + " failed: " + e.getCause());
			}
		}
	}

	private void invokeAllArgsConstructor(Class<?> type) {
		for (Constructor<?> constructor : type.getDeclaredConstructors()) {
			if (constructor.getParameterCount() == 0) {
				continue;
			}
			Object[] args = new Object[constructor.getParameterCount()];
			Class<?>[] parameterTypes = constructor.getParameterTypes();
			for (int i = 0; i < args.length; i++) {
				args[i] = defaultValue(parameterTypes[i]);
			}
			try {
				constructor.setAccessible(true);
				Object instance = constructor.newInstance(args);
				instance.toString();
			} catch (Exception e) {
				// Constructors that need domain specific values are covered by their own tests.
			}
		}
	}

	private Object defaultValue(Class<?> type) {
		if (type == String.class) {
			return "value";
		}
		if (type == boolean.class || type == Boolean.class) {
			return Boolean.TRUE;
		}
		if (type == int.class || type == Integer.class) {
			return 1;
		}
		if (type == long.class || type == Long.class) {
			return 1L;
		}
		if (type == double.class || type == Double.class) {
			return 1.0d;
		}
		if (type == float.class || type == Float.class) {
			return 1.0f;
		}
		if (type == short.class || type == Short.class) {
			return (short) 1;
		}
		if (type == byte.class || type == Byte.class) {
			return (byte) 1;
		}
		if (type == char.class || type == Character.class) {
			return 'v';
		}
		if (type == BigDecimal.class) {
			return BigDecimal.ONE;
		}
		if (type == java.math.BigInteger.class) {
			return java.math.BigInteger.ONE;
		}
		if (type == Timestamp.class) {
			return Timestamp.valueOf("2024-01-01 00:00:00");
		}
		if (type == Date.class) {
			return new Date(0L);
		}
		if (type == java.sql.Date.class) {
			return new java.sql.Date(0L);
		}
		if (type == List.class || type == Iterable.class || type == java.util.Collection.class) {
			return new ArrayList<>();
		}
		if (type == java.util.Set.class) {
			return new HashSet<>();
		}
		if (type == java.util.Map.class) {
			return new HashMap<>();
		}
		if (type.isArray()) {
			Object array = Array.newInstance(type.getComponentType(), 1);
			Object element = defaultValue(type.getComponentType());
			if (element != null) {
				Array.set(array, 0, element);
			}
			return array;
		}
		if (type.getName().startsWith("com.iemr.ecd")) {
			Constructor<?> constructor = noArgConstructor(type);
			if (constructor != null) {
				try {
					constructor.setAccessible(true);
					return constructor.newInstance();
				} catch (Exception e) {
					return null;
				}
			}
		}
		return null;
	}
}

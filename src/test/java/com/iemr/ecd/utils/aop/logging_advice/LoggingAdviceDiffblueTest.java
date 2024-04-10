package com.iemr.ecd.utils.aop.logging_advice;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {LoggingAdvice.class})
@ExtendWith(SpringExtension.class)
class LoggingAdviceDiffblueTest {
    @Autowired
    private LoggingAdvice loggingAdvice;

    /**
     * Method under test:
     * {@link LoggingAdvice#applicationLogger(ProceedingJoinPoint)}
     */
    @Test
    void testApplicationLogger() throws Throwable {
        // Arrange
        Signature signature = mock(Signature.class);
        when(signature.getName()).thenReturn("Name");
        ProceedingJoinPoint pjp = mock(ProceedingJoinPoint.class);
        when(pjp.proceed()).thenReturn("Proceed");
        when(pjp.getArgs()).thenReturn(new Object[]{"Args"});
        when(pjp.getTarget()).thenReturn("Target");
        when(pjp.getSignature()).thenReturn(signature);

        // Act
        Object actualApplicationLoggerResult = loggingAdvice.applicationLogger(pjp);

        // Assert
        verify(pjp).getArgs();
        verify(pjp).getSignature();
        verify(pjp).getTarget();
        verify(pjp).proceed();
        verify(signature).getName();
        assertEquals("Proceed", actualApplicationLoggerResult);
    }

    /**
     * Method under test:
     * {@link LoggingAdvice#applicationLogger(ProceedingJoinPoint)}
     */
    @Test
    void testApplicationLogger2() throws Throwable {
        // Arrange
        Signature signature = mock(Signature.class);
        when(signature.getName()).thenReturn("Name");
        ProceedingJoinPoint pjp = mock(ProceedingJoinPoint.class);
        when(pjp.proceed()).thenReturn(42);
        when(pjp.getArgs()).thenReturn(new Object[]{"Args"});
        when(pjp.getTarget()).thenReturn("Target");
        when(pjp.getSignature()).thenReturn(signature);

        // Act
        loggingAdvice.applicationLogger(pjp);

        // Assert
        verify(pjp).getArgs();
        verify(pjp).getSignature();
        verify(pjp).getTarget();
        verify(pjp).proceed();
        verify(signature).getName();
    }

    /**
     * Method under test:
     * {@link LoggingAdvice#applicationLogger(ProceedingJoinPoint)}
     */
    @Test
    void testApplicationLogger3() throws Throwable {
        // Arrange
        Signature signature = mock(Signature.class);
        when(signature.getName()).thenReturn("Name");
        ProceedingJoinPoint pjp = mock(ProceedingJoinPoint.class);
        when(pjp.proceed()).thenReturn("Proceed");
        when(pjp.getArgs()).thenReturn(new Object[]{"42", "42"});
        when(pjp.getTarget()).thenReturn("Target");
        when(pjp.getSignature()).thenReturn(signature);

        // Act
        Object actualApplicationLoggerResult = loggingAdvice.applicationLogger(pjp);

        // Assert
        verify(pjp).getArgs();
        verify(pjp).getSignature();
        verify(pjp).getTarget();
        verify(pjp).proceed();
        verify(signature).getName();
        assertEquals("Proceed", actualApplicationLoggerResult);
    }

    /**
     * Method under test:
     * {@link LoggingAdvice#applicationLogger(ProceedingJoinPoint)}
     */
    @Test
    void testApplicationLogger4() throws Throwable {
        // Arrange
        Signature signature = mock(Signature.class);
        when(signature.getName()).thenReturn("Name");
        ProceedingJoinPoint pjp = mock(ProceedingJoinPoint.class);
        when(pjp.proceed()).thenReturn(42);
        when(pjp.getArgs()).thenReturn(new Object[]{42});
        when(pjp.getTarget()).thenReturn("Target");
        when(pjp.getSignature()).thenReturn(signature);

        // Act
        loggingAdvice.applicationLogger(pjp);

        // Assert
        verify(pjp).getArgs();
        verify(pjp).getSignature();
        verify(pjp).getTarget();
        verify(pjp).proceed();
        verify(signature).getName();
    }
}

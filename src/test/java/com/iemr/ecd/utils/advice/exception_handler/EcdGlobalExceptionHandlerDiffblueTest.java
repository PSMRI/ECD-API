package com.iemr.ecd.utils.advice.exception_handler;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {EcdGlobalExceptionHandler.class, ECDException.class, InvalidRequestException.class})
@ExtendWith(SpringExtension.class)
class EcdGlobalExceptionHandlerDiffblueTest {
    @Autowired
    private InvalidRequestException invalidRequestException;

    @Autowired
    private ECDException eCDException;

    @Autowired
    private EcdGlobalExceptionHandler ecdGlobalExceptionHandler;

    /**
     * Method under test:
     * {@link EcdGlobalExceptionHandler#handleInvalidRequestParameterException(InvalidRequestException)}
     */
    @Test
    void testHandleInvalidRequestParameterException() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange
        EcdGlobalExceptionHandler ecdGlobalExceptionHandler = new EcdGlobalExceptionHandler();
        InvalidRequestException e = mock(InvalidRequestException.class);
        when(e.getSuppressed()).thenReturn(new Throwable[]{new Throwable()});
        when(e.getStackTrace()).thenReturn(new StackTraceElement[]{new StackTraceElement("foo", "foo", "foo", 1)});
        when(e.getLocalizedMessage()).thenReturn("Localized Message");
        when(e.getMessage()).thenReturn("Not all who wander are lost");
        when(e.getCause()).thenReturn(new Throwable());

        // Act
        CustomExceptionResponse actualHandleInvalidRequestParameterExceptionResult = ecdGlobalExceptionHandler
                .handleInvalidRequestParameterException(e);

        // Assert
        verify(e, atLeast(1)).getCause();
        verify(e).getLocalizedMessage();
        verify(e, atLeast(1)).getMessage();
        verify(e, atLeast(1)).getStackTrace();
        verify(e).getSuppressed();
        assertEquals("Not all who wander are lost", actualHandleInvalidRequestParameterExceptionResult.getErrorMessage());
        assertEquals(CustomExceptionResponse.GENERIC_FAILURE,
                actualHandleInvalidRequestParameterExceptionResult.getStatusCode());
    }

    /**
     * Method under test:
     * {@link EcdGlobalExceptionHandler#handleInvalidRequestParameterException(InvalidRequestException)}
     */
    @Test
    void testHandleInvalidRequestParameterException2() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange
        EcdGlobalExceptionHandler ecdGlobalExceptionHandler = new EcdGlobalExceptionHandler();

        IOException ioException = new IOException("foo");
        ioException.addSuppressed(new Throwable());
        InvalidRequestException e = mock(InvalidRequestException.class);
        when(e.getSuppressed()).thenReturn(new Throwable[]{new Throwable()});
        when(e.getStackTrace()).thenReturn(new StackTraceElement[]{new StackTraceElement("foo", "foo", "foo", 1)});
        when(e.getLocalizedMessage()).thenReturn("Localized Message");
        when(e.getMessage()).thenReturn("Not all who wander are lost");
        when(e.getCause()).thenReturn(ioException);

        // Act
        CustomExceptionResponse actualHandleInvalidRequestParameterExceptionResult = ecdGlobalExceptionHandler
                .handleInvalidRequestParameterException(e);

        // Assert
        verify(e, atLeast(1)).getCause();
        verify(e).getLocalizedMessage();
        verify(e, atLeast(1)).getMessage();
        verify(e, atLeast(1)).getStackTrace();
        verify(e).getSuppressed();
        assertEquals("Not all who wander are lost", actualHandleInvalidRequestParameterExceptionResult.getErrorMessage());
        assertEquals(CustomExceptionResponse.ENVIRONMENT_EXCEPTION,
                actualHandleInvalidRequestParameterExceptionResult.getStatusCode());
    }

    /**
     * Method under test:
     * {@link EcdGlobalExceptionHandler#handleInvalidRequestParameterException(InvalidRequestException)}
     */
    @Test
    void testHandleInvalidRequestParameterException3() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange
        EcdGlobalExceptionHandler ecdGlobalExceptionHandler = new EcdGlobalExceptionHandler();

        ArrayIndexOutOfBoundsException arrayIndexOutOfBoundsException = new ArrayIndexOutOfBoundsException(1);
        arrayIndexOutOfBoundsException.addSuppressed(new Throwable());
        InvalidRequestException e = mock(InvalidRequestException.class);
        when(e.getSuppressed()).thenReturn(new Throwable[]{new Throwable()});
        when(e.getStackTrace()).thenReturn(new StackTraceElement[]{new StackTraceElement("foo", "foo", "foo", 1)});
        when(e.getLocalizedMessage()).thenReturn("Localized Message");
        when(e.getMessage()).thenReturn("Not all who wander are lost");
        when(e.getCause()).thenReturn(arrayIndexOutOfBoundsException);

        // Act
        CustomExceptionResponse actualHandleInvalidRequestParameterExceptionResult = ecdGlobalExceptionHandler
                .handleInvalidRequestParameterException(e);

        // Assert
        verify(e, atLeast(1)).getCause();
        verify(e).getLocalizedMessage();
        verify(e, atLeast(1)).getMessage();
        verify(e, atLeast(1)).getStackTrace();
        verify(e).getSuppressed();
        assertEquals("Not all who wander are lost", actualHandleInvalidRequestParameterExceptionResult.getErrorMessage());
        assertEquals(CustomExceptionResponse.ENVIRONMENT_EXCEPTION,
                actualHandleInvalidRequestParameterExceptionResult.getStatusCode());
    }

    /**
     * Method under test:
     * {@link EcdGlobalExceptionHandler#handleInvalidRequestParameterException(InvalidRequestException)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testHandleInvalidRequestParameterException4() {
        // TODO: Diffblue Cover was only able to create a partial test for this method:
        //   Reason: No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "Object.getClass()" because the return value of "java.lang.Throwable.getCause()" is null
        //       at com.iemr.ecd.utils.advice.exception_handler.CustomExceptionResponse.setError(CustomExceptionResponse.java:105)
        //       at com.iemr.ecd.utils.advice.exception_handler.EcdGlobalExceptionHandler.handleInvalidRequestParameterException(EcdGlobalExceptionHandler.java:39)
        //   See https://diff.blue/R013 to resolve this issue.

        // Arrange and Act
        ecdGlobalExceptionHandler.handleInvalidRequestParameterException(invalidRequestException);
    }

    /**
     * Method under test:
     * {@link EcdGlobalExceptionHandler#handleGeneralException(ECDException)}
     */
    @Test
    void testHandleGeneralException() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange
        EcdGlobalExceptionHandler ecdGlobalExceptionHandler = new EcdGlobalExceptionHandler();

        // Act
        CustomExceptionResponse actualHandleGeneralExceptionResult = ecdGlobalExceptionHandler
                .handleGeneralException(new ECDException(new Throwable()));

        // Assert
        assertEquals("java.lang.Throwable", actualHandleGeneralExceptionResult.getErrorMessage());
        assertEquals(CustomExceptionResponse.GENERIC_FAILURE, actualHandleGeneralExceptionResult.getStatusCode());
    }

    /**
     * Method under test:
     * {@link EcdGlobalExceptionHandler#handleGeneralException(ECDException)}
     */
    @Test
    void testHandleGeneralException2() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange
        EcdGlobalExceptionHandler ecdGlobalExceptionHandler = new EcdGlobalExceptionHandler();
        ECDException e = mock(ECDException.class);
        when(e.getSuppressed()).thenReturn(new Throwable[]{new Throwable()});
        when(e.getStackTrace()).thenReturn(new StackTraceElement[]{new StackTraceElement("foo", "foo", "foo", 1)});
        when(e.getLocalizedMessage()).thenReturn("Localized Message");
        when(e.getMessage()).thenReturn("Not all who wander are lost");
        when(e.getCause()).thenReturn(new Throwable());

        // Act
        CustomExceptionResponse actualHandleGeneralExceptionResult = ecdGlobalExceptionHandler.handleGeneralException(e);

        // Assert
        verify(e, atLeast(1)).getCause();
        verify(e).getLocalizedMessage();
        verify(e, atLeast(1)).getMessage();
        verify(e, atLeast(1)).getStackTrace();
        verify(e).getSuppressed();
        assertEquals("Not all who wander are lost", actualHandleGeneralExceptionResult.getErrorMessage());
        assertEquals(CustomExceptionResponse.GENERIC_FAILURE, actualHandleGeneralExceptionResult.getStatusCode());
    }

    /**
     * Method under test:
     * {@link EcdGlobalExceptionHandler#handleGeneralException(ECDException)}
     */
    @Test
    void testHandleGeneralException3() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange
        EcdGlobalExceptionHandler ecdGlobalExceptionHandler = new EcdGlobalExceptionHandler();

        IOException ioException = new IOException("foo");
        ioException.addSuppressed(new Throwable());
        ECDException e = mock(ECDException.class);
        when(e.getSuppressed()).thenReturn(new Throwable[]{new Throwable()});
        when(e.getStackTrace()).thenReturn(new StackTraceElement[]{new StackTraceElement("foo", "foo", "foo", 1)});
        when(e.getLocalizedMessage()).thenReturn("Localized Message");
        when(e.getMessage()).thenReturn("Not all who wander are lost");
        when(e.getCause()).thenReturn(ioException);

        // Act
        CustomExceptionResponse actualHandleGeneralExceptionResult = ecdGlobalExceptionHandler.handleGeneralException(e);

        // Assert
        verify(e, atLeast(1)).getCause();
        verify(e).getLocalizedMessage();
        verify(e, atLeast(1)).getMessage();
        verify(e, atLeast(1)).getStackTrace();
        verify(e).getSuppressed();
        assertEquals("Not all who wander are lost", actualHandleGeneralExceptionResult.getErrorMessage());
        assertEquals(CustomExceptionResponse.ENVIRONMENT_EXCEPTION, actualHandleGeneralExceptionResult.getStatusCode());
    }

    /**
     * Method under test:
     * {@link EcdGlobalExceptionHandler#handleGeneralException(ECDException)}
     */
    @Test
    void testHandleGeneralException4() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange
        EcdGlobalExceptionHandler ecdGlobalExceptionHandler = new EcdGlobalExceptionHandler();

        ArrayIndexOutOfBoundsException arrayIndexOutOfBoundsException = new ArrayIndexOutOfBoundsException(1);
        arrayIndexOutOfBoundsException.addSuppressed(new Throwable());
        ECDException e = mock(ECDException.class);
        when(e.getSuppressed()).thenReturn(new Throwable[]{new Throwable()});
        when(e.getStackTrace()).thenReturn(new StackTraceElement[]{new StackTraceElement("foo", "foo", "foo", 1)});
        when(e.getLocalizedMessage()).thenReturn("Localized Message");
        when(e.getMessage()).thenReturn("Not all who wander are lost");
        when(e.getCause()).thenReturn(arrayIndexOutOfBoundsException);

        // Act
        CustomExceptionResponse actualHandleGeneralExceptionResult = ecdGlobalExceptionHandler.handleGeneralException(e);

        // Assert
        verify(e, atLeast(1)).getCause();
        verify(e).getLocalizedMessage();
        verify(e, atLeast(1)).getMessage();
        verify(e, atLeast(1)).getStackTrace();
        verify(e).getSuppressed();
        assertEquals("Not all who wander are lost", actualHandleGeneralExceptionResult.getErrorMessage());
        assertEquals(CustomExceptionResponse.ENVIRONMENT_EXCEPTION, actualHandleGeneralExceptionResult.getStatusCode());
    }

    /**
     * Method under test:
     * {@link EcdGlobalExceptionHandler#handleGeneralException(ECDException)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testHandleGeneralException5() {
        // TODO: Diffblue Cover was only able to create a partial test for this method:
        //   Reason: No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "Object.getClass()" because the return value of "java.lang.Throwable.getCause()" is null
        //       at com.iemr.ecd.utils.advice.exception_handler.CustomExceptionResponse.setError(CustomExceptionResponse.java:105)
        //       at com.iemr.ecd.utils.advice.exception_handler.EcdGlobalExceptionHandler.handleGeneralException(EcdGlobalExceptionHandler.java:49)
        //   See https://diff.blue/R013 to resolve this issue.

        // Arrange and Act
        ecdGlobalExceptionHandler.handleGeneralException(eCDException);
    }
}

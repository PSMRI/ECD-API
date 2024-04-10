package com.iemr.ecd.utils.http_request_interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.servlet.ModelAndView;

@ContextConfiguration(classes = {HttpInterceptor.class})
@ExtendWith(SpringExtension.class)
class HttpInterceptorDiffblueTest {
    @Autowired
    private HttpInterceptor httpInterceptor;

    /**
     * Method under test:
     * {@link HttpInterceptor#preHandle(HttpServletRequest, HttpServletResponse, Object)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testPreHandle() throws Exception {
        // TODO: Diffblue Cover was only able to create a partial test for this method:
        //   Reason: Current JVM does not support JDK which compiled the project.
        //   Diffblue Cover is running on JVM version <unknown>, but your
        //   project was built with JDK version <unknown>.
        //   Diffblue Cover needs to be executed with the same or a more recent Java
        //   version than the version with which the project was compiled.
        //   Classes compiled with wrong JDK version:
        //     org/apache/jasper/servlet/JspCServletContext has been compiled by a more recent version of the Java Runtime (class file version 65.0), this version of the Java Runtime only recognizes class file versions up to 61.0
        //   For best results recompile the project and run Diffblue Cover with the same
        //   supported Java version: 8, 11 (but not 11.0.7), 17, and 21.
        //   See https://diff.blue/E051 to resolve this issue.

        // Arrange
        // TODO: Populate arranged inputs
        HttpServletRequest request = null;
        HttpServletResponse response = null;
        Object object = null;

        // Act
        boolean actualPreHandleResult = this.httpInterceptor.preHandle(request, response, object);

        // Assert
        // TODO: Add assertions on result
    }

    /**
     * Method under test:
     * {@link HttpInterceptor#postHandle(HttpServletRequest, HttpServletResponse, Object, ModelAndView)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testPostHandle() throws Exception {
        // TODO: Diffblue Cover was only able to create a partial test for this method:
        //   Reason: Current JVM does not support JDK which compiled the project.
        //   Diffblue Cover is running on JVM version <unknown>, but your
        //   project was built with JDK version <unknown>.
        //   Diffblue Cover needs to be executed with the same or a more recent Java
        //   version than the version with which the project was compiled.
        //   Classes compiled with wrong JDK version:
        //     org/apache/jasper/servlet/JspCServletContext has been compiled by a more recent version of the Java Runtime (class file version 65.0), this version of the Java Runtime only recognizes class file versions up to 61.0
        //   For best results recompile the project and run Diffblue Cover with the same
        //   supported Java version: 8, 11 (but not 11.0.7), 17, and 21.
        //   See https://diff.blue/E051 to resolve this issue.

        // Arrange
        // TODO: Populate arranged inputs
        HttpServletRequest request = null;
        HttpServletResponse response = null;
        Object object = null;
        ModelAndView model = null;

        // Act
        this.httpInterceptor.postHandle(request, response, object, model);

        // Assert
        // TODO: Add assertions on result
    }

    /**
     * Method under test:
     * {@link HttpInterceptor#afterCompletion(HttpServletRequest, HttpServletResponse, Object, Exception)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testAfterCompletion() throws Exception {
        // TODO: Diffblue Cover was only able to create a partial test for this method:
        //   Reason: Current JVM does not support JDK which compiled the project.
        //   Diffblue Cover is running on JVM version <unknown>, but your
        //   project was built with JDK version <unknown>.
        //   Diffblue Cover needs to be executed with the same or a more recent Java
        //   version than the version with which the project was compiled.
        //   Classes compiled with wrong JDK version:
        //     org/apache/jasper/servlet/JspCServletContext has been compiled by a more recent version of the Java Runtime (class file version 65.0), this version of the Java Runtime only recognizes class file versions up to 61.0
        //   For best results recompile the project and run Diffblue Cover with the same
        //   supported Java version: 8, 11 (but not 11.0.7), 17, and 21.
        //   See https://diff.blue/E051 to resolve this issue.

        // Arrange
        // TODO: Populate arranged inputs
        HttpServletRequest request = null;
        HttpServletResponse response = null;
        Object object = null;
        Exception arg3 = null;

        // Act
        this.httpInterceptor.afterCompletion(request, response, object, arg3);

        // Assert
        // TODO: Add assertions on result
    }
}

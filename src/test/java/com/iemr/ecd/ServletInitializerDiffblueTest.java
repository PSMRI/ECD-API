package com.iemr.ecd;

import static org.junit.jupiter.api.Assertions.assertSame;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {ServletInitializer.class})
@ExtendWith(SpringExtension.class)
class ServletInitializerDiffblueTest {
    @Autowired
    private ServletInitializer servletInitializer;

    /**
     * Method under test:
     * {@link ServletInitializer#configure(SpringApplicationBuilder)}
     */
    @Test
    void testConfigure() {
        // Arrange
        Class<Object> forNameResult = Object.class;
        SpringApplicationBuilder application = new SpringApplicationBuilder(forNameResult);

        // Act and Assert
        assertSame(application, servletInitializer.configure(application));
    }
}

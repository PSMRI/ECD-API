package com.iemr.ecd.controller.reports;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.iemr.ecd.service.report.ReportService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {ReportController.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class ReportControllerDiffblueTest {
    @Autowired
    private ReportController reportController;

    @MockBean
    private ReportService reportService;

    /**
     * Method under test: {@link ReportController#getCallDetailsReport(String)}
     */
    @Test
    void testGetCallDetailsReport() throws Exception {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange
        ReportController reportController = new ReportController();

        // Act
        ResponseEntity<Object> actualCallDetailsReport = reportController.getCallDetailsReport("Json Request");

        // Assert
        assertEquals("com.google.gson.stream.MalformedJsonException: Use JsonReader.setLenient(true) to accept malformed"
                + " JSON at line 1 column 7 path $", reportController.getAbortionReport("Json Request").getBody());
        assertEquals("com.google.gson.stream.MalformedJsonException: Use JsonReader.setLenient(true) to accept malformed"
                + " JSON at line 1 column 7 path $", actualCallDetailsReport.getBody());
        assertEquals(500, actualCallDetailsReport.getStatusCodeValue());
        assertTrue(actualCallDetailsReport.getHeaders().isEmpty());
    }

    /**
     * Method under test: {@link ReportController#getCallDetailsReport(String)}
     */
    @Test
    void testGetCallDetailsReport2() throws Exception {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange
        ReportController reportController = new ReportController();

        // Act
        ResponseEntity<Object> actualCallDetailsReport = reportController.getCallDetailsReport("foo");

        // Assert
        assertEquals("Not a JSON Object: \"foo\"", actualCallDetailsReport.getBody());
        assertEquals("com.google.gson.stream.MalformedJsonException: Use JsonReader.setLenient(true) to accept malformed"
                + " JSON at line 1 column 7 path $", reportController.getAashaHomeVisitGapReport("Json Request").getBody());
        assertEquals(500, actualCallDetailsReport.getStatusCodeValue());
        assertTrue(actualCallDetailsReport.getHeaders().isEmpty());
    }

    /**
     * Method under test: {@link ReportController#getCallDetailsReport(String)}
     */
    @Test
    void testGetCallDetailsReport3() throws Exception {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange
        ReportController reportController = new ReportController();

        // Act
        ResponseEntity<Object> actualCallDetailsReport = reportController.getCallDetailsReport("ECDCallDetailsReport");

        // Assert
        assertEquals("Not a JSON Object: \"ECDCallDetailsReport\"", actualCallDetailsReport.getBody());
        assertEquals("com.google.gson.stream.MalformedJsonException: Use JsonReader.setLenient(true) to accept malformed"
                + " JSON at line 1 column 7 path $", reportController.getAashaHomeVisitGapReport("Json Request").getBody());
        assertEquals(500, actualCallDetailsReport.getStatusCodeValue());
        assertTrue(actualCallDetailsReport.getHeaders().isEmpty());
    }

    /**
     * Method under test: {@link ReportController#getCallDetailsReport(String)}
     */
    @Test
    void testGetCallDetailsReport4() throws Exception {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange
        ReportController reportController = new ReportController();

        // Act
        ResponseEntity<Object> actualCallDetailsReport = reportController.getCallDetailsReport("42");

        // Assert
        assertEquals("Not a JSON Object: 42", actualCallDetailsReport.getBody());
        assertEquals("com.google.gson.stream.MalformedJsonException: Use JsonReader.setLenient(true) to accept malformed"
                + " JSON at line 1 column 7 path $", reportController.getAashaHomeVisitGapReport("Json Request").getBody());
        assertEquals(500, actualCallDetailsReport.getStatusCodeValue());
        assertTrue(actualCallDetailsReport.getHeaders().isEmpty());
    }

    /**
     * Method under test: {@link ReportController#getCallDetailsReport(String)}
     */
    @Test
    void testGetCallDetailsReport5() throws Exception {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange
        ReportController reportController = new ReportController();

        // Act
        ResponseEntity<Object> actualCallDetailsReport = reportController.getCallDetailsReport("");

        // Assert
        assertEquals("Not a JSON Object: null", actualCallDetailsReport.getBody());
        assertEquals("com.google.gson.stream.MalformedJsonException: Use JsonReader.setLenient(true) to accept malformed"
                + " JSON at line 1 column 7 path $", reportController.getAashaHomeVisitGapReport("Json Request").getBody());
        assertEquals(500, actualCallDetailsReport.getStatusCodeValue());
        assertTrue(actualCallDetailsReport.getHeaders().isEmpty());
    }

    /**
     * Method under test: {@link ReportController#getCallDetailsReport(String)}
     */
    @Test
    void testGetCallDetailsReport6() throws Exception {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange
        ReportController reportController = new ReportController();

        // Act
        ResponseEntity<Object> actualCallDetailsReport = reportController.getCallDetailsReport("42ECDCallDetailsReport");

        // Assert
        assertEquals("Not a JSON Object: \"42ECDCallDetailsReport\"", actualCallDetailsReport.getBody());
        assertEquals("com.google.gson.stream.MalformedJsonException: Use JsonReader.setLenient(true) to accept malformed"
                + " JSON at line 1 column 7 path $", reportController.getAashaHomeVisitGapReport("Json Request").getBody());
        assertEquals(500, actualCallDetailsReport.getStatusCodeValue());
        assertTrue(actualCallDetailsReport.getHeaders().isEmpty());
    }

    /**
     * Method under test: {@link ReportController#getCallSummaryReport(String)}
     */
    @Test
    void testGetCallSummaryReport() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange
        ReportController reportController = new ReportController();

        // Act
        ResponseEntity<Object> actualCallSummaryReport = reportController.getCallSummaryReport("Json Request");

        // Assert
        assertEquals("com.google.gson.stream.MalformedJsonException: Use JsonReader.setLenient(true) to accept malformed"
                + " JSON at line 1 column 7 path $", reportController.getAbortionReport("Json Request").getBody());
        assertEquals("com.google.gson.stream.MalformedJsonException: Use JsonReader.setLenient(true) to accept malformed"
                + " JSON at line 1 column 7 path $", actualCallSummaryReport.getBody());
        assertEquals(500, actualCallSummaryReport.getStatusCodeValue());
        assertTrue(actualCallSummaryReport.getHeaders().isEmpty());
    }

    /**
     * Method under test: {@link ReportController#getCallSummaryReport(String)}
     */
    @Test
    void testGetCallSummaryReport2() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange
        ReportController reportController = new ReportController();

        // Act
        ResponseEntity<Object> actualCallSummaryReport = reportController.getCallSummaryReport("foo");

        // Assert
        assertEquals("Not a JSON Object: \"foo\"", actualCallSummaryReport.getBody());
        assertEquals("com.google.gson.stream.MalformedJsonException: Use JsonReader.setLenient(true) to accept malformed"
                + " JSON at line 1 column 7 path $", reportController.getAashaHomeVisitGapReport("Json Request").getBody());
        assertEquals(500, actualCallSummaryReport.getStatusCodeValue());
        assertTrue(actualCallSummaryReport.getHeaders().isEmpty());
    }

    /**
     * Method under test: {@link ReportController#getCallSummaryReport(String)}
     */
    @Test
    void testGetCallSummaryReport3() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange
        ReportController reportController = new ReportController();

        // Act
        ResponseEntity<Object> actualCallSummaryReport = reportController.getCallSummaryReport("ECDCallSummaryReport");

        // Assert
        assertEquals("Not a JSON Object: \"ECDCallSummaryReport\"", actualCallSummaryReport.getBody());
        assertEquals("com.google.gson.stream.MalformedJsonException: Use JsonReader.setLenient(true) to accept malformed"
                + " JSON at line 1 column 7 path $", reportController.getAashaHomeVisitGapReport("Json Request").getBody());
        assertEquals(500, actualCallSummaryReport.getStatusCodeValue());
        assertTrue(actualCallSummaryReport.getHeaders().isEmpty());
    }

    /**
     * Method under test: {@link ReportController#getCallSummaryReport(String)}
     */
    @Test
    void testGetCallSummaryReport4() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange
        ReportController reportController = new ReportController();

        // Act
        ResponseEntity<Object> actualCallSummaryReport = reportController.getCallSummaryReport("42");

        // Assert
        assertEquals("Not a JSON Object: 42", actualCallSummaryReport.getBody());
        assertEquals("com.google.gson.stream.MalformedJsonException: Use JsonReader.setLenient(true) to accept malformed"
                + " JSON at line 1 column 7 path $", reportController.getAashaHomeVisitGapReport("Json Request").getBody());
        assertEquals(500, actualCallSummaryReport.getStatusCodeValue());
        assertTrue(actualCallSummaryReport.getHeaders().isEmpty());
    }

    /**
     * Method under test: {@link ReportController#getCallSummaryReport(String)}
     */
    @Test
    void testGetCallSummaryReport5() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange
        ReportController reportController = new ReportController();

        // Act
        ResponseEntity<Object> actualCallSummaryReport = reportController.getCallSummaryReport("");

        // Assert
        assertEquals("Not a JSON Object: null", actualCallSummaryReport.getBody());
        assertEquals("com.google.gson.stream.MalformedJsonException: Use JsonReader.setLenient(true) to accept malformed"
                + " JSON at line 1 column 7 path $", reportController.getAashaHomeVisitGapReport("Json Request").getBody());
        assertEquals(500, actualCallSummaryReport.getStatusCodeValue());
        assertTrue(actualCallSummaryReport.getHeaders().isEmpty());
    }

    /**
     * Method under test: {@link ReportController#getCallSummaryReport(String)}
     */
    @Test
    void testGetCallSummaryReport6() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange
        ReportController reportController = new ReportController();

        // Act
        ResponseEntity<Object> actualCallSummaryReport = reportController.getCallSummaryReport("42ECDCallSummaryReport");

        // Assert
        assertEquals("Not a JSON Object: \"42ECDCallSummaryReport\"", actualCallSummaryReport.getBody());
        assertEquals("com.google.gson.stream.MalformedJsonException: Use JsonReader.setLenient(true) to accept malformed"
                + " JSON at line 1 column 7 path $", reportController.getAashaHomeVisitGapReport("Json Request").getBody());
        assertEquals(500, actualCallSummaryReport.getStatusCodeValue());
        assertTrue(actualCallSummaryReport.getHeaders().isEmpty());
    }

    /**
     * Method under test:
     * {@link ReportController#getCumulativeDistrictReport(String)}
     */
    @Test
    void testGetCumulativeDistrictReport() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange
        ReportController reportController = new ReportController();

        // Act
        ResponseEntity<Object> actualCumulativeDistrictReport = reportController
                .getCumulativeDistrictReport("Json Request");

        // Assert
        assertEquals("com.google.gson.stream.MalformedJsonException: Use JsonReader.setLenient(true) to accept malformed"
                + " JSON at line 1 column 7 path $", reportController.getAbortionReport("Json Request").getBody());
        assertEquals("com.google.gson.stream.MalformedJsonException: Use JsonReader.setLenient(true) to accept malformed"
                + " JSON at line 1 column 7 path $", actualCumulativeDistrictReport.getBody());
        assertEquals(500, actualCumulativeDistrictReport.getStatusCodeValue());
        assertTrue(actualCumulativeDistrictReport.getHeaders().isEmpty());
    }

    /**
     * Method under test:
     * {@link ReportController#getCumulativeDistrictReport(String)}
     */
    @Test
    void testGetCumulativeDistrictReport2() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange
        ReportController reportController = new ReportController();

        // Act
        ResponseEntity<Object> actualCumulativeDistrictReport = reportController.getCumulativeDistrictReport("foo");

        // Assert
        assertEquals("Not a JSON Object: \"foo\"", actualCumulativeDistrictReport.getBody());
        assertEquals("com.google.gson.stream.MalformedJsonException: Use JsonReader.setLenient(true) to accept malformed"
                + " JSON at line 1 column 7 path $", reportController.getAashaHomeVisitGapReport("Json Request").getBody());
        assertEquals(500, actualCumulativeDistrictReport.getStatusCodeValue());
        assertTrue(actualCumulativeDistrictReport.getHeaders().isEmpty());
    }

    /**
     * Method under test:
     * {@link ReportController#getCumulativeDistrictReport(String)}
     */
    @Test
    void testGetCumulativeDistrictReport3() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange
        ReportController reportController = new ReportController();

        // Act
        ResponseEntity<Object> actualCumulativeDistrictReport = reportController
                .getCumulativeDistrictReport("ECDCumulativeDistrictReport");

        // Assert
        assertEquals("Not a JSON Object: \"ECDCumulativeDistrictReport\"", actualCumulativeDistrictReport.getBody());
        assertEquals("com.google.gson.stream.MalformedJsonException: Use JsonReader.setLenient(true) to accept malformed"
                + " JSON at line 1 column 7 path $", reportController.getAashaHomeVisitGapReport("Json Request").getBody());
        assertEquals(500, actualCumulativeDistrictReport.getStatusCodeValue());
        assertTrue(actualCumulativeDistrictReport.getHeaders().isEmpty());
    }

    /**
     * Method under test:
     * {@link ReportController#getCumulativeDistrictReport(String)}
     */
    @Test
    void testGetCumulativeDistrictReport4() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange
        ReportController reportController = new ReportController();

        // Act
        ResponseEntity<Object> actualCumulativeDistrictReport = reportController.getCumulativeDistrictReport("42");

        // Assert
        assertEquals("Not a JSON Object: 42", actualCumulativeDistrictReport.getBody());
        assertEquals("com.google.gson.stream.MalformedJsonException: Use JsonReader.setLenient(true) to accept malformed"
                + " JSON at line 1 column 7 path $", reportController.getAashaHomeVisitGapReport("Json Request").getBody());
        assertEquals(500, actualCumulativeDistrictReport.getStatusCodeValue());
        assertTrue(actualCumulativeDistrictReport.getHeaders().isEmpty());
    }

    /**
     * Method under test:
     * {@link ReportController#getCumulativeDistrictReport(String)}
     */
    @Test
    void testGetCumulativeDistrictReport5() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange
        ReportController reportController = new ReportController();

        // Act
        ResponseEntity<Object> actualCumulativeDistrictReport = reportController.getCumulativeDistrictReport("");

        // Assert
        assertEquals("Not a JSON Object: null", actualCumulativeDistrictReport.getBody());
        assertEquals("com.google.gson.stream.MalformedJsonException: Use JsonReader.setLenient(true) to accept malformed"
                + " JSON at line 1 column 7 path $", reportController.getAashaHomeVisitGapReport("Json Request").getBody());
        assertEquals(500, actualCumulativeDistrictReport.getStatusCodeValue());
        assertTrue(actualCumulativeDistrictReport.getHeaders().isEmpty());
    }

    /**
     * Method under test:
     * {@link ReportController#getCumulativeDistrictReport(String)}
     */
    @Test
    void testGetCumulativeDistrictReport6() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange
        ReportController reportController = new ReportController();

        // Act
        ResponseEntity<Object> actualCumulativeDistrictReport = reportController
                .getCumulativeDistrictReport("42ECDCumulativeDistrictReport");

        // Assert
        assertEquals("Not a JSON Object: \"42ECDCumulativeDistrictReport\"", actualCumulativeDistrictReport.getBody());
        assertEquals("com.google.gson.stream.MalformedJsonException: Use JsonReader.setLenient(true) to accept malformed"
                + " JSON at line 1 column 7 path $", reportController.getAashaHomeVisitGapReport("Json Request").getBody());
        assertEquals(500, actualCumulativeDistrictReport.getStatusCodeValue());
        assertTrue(actualCumulativeDistrictReport.getHeaders().isEmpty());
    }

    /**
     * Method under test:
     * {@link ReportController#getBeneficiarywisefollowupdetails(String)}
     */
    @Test
    void testGetBeneficiarywisefollowupdetails() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange
        ReportController reportController = new ReportController();

        // Act
        ResponseEntity<Object> actualBeneficiarywisefollowupdetails = reportController
                .getBeneficiarywisefollowupdetails("Json Request");

        // Assert
        assertEquals("com.google.gson.stream.MalformedJsonException: Use JsonReader.setLenient(true) to accept malformed"
                + " JSON at line 1 column 7 path $", reportController.getAbortionReport("Json Request").getBody());
        assertEquals("com.google.gson.stream.MalformedJsonException: Use JsonReader.setLenient(true) to accept malformed"
                + " JSON at line 1 column 7 path $", actualBeneficiarywisefollowupdetails.getBody());
        assertEquals(500, actualBeneficiarywisefollowupdetails.getStatusCodeValue());
        assertTrue(actualBeneficiarywisefollowupdetails.getHeaders().isEmpty());
    }

    /**
     * Method under test:
     * {@link ReportController#getBeneficiarywisefollowupdetails(String)}
     */
    @Test
    void testGetBeneficiarywisefollowupdetails2() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange
        ReportController reportController = new ReportController();

        // Act
        ResponseEntity<Object> actualBeneficiarywisefollowupdetails = reportController
                .getBeneficiarywisefollowupdetails("foo");

        // Assert
        assertEquals("Not a JSON Object: \"foo\"", actualBeneficiarywisefollowupdetails.getBody());
        assertEquals("com.google.gson.stream.MalformedJsonException: Use JsonReader.setLenient(true) to accept malformed"
                + " JSON at line 1 column 7 path $", reportController.getAashaHomeVisitGapReport("Json Request").getBody());
        assertEquals(500, actualBeneficiarywisefollowupdetails.getStatusCodeValue());
        assertTrue(actualBeneficiarywisefollowupdetails.getHeaders().isEmpty());
    }

    /**
     * Method under test:
     * {@link ReportController#getBeneficiarywisefollowupdetails(String)}
     */
    @Test
    void testGetBeneficiarywisefollowupdetails3() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange
        ReportController reportController = new ReportController();

        // Act
        ResponseEntity<Object> actualBeneficiarywisefollowupdetails = reportController
                .getBeneficiarywisefollowupdetails("ECDBeneficiarywisefollowupdetailsReport");

        // Assert
        assertEquals("Not a JSON Object: \"ECDBeneficiarywisefollowupdetailsReport\"",
                actualBeneficiarywisefollowupdetails.getBody());
        assertEquals("com.google.gson.stream.MalformedJsonException: Use JsonReader.setLenient(true) to accept malformed"
                + " JSON at line 1 column 7 path $", reportController.getAashaHomeVisitGapReport("Json Request").getBody());
        assertEquals(500, actualBeneficiarywisefollowupdetails.getStatusCodeValue());
        assertTrue(actualBeneficiarywisefollowupdetails.getHeaders().isEmpty());
    }

    /**
     * Method under test:
     * {@link ReportController#getBeneficiarywisefollowupdetails(String)}
     */
    @Test
    void testGetBeneficiarywisefollowupdetails4() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange
        ReportController reportController = new ReportController();

        // Act
        ResponseEntity<Object> actualBeneficiarywisefollowupdetails = reportController
                .getBeneficiarywisefollowupdetails("42");

        // Assert
        assertEquals("Not a JSON Object: 42", actualBeneficiarywisefollowupdetails.getBody());
        assertEquals("com.google.gson.stream.MalformedJsonException: Use JsonReader.setLenient(true) to accept malformed"
                + " JSON at line 1 column 7 path $", reportController.getAashaHomeVisitGapReport("Json Request").getBody());
        assertEquals(500, actualBeneficiarywisefollowupdetails.getStatusCodeValue());
        assertTrue(actualBeneficiarywisefollowupdetails.getHeaders().isEmpty());
    }

    /**
     * Method under test:
     * {@link ReportController#getBeneficiarywisefollowupdetails(String)}
     */
    @Test
    void testGetBeneficiarywisefollowupdetails5() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange
        ReportController reportController = new ReportController();

        // Act
        ResponseEntity<Object> actualBeneficiarywisefollowupdetails = reportController
                .getBeneficiarywisefollowupdetails("");

        // Assert
        assertEquals("Not a JSON Object: null", actualBeneficiarywisefollowupdetails.getBody());
        assertEquals("com.google.gson.stream.MalformedJsonException: Use JsonReader.setLenient(true) to accept malformed"
                + " JSON at line 1 column 7 path $", reportController.getAashaHomeVisitGapReport("Json Request").getBody());
        assertEquals(500, actualBeneficiarywisefollowupdetails.getStatusCodeValue());
        assertTrue(actualBeneficiarywisefollowupdetails.getHeaders().isEmpty());
    }

    /**
     * Method under test:
     * {@link ReportController#getBeneficiarywisefollowupdetails(String)}
     */
    @Test
    void testGetBeneficiarywisefollowupdetails6() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange
        ReportController reportController = new ReportController();

        // Act
        ResponseEntity<Object> actualBeneficiarywisefollowupdetails = reportController
                .getBeneficiarywisefollowupdetails("42ECDBeneficiarywisefollowupdetailsReport");

        // Assert
        assertEquals("Not a JSON Object: \"42ECDBeneficiarywisefollowupdetailsReport\"",
                actualBeneficiarywisefollowupdetails.getBody());
        assertEquals("com.google.gson.stream.MalformedJsonException: Use JsonReader.setLenient(true) to accept malformed"
                + " JSON at line 1 column 7 path $", reportController.getAashaHomeVisitGapReport("Json Request").getBody());
        assertEquals(500, actualBeneficiarywisefollowupdetails.getStatusCodeValue());
        assertTrue(actualBeneficiarywisefollowupdetails.getHeaders().isEmpty());
    }

    /**
     * Method under test: {@link ReportController#getCallDetailReportUnique(String)}
     */
    @Test
    void testGetCallDetailReportUnique() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange
        ReportController reportController = new ReportController();

        // Act
        ResponseEntity<Object> actualCallDetailReportUnique = reportController.getCallDetailReportUnique("Request Obj");

        // Assert
        assertEquals("com.google.gson.stream.MalformedJsonException: Use JsonReader.setLenient(true) to accept malformed"
                + " JSON at line 1 column 10 path $", actualCallDetailReportUnique.getBody());
        assertEquals("com.google.gson.stream.MalformedJsonException: Use JsonReader.setLenient(true) to accept malformed"
                + " JSON at line 1 column 7 path $", reportController.getAbortionReport("Json Request").getBody());
        assertEquals(500, actualCallDetailReportUnique.getStatusCodeValue());
        assertTrue(actualCallDetailReportUnique.getHeaders().isEmpty());
    }

    /**
     * Method under test: {@link ReportController#getCallDetailReportUnique(String)}
     */
    @Test
    void testGetCallDetailReportUnique2() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange
        ReportController reportController = new ReportController();

        // Act
        ResponseEntity<Object> actualCallDetailReportUnique = reportController.getCallDetailReportUnique("foo");

        // Assert
        assertEquals("Not a JSON Object: \"foo\"", actualCallDetailReportUnique.getBody());
        assertEquals("com.google.gson.stream.MalformedJsonException: Use JsonReader.setLenient(true) to accept malformed"
                + " JSON at line 1 column 7 path $", reportController.getAashaHomeVisitGapReport("Json Request").getBody());
        assertEquals(500, actualCallDetailReportUnique.getStatusCodeValue());
        assertTrue(actualCallDetailReportUnique.getHeaders().isEmpty());
    }

    /**
     * Method under test: {@link ReportController#getCallDetailReportUnique(String)}
     */
    @Test
    void testGetCallDetailReportUnique3() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange
        ReportController reportController = new ReportController();

        // Act
        ResponseEntity<Object> actualCallDetailReportUnique = reportController.getCallDetailReportUnique("42");

        // Assert
        assertEquals("Not a JSON Object: 42", actualCallDetailReportUnique.getBody());
        assertEquals("com.google.gson.stream.MalformedJsonException: Use JsonReader.setLenient(true) to accept malformed"
                + " JSON at line 1 column 7 path $", reportController.getAashaHomeVisitGapReport("Json Request").getBody());
        assertEquals(500, actualCallDetailReportUnique.getStatusCodeValue());
        assertTrue(actualCallDetailReportUnique.getHeaders().isEmpty());
    }

    /**
     * Method under test: {@link ReportController#getCallDetailReportUnique(String)}
     */
    @Test
    void testGetCallDetailReportUnique4() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange
        ReportController reportController = new ReportController();

        // Act
        ResponseEntity<Object> actualCallDetailReportUnique = reportController.getCallDetailReportUnique("");

        // Assert
        assertEquals("Not a JSON Object: null", actualCallDetailReportUnique.getBody());
        assertEquals("com.google.gson.stream.MalformedJsonException: Use JsonReader.setLenient(true) to accept malformed"
                + " JSON at line 1 column 7 path $", reportController.getAashaHomeVisitGapReport("Json Request").getBody());
        assertEquals(500, actualCallDetailReportUnique.getStatusCodeValue());
        assertTrue(actualCallDetailReportUnique.getHeaders().isEmpty());
    }

    /**
     * Method under test: {@link ReportController#getBirthDefectReport(String)}
     */
    @Test
    void testGetBirthDefectReport() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange
        ReportController reportController = new ReportController();

        // Act
        ResponseEntity<Object> actualBirthDefectReport = reportController.getBirthDefectReport("Json Request");

        // Assert
        assertEquals("com.google.gson.stream.MalformedJsonException: Use JsonReader.setLenient(true) to accept malformed"
                + " JSON at line 1 column 7 path $", reportController.getAbortionReport("Json Request").getBody());
        assertEquals("com.google.gson.stream.MalformedJsonException: Use JsonReader.setLenient(true) to accept malformed"
                + " JSON at line 1 column 7 path $", actualBirthDefectReport.getBody());
        assertEquals(500, actualBirthDefectReport.getStatusCodeValue());
        assertTrue(actualBirthDefectReport.getHeaders().isEmpty());
    }

    /**
     * Method under test: {@link ReportController#getBirthDefectReport(String)}
     */
    @Test
    void testGetBirthDefectReport2() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange
        ReportController reportController = new ReportController();

        // Act
        ResponseEntity<Object> actualBirthDefectReport = reportController.getBirthDefectReport("foo");

        // Assert
        assertEquals("Not a JSON Object: \"foo\"", actualBirthDefectReport.getBody());
        assertEquals("com.google.gson.stream.MalformedJsonException: Use JsonReader.setLenient(true) to accept malformed"
                + " JSON at line 1 column 7 path $", reportController.getAashaHomeVisitGapReport("Json Request").getBody());
        assertEquals(500, actualBirthDefectReport.getStatusCodeValue());
        assertTrue(actualBirthDefectReport.getHeaders().isEmpty());
    }

    /**
     * Method under test: {@link ReportController#getBirthDefectReport(String)}
     */
    @Test
    void testGetBirthDefectReport3() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange
        ReportController reportController = new ReportController();

        // Act
        ResponseEntity<Object> actualBirthDefectReport = reportController.getBirthDefectReport("ECDBirthDefectReport");

        // Assert
        assertEquals("Not a JSON Object: \"ECDBirthDefectReport\"", actualBirthDefectReport.getBody());
        assertEquals("com.google.gson.stream.MalformedJsonException: Use JsonReader.setLenient(true) to accept malformed"
                + " JSON at line 1 column 7 path $", reportController.getAashaHomeVisitGapReport("Json Request").getBody());
        assertEquals(500, actualBirthDefectReport.getStatusCodeValue());
        assertTrue(actualBirthDefectReport.getHeaders().isEmpty());
    }

    /**
     * Method under test: {@link ReportController#getBirthDefectReport(String)}
     */
    @Test
    void testGetBirthDefectReport4() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange
        ReportController reportController = new ReportController();

        // Act
        ResponseEntity<Object> actualBirthDefectReport = reportController.getBirthDefectReport("42");

        // Assert
        assertEquals("Not a JSON Object: 42", actualBirthDefectReport.getBody());
        assertEquals("com.google.gson.stream.MalformedJsonException: Use JsonReader.setLenient(true) to accept malformed"
                + " JSON at line 1 column 7 path $", reportController.getAashaHomeVisitGapReport("Json Request").getBody());
        assertEquals(500, actualBirthDefectReport.getStatusCodeValue());
        assertTrue(actualBirthDefectReport.getHeaders().isEmpty());
    }

    /**
     * Method under test: {@link ReportController#getBirthDefectReport(String)}
     */
    @Test
    void testGetBirthDefectReport5() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange
        ReportController reportController = new ReportController();

        // Act
        ResponseEntity<Object> actualBirthDefectReport = reportController.getBirthDefectReport("");

        // Assert
        assertEquals("Not a JSON Object: null", actualBirthDefectReport.getBody());
        assertEquals("com.google.gson.stream.MalformedJsonException: Use JsonReader.setLenient(true) to accept malformed"
                + " JSON at line 1 column 7 path $", reportController.getAashaHomeVisitGapReport("Json Request").getBody());
        assertEquals(500, actualBirthDefectReport.getStatusCodeValue());
        assertTrue(actualBirthDefectReport.getHeaders().isEmpty());
    }

    /**
     * Method under test: {@link ReportController#getBirthDefectReport(String)}
     */
    @Test
    void testGetBirthDefectReport6() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange
        ReportController reportController = new ReportController();

        // Act
        ResponseEntity<Object> actualBirthDefectReport = reportController.getBirthDefectReport("42ECDBirthDefectReport");

        // Assert
        assertEquals("Not a JSON Object: \"42ECDBirthDefectReport\"", actualBirthDefectReport.getBody());
        assertEquals("com.google.gson.stream.MalformedJsonException: Use JsonReader.setLenient(true) to accept malformed"
                + " JSON at line 1 column 7 path $", reportController.getAashaHomeVisitGapReport("Json Request").getBody());
        assertEquals(500, actualBirthDefectReport.getStatusCodeValue());
        assertTrue(actualBirthDefectReport.getHeaders().isEmpty());
    }

    /**
     * Method under test:
     * {@link ReportController#getAashaHomeVisitGapReport(String)}
     */
    @Test
    void testGetAashaHomeVisitGapReport() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange
        ReportController reportController = new ReportController();

        // Act
        ResponseEntity<Object> actualAashaHomeVisitGapReport = reportController.getAashaHomeVisitGapReport("Json Request");

        // Assert
        assertEquals("com.google.gson.stream.MalformedJsonException: Use JsonReader.setLenient(true) to accept malformed"
                + " JSON at line 1 column 7 path $", actualAashaHomeVisitGapReport.getBody());
        assertEquals("com.google.gson.stream.MalformedJsonException: Use JsonReader.setLenient(true) to accept malformed"
                + " JSON at line 1 column 7 path $", reportController.getAbsenceInVHSNDReport("Json Request").getBody());
        assertEquals(500, actualAashaHomeVisitGapReport.getStatusCodeValue());
        assertTrue(actualAashaHomeVisitGapReport.getHeaders().isEmpty());
    }

    /**
     * Method under test:
     * {@link ReportController#getAashaHomeVisitGapReport(String)}
     */
    @Test
    void testGetAashaHomeVisitGapReport2() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange
        ReportController reportController = new ReportController();

        // Act
        ResponseEntity<Object> actualAashaHomeVisitGapReport = reportController.getAashaHomeVisitGapReport("foo");

        // Assert
        assertEquals("Not a JSON Object: \"foo\"", actualAashaHomeVisitGapReport.getBody());
        assertEquals("com.google.gson.stream.MalformedJsonException: Use JsonReader.setLenient(true) to accept malformed"
                + " JSON at line 1 column 7 path $", reportController.getAbortionReport("Json Request").getBody());
        assertEquals(500, actualAashaHomeVisitGapReport.getStatusCodeValue());
        assertTrue(actualAashaHomeVisitGapReport.getHeaders().isEmpty());
    }

    /**
     * Method under test:
     * {@link ReportController#getAashaHomeVisitGapReport(String)}
     */
    @Test
    void testGetAashaHomeVisitGapReport3() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange
        ReportController reportController = new ReportController();

        // Act
        ResponseEntity<Object> actualAashaHomeVisitGapReport = reportController
                .getAashaHomeVisitGapReport("ECDAashaHomeVisitGapReport");

        // Assert
        assertEquals("Not a JSON Object: \"ECDAashaHomeVisitGapReport\"", actualAashaHomeVisitGapReport.getBody());
        assertEquals("com.google.gson.stream.MalformedJsonException: Use JsonReader.setLenient(true) to accept malformed"
                + " JSON at line 1 column 7 path $", reportController.getAbortionReport("Json Request").getBody());
        assertEquals(500, actualAashaHomeVisitGapReport.getStatusCodeValue());
        assertTrue(actualAashaHomeVisitGapReport.getHeaders().isEmpty());
    }

    /**
     * Method under test:
     * {@link ReportController#getAashaHomeVisitGapReport(String)}
     */
    @Test
    void testGetAashaHomeVisitGapReport4() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange
        ReportController reportController = new ReportController();

        // Act
        ResponseEntity<Object> actualAashaHomeVisitGapReport = reportController.getAashaHomeVisitGapReport("42");

        // Assert
        assertEquals("Not a JSON Object: 42", actualAashaHomeVisitGapReport.getBody());
        assertEquals("com.google.gson.stream.MalformedJsonException: Use JsonReader.setLenient(true) to accept malformed"
                + " JSON at line 1 column 7 path $", reportController.getAbortionReport("Json Request").getBody());
        assertEquals(500, actualAashaHomeVisitGapReport.getStatusCodeValue());
        assertTrue(actualAashaHomeVisitGapReport.getHeaders().isEmpty());
    }

    /**
     * Method under test:
     * {@link ReportController#getAashaHomeVisitGapReport(String)}
     */
    @Test
    void testGetAashaHomeVisitGapReport5() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange
        ReportController reportController = new ReportController();

        // Act
        ResponseEntity<Object> actualAashaHomeVisitGapReport = reportController.getAashaHomeVisitGapReport("");

        // Assert
        assertEquals("Not a JSON Object: null", actualAashaHomeVisitGapReport.getBody());
        assertEquals("com.google.gson.stream.MalformedJsonException: Use JsonReader.setLenient(true) to accept malformed"
                + " JSON at line 1 column 7 path $", reportController.getAbortionReport("Json Request").getBody());
        assertEquals(500, actualAashaHomeVisitGapReport.getStatusCodeValue());
        assertTrue(actualAashaHomeVisitGapReport.getHeaders().isEmpty());
    }

    /**
     * Method under test:
     * {@link ReportController#getAashaHomeVisitGapReport(String)}
     */
    @Test
    void testGetAashaHomeVisitGapReport6() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange
        ReportController reportController = new ReportController();

        // Act
        ResponseEntity<Object> actualAashaHomeVisitGapReport = reportController
                .getAashaHomeVisitGapReport("42ECDAashaHomeVisitGapReport");

        // Assert
        assertEquals("Not a JSON Object: \"42ECDAashaHomeVisitGapReport\"", actualAashaHomeVisitGapReport.getBody());
        assertEquals("com.google.gson.stream.MalformedJsonException: Use JsonReader.setLenient(true) to accept malformed"
                + " JSON at line 1 column 7 path $", reportController.getAbortionReport("Json Request").getBody());
        assertEquals(500, actualAashaHomeVisitGapReport.getStatusCodeValue());
        assertTrue(actualAashaHomeVisitGapReport.getHeaders().isEmpty());
    }

    /**
     * Method under test:
     * {@link ReportController#getCalciumIFATabNonadherenceReport(String)}
     */
    @Test
    void testGetCalciumIFATabNonadherenceReport() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange
        ReportController reportController = new ReportController();

        // Act
        ResponseEntity<Object> actualCalciumIFATabNonadherenceReport = reportController
                .getCalciumIFATabNonadherenceReport("Json Request");

        // Assert
        assertEquals("com.google.gson.stream.MalformedJsonException: Use JsonReader.setLenient(true) to accept malformed"
                + " JSON at line 1 column 7 path $", reportController.getAbortionReport("Json Request").getBody());
        assertEquals("com.google.gson.stream.MalformedJsonException: Use JsonReader.setLenient(true) to accept malformed"
                + " JSON at line 1 column 7 path $", actualCalciumIFATabNonadherenceReport.getBody());
        assertEquals(500, actualCalciumIFATabNonadherenceReport.getStatusCodeValue());
        assertTrue(actualCalciumIFATabNonadherenceReport.getHeaders().isEmpty());
    }

    /**
     * Method under test:
     * {@link ReportController#getCalciumIFATabNonadherenceReport(String)}
     */
    @Test
    void testGetCalciumIFATabNonadherenceReport2() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange
        ReportController reportController = new ReportController();

        // Act
        ResponseEntity<Object> actualCalciumIFATabNonadherenceReport = reportController
                .getCalciumIFATabNonadherenceReport("foo");

        // Assert
        assertEquals("Not a JSON Object: \"foo\"", actualCalciumIFATabNonadherenceReport.getBody());
        assertEquals("com.google.gson.stream.MalformedJsonException: Use JsonReader.setLenient(true) to accept malformed"
                + " JSON at line 1 column 7 path $", reportController.getAashaHomeVisitGapReport("Json Request").getBody());
        assertEquals(500, actualCalciumIFATabNonadherenceReport.getStatusCodeValue());
        assertTrue(actualCalciumIFATabNonadherenceReport.getHeaders().isEmpty());
    }

    /**
     * Method under test:
     * {@link ReportController#getCalciumIFATabNonadherenceReport(String)}
     */
    @Test
    void testGetCalciumIFATabNonadherenceReport3() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange
        ReportController reportController = new ReportController();

        // Act
        ResponseEntity<Object> actualCalciumIFATabNonadherenceReport = reportController
                .getCalciumIFATabNonadherenceReport("ECDCalciumIFATabNonadherenceReport");

        // Assert
        assertEquals("Not a JSON Object: \"ECDCalciumIFATabNonadherenceReport\"",
                actualCalciumIFATabNonadherenceReport.getBody());
        assertEquals("com.google.gson.stream.MalformedJsonException: Use JsonReader.setLenient(true) to accept malformed"
                + " JSON at line 1 column 7 path $", reportController.getAashaHomeVisitGapReport("Json Request").getBody());
        assertEquals(500, actualCalciumIFATabNonadherenceReport.getStatusCodeValue());
        assertTrue(actualCalciumIFATabNonadherenceReport.getHeaders().isEmpty());
    }

    /**
     * Method under test:
     * {@link ReportController#getCalciumIFATabNonadherenceReport(String)}
     */
    @Test
    void testGetCalciumIFATabNonadherenceReport4() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange
        ReportController reportController = new ReportController();

        // Act
        ResponseEntity<Object> actualCalciumIFATabNonadherenceReport = reportController
                .getCalciumIFATabNonadherenceReport("42");

        // Assert
        assertEquals("Not a JSON Object: 42", actualCalciumIFATabNonadherenceReport.getBody());
        assertEquals("com.google.gson.stream.MalformedJsonException: Use JsonReader.setLenient(true) to accept malformed"
                + " JSON at line 1 column 7 path $", reportController.getAashaHomeVisitGapReport("Json Request").getBody());
        assertEquals(500, actualCalciumIFATabNonadherenceReport.getStatusCodeValue());
        assertTrue(actualCalciumIFATabNonadherenceReport.getHeaders().isEmpty());
    }

    /**
     * Method under test:
     * {@link ReportController#getCalciumIFATabNonadherenceReport(String)}
     */
    @Test
    void testGetCalciumIFATabNonadherenceReport5() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange
        ReportController reportController = new ReportController();

        // Act
        ResponseEntity<Object> actualCalciumIFATabNonadherenceReport = reportController
                .getCalciumIFATabNonadherenceReport("");

        // Assert
        assertEquals("Not a JSON Object: null", actualCalciumIFATabNonadherenceReport.getBody());
        assertEquals("com.google.gson.stream.MalformedJsonException: Use JsonReader.setLenient(true) to accept malformed"
                + " JSON at line 1 column 7 path $", reportController.getAashaHomeVisitGapReport("Json Request").getBody());
        assertEquals(500, actualCalciumIFATabNonadherenceReport.getStatusCodeValue());
        assertTrue(actualCalciumIFATabNonadherenceReport.getHeaders().isEmpty());
    }

    /**
     * Method under test:
     * {@link ReportController#getCalciumIFATabNonadherenceReport(String)}
     */
    @Test
    void testGetCalciumIFATabNonadherenceReport6() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange
        ReportController reportController = new ReportController();

        // Act
        ResponseEntity<Object> actualCalciumIFATabNonadherenceReport = reportController
                .getCalciumIFATabNonadherenceReport("42ECDCalciumIFATabNonadherenceReport");

        // Assert
        assertEquals("Not a JSON Object: \"42ECDCalciumIFATabNonadherenceReport\"",
                actualCalciumIFATabNonadherenceReport.getBody());
        assertEquals("com.google.gson.stream.MalformedJsonException: Use JsonReader.setLenient(true) to accept malformed"
                + " JSON at line 1 column 7 path $", reportController.getAashaHomeVisitGapReport("Json Request").getBody());
        assertEquals(500, actualCalciumIFATabNonadherenceReport.getStatusCodeValue());
        assertTrue(actualCalciumIFATabNonadherenceReport.getHeaders().isEmpty());
    }

    /**
     * Method under test: {@link ReportController#getAbsenceInVHSNDReport(String)}
     */
    @Test
    void testGetAbsenceInVHSNDReport() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange
        ReportController reportController = new ReportController();

        // Act
        ResponseEntity<Object> actualAbsenceInVHSNDReport = reportController.getAbsenceInVHSNDReport("Json Request");

        // Assert
        assertEquals("com.google.gson.stream.MalformedJsonException: Use JsonReader.setLenient(true) to accept malformed"
                + " JSON at line 1 column 7 path $", reportController.getAbortionReport("Json Request").getBody());
        assertEquals("com.google.gson.stream.MalformedJsonException: Use JsonReader.setLenient(true) to accept malformed"
                + " JSON at line 1 column 7 path $", actualAbsenceInVHSNDReport.getBody());
        assertEquals(500, actualAbsenceInVHSNDReport.getStatusCodeValue());
        assertTrue(actualAbsenceInVHSNDReport.getHeaders().isEmpty());
    }

    /**
     * Method under test: {@link ReportController#getAbsenceInVHSNDReport(String)}
     */
    @Test
    void testGetAbsenceInVHSNDReport2() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange
        ReportController reportController = new ReportController();

        // Act
        ResponseEntity<Object> actualAbsenceInVHSNDReport = reportController.getAbsenceInVHSNDReport("foo");

        // Assert
        assertEquals("Not a JSON Object: \"foo\"", actualAbsenceInVHSNDReport.getBody());
        assertEquals("com.google.gson.stream.MalformedJsonException: Use JsonReader.setLenient(true) to accept malformed"
                + " JSON at line 1 column 7 path $", reportController.getAashaHomeVisitGapReport("Json Request").getBody());
        assertEquals(500, actualAbsenceInVHSNDReport.getStatusCodeValue());
        assertTrue(actualAbsenceInVHSNDReport.getHeaders().isEmpty());
    }

    /**
     * Method under test: {@link ReportController#getAbsenceInVHSNDReport(String)}
     */
    @Test
    void testGetAbsenceInVHSNDReport3() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange
        ReportController reportController = new ReportController();

        // Act
        ResponseEntity<Object> actualAbsenceInVHSNDReport = reportController
                .getAbsenceInVHSNDReport("ECDAbsenceInVHSNDReport");

        // Assert
        assertEquals("Not a JSON Object: \"ECDAbsenceInVHSNDReport\"", actualAbsenceInVHSNDReport.getBody());
        assertEquals("com.google.gson.stream.MalformedJsonException: Use JsonReader.setLenient(true) to accept malformed"
                + " JSON at line 1 column 7 path $", reportController.getAashaHomeVisitGapReport("Json Request").getBody());
        assertEquals(500, actualAbsenceInVHSNDReport.getStatusCodeValue());
        assertTrue(actualAbsenceInVHSNDReport.getHeaders().isEmpty());
    }

    /**
     * Method under test: {@link ReportController#getAbsenceInVHSNDReport(String)}
     */
    @Test
    void testGetAbsenceInVHSNDReport4() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange
        ReportController reportController = new ReportController();

        // Act
        ResponseEntity<Object> actualAbsenceInVHSNDReport = reportController.getAbsenceInVHSNDReport("42");

        // Assert
        assertEquals("Not a JSON Object: 42", actualAbsenceInVHSNDReport.getBody());
        assertEquals("com.google.gson.stream.MalformedJsonException: Use JsonReader.setLenient(true) to accept malformed"
                + " JSON at line 1 column 7 path $", reportController.getAashaHomeVisitGapReport("Json Request").getBody());
        assertEquals(500, actualAbsenceInVHSNDReport.getStatusCodeValue());
        assertTrue(actualAbsenceInVHSNDReport.getHeaders().isEmpty());
    }

    /**
     * Method under test: {@link ReportController#getAbsenceInVHSNDReport(String)}
     */
    @Test
    void testGetAbsenceInVHSNDReport5() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange
        ReportController reportController = new ReportController();

        // Act
        ResponseEntity<Object> actualAbsenceInVHSNDReport = reportController.getAbsenceInVHSNDReport("");

        // Assert
        assertEquals("Not a JSON Object: null", actualAbsenceInVHSNDReport.getBody());
        assertEquals("com.google.gson.stream.MalformedJsonException: Use JsonReader.setLenient(true) to accept malformed"
                + " JSON at line 1 column 7 path $", reportController.getAashaHomeVisitGapReport("Json Request").getBody());
        assertEquals(500, actualAbsenceInVHSNDReport.getStatusCodeValue());
        assertTrue(actualAbsenceInVHSNDReport.getHeaders().isEmpty());
    }

    /**
     * Method under test: {@link ReportController#getAbsenceInVHSNDReport(String)}
     */
    @Test
    void testGetAbsenceInVHSNDReport6() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange
        ReportController reportController = new ReportController();

        // Act
        ResponseEntity<Object> actualAbsenceInVHSNDReport = reportController
                .getAbsenceInVHSNDReport("42ECDAbsenceInVHSNDReport");

        // Assert
        assertEquals("Not a JSON Object: \"42ECDAbsenceInVHSNDReport\"", actualAbsenceInVHSNDReport.getBody());
        assertEquals("com.google.gson.stream.MalformedJsonException: Use JsonReader.setLenient(true) to accept malformed"
                + " JSON at line 1 column 7 path $", reportController.getAashaHomeVisitGapReport("Json Request").getBody());
        assertEquals(500, actualAbsenceInVHSNDReport.getStatusCodeValue());
        assertTrue(actualAbsenceInVHSNDReport.getHeaders().isEmpty());
    }

    /**
     * Method under test:
     * {@link ReportController#getVaccineDropOutIdentifiedReport(String)}
     */
    @Test
    void testGetVaccineDropOutIdentifiedReport() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange
        ReportController reportController = new ReportController();

        // Act
        ResponseEntity<Object> actualVaccineDropOutIdentifiedReport = reportController
                .getVaccineDropOutIdentifiedReport("Json Request");

        // Assert
        assertEquals("com.google.gson.stream.MalformedJsonException: Use JsonReader.setLenient(true) to accept malformed"
                + " JSON at line 1 column 7 path $", reportController.getAbortionReport("Json Request").getBody());
        assertEquals("com.google.gson.stream.MalformedJsonException: Use JsonReader.setLenient(true) to accept malformed"
                + " JSON at line 1 column 7 path $", actualVaccineDropOutIdentifiedReport.getBody());
        assertEquals(500, actualVaccineDropOutIdentifiedReport.getStatusCodeValue());
        assertTrue(actualVaccineDropOutIdentifiedReport.getHeaders().isEmpty());
    }

    /**
     * Method under test:
     * {@link ReportController#getVaccineDropOutIdentifiedReport(String)}
     */
    @Test
    void testGetVaccineDropOutIdentifiedReport2() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange
        ReportController reportController = new ReportController();

        // Act
        ResponseEntity<Object> actualVaccineDropOutIdentifiedReport = reportController
                .getVaccineDropOutIdentifiedReport("foo");

        // Assert
        assertEquals("Not a JSON Object: \"foo\"", actualVaccineDropOutIdentifiedReport.getBody());
        assertEquals("com.google.gson.stream.MalformedJsonException: Use JsonReader.setLenient(true) to accept malformed"
                + " JSON at line 1 column 7 path $", reportController.getAashaHomeVisitGapReport("Json Request").getBody());
        assertEquals(500, actualVaccineDropOutIdentifiedReport.getStatusCodeValue());
        assertTrue(actualVaccineDropOutIdentifiedReport.getHeaders().isEmpty());
    }

    /**
     * Method under test:
     * {@link ReportController#getVaccineDropOutIdentifiedReport(String)}
     */
    @Test
    void testGetVaccineDropOutIdentifiedReport3() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange
        ReportController reportController = new ReportController();

        // Act
        ResponseEntity<Object> actualVaccineDropOutIdentifiedReport = reportController
                .getVaccineDropOutIdentifiedReport("ECDVaccineDropOutIdentifiedReport");

        // Assert
        assertEquals("Not a JSON Object: \"ECDVaccineDropOutIdentifiedReport\"",
                actualVaccineDropOutIdentifiedReport.getBody());
        assertEquals("com.google.gson.stream.MalformedJsonException: Use JsonReader.setLenient(true) to accept malformed"
                + " JSON at line 1 column 7 path $", reportController.getAashaHomeVisitGapReport("Json Request").getBody());
        assertEquals(500, actualVaccineDropOutIdentifiedReport.getStatusCodeValue());
        assertTrue(actualVaccineDropOutIdentifiedReport.getHeaders().isEmpty());
    }

    /**
     * Method under test:
     * {@link ReportController#getVaccineDropOutIdentifiedReport(String)}
     */
    @Test
    void testGetVaccineDropOutIdentifiedReport4() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange
        ReportController reportController = new ReportController();

        // Act
        ResponseEntity<Object> actualVaccineDropOutIdentifiedReport = reportController
                .getVaccineDropOutIdentifiedReport("42");

        // Assert
        assertEquals("Not a JSON Object: 42", actualVaccineDropOutIdentifiedReport.getBody());
        assertEquals("com.google.gson.stream.MalformedJsonException: Use JsonReader.setLenient(true) to accept malformed"
                + " JSON at line 1 column 7 path $", reportController.getAashaHomeVisitGapReport("Json Request").getBody());
        assertEquals(500, actualVaccineDropOutIdentifiedReport.getStatusCodeValue());
        assertTrue(actualVaccineDropOutIdentifiedReport.getHeaders().isEmpty());
    }

    /**
     * Method under test:
     * {@link ReportController#getVaccineDropOutIdentifiedReport(String)}
     */
    @Test
    void testGetVaccineDropOutIdentifiedReport5() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange
        ReportController reportController = new ReportController();

        // Act
        ResponseEntity<Object> actualVaccineDropOutIdentifiedReport = reportController
                .getVaccineDropOutIdentifiedReport("");

        // Assert
        assertEquals("Not a JSON Object: null", actualVaccineDropOutIdentifiedReport.getBody());
        assertEquals("com.google.gson.stream.MalformedJsonException: Use JsonReader.setLenient(true) to accept malformed"
                + " JSON at line 1 column 7 path $", reportController.getAashaHomeVisitGapReport("Json Request").getBody());
        assertEquals(500, actualVaccineDropOutIdentifiedReport.getStatusCodeValue());
        assertTrue(actualVaccineDropOutIdentifiedReport.getHeaders().isEmpty());
    }

    /**
     * Method under test:
     * {@link ReportController#getVaccineDropOutIdentifiedReport(String)}
     */
    @Test
    void testGetVaccineDropOutIdentifiedReport6() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange
        ReportController reportController = new ReportController();

        // Act
        ResponseEntity<Object> actualVaccineDropOutIdentifiedReport = reportController
                .getVaccineDropOutIdentifiedReport("42ECDVaccineDropOutIdentifiedReport");

        // Assert
        assertEquals("Not a JSON Object: \"42ECDVaccineDropOutIdentifiedReport\"",
                actualVaccineDropOutIdentifiedReport.getBody());
        assertEquals("com.google.gson.stream.MalformedJsonException: Use JsonReader.setLenient(true) to accept malformed"
                + " JSON at line 1 column 7 path $", reportController.getAashaHomeVisitGapReport("Json Request").getBody());
        assertEquals(500, actualVaccineDropOutIdentifiedReport.getStatusCodeValue());
        assertTrue(actualVaccineDropOutIdentifiedReport.getHeaders().isEmpty());
    }

    /**
     * Method under test:
     * {@link ReportController#getVaccineLeftOutIdentifiedReport(String)}
     */
    @Test
    void testGetVaccineLeftOutIdentifiedReport() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange
        ReportController reportController = new ReportController();

        // Act
        ResponseEntity<Object> actualVaccineLeftOutIdentifiedReport = reportController
                .getVaccineLeftOutIdentifiedReport("Json Request");

        // Assert
        assertEquals("com.google.gson.stream.MalformedJsonException: Use JsonReader.setLenient(true) to accept malformed"
                + " JSON at line 1 column 7 path $", reportController.getAbortionReport("Json Request").getBody());
        assertEquals("com.google.gson.stream.MalformedJsonException: Use JsonReader.setLenient(true) to accept malformed"
                + " JSON at line 1 column 7 path $", actualVaccineLeftOutIdentifiedReport.getBody());
        assertEquals(500, actualVaccineLeftOutIdentifiedReport.getStatusCodeValue());
        assertTrue(actualVaccineLeftOutIdentifiedReport.getHeaders().isEmpty());
    }

    /**
     * Method under test:
     * {@link ReportController#getVaccineLeftOutIdentifiedReport(String)}
     */
    @Test
    void testGetVaccineLeftOutIdentifiedReport2() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange
        ReportController reportController = new ReportController();

        // Act
        ResponseEntity<Object> actualVaccineLeftOutIdentifiedReport = reportController
                .getVaccineLeftOutIdentifiedReport("foo");

        // Assert
        assertEquals("Not a JSON Object: \"foo\"", actualVaccineLeftOutIdentifiedReport.getBody());
        assertEquals("com.google.gson.stream.MalformedJsonException: Use JsonReader.setLenient(true) to accept malformed"
                + " JSON at line 1 column 7 path $", reportController.getAashaHomeVisitGapReport("Json Request").getBody());
        assertEquals(500, actualVaccineLeftOutIdentifiedReport.getStatusCodeValue());
        assertTrue(actualVaccineLeftOutIdentifiedReport.getHeaders().isEmpty());
    }

    /**
     * Method under test:
     * {@link ReportController#getVaccineLeftOutIdentifiedReport(String)}
     */
    @Test
    void testGetVaccineLeftOutIdentifiedReport3() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange
        ReportController reportController = new ReportController();

        // Act
        ResponseEntity<Object> actualVaccineLeftOutIdentifiedReport = reportController
                .getVaccineLeftOutIdentifiedReport("ECDVaccineLeftOutIdentifiedReport");

        // Assert
        assertEquals("Not a JSON Object: \"ECDVaccineLeftOutIdentifiedReport\"",
                actualVaccineLeftOutIdentifiedReport.getBody());
        assertEquals("com.google.gson.stream.MalformedJsonException: Use JsonReader.setLenient(true) to accept malformed"
                + " JSON at line 1 column 7 path $", reportController.getAashaHomeVisitGapReport("Json Request").getBody());
        assertEquals(500, actualVaccineLeftOutIdentifiedReport.getStatusCodeValue());
        assertTrue(actualVaccineLeftOutIdentifiedReport.getHeaders().isEmpty());
    }

    /**
     * Method under test:
     * {@link ReportController#getVaccineLeftOutIdentifiedReport(String)}
     */
    @Test
    void testGetVaccineLeftOutIdentifiedReport4() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange
        ReportController reportController = new ReportController();

        // Act
        ResponseEntity<Object> actualVaccineLeftOutIdentifiedReport = reportController
                .getVaccineLeftOutIdentifiedReport("42");

        // Assert
        assertEquals("Not a JSON Object: 42", actualVaccineLeftOutIdentifiedReport.getBody());
        assertEquals("com.google.gson.stream.MalformedJsonException: Use JsonReader.setLenient(true) to accept malformed"
                + " JSON at line 1 column 7 path $", reportController.getAashaHomeVisitGapReport("Json Request").getBody());
        assertEquals(500, actualVaccineLeftOutIdentifiedReport.getStatusCodeValue());
        assertTrue(actualVaccineLeftOutIdentifiedReport.getHeaders().isEmpty());
    }

    /**
     * Method under test:
     * {@link ReportController#getVaccineLeftOutIdentifiedReport(String)}
     */
    @Test
    void testGetVaccineLeftOutIdentifiedReport5() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange
        ReportController reportController = new ReportController();

        // Act
        ResponseEntity<Object> actualVaccineLeftOutIdentifiedReport = reportController
                .getVaccineLeftOutIdentifiedReport("");

        // Assert
        assertEquals("Not a JSON Object: null", actualVaccineLeftOutIdentifiedReport.getBody());
        assertEquals("com.google.gson.stream.MalformedJsonException: Use JsonReader.setLenient(true) to accept malformed"
                + " JSON at line 1 column 7 path $", reportController.getAashaHomeVisitGapReport("Json Request").getBody());
        assertEquals(500, actualVaccineLeftOutIdentifiedReport.getStatusCodeValue());
        assertTrue(actualVaccineLeftOutIdentifiedReport.getHeaders().isEmpty());
    }

    /**
     * Method under test:
     * {@link ReportController#getVaccineLeftOutIdentifiedReport(String)}
     */
    @Test
    void testGetVaccineLeftOutIdentifiedReport6() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange
        ReportController reportController = new ReportController();

        // Act
        ResponseEntity<Object> actualVaccineLeftOutIdentifiedReport = reportController
                .getVaccineLeftOutIdentifiedReport("42ECDVaccineLeftOutIdentifiedReport");

        // Assert
        assertEquals("Not a JSON Object: \"42ECDVaccineLeftOutIdentifiedReport\"",
                actualVaccineLeftOutIdentifiedReport.getBody());
        assertEquals("com.google.gson.stream.MalformedJsonException: Use JsonReader.setLenient(true) to accept malformed"
                + " JSON at line 1 column 7 path $", reportController.getAashaHomeVisitGapReport("Json Request").getBody());
        assertEquals(500, actualVaccineLeftOutIdentifiedReport.getStatusCodeValue());
        assertTrue(actualVaccineLeftOutIdentifiedReport.getHeaders().isEmpty());
    }

    /**
     * Method under test:
     * {@link ReportController#getDevelopmentalDelayReport(String)}
     */
    @Test
    void testGetDevelopmentalDelayReport() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange
        ReportController reportController = new ReportController();

        // Act
        ResponseEntity<Object> actualDevelopmentalDelayReport = reportController
                .getDevelopmentalDelayReport("Json Request");

        // Assert
        assertEquals("com.google.gson.stream.MalformedJsonException: Use JsonReader.setLenient(true) to accept malformed"
                + " JSON at line 1 column 7 path $", reportController.getAbortionReport("Json Request").getBody());
        assertEquals("com.google.gson.stream.MalformedJsonException: Use JsonReader.setLenient(true) to accept malformed"
                + " JSON at line 1 column 7 path $", actualDevelopmentalDelayReport.getBody());
        assertEquals(500, actualDevelopmentalDelayReport.getStatusCodeValue());
        assertTrue(actualDevelopmentalDelayReport.getHeaders().isEmpty());
    }

    /**
     * Method under test:
     * {@link ReportController#getDevelopmentalDelayReport(String)}
     */
    @Test
    void testGetDevelopmentalDelayReport2() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange
        ReportController reportController = new ReportController();

        // Act
        ResponseEntity<Object> actualDevelopmentalDelayReport = reportController.getDevelopmentalDelayReport("foo");

        // Assert
        assertEquals("Not a JSON Object: \"foo\"", actualDevelopmentalDelayReport.getBody());
        assertEquals("com.google.gson.stream.MalformedJsonException: Use JsonReader.setLenient(true) to accept malformed"
                + " JSON at line 1 column 7 path $", reportController.getAashaHomeVisitGapReport("Json Request").getBody());
        assertEquals(500, actualDevelopmentalDelayReport.getStatusCodeValue());
        assertTrue(actualDevelopmentalDelayReport.getHeaders().isEmpty());
    }

    /**
     * Method under test:
     * {@link ReportController#getDevelopmentalDelayReport(String)}
     */
    @Test
    void testGetDevelopmentalDelayReport3() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange
        ReportController reportController = new ReportController();

        // Act
        ResponseEntity<Object> actualDevelopmentalDelayReport = reportController
                .getDevelopmentalDelayReport("ECDDevelopmentalDelayReport");

        // Assert
        assertEquals("Not a JSON Object: \"ECDDevelopmentalDelayReport\"", actualDevelopmentalDelayReport.getBody());
        assertEquals("com.google.gson.stream.MalformedJsonException: Use JsonReader.setLenient(true) to accept malformed"
                + " JSON at line 1 column 7 path $", reportController.getAashaHomeVisitGapReport("Json Request").getBody());
        assertEquals(500, actualDevelopmentalDelayReport.getStatusCodeValue());
        assertTrue(actualDevelopmentalDelayReport.getHeaders().isEmpty());
    }

    /**
     * Method under test:
     * {@link ReportController#getDevelopmentalDelayReport(String)}
     */
    @Test
    void testGetDevelopmentalDelayReport4() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange
        ReportController reportController = new ReportController();

        // Act
        ResponseEntity<Object> actualDevelopmentalDelayReport = reportController.getDevelopmentalDelayReport("42");

        // Assert
        assertEquals("Not a JSON Object: 42", actualDevelopmentalDelayReport.getBody());
        assertEquals("com.google.gson.stream.MalformedJsonException: Use JsonReader.setLenient(true) to accept malformed"
                + " JSON at line 1 column 7 path $", reportController.getAashaHomeVisitGapReport("Json Request").getBody());
        assertEquals(500, actualDevelopmentalDelayReport.getStatusCodeValue());
        assertTrue(actualDevelopmentalDelayReport.getHeaders().isEmpty());
    }

    /**
     * Method under test:
     * {@link ReportController#getDevelopmentalDelayReport(String)}
     */
    @Test
    void testGetDevelopmentalDelayReport5() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange
        ReportController reportController = new ReportController();

        // Act
        ResponseEntity<Object> actualDevelopmentalDelayReport = reportController.getDevelopmentalDelayReport("");

        // Assert
        assertEquals("Not a JSON Object: null", actualDevelopmentalDelayReport.getBody());
        assertEquals("com.google.gson.stream.MalformedJsonException: Use JsonReader.setLenient(true) to accept malformed"
                + " JSON at line 1 column 7 path $", reportController.getAashaHomeVisitGapReport("Json Request").getBody());
        assertEquals(500, actualDevelopmentalDelayReport.getStatusCodeValue());
        assertTrue(actualDevelopmentalDelayReport.getHeaders().isEmpty());
    }

    /**
     * Method under test:
     * {@link ReportController#getDevelopmentalDelayReport(String)}
     */
    @Test
    void testGetDevelopmentalDelayReport6() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange
        ReportController reportController = new ReportController();

        // Act
        ResponseEntity<Object> actualDevelopmentalDelayReport = reportController
                .getDevelopmentalDelayReport("42ECDDevelopmentalDelayReport");

        // Assert
        assertEquals("Not a JSON Object: \"42ECDDevelopmentalDelayReport\"", actualDevelopmentalDelayReport.getBody());
        assertEquals("com.google.gson.stream.MalformedJsonException: Use JsonReader.setLenient(true) to accept malformed"
                + " JSON at line 1 column 7 path $", reportController.getAashaHomeVisitGapReport("Json Request").getBody());
        assertEquals(500, actualDevelopmentalDelayReport.getStatusCodeValue());
        assertTrue(actualDevelopmentalDelayReport.getHeaders().isEmpty());
    }

    /**
     * Method under test: {@link ReportController#getAbortionReport(String)}
     */
    @Test
    void testGetAbortionReport() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange
        ReportController reportController = new ReportController();

        // Act
        ResponseEntity<Object> actualAbortionReport = reportController.getAbortionReport("Json Request");

        // Assert
        assertEquals("com.google.gson.stream.MalformedJsonException: Use JsonReader.setLenient(true) to accept malformed"
                + " JSON at line 1 column 7 path $", actualAbortionReport.getBody());
        assertEquals("com.google.gson.stream.MalformedJsonException: Use JsonReader.setLenient(true) to accept malformed"
                + " JSON at line 1 column 7 path $", reportController.getAbsenceInVHSNDReport("Json Request").getBody());
        assertEquals(500, actualAbortionReport.getStatusCodeValue());
        assertTrue(actualAbortionReport.getHeaders().isEmpty());
    }

    /**
     * Method under test: {@link ReportController#getAbortionReport(String)}
     */
    @Test
    void testGetAbortionReport2() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange
        ReportController reportController = new ReportController();

        // Act
        ResponseEntity<Object> actualAbortionReport = reportController.getAbortionReport("foo");

        // Assert
        assertEquals("Not a JSON Object: \"foo\"", actualAbortionReport.getBody());
        assertEquals("com.google.gson.stream.MalformedJsonException: Use JsonReader.setLenient(true) to accept malformed"
                + " JSON at line 1 column 7 path $", reportController.getAashaHomeVisitGapReport("Json Request").getBody());
        assertEquals(500, actualAbortionReport.getStatusCodeValue());
        assertTrue(actualAbortionReport.getHeaders().isEmpty());
    }

    /**
     * Method under test: {@link ReportController#getAbortionReport(String)}
     */
    @Test
    void testGetAbortionReport3() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange
        ReportController reportController = new ReportController();

        // Act
        ResponseEntity<Object> actualAbortionReport = reportController.getAbortionReport("ECDAbortionReport");

        // Assert
        assertEquals("Not a JSON Object: \"ECDAbortionReport\"", actualAbortionReport.getBody());
        assertEquals("com.google.gson.stream.MalformedJsonException: Use JsonReader.setLenient(true) to accept malformed"
                + " JSON at line 1 column 7 path $", reportController.getAashaHomeVisitGapReport("Json Request").getBody());
        assertEquals(500, actualAbortionReport.getStatusCodeValue());
        assertTrue(actualAbortionReport.getHeaders().isEmpty());
    }

    /**
     * Method under test: {@link ReportController#getAbortionReport(String)}
     */
    @Test
    void testGetAbortionReport4() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange
        ReportController reportController = new ReportController();

        // Act
        ResponseEntity<Object> actualAbortionReport = reportController.getAbortionReport("42");

        // Assert
        assertEquals("Not a JSON Object: 42", actualAbortionReport.getBody());
        assertEquals("com.google.gson.stream.MalformedJsonException: Use JsonReader.setLenient(true) to accept malformed"
                + " JSON at line 1 column 7 path $", reportController.getAashaHomeVisitGapReport("Json Request").getBody());
        assertEquals(500, actualAbortionReport.getStatusCodeValue());
        assertTrue(actualAbortionReport.getHeaders().isEmpty());
    }

    /**
     * Method under test: {@link ReportController#getAbortionReport(String)}
     */
    @Test
    void testGetAbortionReport5() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange
        ReportController reportController = new ReportController();

        // Act
        ResponseEntity<Object> actualAbortionReport = reportController.getAbortionReport("");

        // Assert
        assertEquals("Not a JSON Object: null", actualAbortionReport.getBody());
        assertEquals("com.google.gson.stream.MalformedJsonException: Use JsonReader.setLenient(true) to accept malformed"
                + " JSON at line 1 column 7 path $", reportController.getAashaHomeVisitGapReport("Json Request").getBody());
        assertEquals(500, actualAbortionReport.getStatusCodeValue());
        assertTrue(actualAbortionReport.getHeaders().isEmpty());
    }

    /**
     * Method under test: {@link ReportController#getAbortionReport(String)}
     */
    @Test
    void testGetAbortionReport6() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange
        ReportController reportController = new ReportController();

        // Act
        ResponseEntity<Object> actualAbortionReport = reportController.getAbortionReport("42ECDAbortionReport");

        // Assert
        assertEquals("Not a JSON Object: \"42ECDAbortionReport\"", actualAbortionReport.getBody());
        assertEquals("com.google.gson.stream.MalformedJsonException: Use JsonReader.setLenient(true) to accept malformed"
                + " JSON at line 1 column 7 path $", reportController.getAashaHomeVisitGapReport("Json Request").getBody());
        assertEquals(500, actualAbortionReport.getStatusCodeValue());
        assertTrue(actualAbortionReport.getHeaders().isEmpty());
    }

    /**
     * Method under test: {@link ReportController#getDeliveryStatusReport(String)}
     */
    @Test
    void testGetDeliveryStatusReport() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange
        ReportController reportController = new ReportController();

        // Act
        ResponseEntity<Object> actualDeliveryStatusReport = reportController.getDeliveryStatusReport("Json Request");

        // Assert
        assertEquals("com.google.gson.stream.MalformedJsonException: Use JsonReader.setLenient(true) to accept malformed"
                + " JSON at line 1 column 7 path $", reportController.getAbortionReport("Json Request").getBody());
        assertEquals("com.google.gson.stream.MalformedJsonException: Use JsonReader.setLenient(true) to accept malformed"
                + " JSON at line 1 column 7 path $", actualDeliveryStatusReport.getBody());
        assertEquals(500, actualDeliveryStatusReport.getStatusCodeValue());
        assertTrue(actualDeliveryStatusReport.getHeaders().isEmpty());
    }

    /**
     * Method under test: {@link ReportController#getDeliveryStatusReport(String)}
     */
    @Test
    void testGetDeliveryStatusReport2() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange
        ReportController reportController = new ReportController();

        // Act
        ResponseEntity<Object> actualDeliveryStatusReport = reportController.getDeliveryStatusReport("foo");

        // Assert
        assertEquals("Not a JSON Object: \"foo\"", actualDeliveryStatusReport.getBody());
        assertEquals("com.google.gson.stream.MalformedJsonException: Use JsonReader.setLenient(true) to accept malformed"
                + " JSON at line 1 column 7 path $", reportController.getAashaHomeVisitGapReport("Json Request").getBody());
        assertEquals(500, actualDeliveryStatusReport.getStatusCodeValue());
        assertTrue(actualDeliveryStatusReport.getHeaders().isEmpty());
    }

    /**
     * Method under test: {@link ReportController#getDeliveryStatusReport(String)}
     */
    @Test
    void testGetDeliveryStatusReport3() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange
        ReportController reportController = new ReportController();

        // Act
        ResponseEntity<Object> actualDeliveryStatusReport = reportController
                .getDeliveryStatusReport("ECDDeliveryStatusReport");

        // Assert
        assertEquals("Not a JSON Object: \"ECDDeliveryStatusReport\"", actualDeliveryStatusReport.getBody());
        assertEquals("com.google.gson.stream.MalformedJsonException: Use JsonReader.setLenient(true) to accept malformed"
                + " JSON at line 1 column 7 path $", reportController.getAashaHomeVisitGapReport("Json Request").getBody());
        assertEquals(500, actualDeliveryStatusReport.getStatusCodeValue());
        assertTrue(actualDeliveryStatusReport.getHeaders().isEmpty());
    }

    /**
     * Method under test: {@link ReportController#getDeliveryStatusReport(String)}
     */
    @Test
    void testGetDeliveryStatusReport4() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange
        ReportController reportController = new ReportController();

        // Act
        ResponseEntity<Object> actualDeliveryStatusReport = reportController.getDeliveryStatusReport("42");

        // Assert
        assertEquals("Not a JSON Object: 42", actualDeliveryStatusReport.getBody());
        assertEquals("com.google.gson.stream.MalformedJsonException: Use JsonReader.setLenient(true) to accept malformed"
                + " JSON at line 1 column 7 path $", reportController.getAashaHomeVisitGapReport("Json Request").getBody());
        assertEquals(500, actualDeliveryStatusReport.getStatusCodeValue());
        assertTrue(actualDeliveryStatusReport.getHeaders().isEmpty());
    }

    /**
     * Method under test: {@link ReportController#getDeliveryStatusReport(String)}
     */
    @Test
    void testGetDeliveryStatusReport5() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange
        ReportController reportController = new ReportController();

        // Act
        ResponseEntity<Object> actualDeliveryStatusReport = reportController.getDeliveryStatusReport("");

        // Assert
        assertEquals("Not a JSON Object: null", actualDeliveryStatusReport.getBody());
        assertEquals("com.google.gson.stream.MalformedJsonException: Use JsonReader.setLenient(true) to accept malformed"
                + " JSON at line 1 column 7 path $", reportController.getAashaHomeVisitGapReport("Json Request").getBody());
        assertEquals(500, actualDeliveryStatusReport.getStatusCodeValue());
        assertTrue(actualDeliveryStatusReport.getHeaders().isEmpty());
    }

    /**
     * Method under test: {@link ReportController#getDeliveryStatusReport(String)}
     */
    @Test
    void testGetDeliveryStatusReport6() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange
        ReportController reportController = new ReportController();

        // Act
        ResponseEntity<Object> actualDeliveryStatusReport = reportController
                .getDeliveryStatusReport("42ECDDeliveryStatusReport");

        // Assert
        assertEquals("Not a JSON Object: \"42ECDDeliveryStatusReport\"", actualDeliveryStatusReport.getBody());
        assertEquals("com.google.gson.stream.MalformedJsonException: Use JsonReader.setLenient(true) to accept malformed"
                + " JSON at line 1 column 7 path $", reportController.getAashaHomeVisitGapReport("Json Request").getBody());
        assertEquals(500, actualDeliveryStatusReport.getStatusCodeValue());
        assertTrue(actualDeliveryStatusReport.getHeaders().isEmpty());
    }

    /**
     * Method under test:
     * {@link ReportController#getHRPCasesIdentifiedReport(String)}
     */
    @Test
    void testGetHRPCasesIdentifiedReport() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange
        ReportController reportController = new ReportController();

        // Act
        ResponseEntity<Object> actualHRPCasesIdentifiedReport = reportController
                .getHRPCasesIdentifiedReport("Json Request");

        // Assert
        assertEquals("com.google.gson.stream.MalformedJsonException: Use JsonReader.setLenient(true) to accept malformed"
                + " JSON at line 1 column 7 path $", reportController.getAbortionReport("Json Request").getBody());
        assertEquals("com.google.gson.stream.MalformedJsonException: Use JsonReader.setLenient(true) to accept malformed"
                + " JSON at line 1 column 7 path $", actualHRPCasesIdentifiedReport.getBody());
        assertEquals(500, actualHRPCasesIdentifiedReport.getStatusCodeValue());
        assertTrue(actualHRPCasesIdentifiedReport.getHeaders().isEmpty());
    }

    /**
     * Method under test:
     * {@link ReportController#getHRPCasesIdentifiedReport(String)}
     */
    @Test
    void testGetHRPCasesIdentifiedReport2() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange
        ReportController reportController = new ReportController();

        // Act
        ResponseEntity<Object> actualHRPCasesIdentifiedReport = reportController.getHRPCasesIdentifiedReport("foo");

        // Assert
        assertEquals("Not a JSON Object: \"foo\"", actualHRPCasesIdentifiedReport.getBody());
        assertEquals("com.google.gson.stream.MalformedJsonException: Use JsonReader.setLenient(true) to accept malformed"
                + " JSON at line 1 column 7 path $", reportController.getAashaHomeVisitGapReport("Json Request").getBody());
        assertEquals(500, actualHRPCasesIdentifiedReport.getStatusCodeValue());
        assertTrue(actualHRPCasesIdentifiedReport.getHeaders().isEmpty());
    }

    /**
     * Method under test:
     * {@link ReportController#getHRPCasesIdentifiedReport(String)}
     */
    @Test
    void testGetHRPCasesIdentifiedReport3() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange
        ReportController reportController = new ReportController();

        // Act
        ResponseEntity<Object> actualHRPCasesIdentifiedReport = reportController
                .getHRPCasesIdentifiedReport("ECDHRPWCasesIdentifiedReport");

        // Assert
        assertEquals("Not a JSON Object: \"ECDHRPWCasesIdentifiedReport\"", actualHRPCasesIdentifiedReport.getBody());
        assertEquals("com.google.gson.stream.MalformedJsonException: Use JsonReader.setLenient(true) to accept malformed"
                + " JSON at line 1 column 7 path $", reportController.getAashaHomeVisitGapReport("Json Request").getBody());
        assertEquals(500, actualHRPCasesIdentifiedReport.getStatusCodeValue());
        assertTrue(actualHRPCasesIdentifiedReport.getHeaders().isEmpty());
    }

    /**
     * Method under test:
     * {@link ReportController#getHRPCasesIdentifiedReport(String)}
     */
    @Test
    void testGetHRPCasesIdentifiedReport4() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange
        ReportController reportController = new ReportController();

        // Act
        ResponseEntity<Object> actualHRPCasesIdentifiedReport = reportController.getHRPCasesIdentifiedReport("42");

        // Assert
        assertEquals("Not a JSON Object: 42", actualHRPCasesIdentifiedReport.getBody());
        assertEquals("com.google.gson.stream.MalformedJsonException: Use JsonReader.setLenient(true) to accept malformed"
                + " JSON at line 1 column 7 path $", reportController.getAashaHomeVisitGapReport("Json Request").getBody());
        assertEquals(500, actualHRPCasesIdentifiedReport.getStatusCodeValue());
        assertTrue(actualHRPCasesIdentifiedReport.getHeaders().isEmpty());
    }

    /**
     * Method under test:
     * {@link ReportController#getHRPCasesIdentifiedReport(String)}
     */
    @Test
    void testGetHRPCasesIdentifiedReport5() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange
        ReportController reportController = new ReportController();

        // Act
        ResponseEntity<Object> actualHRPCasesIdentifiedReport = reportController.getHRPCasesIdentifiedReport("");

        // Assert
        assertEquals("Not a JSON Object: null", actualHRPCasesIdentifiedReport.getBody());
        assertEquals("com.google.gson.stream.MalformedJsonException: Use JsonReader.setLenient(true) to accept malformed"
                + " JSON at line 1 column 7 path $", reportController.getAashaHomeVisitGapReport("Json Request").getBody());
        assertEquals(500, actualHRPCasesIdentifiedReport.getStatusCodeValue());
        assertTrue(actualHRPCasesIdentifiedReport.getHeaders().isEmpty());
    }

    /**
     * Method under test:
     * {@link ReportController#getHRPCasesIdentifiedReport(String)}
     */
    @Test
    void testGetHRPCasesIdentifiedReport6() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange
        ReportController reportController = new ReportController();

        // Act
        ResponseEntity<Object> actualHRPCasesIdentifiedReport = reportController
                .getHRPCasesIdentifiedReport("42ECDHRPWCasesIdentifiedReport");

        // Assert
        assertEquals("Not a JSON Object: \"42ECDHRPWCasesIdentifiedReport\"", actualHRPCasesIdentifiedReport.getBody());
        assertEquals("com.google.gson.stream.MalformedJsonException: Use JsonReader.setLenient(true) to accept malformed"
                + " JSON at line 1 column 7 path $", reportController.getAashaHomeVisitGapReport("Json Request").getBody());
        assertEquals(500, actualHRPCasesIdentifiedReport.getStatusCodeValue());
        assertTrue(actualHRPCasesIdentifiedReport.getHeaders().isEmpty());
    }

    /**
     * Method under test: {@link ReportController#getInfantsHighRiskReport(String)}
     */
    @Test
    void testGetInfantsHighRiskReport() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange
        ReportController reportController = new ReportController();

        // Act
        ResponseEntity<Object> actualInfantsHighRiskReport = reportController.getInfantsHighRiskReport("Json Request");

        // Assert
        assertEquals("com.google.gson.stream.MalformedJsonException: Use JsonReader.setLenient(true) to accept malformed"
                + " JSON at line 1 column 7 path $", reportController.getAbortionReport("Json Request").getBody());
        assertEquals("com.google.gson.stream.MalformedJsonException: Use JsonReader.setLenient(true) to accept malformed"
                + " JSON at line 1 column 7 path $", actualInfantsHighRiskReport.getBody());
        assertEquals(500, actualInfantsHighRiskReport.getStatusCodeValue());
        assertTrue(actualInfantsHighRiskReport.getHeaders().isEmpty());
    }

    /**
     * Method under test: {@link ReportController#getInfantsHighRiskReport(String)}
     */
    @Test
    void testGetInfantsHighRiskReport2() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange
        ReportController reportController = new ReportController();

        // Act
        ResponseEntity<Object> actualInfantsHighRiskReport = reportController.getInfantsHighRiskReport("foo");

        // Assert
        assertEquals("Not a JSON Object: \"foo\"", actualInfantsHighRiskReport.getBody());
        assertEquals("com.google.gson.stream.MalformedJsonException: Use JsonReader.setLenient(true) to accept malformed"
                + " JSON at line 1 column 7 path $", reportController.getAashaHomeVisitGapReport("Json Request").getBody());
        assertEquals(500, actualInfantsHighRiskReport.getStatusCodeValue());
        assertTrue(actualInfantsHighRiskReport.getHeaders().isEmpty());
    }

    /**
     * Method under test: {@link ReportController#getInfantsHighRiskReport(String)}
     */
    @Test
    void testGetInfantsHighRiskReport3() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange
        ReportController reportController = new ReportController();

        // Act
        ResponseEntity<Object> actualInfantsHighRiskReport = reportController
                .getInfantsHighRiskReport("ECDInfantsHighRiskReport");

        // Assert
        assertEquals("Not a JSON Object: \"ECDInfantsHighRiskReport\"", actualInfantsHighRiskReport.getBody());
        assertEquals("com.google.gson.stream.MalformedJsonException: Use JsonReader.setLenient(true) to accept malformed"
                + " JSON at line 1 column 7 path $", reportController.getAashaHomeVisitGapReport("Json Request").getBody());
        assertEquals(500, actualInfantsHighRiskReport.getStatusCodeValue());
        assertTrue(actualInfantsHighRiskReport.getHeaders().isEmpty());
    }

    /**
     * Method under test: {@link ReportController#getInfantsHighRiskReport(String)}
     */
    @Test
    void testGetInfantsHighRiskReport4() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange
        ReportController reportController = new ReportController();

        // Act
        ResponseEntity<Object> actualInfantsHighRiskReport = reportController.getInfantsHighRiskReport("42");

        // Assert
        assertEquals("Not a JSON Object: 42", actualInfantsHighRiskReport.getBody());
        assertEquals("com.google.gson.stream.MalformedJsonException: Use JsonReader.setLenient(true) to accept malformed"
                + " JSON at line 1 column 7 path $", reportController.getAashaHomeVisitGapReport("Json Request").getBody());
        assertEquals(500, actualInfantsHighRiskReport.getStatusCodeValue());
        assertTrue(actualInfantsHighRiskReport.getHeaders().isEmpty());
    }

    /**
     * Method under test: {@link ReportController#getInfantsHighRiskReport(String)}
     */
    @Test
    void testGetInfantsHighRiskReport5() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange
        ReportController reportController = new ReportController();

        // Act
        ResponseEntity<Object> actualInfantsHighRiskReport = reportController.getInfantsHighRiskReport("");

        // Assert
        assertEquals("Not a JSON Object: null", actualInfantsHighRiskReport.getBody());
        assertEquals("com.google.gson.stream.MalformedJsonException: Use JsonReader.setLenient(true) to accept malformed"
                + " JSON at line 1 column 7 path $", reportController.getAashaHomeVisitGapReport("Json Request").getBody());
        assertEquals(500, actualInfantsHighRiskReport.getStatusCodeValue());
        assertTrue(actualInfantsHighRiskReport.getHeaders().isEmpty());
    }

    /**
     * Method under test: {@link ReportController#getInfantsHighRiskReport(String)}
     */
    @Test
    void testGetInfantsHighRiskReport6() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange
        ReportController reportController = new ReportController();

        // Act
        ResponseEntity<Object> actualInfantsHighRiskReport = reportController
                .getInfantsHighRiskReport("42ECDInfantsHighRiskReport");

        // Assert
        assertEquals("Not a JSON Object: \"42ECDInfantsHighRiskReport\"", actualInfantsHighRiskReport.getBody());
        assertEquals("com.google.gson.stream.MalformedJsonException: Use JsonReader.setLenient(true) to accept malformed"
                + " JSON at line 1 column 7 path $", reportController.getAashaHomeVisitGapReport("Json Request").getBody());
        assertEquals(500, actualInfantsHighRiskReport.getStatusCodeValue());
        assertTrue(actualInfantsHighRiskReport.getHeaders().isEmpty());
    }

    /**
     * Method under test: {@link ReportController#getMaternalDeathReport(String)}
     */
    @Test
    void testGetMaternalDeathReport() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange
        ReportController reportController = new ReportController();

        // Act
        ResponseEntity<Object> actualMaternalDeathReport = reportController.getMaternalDeathReport("Json Request");

        // Assert
        assertEquals("com.google.gson.stream.MalformedJsonException: Use JsonReader.setLenient(true) to accept malformed"
                + " JSON at line 1 column 7 path $", reportController.getAbortionReport("Json Request").getBody());
        assertEquals("com.google.gson.stream.MalformedJsonException: Use JsonReader.setLenient(true) to accept malformed"
                + " JSON at line 1 column 7 path $", actualMaternalDeathReport.getBody());
        assertEquals(500, actualMaternalDeathReport.getStatusCodeValue());
        assertTrue(actualMaternalDeathReport.getHeaders().isEmpty());
    }

    /**
     * Method under test: {@link ReportController#getMaternalDeathReport(String)}
     */
    @Test
    void testGetMaternalDeathReport2() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange
        ReportController reportController = new ReportController();

        // Act
        ResponseEntity<Object> actualMaternalDeathReport = reportController.getMaternalDeathReport("foo");

        // Assert
        assertEquals("Not a JSON Object: \"foo\"", actualMaternalDeathReport.getBody());
        assertEquals("com.google.gson.stream.MalformedJsonException: Use JsonReader.setLenient(true) to accept malformed"
                + " JSON at line 1 column 7 path $", reportController.getAashaHomeVisitGapReport("Json Request").getBody());
        assertEquals(500, actualMaternalDeathReport.getStatusCodeValue());
        assertTrue(actualMaternalDeathReport.getHeaders().isEmpty());
    }

    /**
     * Method under test: {@link ReportController#getMaternalDeathReport(String)}
     */
    @Test
    void testGetMaternalDeathReport3() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange
        ReportController reportController = new ReportController();

        // Act
        ResponseEntity<Object> actualMaternalDeathReport = reportController
                .getMaternalDeathReport("ECDMaternalDeathReport");

        // Assert
        assertEquals("Not a JSON Object: \"ECDMaternalDeathReport\"", actualMaternalDeathReport.getBody());
        assertEquals("com.google.gson.stream.MalformedJsonException: Use JsonReader.setLenient(true) to accept malformed"
                + " JSON at line 1 column 7 path $", reportController.getAashaHomeVisitGapReport("Json Request").getBody());
        assertEquals(500, actualMaternalDeathReport.getStatusCodeValue());
        assertTrue(actualMaternalDeathReport.getHeaders().isEmpty());
    }

    /**
     * Method under test: {@link ReportController#getMaternalDeathReport(String)}
     */
    @Test
    void testGetMaternalDeathReport4() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange
        ReportController reportController = new ReportController();

        // Act
        ResponseEntity<Object> actualMaternalDeathReport = reportController.getMaternalDeathReport("42");

        // Assert
        assertEquals("Not a JSON Object: 42", actualMaternalDeathReport.getBody());
        assertEquals("com.google.gson.stream.MalformedJsonException: Use JsonReader.setLenient(true) to accept malformed"
                + " JSON at line 1 column 7 path $", reportController.getAashaHomeVisitGapReport("Json Request").getBody());
        assertEquals(500, actualMaternalDeathReport.getStatusCodeValue());
        assertTrue(actualMaternalDeathReport.getHeaders().isEmpty());
    }

    /**
     * Method under test: {@link ReportController#getMaternalDeathReport(String)}
     */
    @Test
    void testGetMaternalDeathReport5() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange
        ReportController reportController = new ReportController();

        // Act
        ResponseEntity<Object> actualMaternalDeathReport = reportController.getMaternalDeathReport("");

        // Assert
        assertEquals("Not a JSON Object: null", actualMaternalDeathReport.getBody());
        assertEquals("com.google.gson.stream.MalformedJsonException: Use JsonReader.setLenient(true) to accept malformed"
                + " JSON at line 1 column 7 path $", reportController.getAashaHomeVisitGapReport("Json Request").getBody());
        assertEquals(500, actualMaternalDeathReport.getStatusCodeValue());
        assertTrue(actualMaternalDeathReport.getHeaders().isEmpty());
    }

    /**
     * Method under test: {@link ReportController#getMaternalDeathReport(String)}
     */
    @Test
    void testGetMaternalDeathReport6() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange
        ReportController reportController = new ReportController();

        // Act
        ResponseEntity<Object> actualMaternalDeathReport = reportController
                .getMaternalDeathReport("42ECDMaternalDeathReport");

        // Assert
        assertEquals("Not a JSON Object: \"42ECDMaternalDeathReport\"", actualMaternalDeathReport.getBody());
        assertEquals("com.google.gson.stream.MalformedJsonException: Use JsonReader.setLenient(true) to accept malformed"
                + " JSON at line 1 column 7 path $", reportController.getAashaHomeVisitGapReport("Json Request").getBody());
        assertEquals(500, actualMaternalDeathReport.getStatusCodeValue());
        assertTrue(actualMaternalDeathReport.getHeaders().isEmpty());
    }

    /**
     * Method under test: {@link ReportController#getStillBirthReport(String)}
     */
    @Test
    void testGetStillBirthReport() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange
        ReportController reportController = new ReportController();

        // Act
        ResponseEntity<Object> actualStillBirthReport = reportController.getStillBirthReport("Json Request");

        // Assert
        assertEquals("com.google.gson.stream.MalformedJsonException: Use JsonReader.setLenient(true) to accept malformed"
                + " JSON at line 1 column 7 path $", reportController.getAbortionReport("Json Request").getBody());
        assertEquals("com.google.gson.stream.MalformedJsonException: Use JsonReader.setLenient(true) to accept malformed"
                + " JSON at line 1 column 7 path $", actualStillBirthReport.getBody());
        assertEquals(500, actualStillBirthReport.getStatusCodeValue());
        assertTrue(actualStillBirthReport.getHeaders().isEmpty());
    }

    /**
     * Method under test: {@link ReportController#getStillBirthReport(String)}
     */
    @Test
    void testGetStillBirthReport2() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange
        ReportController reportController = new ReportController();

        // Act
        ResponseEntity<Object> actualStillBirthReport = reportController.getStillBirthReport("foo");

        // Assert
        assertEquals("Not a JSON Object: \"foo\"", actualStillBirthReport.getBody());
        assertEquals("com.google.gson.stream.MalformedJsonException: Use JsonReader.setLenient(true) to accept malformed"
                + " JSON at line 1 column 7 path $", reportController.getAashaHomeVisitGapReport("Json Request").getBody());
        assertEquals(500, actualStillBirthReport.getStatusCodeValue());
        assertTrue(actualStillBirthReport.getHeaders().isEmpty());
    }

    /**
     * Method under test: {@link ReportController#getStillBirthReport(String)}
     */
    @Test
    void testGetStillBirthReport3() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange
        ReportController reportController = new ReportController();

        // Act
        ResponseEntity<Object> actualStillBirthReport = reportController.getStillBirthReport("ECDStillBirthReport");

        // Assert
        assertEquals("Not a JSON Object: \"ECDStillBirthReport\"", actualStillBirthReport.getBody());
        assertEquals("com.google.gson.stream.MalformedJsonException: Use JsonReader.setLenient(true) to accept malformed"
                + " JSON at line 1 column 7 path $", reportController.getAashaHomeVisitGapReport("Json Request").getBody());
        assertEquals(500, actualStillBirthReport.getStatusCodeValue());
        assertTrue(actualStillBirthReport.getHeaders().isEmpty());
    }

    /**
     * Method under test: {@link ReportController#getStillBirthReport(String)}
     */
    @Test
    void testGetStillBirthReport4() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange
        ReportController reportController = new ReportController();

        // Act
        ResponseEntity<Object> actualStillBirthReport = reportController.getStillBirthReport("42");

        // Assert
        assertEquals("Not a JSON Object: 42", actualStillBirthReport.getBody());
        assertEquals("com.google.gson.stream.MalformedJsonException: Use JsonReader.setLenient(true) to accept malformed"
                + " JSON at line 1 column 7 path $", reportController.getAashaHomeVisitGapReport("Json Request").getBody());
        assertEquals(500, actualStillBirthReport.getStatusCodeValue());
        assertTrue(actualStillBirthReport.getHeaders().isEmpty());
    }

    /**
     * Method under test: {@link ReportController#getStillBirthReport(String)}
     */
    @Test
    void testGetStillBirthReport5() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange
        ReportController reportController = new ReportController();

        // Act
        ResponseEntity<Object> actualStillBirthReport = reportController.getStillBirthReport("");

        // Assert
        assertEquals("Not a JSON Object: null", actualStillBirthReport.getBody());
        assertEquals("com.google.gson.stream.MalformedJsonException: Use JsonReader.setLenient(true) to accept malformed"
                + " JSON at line 1 column 7 path $", reportController.getAashaHomeVisitGapReport("Json Request").getBody());
        assertEquals(500, actualStillBirthReport.getStatusCodeValue());
        assertTrue(actualStillBirthReport.getHeaders().isEmpty());
    }

    /**
     * Method under test: {@link ReportController#getStillBirthReport(String)}
     */
    @Test
    void testGetStillBirthReport6() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange
        ReportController reportController = new ReportController();

        // Act
        ResponseEntity<Object> actualStillBirthReport = reportController.getStillBirthReport("42ECDStillBirthReport");

        // Assert
        assertEquals("Not a JSON Object: \"42ECDStillBirthReport\"", actualStillBirthReport.getBody());
        assertEquals("com.google.gson.stream.MalformedJsonException: Use JsonReader.setLenient(true) to accept malformed"
                + " JSON at line 1 column 7 path $", reportController.getAashaHomeVisitGapReport("Json Request").getBody());
        assertEquals(500, actualStillBirthReport.getStatusCodeValue());
        assertTrue(actualStillBirthReport.getHeaders().isEmpty());
    }

    /**
     * Method under test: {@link ReportController#getBabyDeathReport(String)}
     */
    @Test
    void testGetBabyDeathReport() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange
        ReportController reportController = new ReportController();

        // Act
        ResponseEntity<Object> actualBabyDeathReport = reportController.getBabyDeathReport("Json Request");

        // Assert
        assertEquals("com.google.gson.stream.MalformedJsonException: Use JsonReader.setLenient(true) to accept malformed"
                + " JSON at line 1 column 7 path $", reportController.getAbortionReport("Json Request").getBody());
        assertEquals("com.google.gson.stream.MalformedJsonException: Use JsonReader.setLenient(true) to accept malformed"
                + " JSON at line 1 column 7 path $", actualBabyDeathReport.getBody());
        assertEquals(500, actualBabyDeathReport.getStatusCodeValue());
        assertTrue(actualBabyDeathReport.getHeaders().isEmpty());
    }

    /**
     * Method under test: {@link ReportController#getBabyDeathReport(String)}
     */
    @Test
    void testGetBabyDeathReport2() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange
        ReportController reportController = new ReportController();

        // Act
        ResponseEntity<Object> actualBabyDeathReport = reportController.getBabyDeathReport("foo");

        // Assert
        assertEquals("Not a JSON Object: \"foo\"", actualBabyDeathReport.getBody());
        assertEquals("com.google.gson.stream.MalformedJsonException: Use JsonReader.setLenient(true) to accept malformed"
                + " JSON at line 1 column 7 path $", reportController.getAashaHomeVisitGapReport("Json Request").getBody());
        assertEquals(500, actualBabyDeathReport.getStatusCodeValue());
        assertTrue(actualBabyDeathReport.getHeaders().isEmpty());
    }

    /**
     * Method under test: {@link ReportController#getBabyDeathReport(String)}
     */
    @Test
    void testGetBabyDeathReport3() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange
        ReportController reportController = new ReportController();

        // Act
        ResponseEntity<Object> actualBabyDeathReport = reportController.getBabyDeathReport("ECDBabyDeathReport");

        // Assert
        assertEquals("Not a JSON Object: \"ECDBabyDeathReport\"", actualBabyDeathReport.getBody());
        assertEquals("com.google.gson.stream.MalformedJsonException: Use JsonReader.setLenient(true) to accept malformed"
                + " JSON at line 1 column 7 path $", reportController.getAashaHomeVisitGapReport("Json Request").getBody());
        assertEquals(500, actualBabyDeathReport.getStatusCodeValue());
        assertTrue(actualBabyDeathReport.getHeaders().isEmpty());
    }

    /**
     * Method under test: {@link ReportController#getBabyDeathReport(String)}
     */
    @Test
    void testGetBabyDeathReport4() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange
        ReportController reportController = new ReportController();

        // Act
        ResponseEntity<Object> actualBabyDeathReport = reportController.getBabyDeathReport("42");

        // Assert
        assertEquals("Not a JSON Object: 42", actualBabyDeathReport.getBody());
        assertEquals("com.google.gson.stream.MalformedJsonException: Use JsonReader.setLenient(true) to accept malformed"
                + " JSON at line 1 column 7 path $", reportController.getAashaHomeVisitGapReport("Json Request").getBody());
        assertEquals(500, actualBabyDeathReport.getStatusCodeValue());
        assertTrue(actualBabyDeathReport.getHeaders().isEmpty());
    }

    /**
     * Method under test: {@link ReportController#getBabyDeathReport(String)}
     */
    @Test
    void testGetBabyDeathReport5() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange
        ReportController reportController = new ReportController();

        // Act
        ResponseEntity<Object> actualBabyDeathReport = reportController.getBabyDeathReport("");

        // Assert
        assertEquals("Not a JSON Object: null", actualBabyDeathReport.getBody());
        assertEquals("com.google.gson.stream.MalformedJsonException: Use JsonReader.setLenient(true) to accept malformed"
                + " JSON at line 1 column 7 path $", reportController.getAashaHomeVisitGapReport("Json Request").getBody());
        assertEquals(500, actualBabyDeathReport.getStatusCodeValue());
        assertTrue(actualBabyDeathReport.getHeaders().isEmpty());
    }

    /**
     * Method under test: {@link ReportController#getBabyDeathReport(String)}
     */
    @Test
    void testGetBabyDeathReport6() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange
        ReportController reportController = new ReportController();

        // Act
        ResponseEntity<Object> actualBabyDeathReport = reportController.getBabyDeathReport("42ECDBabyDeathReport");

        // Assert
        assertEquals("Not a JSON Object: \"42ECDBabyDeathReport\"", actualBabyDeathReport.getBody());
        assertEquals("com.google.gson.stream.MalformedJsonException: Use JsonReader.setLenient(true) to accept malformed"
                + " JSON at line 1 column 7 path $", reportController.getAashaHomeVisitGapReport("Json Request").getBody());
        assertEquals(500, actualBabyDeathReport.getStatusCodeValue());
        assertTrue(actualBabyDeathReport.getHeaders().isEmpty());
    }

    /**
     * Method under test:
     * {@link ReportController#getNotConnectedPhonelistDiffformatReport(String)}
     */
    @Test
    void testGetNotConnectedPhonelistDiffformatReport() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange
        ReportController reportController = new ReportController();

        // Act
        ResponseEntity<Object> actualNotConnectedPhonelistDiffformatReport = reportController
                .getNotConnectedPhonelistDiffformatReport("Json Request");

        // Assert
        assertEquals("com.google.gson.stream.MalformedJsonException: Use JsonReader.setLenient(true) to accept malformed"
                + " JSON at line 1 column 7 path $", reportController.getAbortionReport("Json Request").getBody());
        assertEquals("com.google.gson.stream.MalformedJsonException: Use JsonReader.setLenient(true) to accept malformed"
                + " JSON at line 1 column 7 path $", actualNotConnectedPhonelistDiffformatReport.getBody());
        assertEquals(500, actualNotConnectedPhonelistDiffformatReport.getStatusCodeValue());
        assertTrue(actualNotConnectedPhonelistDiffformatReport.getHeaders().isEmpty());
    }

    /**
     * Method under test:
     * {@link ReportController#getNotConnectedPhonelistDiffformatReport(String)}
     */
    @Test
    void testGetNotConnectedPhonelistDiffformatReport2() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange
        ReportController reportController = new ReportController();

        // Act
        ResponseEntity<Object> actualNotConnectedPhonelistDiffformatReport = reportController
                .getNotConnectedPhonelistDiffformatReport("foo");

        // Assert
        assertEquals("Not a JSON Object: \"foo\"", actualNotConnectedPhonelistDiffformatReport.getBody());
        assertEquals("com.google.gson.stream.MalformedJsonException: Use JsonReader.setLenient(true) to accept malformed"
                + " JSON at line 1 column 7 path $", reportController.getAashaHomeVisitGapReport("Json Request").getBody());
        assertEquals(500, actualNotConnectedPhonelistDiffformatReport.getStatusCodeValue());
        assertTrue(actualNotConnectedPhonelistDiffformatReport.getHeaders().isEmpty());
    }

    /**
     * Method under test:
     * {@link ReportController#getNotConnectedPhonelistDiffformatReport(String)}
     */
    @Test
    void testGetNotConnectedPhonelistDiffformatReport3() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange
        ReportController reportController = new ReportController();

        // Act
        ResponseEntity<Object> actualNotConnectedPhonelistDiffformatReport = reportController
                .getNotConnectedPhonelistDiffformatReport("ECDNotConnectedPhonelistDiffformatReport");

        // Assert
        assertEquals("Not a JSON Object: \"ECDNotConnectedPhonelistDiffformatReport\"",
                actualNotConnectedPhonelistDiffformatReport.getBody());
        assertEquals("com.google.gson.stream.MalformedJsonException: Use JsonReader.setLenient(true) to accept malformed"
                + " JSON at line 1 column 7 path $", reportController.getAashaHomeVisitGapReport("Json Request").getBody());
        assertEquals(500, actualNotConnectedPhonelistDiffformatReport.getStatusCodeValue());
        assertTrue(actualNotConnectedPhonelistDiffformatReport.getHeaders().isEmpty());
    }

    /**
     * Method under test:
     * {@link ReportController#getNotConnectedPhonelistDiffformatReport(String)}
     */
    @Test
    void testGetNotConnectedPhonelistDiffformatReport4() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange
        ReportController reportController = new ReportController();

        // Act
        ResponseEntity<Object> actualNotConnectedPhonelistDiffformatReport = reportController
                .getNotConnectedPhonelistDiffformatReport("42");

        // Assert
        assertEquals("Not a JSON Object: 42", actualNotConnectedPhonelistDiffformatReport.getBody());
        assertEquals("com.google.gson.stream.MalformedJsonException: Use JsonReader.setLenient(true) to accept malformed"
                + " JSON at line 1 column 7 path $", reportController.getAashaHomeVisitGapReport("Json Request").getBody());
        assertEquals(500, actualNotConnectedPhonelistDiffformatReport.getStatusCodeValue());
        assertTrue(actualNotConnectedPhonelistDiffformatReport.getHeaders().isEmpty());
    }

    /**
     * Method under test:
     * {@link ReportController#getNotConnectedPhonelistDiffformatReport(String)}
     */
    @Test
    void testGetNotConnectedPhonelistDiffformatReport5() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange
        ReportController reportController = new ReportController();

        // Act
        ResponseEntity<Object> actualNotConnectedPhonelistDiffformatReport = reportController
                .getNotConnectedPhonelistDiffformatReport("");

        // Assert
        assertEquals("Not a JSON Object: null", actualNotConnectedPhonelistDiffformatReport.getBody());
        assertEquals("com.google.gson.stream.MalformedJsonException: Use JsonReader.setLenient(true) to accept malformed"
                + " JSON at line 1 column 7 path $", reportController.getAashaHomeVisitGapReport("Json Request").getBody());
        assertEquals(500, actualNotConnectedPhonelistDiffformatReport.getStatusCodeValue());
        assertTrue(actualNotConnectedPhonelistDiffformatReport.getHeaders().isEmpty());
    }

    /**
     * Method under test:
     * {@link ReportController#getNotConnectedPhonelistDiffformatReport(String)}
     */
    @Test
    void testGetNotConnectedPhonelistDiffformatReport6() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange
        ReportController reportController = new ReportController();

        // Act
        ResponseEntity<Object> actualNotConnectedPhonelistDiffformatReport = reportController
                .getNotConnectedPhonelistDiffformatReport("42ECDNotConnectedPhonelistDiffformatReport");

        // Assert
        assertEquals("Not a JSON Object: \"42ECDNotConnectedPhonelistDiffformatReport\"",
                actualNotConnectedPhonelistDiffformatReport.getBody());
        assertEquals("com.google.gson.stream.MalformedJsonException: Use JsonReader.setLenient(true) to accept malformed"
                + " JSON at line 1 column 7 path $", reportController.getAashaHomeVisitGapReport("Json Request").getBody());
        assertEquals(500, actualNotConnectedPhonelistDiffformatReport.getStatusCodeValue());
        assertTrue(actualNotConnectedPhonelistDiffformatReport.getHeaders().isEmpty());
    }

    /**
     * Method under test:
     * {@link ReportController#getJSYRelatedComplaintsReport(String)}
     */
    @Test
    void testGetJSYRelatedComplaintsReport() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange
        ReportController reportController = new ReportController();

        // Act
        ResponseEntity<Object> actualJSYRelatedComplaintsReport = reportController
                .getJSYRelatedComplaintsReport("Json Request");

        // Assert
        assertEquals("com.google.gson.stream.MalformedJsonException: Use JsonReader.setLenient(true) to accept malformed"
                + " JSON at line 1 column 7 path $", reportController.getAbortionReport("Json Request").getBody());
        assertEquals("com.google.gson.stream.MalformedJsonException: Use JsonReader.setLenient(true) to accept malformed"
                + " JSON at line 1 column 7 path $", actualJSYRelatedComplaintsReport.getBody());
        assertEquals(500, actualJSYRelatedComplaintsReport.getStatusCodeValue());
        assertTrue(actualJSYRelatedComplaintsReport.getHeaders().isEmpty());
    }

    /**
     * Method under test:
     * {@link ReportController#getJSYRelatedComplaintsReport(String)}
     */
    @Test
    void testGetJSYRelatedComplaintsReport2() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange
        ReportController reportController = new ReportController();

        // Act
        ResponseEntity<Object> actualJSYRelatedComplaintsReport = reportController.getJSYRelatedComplaintsReport("foo");

        // Assert
        assertEquals("Not a JSON Object: \"foo\"", actualJSYRelatedComplaintsReport.getBody());
        assertEquals("com.google.gson.stream.MalformedJsonException: Use JsonReader.setLenient(true) to accept malformed"
                + " JSON at line 1 column 7 path $", reportController.getAashaHomeVisitGapReport("Json Request").getBody());
        assertEquals(500, actualJSYRelatedComplaintsReport.getStatusCodeValue());
        assertTrue(actualJSYRelatedComplaintsReport.getHeaders().isEmpty());
    }

    /**
     * Method under test:
     * {@link ReportController#getJSYRelatedComplaintsReport(String)}
     */
    @Test
    void testGetJSYRelatedComplaintsReport3() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange
        ReportController reportController = new ReportController();

        // Act
        ResponseEntity<Object> actualJSYRelatedComplaintsReport = reportController
                .getJSYRelatedComplaintsReport("ECDJSYRelatedComplaintsReport");

        // Assert
        assertEquals("Not a JSON Object: \"ECDJSYRelatedComplaintsReport\"", actualJSYRelatedComplaintsReport.getBody());
        assertEquals("com.google.gson.stream.MalformedJsonException: Use JsonReader.setLenient(true) to accept malformed"
                + " JSON at line 1 column 7 path $", reportController.getAashaHomeVisitGapReport("Json Request").getBody());
        assertEquals(500, actualJSYRelatedComplaintsReport.getStatusCodeValue());
        assertTrue(actualJSYRelatedComplaintsReport.getHeaders().isEmpty());
    }

    /**
     * Method under test:
     * {@link ReportController#getJSYRelatedComplaintsReport(String)}
     */
    @Test
    void testGetJSYRelatedComplaintsReport4() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange
        ReportController reportController = new ReportController();

        // Act
        ResponseEntity<Object> actualJSYRelatedComplaintsReport = reportController.getJSYRelatedComplaintsReport("42");

        // Assert
        assertEquals("Not a JSON Object: 42", actualJSYRelatedComplaintsReport.getBody());
        assertEquals("com.google.gson.stream.MalformedJsonException: Use JsonReader.setLenient(true) to accept malformed"
                + " JSON at line 1 column 7 path $", reportController.getAashaHomeVisitGapReport("Json Request").getBody());
        assertEquals(500, actualJSYRelatedComplaintsReport.getStatusCodeValue());
        assertTrue(actualJSYRelatedComplaintsReport.getHeaders().isEmpty());
    }

    /**
     * Method under test:
     * {@link ReportController#getJSYRelatedComplaintsReport(String)}
     */
    @Test
    void testGetJSYRelatedComplaintsReport5() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange
        ReportController reportController = new ReportController();

        // Act
        ResponseEntity<Object> actualJSYRelatedComplaintsReport = reportController.getJSYRelatedComplaintsReport("");

        // Assert
        assertEquals("Not a JSON Object: null", actualJSYRelatedComplaintsReport.getBody());
        assertEquals("com.google.gson.stream.MalformedJsonException: Use JsonReader.setLenient(true) to accept malformed"
                + " JSON at line 1 column 7 path $", reportController.getAashaHomeVisitGapReport("Json Request").getBody());
        assertEquals(500, actualJSYRelatedComplaintsReport.getStatusCodeValue());
        assertTrue(actualJSYRelatedComplaintsReport.getHeaders().isEmpty());
    }

    /**
     * Method under test:
     * {@link ReportController#getJSYRelatedComplaintsReport(String)}
     */
    @Test
    void testGetJSYRelatedComplaintsReport6() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange
        ReportController reportController = new ReportController();

        // Act
        ResponseEntity<Object> actualJSYRelatedComplaintsReport = reportController
                .getJSYRelatedComplaintsReport("42ECDJSYRelatedComplaintsReport");

        // Assert
        assertEquals("Not a JSON Object: \"42ECDJSYRelatedComplaintsReport\"", actualJSYRelatedComplaintsReport.getBody());
        assertEquals("com.google.gson.stream.MalformedJsonException: Use JsonReader.setLenient(true) to accept malformed"
                + " JSON at line 1 column 7 path $", reportController.getAashaHomeVisitGapReport("Json Request").getBody());
        assertEquals(500, actualJSYRelatedComplaintsReport.getStatusCodeValue());
        assertTrue(actualJSYRelatedComplaintsReport.getHeaders().isEmpty());
    }

    /**
     * Method under test: {@link ReportController#getMiscarriageReport(String)}
     */
    @Test
    void testGetMiscarriageReport() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange
        ReportController reportController = new ReportController();

        // Act
        ResponseEntity<Object> actualMiscarriageReport = reportController.getMiscarriageReport("Json Request");

        // Assert
        assertEquals("com.google.gson.stream.MalformedJsonException: Use JsonReader.setLenient(true) to accept malformed"
                + " JSON at line 1 column 7 path $", reportController.getAbortionReport("Json Request").getBody());
        assertEquals("com.google.gson.stream.MalformedJsonException: Use JsonReader.setLenient(true) to accept malformed"
                + " JSON at line 1 column 7 path $", actualMiscarriageReport.getBody());
        assertEquals(500, actualMiscarriageReport.getStatusCodeValue());
        assertTrue(actualMiscarriageReport.getHeaders().isEmpty());
    }

    /**
     * Method under test: {@link ReportController#getMiscarriageReport(String)}
     */
    @Test
    void testGetMiscarriageReport2() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange
        ReportController reportController = new ReportController();

        // Act
        ResponseEntity<Object> actualMiscarriageReport = reportController.getMiscarriageReport("foo");

        // Assert
        assertEquals("Not a JSON Object: \"foo\"", actualMiscarriageReport.getBody());
        assertEquals("com.google.gson.stream.MalformedJsonException: Use JsonReader.setLenient(true) to accept malformed"
                + " JSON at line 1 column 7 path $", reportController.getAashaHomeVisitGapReport("Json Request").getBody());
        assertEquals(500, actualMiscarriageReport.getStatusCodeValue());
        assertTrue(actualMiscarriageReport.getHeaders().isEmpty());
    }

    /**
     * Method under test: {@link ReportController#getMiscarriageReport(String)}
     */
    @Test
    void testGetMiscarriageReport3() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange
        ReportController reportController = new ReportController();

        // Act
        ResponseEntity<Object> actualMiscarriageReport = reportController.getMiscarriageReport("ECDMiscarriageReport");

        // Assert
        assertEquals("Not a JSON Object: \"ECDMiscarriageReport\"", actualMiscarriageReport.getBody());
        assertEquals("com.google.gson.stream.MalformedJsonException: Use JsonReader.setLenient(true) to accept malformed"
                + " JSON at line 1 column 7 path $", reportController.getAashaHomeVisitGapReport("Json Request").getBody());
        assertEquals(500, actualMiscarriageReport.getStatusCodeValue());
        assertTrue(actualMiscarriageReport.getHeaders().isEmpty());
    }

    /**
     * Method under test: {@link ReportController#getMiscarriageReport(String)}
     */
    @Test
    void testGetMiscarriageReport4() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange
        ReportController reportController = new ReportController();

        // Act
        ResponseEntity<Object> actualMiscarriageReport = reportController.getMiscarriageReport("42");

        // Assert
        assertEquals("Not a JSON Object: 42", actualMiscarriageReport.getBody());
        assertEquals("com.google.gson.stream.MalformedJsonException: Use JsonReader.setLenient(true) to accept malformed"
                + " JSON at line 1 column 7 path $", reportController.getAashaHomeVisitGapReport("Json Request").getBody());
        assertEquals(500, actualMiscarriageReport.getStatusCodeValue());
        assertTrue(actualMiscarriageReport.getHeaders().isEmpty());
    }

    /**
     * Method under test: {@link ReportController#getMiscarriageReport(String)}
     */
    @Test
    void testGetMiscarriageReport5() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange
        ReportController reportController = new ReportController();

        // Act
        ResponseEntity<Object> actualMiscarriageReport = reportController.getMiscarriageReport("");

        // Assert
        assertEquals("Not a JSON Object: null", actualMiscarriageReport.getBody());
        assertEquals("com.google.gson.stream.MalformedJsonException: Use JsonReader.setLenient(true) to accept malformed"
                + " JSON at line 1 column 7 path $", reportController.getAashaHomeVisitGapReport("Json Request").getBody());
        assertEquals(500, actualMiscarriageReport.getStatusCodeValue());
        assertTrue(actualMiscarriageReport.getHeaders().isEmpty());
    }

    /**
     * Method under test: {@link ReportController#getMiscarriageReport(String)}
     */
    @Test
    void testGetMiscarriageReport6() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange
        ReportController reportController = new ReportController();

        // Act
        ResponseEntity<Object> actualMiscarriageReport = reportController.getMiscarriageReport("42ECDMiscarriageReport");

        // Assert
        assertEquals("Not a JSON Object: \"42ECDMiscarriageReport\"", actualMiscarriageReport.getBody());
        assertEquals("com.google.gson.stream.MalformedJsonException: Use JsonReader.setLenient(true) to accept malformed"
                + " JSON at line 1 column 7 path $", reportController.getAashaHomeVisitGapReport("Json Request").getBody());
        assertEquals(500, actualMiscarriageReport.getStatusCodeValue());
        assertTrue(actualMiscarriageReport.getHeaders().isEmpty());
    }

    /**
     * Method under test: {@link ReportController#getFileName(String, String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGetFileName() {
        // TODO: Diffblue Cover was only able to create a partial test for this method:
        //   Reason: No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   com.google.gson.JsonSyntaxException: com.google.gson.stream.MalformedJsonException: Use JsonReader.setLenient(true) to accept malformed JSON at line 1 column 7 path $
        //       at com.google.gson.JsonParser.parseReader(JsonParser.java:76)
        //       at com.google.gson.JsonParser.parseString(JsonParser.java:51)
        //       at com.google.gson.JsonParser.parse(JsonParser.java:115)
        //       at com.iemr.ecd.controller.reports.ReportController.getFileName(ReportController.java:618)
        //   com.google.gson.stream.MalformedJsonException: Use JsonReader.setLenient(true) to accept malformed JSON at line 1 column 7 path $
        //       at com.google.gson.stream.JsonReader.syntaxError(JsonReader.java:1659)
        //       at com.google.gson.stream.JsonReader.checkLenient(JsonReader.java:1465)
        //       at com.google.gson.stream.JsonReader.doPeek(JsonReader.java:551)
        //       at com.google.gson.stream.JsonReader.peek(JsonReader.java:433)
        //       at com.google.gson.JsonParser.parseReader(JsonParser.java:71)
        //       at com.google.gson.JsonParser.parseString(JsonParser.java:51)
        //       at com.google.gson.JsonParser.parse(JsonParser.java:115)
        //       at com.iemr.ecd.controller.reports.ReportController.getFileName(ReportController.java:618)
        //   See https://diff.blue/R013 to resolve this issue.

        // Arrange and Act
        reportController.getFileName("Json Request", "Name");
    }
}

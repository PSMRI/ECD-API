package com.iemr.ecd.service.report;

import com.iemr.ecd.repository.report.EcdReportRepo;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {ReportServiceImpl.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class ReportServiceImplDiffblueTest {
    @MockBean
    private EcdReportRepo ecdReportRepo;

    @Autowired
    private ReportServiceImpl reportServiceImpl;

    /**
     * Method under test:
     * {@link ReportServiceImpl#getCallDetailsReport(String, String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGetCallDetailsReport() throws Exception {
        // TODO: Diffblue Cover was only able to create a partial test for this method:
        //   Reason: No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   com.google.gson.JsonSyntaxException: java.lang.IllegalStateException: Expected BEGIN_OBJECT but was STRING at line 1 column 1 path $
        //       at com.google.gson.internal.bind.ReflectiveTypeAdapterFactory$Adapter.read(ReflectiveTypeAdapterFactory.java:397)
        //       at com.google.gson.Gson.fromJson(Gson.java:1227)
        //       at com.google.gson.Gson.fromJson(Gson.java:1137)
        //       at com.google.gson.Gson.fromJson(Gson.java:1047)
        //       at com.google.gson.Gson.fromJson(Gson.java:982)
        //       at com.iemr.ecd.utils.mapper.InputMapper.fromJson(InputMapper.java:78)
        //       at com.iemr.ecd.service.report.ReportServiceImpl.getCallDetailsReport(ReportServiceImpl.java:58)
        //   java.lang.IllegalStateException: Expected BEGIN_OBJECT but was STRING at line 1 column 1 path $
        //       at com.google.gson.stream.JsonReader.beginObject(JsonReader.java:393)
        //       at com.google.gson.internal.bind.ReflectiveTypeAdapterFactory$Adapter.read(ReflectiveTypeAdapterFactory.java:386)
        //       at com.google.gson.Gson.fromJson(Gson.java:1227)
        //       at com.google.gson.Gson.fromJson(Gson.java:1137)
        //       at com.google.gson.Gson.fromJson(Gson.java:1047)
        //       at com.google.gson.Gson.fromJson(Gson.java:982)
        //       at com.iemr.ecd.utils.mapper.InputMapper.fromJson(InputMapper.java:78)
        //       at com.iemr.ecd.service.report.ReportServiceImpl.getCallDetailsReport(ReportServiceImpl.java:58)
        //   See https://diff.blue/R013 to resolve this issue.

        // Arrange and Act
        reportServiceImpl.getCallDetailsReport("Request", "foo.txt");
    }

    /**
     * Method under test:
     * {@link ReportServiceImpl#getCallSummaryReport(String, String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGetCallSummaryReport() throws Exception {
        // TODO: Diffblue Cover was only able to create a partial test for this method:
        //   Reason: No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   com.google.gson.JsonSyntaxException: java.lang.IllegalStateException: Expected BEGIN_OBJECT but was STRING at line 1 column 1 path $
        //       at com.google.gson.internal.bind.ReflectiveTypeAdapterFactory$Adapter.read(ReflectiveTypeAdapterFactory.java:397)
        //       at com.google.gson.Gson.fromJson(Gson.java:1227)
        //       at com.google.gson.Gson.fromJson(Gson.java:1137)
        //       at com.google.gson.Gson.fromJson(Gson.java:1047)
        //       at com.google.gson.Gson.fromJson(Gson.java:982)
        //       at com.iemr.ecd.utils.mapper.InputMapper.fromJson(InputMapper.java:78)
        //       at com.iemr.ecd.service.report.ReportServiceImpl.getCallSummaryReport(ReportServiceImpl.java:99)
        //   java.lang.IllegalStateException: Expected BEGIN_OBJECT but was STRING at line 1 column 1 path $
        //       at com.google.gson.stream.JsonReader.beginObject(JsonReader.java:393)
        //       at com.google.gson.internal.bind.ReflectiveTypeAdapterFactory$Adapter.read(ReflectiveTypeAdapterFactory.java:386)
        //       at com.google.gson.Gson.fromJson(Gson.java:1227)
        //       at com.google.gson.Gson.fromJson(Gson.java:1137)
        //       at com.google.gson.Gson.fromJson(Gson.java:1047)
        //       at com.google.gson.Gson.fromJson(Gson.java:982)
        //       at com.iemr.ecd.utils.mapper.InputMapper.fromJson(InputMapper.java:78)
        //       at com.iemr.ecd.service.report.ReportServiceImpl.getCallSummaryReport(ReportServiceImpl.java:99)
        //   See https://diff.blue/R013 to resolve this issue.

        // Arrange and Act
        reportServiceImpl.getCallSummaryReport("Request", "foo.txt");
    }

    /**
     * Method under test:
     * {@link ReportServiceImpl#getCumulativeDistrictReport(String, String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGetCumulativeDistrictReport() throws Exception {
        // TODO: Diffblue Cover was only able to create a partial test for this method:
        //   Reason: No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   com.google.gson.JsonSyntaxException: java.lang.IllegalStateException: Expected BEGIN_OBJECT but was STRING at line 1 column 1 path $
        //       at com.google.gson.internal.bind.ReflectiveTypeAdapterFactory$Adapter.read(ReflectiveTypeAdapterFactory.java:397)
        //       at com.google.gson.Gson.fromJson(Gson.java:1227)
        //       at com.google.gson.Gson.fromJson(Gson.java:1137)
        //       at com.google.gson.Gson.fromJson(Gson.java:1047)
        //       at com.google.gson.Gson.fromJson(Gson.java:982)
        //       at com.iemr.ecd.utils.mapper.InputMapper.fromJson(InputMapper.java:78)
        //       at com.iemr.ecd.service.report.ReportServiceImpl.getCumulativeDistrictReport(ReportServiceImpl.java:139)
        //   java.lang.IllegalStateException: Expected BEGIN_OBJECT but was STRING at line 1 column 1 path $
        //       at com.google.gson.stream.JsonReader.beginObject(JsonReader.java:393)
        //       at com.google.gson.internal.bind.ReflectiveTypeAdapterFactory$Adapter.read(ReflectiveTypeAdapterFactory.java:386)
        //       at com.google.gson.Gson.fromJson(Gson.java:1227)
        //       at com.google.gson.Gson.fromJson(Gson.java:1137)
        //       at com.google.gson.Gson.fromJson(Gson.java:1047)
        //       at com.google.gson.Gson.fromJson(Gson.java:982)
        //       at com.iemr.ecd.utils.mapper.InputMapper.fromJson(InputMapper.java:78)
        //       at com.iemr.ecd.service.report.ReportServiceImpl.getCumulativeDistrictReport(ReportServiceImpl.java:139)
        //   See https://diff.blue/R013 to resolve this issue.

        // Arrange and Act
        reportServiceImpl.getCumulativeDistrictReport("Request", "foo.txt");
    }

    /**
     * Method under test:
     * {@link ReportServiceImpl#getBeneficiarywisefollowupdetails(String, String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGetBeneficiarywisefollowupdetails() throws Exception {
        // TODO: Diffblue Cover was only able to create a partial test for this method:
        //   Reason: No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   com.google.gson.JsonSyntaxException: java.lang.IllegalStateException: Expected BEGIN_OBJECT but was STRING at line 1 column 1 path $
        //       at com.google.gson.internal.bind.ReflectiveTypeAdapterFactory$Adapter.read(ReflectiveTypeAdapterFactory.java:397)
        //       at com.google.gson.Gson.fromJson(Gson.java:1227)
        //       at com.google.gson.Gson.fromJson(Gson.java:1137)
        //       at com.google.gson.Gson.fromJson(Gson.java:1047)
        //       at com.google.gson.Gson.fromJson(Gson.java:982)
        //       at com.iemr.ecd.utils.mapper.InputMapper.fromJson(InputMapper.java:78)
        //       at com.iemr.ecd.service.report.ReportServiceImpl.getBeneficiarywisefollowupdetails(ReportServiceImpl.java:183)
        //   java.lang.IllegalStateException: Expected BEGIN_OBJECT but was STRING at line 1 column 1 path $
        //       at com.google.gson.stream.JsonReader.beginObject(JsonReader.java:393)
        //       at com.google.gson.internal.bind.ReflectiveTypeAdapterFactory$Adapter.read(ReflectiveTypeAdapterFactory.java:386)
        //       at com.google.gson.Gson.fromJson(Gson.java:1227)
        //       at com.google.gson.Gson.fromJson(Gson.java:1137)
        //       at com.google.gson.Gson.fromJson(Gson.java:1047)
        //       at com.google.gson.Gson.fromJson(Gson.java:982)
        //       at com.iemr.ecd.utils.mapper.InputMapper.fromJson(InputMapper.java:78)
        //       at com.iemr.ecd.service.report.ReportServiceImpl.getBeneficiarywisefollowupdetails(ReportServiceImpl.java:183)
        //   See https://diff.blue/R013 to resolve this issue.

        // Arrange and Act
        reportServiceImpl.getBeneficiarywisefollowupdetails("Request", "foo.txt");
    }

    /**
     * Method under test:
     * {@link ReportServiceImpl#getCallDetailReportUnique(String, String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGetCallDetailReportUnique() throws Exception {
        // TODO: Diffblue Cover was only able to create a partial test for this method:
        //   Reason: No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   com.google.gson.JsonSyntaxException: java.lang.IllegalStateException: Expected BEGIN_OBJECT but was STRING at line 1 column 1 path $
        //       at com.google.gson.internal.bind.ReflectiveTypeAdapterFactory$Adapter.read(ReflectiveTypeAdapterFactory.java:397)
        //       at com.google.gson.Gson.fromJson(Gson.java:1227)
        //       at com.google.gson.Gson.fromJson(Gson.java:1137)
        //       at com.google.gson.Gson.fromJson(Gson.java:1047)
        //       at com.google.gson.Gson.fromJson(Gson.java:982)
        //       at com.iemr.ecd.utils.mapper.InputMapper.fromJson(InputMapper.java:78)
        //       at com.iemr.ecd.service.report.ReportServiceImpl.getCallDetailReportUnique(ReportServiceImpl.java:224)
        //   java.lang.IllegalStateException: Expected BEGIN_OBJECT but was STRING at line 1 column 1 path $
        //       at com.google.gson.stream.JsonReader.beginObject(JsonReader.java:393)
        //       at com.google.gson.internal.bind.ReflectiveTypeAdapterFactory$Adapter.read(ReflectiveTypeAdapterFactory.java:386)
        //       at com.google.gson.Gson.fromJson(Gson.java:1227)
        //       at com.google.gson.Gson.fromJson(Gson.java:1137)
        //       at com.google.gson.Gson.fromJson(Gson.java:1047)
        //       at com.google.gson.Gson.fromJson(Gson.java:982)
        //       at com.iemr.ecd.utils.mapper.InputMapper.fromJson(InputMapper.java:78)
        //       at com.iemr.ecd.service.report.ReportServiceImpl.getCallDetailReportUnique(ReportServiceImpl.java:224)
        //   See https://diff.blue/R013 to resolve this issue.

        // Arrange and Act
        reportServiceImpl.getCallDetailReportUnique("Request", "foo.txt");
    }

    /**
     * Method under test:
     * {@link ReportServiceImpl#getBirthDefectReport(String, String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGetBirthDefectReport() throws Exception {
        // TODO: Diffblue Cover was only able to create a partial test for this method:
        //   Reason: No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   com.google.gson.JsonSyntaxException: java.lang.IllegalStateException: Expected BEGIN_OBJECT but was STRING at line 1 column 1 path $
        //       at com.google.gson.internal.bind.ReflectiveTypeAdapterFactory$Adapter.read(ReflectiveTypeAdapterFactory.java:397)
        //       at com.google.gson.Gson.fromJson(Gson.java:1227)
        //       at com.google.gson.Gson.fromJson(Gson.java:1137)
        //       at com.google.gson.Gson.fromJson(Gson.java:1047)
        //       at com.google.gson.Gson.fromJson(Gson.java:982)
        //       at com.iemr.ecd.utils.mapper.InputMapper.fromJson(InputMapper.java:78)
        //       at com.iemr.ecd.service.report.ReportServiceImpl.getBirthDefectReport(ReportServiceImpl.java:266)
        //   java.lang.IllegalStateException: Expected BEGIN_OBJECT but was STRING at line 1 column 1 path $
        //       at com.google.gson.stream.JsonReader.beginObject(JsonReader.java:393)
        //       at com.google.gson.internal.bind.ReflectiveTypeAdapterFactory$Adapter.read(ReflectiveTypeAdapterFactory.java:386)
        //       at com.google.gson.Gson.fromJson(Gson.java:1227)
        //       at com.google.gson.Gson.fromJson(Gson.java:1137)
        //       at com.google.gson.Gson.fromJson(Gson.java:1047)
        //       at com.google.gson.Gson.fromJson(Gson.java:982)
        //       at com.iemr.ecd.utils.mapper.InputMapper.fromJson(InputMapper.java:78)
        //       at com.iemr.ecd.service.report.ReportServiceImpl.getBirthDefectReport(ReportServiceImpl.java:266)
        //   See https://diff.blue/R013 to resolve this issue.

        // Arrange and Act
        reportServiceImpl.getBirthDefectReport("Request", "foo.txt");
    }

    /**
     * Method under test:
     * {@link ReportServiceImpl#getAashaHomeVisitGapReport(String, String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGetAashaHomeVisitGapReport() throws Exception {
        // TODO: Diffblue Cover was only able to create a partial test for this method:
        //   Reason: No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   com.google.gson.JsonSyntaxException: java.lang.IllegalStateException: Expected BEGIN_OBJECT but was STRING at line 1 column 1 path $
        //       at com.google.gson.internal.bind.ReflectiveTypeAdapterFactory$Adapter.read(ReflectiveTypeAdapterFactory.java:397)
        //       at com.google.gson.Gson.fromJson(Gson.java:1227)
        //       at com.google.gson.Gson.fromJson(Gson.java:1137)
        //       at com.google.gson.Gson.fromJson(Gson.java:1047)
        //       at com.google.gson.Gson.fromJson(Gson.java:982)
        //       at com.iemr.ecd.utils.mapper.InputMapper.fromJson(InputMapper.java:78)
        //       at com.iemr.ecd.service.report.ReportServiceImpl.getAashaHomeVisitGapReport(ReportServiceImpl.java:305)
        //   java.lang.IllegalStateException: Expected BEGIN_OBJECT but was STRING at line 1 column 1 path $
        //       at com.google.gson.stream.JsonReader.beginObject(JsonReader.java:393)
        //       at com.google.gson.internal.bind.ReflectiveTypeAdapterFactory$Adapter.read(ReflectiveTypeAdapterFactory.java:386)
        //       at com.google.gson.Gson.fromJson(Gson.java:1227)
        //       at com.google.gson.Gson.fromJson(Gson.java:1137)
        //       at com.google.gson.Gson.fromJson(Gson.java:1047)
        //       at com.google.gson.Gson.fromJson(Gson.java:982)
        //       at com.iemr.ecd.utils.mapper.InputMapper.fromJson(InputMapper.java:78)
        //       at com.iemr.ecd.service.report.ReportServiceImpl.getAashaHomeVisitGapReport(ReportServiceImpl.java:305)
        //   See https://diff.blue/R013 to resolve this issue.

        // Arrange and Act
        reportServiceImpl.getAashaHomeVisitGapReport("Request", "foo.txt");
    }

    /**
     * Method under test:
     * {@link ReportServiceImpl#getCalciumIFATabNonadherenceReport(String, String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGetCalciumIFATabNonadherenceReport() throws Exception {
        // TODO: Diffblue Cover was only able to create a partial test for this method:
        //   Reason: No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   com.google.gson.JsonSyntaxException: java.lang.IllegalStateException: Expected BEGIN_OBJECT but was STRING at line 1 column 1 path $
        //       at com.google.gson.internal.bind.ReflectiveTypeAdapterFactory$Adapter.read(ReflectiveTypeAdapterFactory.java:397)
        //       at com.google.gson.Gson.fromJson(Gson.java:1227)
        //       at com.google.gson.Gson.fromJson(Gson.java:1137)
        //       at com.google.gson.Gson.fromJson(Gson.java:1047)
        //       at com.google.gson.Gson.fromJson(Gson.java:982)
        //       at com.iemr.ecd.utils.mapper.InputMapper.fromJson(InputMapper.java:78)
        //       at com.iemr.ecd.service.report.ReportServiceImpl.getCalciumIFATabNonadherenceReport(ReportServiceImpl.java:344)
        //   java.lang.IllegalStateException: Expected BEGIN_OBJECT but was STRING at line 1 column 1 path $
        //       at com.google.gson.stream.JsonReader.beginObject(JsonReader.java:393)
        //       at com.google.gson.internal.bind.ReflectiveTypeAdapterFactory$Adapter.read(ReflectiveTypeAdapterFactory.java:386)
        //       at com.google.gson.Gson.fromJson(Gson.java:1227)
        //       at com.google.gson.Gson.fromJson(Gson.java:1137)
        //       at com.google.gson.Gson.fromJson(Gson.java:1047)
        //       at com.google.gson.Gson.fromJson(Gson.java:982)
        //       at com.iemr.ecd.utils.mapper.InputMapper.fromJson(InputMapper.java:78)
        //       at com.iemr.ecd.service.report.ReportServiceImpl.getCalciumIFATabNonadherenceReport(ReportServiceImpl.java:344)
        //   See https://diff.blue/R013 to resolve this issue.

        // Arrange and Act
        reportServiceImpl.getCalciumIFATabNonadherenceReport("Request", "foo.txt");
    }

    /**
     * Method under test:
     * {@link ReportServiceImpl#getAbsenceInVHSNDReport(String, String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGetAbsenceInVHSNDReport() throws Exception {
        // TODO: Diffblue Cover was only able to create a partial test for this method:
        //   Reason: No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   com.google.gson.JsonSyntaxException: java.lang.IllegalStateException: Expected BEGIN_OBJECT but was STRING at line 1 column 1 path $
        //       at com.google.gson.internal.bind.ReflectiveTypeAdapterFactory$Adapter.read(ReflectiveTypeAdapterFactory.java:397)
        //       at com.google.gson.Gson.fromJson(Gson.java:1227)
        //       at com.google.gson.Gson.fromJson(Gson.java:1137)
        //       at com.google.gson.Gson.fromJson(Gson.java:1047)
        //       at com.google.gson.Gson.fromJson(Gson.java:982)
        //       at com.iemr.ecd.utils.mapper.InputMapper.fromJson(InputMapper.java:78)
        //       at com.iemr.ecd.service.report.ReportServiceImpl.getAbsenceInVHSNDReport(ReportServiceImpl.java:383)
        //   java.lang.IllegalStateException: Expected BEGIN_OBJECT but was STRING at line 1 column 1 path $
        //       at com.google.gson.stream.JsonReader.beginObject(JsonReader.java:393)
        //       at com.google.gson.internal.bind.ReflectiveTypeAdapterFactory$Adapter.read(ReflectiveTypeAdapterFactory.java:386)
        //       at com.google.gson.Gson.fromJson(Gson.java:1227)
        //       at com.google.gson.Gson.fromJson(Gson.java:1137)
        //       at com.google.gson.Gson.fromJson(Gson.java:1047)
        //       at com.google.gson.Gson.fromJson(Gson.java:982)
        //       at com.iemr.ecd.utils.mapper.InputMapper.fromJson(InputMapper.java:78)
        //       at com.iemr.ecd.service.report.ReportServiceImpl.getAbsenceInVHSNDReport(ReportServiceImpl.java:383)
        //   See https://diff.blue/R013 to resolve this issue.

        // Arrange and Act
        reportServiceImpl.getAbsenceInVHSNDReport("Request", "foo.txt");
    }

    /**
     * Method under test:
     * {@link ReportServiceImpl#getVaccineDropOutIdentifiedReport(String, String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGetVaccineDropOutIdentifiedReport() throws Exception {
        // TODO: Diffblue Cover was only able to create a partial test for this method:
        //   Reason: No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   com.google.gson.JsonSyntaxException: java.lang.IllegalStateException: Expected BEGIN_OBJECT but was STRING at line 1 column 1 path $
        //       at com.google.gson.internal.bind.ReflectiveTypeAdapterFactory$Adapter.read(ReflectiveTypeAdapterFactory.java:397)
        //       at com.google.gson.Gson.fromJson(Gson.java:1227)
        //       at com.google.gson.Gson.fromJson(Gson.java:1137)
        //       at com.google.gson.Gson.fromJson(Gson.java:1047)
        //       at com.google.gson.Gson.fromJson(Gson.java:982)
        //       at com.iemr.ecd.utils.mapper.InputMapper.fromJson(InputMapper.java:78)
        //       at com.iemr.ecd.service.report.ReportServiceImpl.getVaccineDropOutIdentifiedReport(ReportServiceImpl.java:422)
        //   java.lang.IllegalStateException: Expected BEGIN_OBJECT but was STRING at line 1 column 1 path $
        //       at com.google.gson.stream.JsonReader.beginObject(JsonReader.java:393)
        //       at com.google.gson.internal.bind.ReflectiveTypeAdapterFactory$Adapter.read(ReflectiveTypeAdapterFactory.java:386)
        //       at com.google.gson.Gson.fromJson(Gson.java:1227)
        //       at com.google.gson.Gson.fromJson(Gson.java:1137)
        //       at com.google.gson.Gson.fromJson(Gson.java:1047)
        //       at com.google.gson.Gson.fromJson(Gson.java:982)
        //       at com.iemr.ecd.utils.mapper.InputMapper.fromJson(InputMapper.java:78)
        //       at com.iemr.ecd.service.report.ReportServiceImpl.getVaccineDropOutIdentifiedReport(ReportServiceImpl.java:422)
        //   See https://diff.blue/R013 to resolve this issue.

        // Arrange and Act
        reportServiceImpl.getVaccineDropOutIdentifiedReport("Request", "foo.txt");
    }

    /**
     * Method under test:
     * {@link ReportServiceImpl#getVaccineLeftOutIdentifiedReport(String, String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGetVaccineLeftOutIdentifiedReport() throws Exception {
        // TODO: Diffblue Cover was only able to create a partial test for this method:
        //   Reason: No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   com.google.gson.JsonSyntaxException: java.lang.IllegalStateException: Expected BEGIN_OBJECT but was STRING at line 1 column 1 path $
        //       at com.google.gson.internal.bind.ReflectiveTypeAdapterFactory$Adapter.read(ReflectiveTypeAdapterFactory.java:397)
        //       at com.google.gson.Gson.fromJson(Gson.java:1227)
        //       at com.google.gson.Gson.fromJson(Gson.java:1137)
        //       at com.google.gson.Gson.fromJson(Gson.java:1047)
        //       at com.google.gson.Gson.fromJson(Gson.java:982)
        //       at com.iemr.ecd.utils.mapper.InputMapper.fromJson(InputMapper.java:78)
        //       at com.iemr.ecd.service.report.ReportServiceImpl.getVaccineLeftOutIdentifiedReport(ReportServiceImpl.java:461)
        //   java.lang.IllegalStateException: Expected BEGIN_OBJECT but was STRING at line 1 column 1 path $
        //       at com.google.gson.stream.JsonReader.beginObject(JsonReader.java:393)
        //       at com.google.gson.internal.bind.ReflectiveTypeAdapterFactory$Adapter.read(ReflectiveTypeAdapterFactory.java:386)
        //       at com.google.gson.Gson.fromJson(Gson.java:1227)
        //       at com.google.gson.Gson.fromJson(Gson.java:1137)
        //       at com.google.gson.Gson.fromJson(Gson.java:1047)
        //       at com.google.gson.Gson.fromJson(Gson.java:982)
        //       at com.iemr.ecd.utils.mapper.InputMapper.fromJson(InputMapper.java:78)
        //       at com.iemr.ecd.service.report.ReportServiceImpl.getVaccineLeftOutIdentifiedReport(ReportServiceImpl.java:461)
        //   See https://diff.blue/R013 to resolve this issue.

        // Arrange and Act
        reportServiceImpl.getVaccineLeftOutIdentifiedReport("Request", "foo.txt");
    }

    /**
     * Method under test:
     * {@link ReportServiceImpl#getDevelopmentalDelayReport(String, String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGetDevelopmentalDelayReport() throws Exception {
        // TODO: Diffblue Cover was only able to create a partial test for this method:
        //   Reason: No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   com.google.gson.JsonSyntaxException: java.lang.IllegalStateException: Expected BEGIN_OBJECT but was STRING at line 1 column 1 path $
        //       at com.google.gson.internal.bind.ReflectiveTypeAdapterFactory$Adapter.read(ReflectiveTypeAdapterFactory.java:397)
        //       at com.google.gson.Gson.fromJson(Gson.java:1227)
        //       at com.google.gson.Gson.fromJson(Gson.java:1137)
        //       at com.google.gson.Gson.fromJson(Gson.java:1047)
        //       at com.google.gson.Gson.fromJson(Gson.java:982)
        //       at com.iemr.ecd.utils.mapper.InputMapper.fromJson(InputMapper.java:78)
        //       at com.iemr.ecd.service.report.ReportServiceImpl.getDevelopmentalDelayReport(ReportServiceImpl.java:500)
        //   java.lang.IllegalStateException: Expected BEGIN_OBJECT but was STRING at line 1 column 1 path $
        //       at com.google.gson.stream.JsonReader.beginObject(JsonReader.java:393)
        //       at com.google.gson.internal.bind.ReflectiveTypeAdapterFactory$Adapter.read(ReflectiveTypeAdapterFactory.java:386)
        //       at com.google.gson.Gson.fromJson(Gson.java:1227)
        //       at com.google.gson.Gson.fromJson(Gson.java:1137)
        //       at com.google.gson.Gson.fromJson(Gson.java:1047)
        //       at com.google.gson.Gson.fromJson(Gson.java:982)
        //       at com.iemr.ecd.utils.mapper.InputMapper.fromJson(InputMapper.java:78)
        //       at com.iemr.ecd.service.report.ReportServiceImpl.getDevelopmentalDelayReport(ReportServiceImpl.java:500)
        //   See https://diff.blue/R013 to resolve this issue.

        // Arrange and Act
        reportServiceImpl.getDevelopmentalDelayReport("Request", "foo.txt");
    }

    /**
     * Method under test:
     * {@link ReportServiceImpl#getAbortionReport(String, String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGetAbortionReport() throws Exception {
        // TODO: Diffblue Cover was only able to create a partial test for this method:
        //   Reason: No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   com.google.gson.JsonSyntaxException: java.lang.IllegalStateException: Expected BEGIN_OBJECT but was STRING at line 1 column 1 path $
        //       at com.google.gson.internal.bind.ReflectiveTypeAdapterFactory$Adapter.read(ReflectiveTypeAdapterFactory.java:397)
        //       at com.google.gson.Gson.fromJson(Gson.java:1227)
        //       at com.google.gson.Gson.fromJson(Gson.java:1137)
        //       at com.google.gson.Gson.fromJson(Gson.java:1047)
        //       at com.google.gson.Gson.fromJson(Gson.java:982)
        //       at com.iemr.ecd.utils.mapper.InputMapper.fromJson(InputMapper.java:78)
        //       at com.iemr.ecd.service.report.ReportServiceImpl.getAbortionReport(ReportServiceImpl.java:539)
        //   java.lang.IllegalStateException: Expected BEGIN_OBJECT but was STRING at line 1 column 1 path $
        //       at com.google.gson.stream.JsonReader.beginObject(JsonReader.java:393)
        //       at com.google.gson.internal.bind.ReflectiveTypeAdapterFactory$Adapter.read(ReflectiveTypeAdapterFactory.java:386)
        //       at com.google.gson.Gson.fromJson(Gson.java:1227)
        //       at com.google.gson.Gson.fromJson(Gson.java:1137)
        //       at com.google.gson.Gson.fromJson(Gson.java:1047)
        //       at com.google.gson.Gson.fromJson(Gson.java:982)
        //       at com.iemr.ecd.utils.mapper.InputMapper.fromJson(InputMapper.java:78)
        //       at com.iemr.ecd.service.report.ReportServiceImpl.getAbortionReport(ReportServiceImpl.java:539)
        //   See https://diff.blue/R013 to resolve this issue.

        // Arrange and Act
        reportServiceImpl.getAbortionReport("Request", "foo.txt");
    }

    /**
     * Method under test:
     * {@link ReportServiceImpl#getDeliveryStatusReport(String, String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGetDeliveryStatusReport() throws Exception {
        // TODO: Diffblue Cover was only able to create a partial test for this method:
        //   Reason: No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   com.google.gson.JsonSyntaxException: java.lang.IllegalStateException: Expected BEGIN_OBJECT but was STRING at line 1 column 1 path $
        //       at com.google.gson.internal.bind.ReflectiveTypeAdapterFactory$Adapter.read(ReflectiveTypeAdapterFactory.java:397)
        //       at com.google.gson.Gson.fromJson(Gson.java:1227)
        //       at com.google.gson.Gson.fromJson(Gson.java:1137)
        //       at com.google.gson.Gson.fromJson(Gson.java:1047)
        //       at com.google.gson.Gson.fromJson(Gson.java:982)
        //       at com.iemr.ecd.utils.mapper.InputMapper.fromJson(InputMapper.java:78)
        //       at com.iemr.ecd.service.report.ReportServiceImpl.getDeliveryStatusReport(ReportServiceImpl.java:578)
        //   java.lang.IllegalStateException: Expected BEGIN_OBJECT but was STRING at line 1 column 1 path $
        //       at com.google.gson.stream.JsonReader.beginObject(JsonReader.java:393)
        //       at com.google.gson.internal.bind.ReflectiveTypeAdapterFactory$Adapter.read(ReflectiveTypeAdapterFactory.java:386)
        //       at com.google.gson.Gson.fromJson(Gson.java:1227)
        //       at com.google.gson.Gson.fromJson(Gson.java:1137)
        //       at com.google.gson.Gson.fromJson(Gson.java:1047)
        //       at com.google.gson.Gson.fromJson(Gson.java:982)
        //       at com.iemr.ecd.utils.mapper.InputMapper.fromJson(InputMapper.java:78)
        //       at com.iemr.ecd.service.report.ReportServiceImpl.getDeliveryStatusReport(ReportServiceImpl.java:578)
        //   See https://diff.blue/R013 to resolve this issue.

        // Arrange and Act
        reportServiceImpl.getDeliveryStatusReport("Request", "foo.txt");
    }

    /**
     * Method under test:
     * {@link ReportServiceImpl#getHRPCasesIdentifiedReport(String, String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGetHRPCasesIdentifiedReport() throws Exception {
        // TODO: Diffblue Cover was only able to create a partial test for this method:
        //   Reason: No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   com.google.gson.JsonSyntaxException: java.lang.IllegalStateException: Expected BEGIN_OBJECT but was STRING at line 1 column 1 path $
        //       at com.google.gson.internal.bind.ReflectiveTypeAdapterFactory$Adapter.read(ReflectiveTypeAdapterFactory.java:397)
        //       at com.google.gson.Gson.fromJson(Gson.java:1227)
        //       at com.google.gson.Gson.fromJson(Gson.java:1137)
        //       at com.google.gson.Gson.fromJson(Gson.java:1047)
        //       at com.google.gson.Gson.fromJson(Gson.java:982)
        //       at com.iemr.ecd.utils.mapper.InputMapper.fromJson(InputMapper.java:78)
        //       at com.iemr.ecd.service.report.ReportServiceImpl.getHRPCasesIdentifiedReport(ReportServiceImpl.java:617)
        //   java.lang.IllegalStateException: Expected BEGIN_OBJECT but was STRING at line 1 column 1 path $
        //       at com.google.gson.stream.JsonReader.beginObject(JsonReader.java:393)
        //       at com.google.gson.internal.bind.ReflectiveTypeAdapterFactory$Adapter.read(ReflectiveTypeAdapterFactory.java:386)
        //       at com.google.gson.Gson.fromJson(Gson.java:1227)
        //       at com.google.gson.Gson.fromJson(Gson.java:1137)
        //       at com.google.gson.Gson.fromJson(Gson.java:1047)
        //       at com.google.gson.Gson.fromJson(Gson.java:982)
        //       at com.iemr.ecd.utils.mapper.InputMapper.fromJson(InputMapper.java:78)
        //       at com.iemr.ecd.service.report.ReportServiceImpl.getHRPCasesIdentifiedReport(ReportServiceImpl.java:617)
        //   See https://diff.blue/R013 to resolve this issue.

        // Arrange and Act
        reportServiceImpl.getHRPCasesIdentifiedReport("Request", "foo.txt");
    }

    /**
     * Method under test:
     * {@link ReportServiceImpl#getInfantsHighRiskReport(String, String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGetInfantsHighRiskReport() throws Exception {
        // TODO: Diffblue Cover was only able to create a partial test for this method:
        //   Reason: No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   com.google.gson.JsonSyntaxException: java.lang.IllegalStateException: Expected BEGIN_OBJECT but was STRING at line 1 column 1 path $
        //       at com.google.gson.internal.bind.ReflectiveTypeAdapterFactory$Adapter.read(ReflectiveTypeAdapterFactory.java:397)
        //       at com.google.gson.Gson.fromJson(Gson.java:1227)
        //       at com.google.gson.Gson.fromJson(Gson.java:1137)
        //       at com.google.gson.Gson.fromJson(Gson.java:1047)
        //       at com.google.gson.Gson.fromJson(Gson.java:982)
        //       at com.iemr.ecd.utils.mapper.InputMapper.fromJson(InputMapper.java:78)
        //       at com.iemr.ecd.service.report.ReportServiceImpl.getInfantsHighRiskReport(ReportServiceImpl.java:656)
        //   java.lang.IllegalStateException: Expected BEGIN_OBJECT but was STRING at line 1 column 1 path $
        //       at com.google.gson.stream.JsonReader.beginObject(JsonReader.java:393)
        //       at com.google.gson.internal.bind.ReflectiveTypeAdapterFactory$Adapter.read(ReflectiveTypeAdapterFactory.java:386)
        //       at com.google.gson.Gson.fromJson(Gson.java:1227)
        //       at com.google.gson.Gson.fromJson(Gson.java:1137)
        //       at com.google.gson.Gson.fromJson(Gson.java:1047)
        //       at com.google.gson.Gson.fromJson(Gson.java:982)
        //       at com.iemr.ecd.utils.mapper.InputMapper.fromJson(InputMapper.java:78)
        //       at com.iemr.ecd.service.report.ReportServiceImpl.getInfantsHighRiskReport(ReportServiceImpl.java:656)
        //   See https://diff.blue/R013 to resolve this issue.

        // Arrange and Act
        reportServiceImpl.getInfantsHighRiskReport("Request", "foo.txt");
    }

    /**
     * Method under test:
     * {@link ReportServiceImpl#getMaternalDeathReport(String, String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGetMaternalDeathReport() throws Exception {
        // TODO: Diffblue Cover was only able to create a partial test for this method:
        //   Reason: No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   com.google.gson.JsonSyntaxException: java.lang.IllegalStateException: Expected BEGIN_OBJECT but was STRING at line 1 column 1 path $
        //       at com.google.gson.internal.bind.ReflectiveTypeAdapterFactory$Adapter.read(ReflectiveTypeAdapterFactory.java:397)
        //       at com.google.gson.Gson.fromJson(Gson.java:1227)
        //       at com.google.gson.Gson.fromJson(Gson.java:1137)
        //       at com.google.gson.Gson.fromJson(Gson.java:1047)
        //       at com.google.gson.Gson.fromJson(Gson.java:982)
        //       at com.iemr.ecd.utils.mapper.InputMapper.fromJson(InputMapper.java:78)
        //       at com.iemr.ecd.service.report.ReportServiceImpl.getMaternalDeathReport(ReportServiceImpl.java:695)
        //   java.lang.IllegalStateException: Expected BEGIN_OBJECT but was STRING at line 1 column 1 path $
        //       at com.google.gson.stream.JsonReader.beginObject(JsonReader.java:393)
        //       at com.google.gson.internal.bind.ReflectiveTypeAdapterFactory$Adapter.read(ReflectiveTypeAdapterFactory.java:386)
        //       at com.google.gson.Gson.fromJson(Gson.java:1227)
        //       at com.google.gson.Gson.fromJson(Gson.java:1137)
        //       at com.google.gson.Gson.fromJson(Gson.java:1047)
        //       at com.google.gson.Gson.fromJson(Gson.java:982)
        //       at com.iemr.ecd.utils.mapper.InputMapper.fromJson(InputMapper.java:78)
        //       at com.iemr.ecd.service.report.ReportServiceImpl.getMaternalDeathReport(ReportServiceImpl.java:695)
        //   See https://diff.blue/R013 to resolve this issue.

        // Arrange and Act
        reportServiceImpl.getMaternalDeathReport("Request", "foo.txt");
    }

    /**
     * Method under test:
     * {@link ReportServiceImpl#getStillBirthReport(String, String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGetStillBirthReport() throws Exception {
        // TODO: Diffblue Cover was only able to create a partial test for this method:
        //   Reason: No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   com.google.gson.JsonSyntaxException: java.lang.IllegalStateException: Expected BEGIN_OBJECT but was STRING at line 1 column 1 path $
        //       at com.google.gson.internal.bind.ReflectiveTypeAdapterFactory$Adapter.read(ReflectiveTypeAdapterFactory.java:397)
        //       at com.google.gson.Gson.fromJson(Gson.java:1227)
        //       at com.google.gson.Gson.fromJson(Gson.java:1137)
        //       at com.google.gson.Gson.fromJson(Gson.java:1047)
        //       at com.google.gson.Gson.fromJson(Gson.java:982)
        //       at com.iemr.ecd.utils.mapper.InputMapper.fromJson(InputMapper.java:78)
        //       at com.iemr.ecd.service.report.ReportServiceImpl.getStillBirthReport(ReportServiceImpl.java:734)
        //   java.lang.IllegalStateException: Expected BEGIN_OBJECT but was STRING at line 1 column 1 path $
        //       at com.google.gson.stream.JsonReader.beginObject(JsonReader.java:393)
        //       at com.google.gson.internal.bind.ReflectiveTypeAdapterFactory$Adapter.read(ReflectiveTypeAdapterFactory.java:386)
        //       at com.google.gson.Gson.fromJson(Gson.java:1227)
        //       at com.google.gson.Gson.fromJson(Gson.java:1137)
        //       at com.google.gson.Gson.fromJson(Gson.java:1047)
        //       at com.google.gson.Gson.fromJson(Gson.java:982)
        //       at com.iemr.ecd.utils.mapper.InputMapper.fromJson(InputMapper.java:78)
        //       at com.iemr.ecd.service.report.ReportServiceImpl.getStillBirthReport(ReportServiceImpl.java:734)
        //   See https://diff.blue/R013 to resolve this issue.

        // Arrange and Act
        reportServiceImpl.getStillBirthReport("Request", "foo.txt");
    }

    /**
     * Method under test:
     * {@link ReportServiceImpl#getBabyDeathReport(String, String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGetBabyDeathReport() throws Exception {
        // TODO: Diffblue Cover was only able to create a partial test for this method:
        //   Reason: No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   com.google.gson.JsonSyntaxException: java.lang.IllegalStateException: Expected BEGIN_OBJECT but was STRING at line 1 column 1 path $
        //       at com.google.gson.internal.bind.ReflectiveTypeAdapterFactory$Adapter.read(ReflectiveTypeAdapterFactory.java:397)
        //       at com.google.gson.Gson.fromJson(Gson.java:1227)
        //       at com.google.gson.Gson.fromJson(Gson.java:1137)
        //       at com.google.gson.Gson.fromJson(Gson.java:1047)
        //       at com.google.gson.Gson.fromJson(Gson.java:982)
        //       at com.iemr.ecd.utils.mapper.InputMapper.fromJson(InputMapper.java:78)
        //       at com.iemr.ecd.service.report.ReportServiceImpl.getBabyDeathReport(ReportServiceImpl.java:773)
        //   java.lang.IllegalStateException: Expected BEGIN_OBJECT but was STRING at line 1 column 1 path $
        //       at com.google.gson.stream.JsonReader.beginObject(JsonReader.java:393)
        //       at com.google.gson.internal.bind.ReflectiveTypeAdapterFactory$Adapter.read(ReflectiveTypeAdapterFactory.java:386)
        //       at com.google.gson.Gson.fromJson(Gson.java:1227)
        //       at com.google.gson.Gson.fromJson(Gson.java:1137)
        //       at com.google.gson.Gson.fromJson(Gson.java:1047)
        //       at com.google.gson.Gson.fromJson(Gson.java:982)
        //       at com.iemr.ecd.utils.mapper.InputMapper.fromJson(InputMapper.java:78)
        //       at com.iemr.ecd.service.report.ReportServiceImpl.getBabyDeathReport(ReportServiceImpl.java:773)
        //   See https://diff.blue/R013 to resolve this issue.

        // Arrange and Act
        reportServiceImpl.getBabyDeathReport("Request", "foo.txt");
    }

    /**
     * Method under test:
     * {@link ReportServiceImpl#getNotConnectedPhonelistDiffformatReport(String, String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGetNotConnectedPhonelistDiffformatReport() throws Exception {
        // TODO: Diffblue Cover was only able to create a partial test for this method:
        //   Reason: No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   com.google.gson.JsonSyntaxException: java.lang.IllegalStateException: Expected BEGIN_OBJECT but was STRING at line 1 column 1 path $
        //       at com.google.gson.internal.bind.ReflectiveTypeAdapterFactory$Adapter.read(ReflectiveTypeAdapterFactory.java:397)
        //       at com.google.gson.Gson.fromJson(Gson.java:1227)
        //       at com.google.gson.Gson.fromJson(Gson.java:1137)
        //       at com.google.gson.Gson.fromJson(Gson.java:1047)
        //       at com.google.gson.Gson.fromJson(Gson.java:982)
        //       at com.iemr.ecd.utils.mapper.InputMapper.fromJson(InputMapper.java:78)
        //       at com.iemr.ecd.service.report.ReportServiceImpl.getNotConnectedPhonelistDiffformatReport(ReportServiceImpl.java:812)
        //   java.lang.IllegalStateException: Expected BEGIN_OBJECT but was STRING at line 1 column 1 path $
        //       at com.google.gson.stream.JsonReader.beginObject(JsonReader.java:393)
        //       at com.google.gson.internal.bind.ReflectiveTypeAdapterFactory$Adapter.read(ReflectiveTypeAdapterFactory.java:386)
        //       at com.google.gson.Gson.fromJson(Gson.java:1227)
        //       at com.google.gson.Gson.fromJson(Gson.java:1137)
        //       at com.google.gson.Gson.fromJson(Gson.java:1047)
        //       at com.google.gson.Gson.fromJson(Gson.java:982)
        //       at com.iemr.ecd.utils.mapper.InputMapper.fromJson(InputMapper.java:78)
        //       at com.iemr.ecd.service.report.ReportServiceImpl.getNotConnectedPhonelistDiffformatReport(ReportServiceImpl.java:812)
        //   See https://diff.blue/R013 to resolve this issue.

        // Arrange and Act
        reportServiceImpl.getNotConnectedPhonelistDiffformatReport("Request", "foo.txt");
    }

    /**
     * Method under test:
     * {@link ReportServiceImpl#getJSYRelatedComplaintsReport(String, String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGetJSYRelatedComplaintsReport() throws Exception {
        // TODO: Diffblue Cover was only able to create a partial test for this method:
        //   Reason: No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   com.google.gson.JsonSyntaxException: java.lang.IllegalStateException: Expected BEGIN_OBJECT but was STRING at line 1 column 1 path $
        //       at com.google.gson.internal.bind.ReflectiveTypeAdapterFactory$Adapter.read(ReflectiveTypeAdapterFactory.java:397)
        //       at com.google.gson.Gson.fromJson(Gson.java:1227)
        //       at com.google.gson.Gson.fromJson(Gson.java:1137)
        //       at com.google.gson.Gson.fromJson(Gson.java:1047)
        //       at com.google.gson.Gson.fromJson(Gson.java:982)
        //       at com.iemr.ecd.utils.mapper.InputMapper.fromJson(InputMapper.java:78)
        //       at com.iemr.ecd.service.report.ReportServiceImpl.getJSYRelatedComplaintsReport(ReportServiceImpl.java:851)
        //   java.lang.IllegalStateException: Expected BEGIN_OBJECT but was STRING at line 1 column 1 path $
        //       at com.google.gson.stream.JsonReader.beginObject(JsonReader.java:393)
        //       at com.google.gson.internal.bind.ReflectiveTypeAdapterFactory$Adapter.read(ReflectiveTypeAdapterFactory.java:386)
        //       at com.google.gson.Gson.fromJson(Gson.java:1227)
        //       at com.google.gson.Gson.fromJson(Gson.java:1137)
        //       at com.google.gson.Gson.fromJson(Gson.java:1047)
        //       at com.google.gson.Gson.fromJson(Gson.java:982)
        //       at com.iemr.ecd.utils.mapper.InputMapper.fromJson(InputMapper.java:78)
        //       at com.iemr.ecd.service.report.ReportServiceImpl.getJSYRelatedComplaintsReport(ReportServiceImpl.java:851)
        //   See https://diff.blue/R013 to resolve this issue.

        // Arrange and Act
        reportServiceImpl.getJSYRelatedComplaintsReport("Request", "foo.txt");
    }

    /**
     * Method under test:
     * {@link ReportServiceImpl#getMiscarriageReport(String, String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGetMiscarriageReport() throws Exception {
        // TODO: Diffblue Cover was only able to create a partial test for this method:
        //   Reason: No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   com.google.gson.JsonSyntaxException: java.lang.IllegalStateException: Expected BEGIN_OBJECT but was STRING at line 1 column 1 path $
        //       at com.google.gson.internal.bind.ReflectiveTypeAdapterFactory$Adapter.read(ReflectiveTypeAdapterFactory.java:397)
        //       at com.google.gson.Gson.fromJson(Gson.java:1227)
        //       at com.google.gson.Gson.fromJson(Gson.java:1137)
        //       at com.google.gson.Gson.fromJson(Gson.java:1047)
        //       at com.google.gson.Gson.fromJson(Gson.java:982)
        //       at com.iemr.ecd.utils.mapper.InputMapper.fromJson(InputMapper.java:78)
        //       at com.iemr.ecd.service.report.ReportServiceImpl.getMiscarriageReport(ReportServiceImpl.java:890)
        //   java.lang.IllegalStateException: Expected BEGIN_OBJECT but was STRING at line 1 column 1 path $
        //       at com.google.gson.stream.JsonReader.beginObject(JsonReader.java:393)
        //       at com.google.gson.internal.bind.ReflectiveTypeAdapterFactory$Adapter.read(ReflectiveTypeAdapterFactory.java:386)
        //       at com.google.gson.Gson.fromJson(Gson.java:1227)
        //       at com.google.gson.Gson.fromJson(Gson.java:1137)
        //       at com.google.gson.Gson.fromJson(Gson.java:1047)
        //       at com.google.gson.Gson.fromJson(Gson.java:982)
        //       at com.iemr.ecd.utils.mapper.InputMapper.fromJson(InputMapper.java:78)
        //       at com.iemr.ecd.service.report.ReportServiceImpl.getMiscarriageReport(ReportServiceImpl.java:890)
        //   See https://diff.blue/R013 to resolve this issue.

        // Arrange and Act
        reportServiceImpl.getMiscarriageReport("Request", "foo.txt");
    }
}

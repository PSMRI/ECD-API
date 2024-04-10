package com.iemr.ecd.model.excel;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class ExcelHelperDiffblueTest {
    /**
     * Method under test:
     * {@link ExcelHelper#tutorialsToExcel(String[], List, Criteria, String[])}
     */
    @Test
    void testTutorialsToExcel() throws Exception {
        // Arrange
        ArrayList<Object[]> result = new ArrayList<>();

        Criteria criteria = new Criteria();
        criteria.setAgent_Id("42");
        criteria.setEnd_Date("2020-03-01");
        criteria.setRole("Role");
        criteria.setStart_Date("2020-03-01");
        criteria.setType("Role");

        // Act
        ExcelHelper.tutorialsToExcel(new String[]{"Headers"}, result, criteria, new String[]{"Criteria Columns"});

        // Assert
        assertEquals("2020-03-01", criteria.getEnd_Date());
        assertEquals("2020-03-01", criteria.getStart_Date());
    }

    /**
     * Method under test:
     * {@link ExcelHelper#tutorialsToExcel(String[], List, Criteria, String[])}
     */
    @Test
    void testTutorialsToExcel2() throws Exception {
        // Arrange
        ArrayList<Object[]> result = new ArrayList<>();

        Criteria criteria = new Criteria();
        criteria.setAgent_Id("42");
        criteria.setEnd_Date("2020-03-01");
        criteria.setRole("Role");
        criteria.setStart_Date("2020-03-01");
        criteria.setType("Role");

        // Act
        ExcelHelper.tutorialsToExcel(new String[]{"U/U"}, result, criteria, new String[]{"Criteria Columns"});

        // Assert
        assertEquals("2020-03-01", criteria.getEnd_Date());
        assertEquals("2020-03-01", criteria.getStart_Date());
    }

    /**
     * Method under test:
     * {@link ExcelHelper#tutorialsToExcel(String[], List, Criteria, String[])}
     */
    @Test
    void testTutorialsToExcel3() throws Exception {
        // Arrange
        ArrayList<Object[]> result = new ArrayList<>();

        Criteria criteria = new Criteria();
        criteria.setAgent_Id("42");
        criteria.setEnd_Date("2020-03-01");
        criteria.setRole("Role");
        criteria.setStart_Date("2020-03-01");
        criteria.setType("Role");

        // Act
        ExcelHelper.tutorialsToExcel(new String[]{null}, result, criteria, new String[]{"Criteria Columns"});

        // Assert
        assertEquals("2020-03-01", criteria.getEnd_Date());
        assertEquals("2020-03-01", criteria.getStart_Date());
    }

    /**
     * Method under test:
     * {@link ExcelHelper#tutorialsToExcel(String[], List, Criteria, String[])}
     */
    @Test
    void testTutorialsToExcel4() throws Exception {
        // Arrange
        ArrayList<Object[]> result = new ArrayList<>();

        Criteria criteria = new Criteria();
        criteria.setAgent_Id("42");
        criteria.setEnd_Date("2020-03-01");
        criteria.setRole("Role");
        criteria.setStart_Date("2020-03-01");
        criteria.setType("Role");

        // Act and Assert
        assertThrows(Exception.class,
                () -> ExcelHelper.tutorialsToExcel(null, result, criteria, new String[]{"Criteria Columns"}));
    }

    /**
     * Method under test:
     * {@link ExcelHelper#tutorialsToExcel(String[], List, Criteria, String[])}
     */
    @Test
    void testTutorialsToExcel5() throws Exception {
        // Arrange
        ArrayList<Object[]> result = new ArrayList<>();

        Criteria criteria = new Criteria();
        criteria.setAgent_Id("42");
        criteria.setEnd_Date("2020-03-01");
        criteria.setRole("Role");
        criteria.setStart_Date("2020-03-01");
        criteria.setType("Role");

        // Act
        ExcelHelper.tutorialsToExcel(new String[]{}, result, criteria, new String[]{"Criteria Columns"});

        // Assert
        assertEquals("2020-03-01", criteria.getEnd_Date());
        assertEquals("2020-03-01", criteria.getStart_Date());
    }

    /**
     * Method under test:
     * {@link ExcelHelper#tutorialsToExcel(String[], List, Criteria, String[])}
     */
    @Test
    void testTutorialsToExcel6() throws Exception {
        // Arrange
        ArrayList<Object[]> result = new ArrayList<>();

        Criteria criteria = new Criteria();
        criteria.setAgent_Id("42");
        criteria.setEnd_Date("2020-03-01");
        criteria.setRole("Role");
        criteria.setStart_Date("2020-03-01");
        criteria.setType("Role");

        // Act
        ExcelHelper.tutorialsToExcel(new String[]{"U/U", "/", "U/U"}, result, criteria, new String[]{"Criteria Columns"});

        // Assert
        assertEquals("2020-03-01", criteria.getEnd_Date());
        assertEquals("2020-03-01", criteria.getStart_Date());
    }

    /**
     * Method under test:
     * {@link ExcelHelper#tutorialsToExcel(String[], List, Criteria, String[])}
     */
    @Test
    void testTutorialsToExcel7() throws Exception {
        // Arrange
        ArrayList<Object[]> result = new ArrayList<>();
        result.add(new Object[]{"42"});

        Criteria criteria = new Criteria();
        criteria.setAgent_Id("42");
        criteria.setEnd_Date("2020-03-01");
        criteria.setRole("Role");
        criteria.setStart_Date("2020-03-01");
        criteria.setType("Role");

        // Act
        ExcelHelper.tutorialsToExcel(new String[]{"Headers"}, result, criteria, new String[]{"Criteria Columns"});

        // Assert
        assertEquals("2020-03-01", criteria.getEnd_Date());
        assertEquals("2020-03-01", criteria.getStart_Date());
    }

    /**
     * Method under test:
     * {@link ExcelHelper#tutorialsToExcel(String[], List, Criteria, String[])}
     */
    @Test
    void testTutorialsToExcel8() throws Exception {
        // Arrange
        ArrayList<Object[]> result = new ArrayList<>();
        result.add(new Object[]{"42"});
        result.add(new Object[]{"42"});

        Criteria criteria = new Criteria();
        criteria.setAgent_Id("42");
        criteria.setEnd_Date("2020-03-01");
        criteria.setRole("Role");
        criteria.setStart_Date("2020-03-01");
        criteria.setType("Role");

        // Act
        ExcelHelper.tutorialsToExcel(new String[]{"Headers"}, result, criteria, new String[]{"Criteria Columns"});

        // Assert
        assertEquals("2020-03-01", criteria.getEnd_Date());
        assertEquals("2020-03-01", criteria.getStart_Date());
    }

    /**
     * Method under test:
     * {@link ExcelHelper#tutorialsToExcel(String[], List, Criteria, String[])}
     */
    @Test
    void testTutorialsToExcel9() throws Exception {
        // Arrange
        ArrayList<Object[]> result = new ArrayList<>();
        result.add(new Object[]{null});

        Criteria criteria = new Criteria();
        criteria.setAgent_Id("42");
        criteria.setEnd_Date("2020-03-01");
        criteria.setRole("Role");
        criteria.setStart_Date("2020-03-01");
        criteria.setType("Role");

        // Act
        ExcelHelper.tutorialsToExcel(new String[]{"Headers"}, result, criteria, new String[]{"Criteria Columns"});

        // Assert
        assertEquals("2020-03-01", criteria.getEnd_Date());
        assertEquals("2020-03-01", criteria.getStart_Date());
    }

    /**
     * Method under test:
     * {@link ExcelHelper#tutorialsToExcel(String[], List, Criteria, String[])}
     */
    @Test
    void testTutorialsToExcel10() throws Exception {
        // Arrange
        ArrayList<Object[]> result = new ArrayList<>();
        Criteria criteria = mock(Criteria.class);
        when(criteria.getEnd_Date()).thenReturn("2020-03-01");
        when(criteria.getStart_Date()).thenReturn("2020-03-01");
        doNothing().when(criteria).setAgent_Id(Mockito.<String>any());
        doNothing().when(criteria).setEnd_Date(Mockito.<String>any());
        doNothing().when(criteria).setRole(Mockito.<String>any());
        doNothing().when(criteria).setStart_Date(Mockito.<String>any());
        doNothing().when(criteria).setType(Mockito.<String>any());
        criteria.setAgent_Id("42");
        criteria.setEnd_Date("2020-03-01");
        criteria.setRole("Role");
        criteria.setStart_Date("2020-03-01");
        criteria.setType("Role");

        // Act
        ExcelHelper.tutorialsToExcel(new String[]{"Headers"}, result, criteria, new String[]{"Criteria Columns"});

        // Assert
        verify(criteria).getEnd_Date();
        verify(criteria).getStart_Date();
        verify(criteria).setAgent_Id(eq("42"));
        verify(criteria, atLeast(1)).setEnd_Date(eq("2020-03-01"));
        verify(criteria).setRole(eq("Role"));
        verify(criteria, atLeast(1)).setStart_Date(eq("2020-03-01"));
        verify(criteria).setType(eq("Role"));
    }

    /**
     * Method under test:
     * {@link ExcelHelper#tutorialsToExcel(String[], List, Criteria, String[])}
     */
    @Test
    void testTutorialsToExcel11() throws Exception {
        // Arrange
        ArrayList<Object[]> result = new ArrayList<>();
        Criteria criteria = mock(Criteria.class);
        when(criteria.getEnd_Date()).thenReturn(".9");
        when(criteria.getStart_Date()).thenReturn("2020-03-01");
        doNothing().when(criteria).setAgent_Id(Mockito.<String>any());
        doNothing().when(criteria).setEnd_Date(Mockito.<String>any());
        doNothing().when(criteria).setRole(Mockito.<String>any());
        doNothing().when(criteria).setStart_Date(Mockito.<String>any());
        doNothing().when(criteria).setType(Mockito.<String>any());
        criteria.setAgent_Id("42");
        criteria.setEnd_Date("2020-03-01");
        criteria.setRole("Role");
        criteria.setStart_Date("2020-03-01");
        criteria.setType("Role");

        // Act
        ExcelHelper.tutorialsToExcel(new String[]{"Headers"}, result, criteria, new String[]{"Criteria Columns"});

        // Assert
        verify(criteria).getEnd_Date();
        verify(criteria).getStart_Date();
        verify(criteria).setAgent_Id(eq("42"));
        verify(criteria, atLeast(1)).setEnd_Date(Mockito.<String>any());
        verify(criteria).setRole(eq("Role"));
        verify(criteria, atLeast(1)).setStart_Date(eq("2020-03-01"));
        verify(criteria).setType(eq("Role"));
    }

    /**
     * Method under test:
     * {@link ExcelHelper#tutorialsToExcel(String[], List, Criteria, String[])}
     */
    @Test
    void testTutorialsToExcel12() throws Exception {
        // Arrange
        ArrayList<Object[]> result = new ArrayList<>();
        Criteria criteria = mock(Criteria.class);
        when(criteria.getEnd_Date()).thenReturn(null);
        when(criteria.getStart_Date()).thenReturn("2020-03-01");
        doNothing().when(criteria).setAgent_Id(Mockito.<String>any());
        doNothing().when(criteria).setEnd_Date(Mockito.<String>any());
        doNothing().when(criteria).setRole(Mockito.<String>any());
        doNothing().when(criteria).setStart_Date(Mockito.<String>any());
        doNothing().when(criteria).setType(Mockito.<String>any());
        criteria.setAgent_Id("42");
        criteria.setEnd_Date("2020-03-01");
        criteria.setRole("Role");
        criteria.setStart_Date("2020-03-01");
        criteria.setType("Role");

        // Act and Assert
        assertThrows(Exception.class, () -> ExcelHelper.tutorialsToExcel(new String[]{"Headers"}, result, criteria,
                new String[]{"Criteria Columns"}));
        verify(criteria).getEnd_Date();
        verify(criteria).getStart_Date();
        verify(criteria).setAgent_Id(eq("42"));
        verify(criteria).setEnd_Date(eq("2020-03-01"));
        verify(criteria).setRole(eq("Role"));
        verify(criteria, atLeast(1)).setStart_Date(eq("2020-03-01"));
        verify(criteria).setType(eq("Role"));
    }

    /**
     * Method under test:
     * {@link ExcelHelper#tutorialsToExcel(String[], List, Criteria, String[])}
     */
    @Test
    void testTutorialsToExcel13() throws Exception {
        // Arrange
        ArrayList<Object[]> result = new ArrayList<>();
        Criteria criteria = mock(Criteria.class);
        when(criteria.getStart_Date()).thenReturn(null);
        doNothing().when(criteria).setAgent_Id(Mockito.<String>any());
        doNothing().when(criteria).setEnd_Date(Mockito.<String>any());
        doNothing().when(criteria).setRole(Mockito.<String>any());
        doNothing().when(criteria).setStart_Date(Mockito.<String>any());
        doNothing().when(criteria).setType(Mockito.<String>any());
        criteria.setAgent_Id("42");
        criteria.setEnd_Date("2020-03-01");
        criteria.setRole("Role");
        criteria.setStart_Date("2020-03-01");
        criteria.setType("Role");

        // Act and Assert
        assertThrows(Exception.class, () -> ExcelHelper.tutorialsToExcel(new String[]{"Headers"}, result, criteria,
                new String[]{"Criteria Columns"}));
        verify(criteria).getStart_Date();
        verify(criteria).setAgent_Id(eq("42"));
        verify(criteria).setEnd_Date(eq("2020-03-01"));
        verify(criteria).setRole(eq("Role"));
        verify(criteria).setStart_Date(eq("2020-03-01"));
        verify(criteria).setType(eq("Role"));
    }

    /**
     * Method under test:
     * {@link ExcelHelper#tutorialsToExcel(String[], List, Criteria, String[])}
     */
    @Test
    void testTutorialsToExcel14() throws Exception {
        // Arrange
        ArrayList<Object[]> result = new ArrayList<>();
        Criteria criteria = mock(Criteria.class);
        when(criteria.getEnd_Date()).thenReturn("2020-03-01");
        when(criteria.getStart_Date()).thenReturn("2020-03-01");
        doNothing().when(criteria).setAgent_Id(Mockito.<String>any());
        doNothing().when(criteria).setEnd_Date(Mockito.<String>any());
        doNothing().when(criteria).setRole(Mockito.<String>any());
        doNothing().when(criteria).setStart_Date(Mockito.<String>any());
        doNothing().when(criteria).setType(Mockito.<String>any());
        criteria.setAgent_Id("42");
        criteria.setEnd_Date("2020-03-01");
        criteria.setRole("Role");
        criteria.setStart_Date("2020-03-01");
        criteria.setType("Role");

        // Act and Assert
        assertThrows(Exception.class,
                () -> ExcelHelper.tutorialsToExcel(new String[]{"Headers"}, result, criteria, new String[]{null}));
        verify(criteria).getEnd_Date();
        verify(criteria).getStart_Date();
        verify(criteria).setAgent_Id(eq("42"));
        verify(criteria, atLeast(1)).setEnd_Date(eq("2020-03-01"));
        verify(criteria).setRole(eq("Role"));
        verify(criteria, atLeast(1)).setStart_Date(eq("2020-03-01"));
        verify(criteria).setType(eq("Role"));
    }

    /**
     * Method under test:
     * {@link ExcelHelper#tutorialsToExcel(String[], List, Criteria, String[])}
     */
    @Test
    void testTutorialsToExcel15() throws Exception {
        // Arrange
        ArrayList<Object[]> result = new ArrayList<>();
        Criteria criteria = mock(Criteria.class);
        when(criteria.getAgent_Id()).thenReturn("42");
        when(criteria.getEnd_Date()).thenReturn("2020-03-01");
        when(criteria.getStart_Date()).thenReturn("2020-03-01");
        doNothing().when(criteria).setAgent_Id(Mockito.<String>any());
        doNothing().when(criteria).setEnd_Date(Mockito.<String>any());
        doNothing().when(criteria).setRole(Mockito.<String>any());
        doNothing().when(criteria).setStart_Date(Mockito.<String>any());
        doNothing().when(criteria).setType(Mockito.<String>any());
        criteria.setAgent_Id("42");
        criteria.setEnd_Date("2020-03-01");
        criteria.setRole("Role");
        criteria.setStart_Date("2020-03-01");
        criteria.setType("Role");

        // Act
        ExcelHelper.tutorialsToExcel(new String[]{"Headers"}, result, criteria, new String[]{"Agent_Id"});

        // Assert
        verify(criteria).getAgent_Id();
        verify(criteria).getEnd_Date();
        verify(criteria).getStart_Date();
        verify(criteria).setAgent_Id(eq("42"));
        verify(criteria, atLeast(1)).setEnd_Date(eq("2020-03-01"));
        verify(criteria).setRole(eq("Role"));
        verify(criteria, atLeast(1)).setStart_Date(eq("2020-03-01"));
        verify(criteria).setType(eq("Role"));
    }

    /**
     * Method under test:
     * {@link ExcelHelper#tutorialsToExcel(String[], List, Criteria, String[])}
     */
    @Test
    void testTutorialsToExcel16() throws Exception {
        // Arrange
        ArrayList<Object[]> result = new ArrayList<>();
        Criteria criteria = mock(Criteria.class);
        when(criteria.getAgent_Id()).thenReturn(null);
        when(criteria.getEnd_Date()).thenReturn("2020-03-01");
        when(criteria.getStart_Date()).thenReturn("2020-03-01");
        doNothing().when(criteria).setAgent_Id(Mockito.<String>any());
        doNothing().when(criteria).setEnd_Date(Mockito.<String>any());
        doNothing().when(criteria).setRole(Mockito.<String>any());
        doNothing().when(criteria).setStart_Date(Mockito.<String>any());
        doNothing().when(criteria).setType(Mockito.<String>any());
        criteria.setAgent_Id("42");
        criteria.setEnd_Date("2020-03-01");
        criteria.setRole("Role");
        criteria.setStart_Date("2020-03-01");
        criteria.setType("Role");

        // Act
        ExcelHelper.tutorialsToExcel(new String[]{"Headers"}, result, criteria, new String[]{"Agent_Id"});

        // Assert
        verify(criteria).getAgent_Id();
        verify(criteria).getEnd_Date();
        verify(criteria).getStart_Date();
        verify(criteria).setAgent_Id(eq("42"));
        verify(criteria, atLeast(1)).setEnd_Date(eq("2020-03-01"));
        verify(criteria).setRole(eq("Role"));
        verify(criteria, atLeast(1)).setStart_Date(eq("2020-03-01"));
        verify(criteria).setType(eq("Role"));
    }

    /**
     * Method under test: {@link ExcelHelper#isValidDate(String)}
     */
    @Test
    void testIsValidDate() {
        // Arrange, Act and Assert
        assertTrue(ExcelHelper.isValidDate("2020-03-01"));
        assertFalse(ExcelHelper.isValidDate("2020/03/01"));
    }
}

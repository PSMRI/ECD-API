package com.iemr.ecd.model.excel;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class CriteriaDiffblueTest {
    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link Criteria#setAgent_Id(String)}
     *   <li>{@link Criteria#setEnd_Date(String)}
     *   <li>{@link Criteria#setStart_Date(String)}
     *   <li>{@link Criteria#setType(String)}
     *   <li>{@link Criteria#getAgent_Id()}
     *   <li>{@link Criteria#getEnd_Date()}
     *   <li>{@link Criteria#getRole()}
     *   <li>{@link Criteria#getStart_Date()}
     * </ul>
     */
    @Test
    void testGettersAndSetters() {
        // Arrange
        Criteria criteria = new Criteria();

        // Act
        criteria.setAgent_Id("42");
        criteria.setEnd_Date("2020-03-01");
        criteria.setStart_Date("2020-03-01");
        criteria.setType("Role");
        String actualAgent_Id = criteria.getAgent_Id();
        String actualEnd_Date = criteria.getEnd_Date();
        String actualRole = criteria.getRole();

        // Assert that nothing has changed
        assertEquals("2020-03-01", actualEnd_Date);
        assertEquals("2020-03-01", criteria.getStart_Date());
        assertEquals("42", actualAgent_Id);
        assertEquals("Role", actualRole);
    }
}

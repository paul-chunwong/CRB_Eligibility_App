package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

// A test class for Invoice which tests the different methods in Invoice class
class InvoiceTest {
    private Invoice i1, i2, i3;

    @BeforeEach
    void runBefore() {
        i1 = new Invoice("2021,Sep,23", 300, "Tutoring", "a9890");
        i2 = new Invoice("2020,Jul,20", 90, "Web Designing", "Sep88232113");
        i3 = new Invoice("2023,Sep,22", 100, "Tutoring", "2021020201");
    }

    @Test
    void testConstructor() {
        assertEquals("2021,Sep,23", i1.getDate());
        assertEquals(300, i1.getAmount());
        assertEquals("Tutoring", i1.getType());
        assertEquals("a9890", i1.getId());

        assertEquals("2020,Jul,20", i2.getDate());
        assertEquals(90, i2.getAmount());
        assertEquals("Web Designing", i2.getType());
        assertEquals("Sep88232113", i2.getId());
    }

    @Test
    void testIsLowIncome() {
        assertFalse(i1.isLowIncome());
        i1.setAmount(899);
        assertFalse(i1.isLowIncome());
        i1.setAmount(100);
        assertFalse(i1.isLowIncome());
        i1.setAmount(99.999);
        assertTrue(i1.isLowIncome());
        i1.setAmount(1);
        assertTrue(i1.isLowIncome());

        assertTrue(i2.isLowIncome());
        assertFalse(i3.isLowIncome());
    }

    @Test
    void testIsHighIncome() {
        assertFalse(i1.isHighIncome());
        i1.setAmount(899);
        assertFalse(i1.isHighIncome());
        i1.setAmount(1000.0);
        assertTrue(i1.isHighIncome());
        i1.setAmount(99999999.99999);
        assertTrue(i1.isHighIncome());

        assertTrue(i2.isLowIncome());
        assertFalse(i3.isLowIncome());
    }

    @Test
    void testSetDate() {
        assertEquals("2021,Sep,23", i1.getDate());
        i1.setDate("2020,Jul,20");
        assertEquals("2020,Jul,20", i1.getDate());
    }

    @Test
    void testSetAmount() {
        assertEquals(300, i1.getAmount());
        i1.setAmount(555);
        assertEquals(555, i1.getAmount());
        i1.setAmount(0.1);
        assertEquals(0.1, i1.getAmount());
    }

    @Test
    void testSetType() {
        assertEquals("Tutoring", i1.getType());
        i1.setType("Driving");
        assertEquals("Driving", i1.getType());
    }

    @Test
    void testSetId() {
        assertEquals("a9890", i1.getId());
        i1.setId("999oioi");
        assertEquals("999oioi", i1.getId());
    }

    @Test
    void testToString() {
        assertEquals("Invoice{" + "date='" + "2021,Sep,23" + '\'' + ", amount=" + 300.0 + ", type='"
                + "Tutoring" + '\'' + ", id='" + "a9890" + '\'' + '}', i1.toString());
        assertEquals("Invoice{" + "date='" + "2020,Jul,20" + '\'' + ", amount=" + 90.0 + ", type='"
                + "Web Designing" + '\'' + ", id='" + "Sep88232113" + '\'' + '}', i2.toString());
    }
}
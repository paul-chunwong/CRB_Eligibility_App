package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

// A test class for Invoices which tests the different methods in Invoices class
class InvoicesTest {
    private Invoices ivos;
    private Invoice i1, i2, i3, i4;

    @BeforeEach
    void runBefore() {
        ivos = new Invoices();

        i1 = new Invoice("2021,Sep,23", 300, "Tutoring", "a9890");
        i2 = new Invoice("2020,Sep,20", 500, "Web Designing", "p2930391");
        i3 = new Invoice("2019,Jul,19", 90, "Tutoring", "a9890010");
        i4 = new Invoice("2020,Aug,01", 99999, "Tutoring", "990k");
    }

    @Test
    void testConstructor() {
        assertEquals(0, ivos.length());
        assertEquals(new Invoices().getInvoices(), ivos.getInvoices());
        assertFalse(ivos.isContain(i1));
    }

    @Test
    void testLength() {
        assertEquals(0, ivos.length());
        ivos.addInvoice(i1);
        assertEquals(1, ivos.length());
        ivos.addInvoice(i2);
        assertEquals(2, ivos.length());
        ivos.removeInvoice("a9890");
        assertEquals(1, ivos.length());
    }

    @Test
    void testIsContain() {
        assertEquals(0, ivos.length());
        assertFalse(ivos.isContain(i1));
        assertFalse(ivos.isContain(i2));

        ivos.addInvoice(i1);
        ivos.addInvoice(i2);
        ivos.addInvoice(new Invoice("123", 234, "345", "456"));
        ivos.addInvoice(new Invoice("888", 888, "888", "888"));
        assertTrue(ivos.isContain(i1));
        assertTrue(ivos.isContain(i2));
        ivos.removeInvoice("a9890");
        assertFalse(ivos.isContain(i1));

        assertTrue(ivos.isContain(new Invoice("123", 234, "345", "456")));
        assertFalse(ivos.isContain(new Invoice("123", 234, "345", "45")));
        assertFalse(ivos.isContain(new Invoice("888", 777, "888", "888")));
        assertFalse(ivos.isContain(new Invoice("888", 888, "777", "888")));
        assertFalse(ivos.isContain(new Invoice("888", 888, "888", "777")));
    }


    @Test
    void testAddInvoice() {
        ivos.addInvoice(i1);
        assertEquals(1, ivos.length());
        assertTrue(ivos.isContain(i1));
        assertFalse(ivos.isContain(i2));

        ivos.addInvoice(i2);
        ivos.addInvoice(i3);
        ivos.addInvoice(i4);
        assertEquals(4, ivos.length());
        assertTrue(ivos.isContain(i2));
        assertTrue(ivos.isContain(i3));
        assertTrue(ivos.isContain(i4));
    }

    @Test
    void testRemoveInvoice() {
        assertFalse(ivos.removeInvoice("a1"));
        ivos.addInvoice(i1);
        ivos.addInvoice(i2);
        ivos.addInvoice(i4);
        assertEquals(3, ivos.length());
        assertTrue(ivos.isContain(i1));
        assertTrue(ivos.isContain(i2));
        assertFalse(ivos.isContain(i3));
        assertTrue(ivos.isContain(i4));

        assertTrue(ivos.removeInvoice("a9890"));
        assertEquals(2, ivos.length());
        assertFalse(ivos.isContain(i1));

        assertTrue(ivos.removeInvoice("p2930391"));
        assertFalse(ivos.removeInvoice("90909090dd"));
        assertTrue(ivos.removeInvoice("990k"));
        assertFalse(ivos.removeInvoice("12093890128309128309"));
        assertEquals(0, ivos.length());
        assertFalse(ivos.isContain(i1));
        assertFalse(ivos.isContain(i2));
        assertFalse(ivos.isContain(i3));
        assertFalse(ivos.isContain(i4));
    }

    @Test
    void testGetEarningWithType() {
        ivos.addInvoice(i1);
        ivos.addInvoice(i2);
        ivos.addInvoice(i3);
        ivos.addInvoice(i4);

        assertEquals(100389, ivos.getEarningWithType("Tutoring"));
        assertEquals(500, ivos.getEarningWithType("Web Designing"));
        assertEquals(0, ivos.getEarningWithType("Doing Nothing"));
    }

    @Test
    void testToString() {
        ivos.addInvoice(i1);
        ivos.addInvoice(i2);
        assertEquals("Invoices{invoices=[Invoice{date='2021,Sep,23', amount=300.0, " +
                "type='Tutoring', id='a9890'}, Invoice{date='2020,Sep,20', amount=500.0, " +
                "type='Web Designing', id='p2930391'}]}", ivos.toString());
    }
}

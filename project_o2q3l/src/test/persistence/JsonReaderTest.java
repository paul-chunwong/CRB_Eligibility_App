package persistence;

import model.Invoice;
import model.Invoices;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

// This [JsonReaderTest class] references code from this:
// [https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo]
class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            Invoices ivos = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyInvoices() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyInvoices.json");
        try {
            Invoices ivos = reader.read();
            assertEquals(0, ivos.length());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralInvoices() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralInvoices.json");
        try {
            Invoices ivos = reader.read();
            List<Invoice> invoices = ivos.getInvoices();
            assertEquals(2, invoices.size());
            checkInvoice("2021,03,23", 100.0,"Tutoring","a1", invoices.get(0));
            checkInvoice("2020,04,21", 9999,"Web Designing","b1", invoices.get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
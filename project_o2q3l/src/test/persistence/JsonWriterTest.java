package persistence;


import model.Invoice;
import model.Invoices;

import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;


// This [JsonWriterTest class] references code from this:
// [https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo]
class JsonWriterTest extends JsonTest {
    //NOTE TO CPSC 210 STUDENTS: the strategy in designing tests for the JsonWriter is to
    //write data to a file and then use the reader to read it back in and check that we
    //read in a copy of what was written out.

    @Test
    void testWriterInvalidFile() {
        try {
            Invoices ivos = new Invoices();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyInvoices() {
        try {
            Invoices ivos = new Invoices();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyInvoices.json");
            writer.open();
            writer.write(ivos);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyInvoices.json");
            ivos = reader.read();
            assertEquals(0, ivos.length());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralInvoices() {
        try {
            Invoices ivos = new Invoices();
            ivos.addInvoice(new Invoice("2021,03,23", 100.0,"Tutoring","a1"));
            ivos.addInvoice(new Invoice("2020,04,21", 9999,"Web Designing","b1"));
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralInvoices.json");
            writer.open();
            writer.write(ivos);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralInvoices.json");
            ivos = reader.read();
            List<Invoice> invoices = ivos.getInvoices();
            assertEquals(2, invoices.size());
            checkInvoice("2021,03,23", 100.0,"Tutoring","a1", invoices.get(0));
            checkInvoice("2020,04,21", 9999,"Web Designing","b1", invoices.get(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
package persistence;

import model.Invoice;
import model.Invoices;

import static org.junit.jupiter.api.Assertions.assertEquals;

// This [JsonTest class] references code from this:
// [https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo]
public class JsonTest {
    protected void checkInvoice(String date, double amount, String type, String id, Invoice invoice) {
        assertEquals(date, invoice.getDate());
        assertEquals(amount, invoice.getAmount());
        assertEquals(type, invoice.getType());
        assertEquals(id, invoice.getId());
    }
}

package persistence;

import model.Event;
import model.EventLog;
import model.Invoices;
import model.Invoice;

import org.json.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;


// This [JsonReader class] references code from this:
// [https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo]
// Represents a reader that reads invoices from JSON data stored in file
public class JsonReader {
    private static String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads invoices from file and returns it;
    // throws IOException if an error occurs reading data from file
    public static Invoices read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        EventLog.getInstance().logEvent(new Event("Invoices are loaded!"));   // event log
        return parseInvoices(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private static String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses invoices from JSON object and returns it
    private static Invoices parseInvoices(JSONObject jsonObject) {
        Invoices invoices = new Invoices();
        addInvoices(invoices, jsonObject);
        return invoices;
    }

    // MODIFIES: ivos
    // EFFECTS: parses invoices from JSON object and adds them to ivos
    private static void addInvoices(Invoices ivos, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("invoices");
        for (Object json : jsonArray) {
            JSONObject nextInvoice = (JSONObject) json;
            addInvoice(ivos, nextInvoice);
        }
    }

    // MODIFIES: ivos
    // EFFECTS: parses invoice from JSON object and adds it to invoices
    private static void addInvoice(Invoices ivos, JSONObject jsonObject) {
        String date = jsonObject.getString("date");
        double amount = jsonObject.getDouble("amount");
        String type = jsonObject.getString("type");
        String id = jsonObject.getString("id");
        Invoice invoice = new Invoice(date, amount, type, id);
        ivos.addInvoice(invoice);
    }
}

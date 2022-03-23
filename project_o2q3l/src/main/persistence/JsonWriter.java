package persistence;

import model.Event;
import model.EventLog;
import model.Invoices;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;


// This [JsonWriter class] references code from this:
// [https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo]
// Represents a writer that writes JSON representation of workroom to file
public class JsonWriter {
    private static final int TAB = 4;
    private static PrintWriter writer;
    private static String destination;

    // EFFECTS: constructs writer to write to destination file
    public JsonWriter(String destination) {
        this.destination = destination;
    }

    // MODIFIES: this
    // EFFECTS: opens writer; throws FileNotFoundException if destination file cannot
    // be opened for writing
    public static void open() throws FileNotFoundException {
        writer = new PrintWriter(new File(destination));
    }

    // MODIFIES: this
    // EFFECTS: writes JSON representation of invoices to file
    public static void write(Invoices ivos) {
        JSONObject json = ivos.toJson();
        saveToFile(json.toString(TAB));
        EventLog.getInstance().logEvent(new Event("Invoices are saved!"));   // event log
    }

    // MODIFIES: this
    // EFFECTS: closes writer
    public static void close() {
        writer.close();
    }

    // MODIFIES: this
    // EFFECTS: writes string to file
    private static void saveToFile(String json) {
        writer.print(json);
    }
}

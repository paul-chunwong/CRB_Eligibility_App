package persistence;

import org.json.JSONObject;


// This [Writable interface] references code from this:
// [https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo]
// Writable is an interface that provides writable
public interface Writable {
    // EFFECTS: returns this as JSON object
    JSONObject toJson();
}

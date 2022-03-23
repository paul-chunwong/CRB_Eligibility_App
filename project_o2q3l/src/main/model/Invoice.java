package model;

import persistence.Writable;
import org.json.JSONObject;


// Represents an invoice having a date, amount(in dollars), type(type of service or work), and a unique id
public class Invoice implements Writable {
    private String date;                        // invoice date
    private double amount;                      // amount of the service or work in dollars
    private String type;                        // type of service or work
    private String id;                          // invoice id

    //REQUIRES: date must be correctly formatted in "yyyy,mm,dd", such as: "2020,05,20" or "2001,01,01",
    //          amount must be > 0; id must be unique String
    //EFFECTS: initializes each newly created Invoice with different parameters(date, amount, type, and id)
    public Invoice(String date, double amount, String type, String id) {
        this.date = date;
        this.amount = amount;
        this.type = type;
        this.id = id;
    }

    //EFFECTS: return true if the amount is < 100, otherwise return false
    public boolean isLowIncome() {
        if (this.amount < 100) {
            return true;
        } else {
            return false;
        }
    }

    //EFFECTS: return true if the amount is >= 1000, otherwise return false
    public boolean isHighIncome() {
        if (this.amount >= 1000) {
            return true;
        } else {
            return false;
        }
    }

    public String getDate() {
        return this.date;
    }

    public double getAmount() {
        return this.amount;
    }

    public String getType() {
        return this.type;
    }

    public String getId() {
        return this.id;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setId(String id) {
        this.id = id;
    }

    //EFFECTS: return a String that contains all the field values in the Invoice object
    @Override
    public String toString() {
        return "Invoice{" + "date='" + date + '\'' + ", amount=" + amount + ", type='" + type + '\'' + ", id='" + id
                + '\'' + '}';
    }

    // This [toJson method] references code from this:
    // [https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo]
    // EFFECTS: return the JSONObject with the content of this invoice object
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("date", date);
        json.put("amount", amount);
        json.put("type", type);
        json.put("id", id);
        return json;
    }
}

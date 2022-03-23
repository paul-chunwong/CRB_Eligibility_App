package model;

import persistence.Writable;

import org.json.JSONArray;
import org.json.JSONObject;

import javax.swing.*;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


// Represents a list of invoices to be handled by application (ui)
public class Invoices implements Writable {
    private LinkedList<Invoice> invoices;      // list of invoices

    //EFFECTS: initializes each newly created Invoices as an empty list.
    public Invoices() {
        this.invoices = new LinkedList<Invoice>();
    }

    //EFFECTS: returns an int that represents the number of invoices currently in the list
    public int length() {
        int length = this.invoices.size();
        return length;
    }

    //EFFECTS: returns true if the given invoice is in the invoices list, otherwise return false
    public boolean isContain(Invoice i) {
        for (Invoice i1 : invoices) {
            if (i1.getDate().equals(i.getDate()) && i1.getAmount() == i.getAmount()
                    && i1.getType().equals(i.getType()) && i1.getId().equals(i.getId())) {
                return true;
            }
        }
        return false;
    }

    //MODIFIES: this
    //EFFECTS: adds Invoice to the end of the Invoices list
    public void addInvoice(Invoice i) {
        this.invoices.add(i);
        EventLog.getInstance().logEvent(new Event("Added invoice (id): " + i.getId()));   // event log
    }

    //MODIFIES: this
    //EFFECTS: removes the Invoice and return true if the Invoice from the Invoices list has the same id as the
    //         id given by user, otherwise, return false
    public boolean removeInvoice(String id) {
        for (Invoice i : this.invoices) {
            if (i.getId().equalsIgnoreCase(id)) {
                EventLog.getInstance().logEvent(new Event("Removed invoice(id): " + id));   // event log
                return this.invoices.remove(i);
            }
        }
        return false;
    }

    // MODIFIES: this
    // EFFECTS: clear the invoices
    public void clearInvoices() {
        this.invoices = new LinkedList<Invoice>();
        EventLog.getInstance().logEvent(new Event("Invoices cleared!"));   // event log
    }

    //EFFECTS: return the total earning from all the invoices that has the same user given type
    public double getEarningWithType(String type) {
        double totalEarning = 0;
        for (Invoice i : this.invoices) {
            if (i.getType().equalsIgnoreCase(type)) {
                totalEarning = totalEarning + i.getAmount();
            }
        }
        return totalEarning;
    }

    public LinkedList<Invoice> getInvoices() {
        return this.invoices;
    }

    //EFFECTS: return a String that contains all the field values in the Invoices object
    @Override
    public String toString() {
        return "Invoices{" + "invoices=" + invoices + '}';
    }

    // This [invoicesToJson method] references code from this:
    // [https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo]
    // EFFECTS: returns invoices in this invoices as a JSON array
    private JSONArray invoicesToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Invoice t : invoices) {
            jsonArray.put(t.toJson());
        }

        return jsonArray;
    }

    // This [toJson method] references code from this:
    // [https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo]
    // EFFECTS: return the JSONObject with the content of this invoices object
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("invoices", invoicesToJson());
        return json;
    }
}

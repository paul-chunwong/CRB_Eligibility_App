package ui;

import model.Event;
import model.EventLog;
import model.Invoice;
import model.Invoices;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

// A remove invoice window for the CRB application
public class RemoveInvoiceWindow extends JFrame implements ActionListener {
    private static final String JSON_STORE = "./data/invoices.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    Invoices invoices;
    InvoicesWindow invoicesWindow;
    JTextField itemIdField;
    private static final String FINISH_ACTION = "FINISH_ACTION";

    // MODIFIES: this
    // EFFECTS: construct the RemoveInvoiceWindow
    public RemoveInvoiceWindow(InvoicesWindow invoicesWindow, Invoices invoices) {
        super("Remove invoice from the list");
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        this.invoicesWindow = invoicesWindow;
        this.invoices = invoices;

        this.createWindow();
        this.createLabels();
        this.createFields();
        this.createButtons();
        this.adjustWindow();
    }

    // MODIFIES: this
    // EFFECTS: set window size and layout
    private void createWindow() {
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        setPreferredSize(new Dimension(550, 200));
        ((JPanel) getContentPane()).setBorder(new EmptyBorder(10, 10, 10, 10));
        setLayout(null);
    }

    // MODIFIES: this
    // EFFECTS: pack and further set the properties of the window
    public void adjustWindow() {
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }

    // MODIFIES: this
    // EFFECTS: set the labels
    private void createLabels() {
        JLabel itemIdLabel = new JLabel("Enter the id of the invoice that you want to remove from the list: ");
        itemIdLabel.setBounds(55, 25, 600, 20);
        add(itemIdLabel);
        itemIdLabel.setForeground(Color.BLUE);
    }

    // MODIFIES: this
    // EFFECTS: set the fields
    private void createFields() {
        itemIdField = new JTextField(30);
        itemIdField.setBounds(50, 55, 300, 20);
        add(itemIdField);
    }

    // MODIFIES: this
    // EFFECTS: set the button
    private void createButtons() {
        JButton finishButton = new JButton("Click here to remove!");
        finishButton.setBounds(175, 115, 150, 20);
        add(finishButton);
        finishButton.setActionCommand(FINISH_ACTION);
        finishButton.addActionListener(this);
        finishButton.setForeground(Color.BLUE);
    }

    // MODIFIES: this
    // EFFECTS: perform the functions according to the user interaction
    @Override
    public void actionPerformed(ActionEvent e) {
        String action = e.getActionCommand();
        if (action.equals(FINISH_ACTION)) {
            removeInvoice();
//            saveInvoice();     // auto save
            handleWindows();
        }
    }

    // MODIFIES: this
    // EFFECTS: remove invoice from the list according to the given id
    private void removeInvoice() {
        String id = itemIdField.getText();
        invoices.removeInvoice(id);
    }

    // MODIFIES: this
    // EFFECTS: save the invoices
    private void saveInvoice() {
        try {
            JsonWriter.open();
            JsonWriter.write(invoices);
            JsonWriter.close();
        } catch (FileNotFoundException e1) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: close and reopen window
    public void handleWindows() {
        invoicesWindow.dispose();
        new InvoicesWindow(invoices);
        this.dispose();
    }
}

package ui;

import model.EventLog;
import model.Event;
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

// An add invoice window for the CRB application
public class AddInvoiceWindow extends JFrame implements ActionListener {
    private static final String JSON_STORE = "./data/invoices.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    JTextField itemDateField;
    JTextField itemAmountField;
    JTextField itemTypeField;
    JTextField itemIdField;
    InvoicesWindow invoicesWindow;
    Invoices invoices;
    private static final String FINISH_ACTION = "FINISH_ACTION";

    // MODIFIES: this
    // EFFECTS: construct the AddInvoiceWindow
    public AddInvoiceWindow(InvoicesWindow invoicesWindow, Invoices invoices) {
        super("Add invoice to the list");
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
        setPreferredSize(new Dimension(700, 380));
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
    // EFFECTS: set labels
    private void createLabels() {
        JLabel itemDateLabel = new JLabel("Enter invoice date: ");
        itemDateLabel.setBounds(50, 60, 400, 20);
        add(itemDateLabel);
        itemDateLabel.setForeground(Color.BLUE);

        JLabel itemAmountLabel = new JLabel(
                "Enter invoice amount: ");
        itemAmountLabel.setBounds(50, 110, 600, 20);
        add(itemAmountLabel);
        itemAmountLabel.setForeground(Color.BLUE);

        JLabel itemTypeLabel = new JLabel(
                "Enter invoice type: ");
        itemTypeLabel.setBounds(50, 160, 600, 20);
        add(itemTypeLabel);
        itemTypeLabel.setForeground(Color.BLUE);

        JLabel itemIdLabel = new JLabel(
                "Enter invoice id: ");
        itemIdLabel.setBounds(50, 210, 600, 20);
        add(itemIdLabel);
        itemIdLabel.setForeground(Color.BLUE);
    }

    // MODIFIES: this
    // EFFECTS: set fields
    private void createFields() {
        itemDateField = new JTextField(30);
        itemDateField.setBounds(50, 80, 300, 20);
        add(itemDateField);

        itemAmountField = new JTextField(30);
        itemAmountField.setBounds(50, 130, 300, 20);
        add(itemAmountField);

        itemTypeField = new JTextField(30);
        itemTypeField.setBounds(50, 180, 300, 20);
        add(itemTypeField);

        itemIdField = new JTextField(30);
        itemIdField.setBounds(50, 230, 300, 20);
        add(itemIdField);
    }

    // MODIFIES: this
    // EFFECTS: set buttons
    private void createButtons() {
        JButton finishButton = new JButton("Click Here To Add Invoice To The List!");
        finishButton.setBounds(400, 150, 250, 20);
        add(finishButton);
        finishButton.setActionCommand(FINISH_ACTION);
        finishButton.addActionListener(this);
        finishButton.setForeground(Color.BLUE);
    }

    // MODIFIES: this
    // EFFECTS: perform the function with the user interactions
    @Override
    public void actionPerformed(ActionEvent e) {
        String action = e.getActionCommand();
        if (action.equals(FINISH_ACTION)) {
            createInvoice();
//            saveInvoice();     // auto save
            handleWindows();
        }
    }

    // MODIFIES: this
    // EFFECTS: set the invoice
    private void createInvoice() {
        String date = itemDateField.getText();
        String amount = itemAmountField.getText();
        int amountInt = Integer.parseInt(amount);
        String type = itemTypeField.getText();
        String id = itemIdField.getText();
        invoices.addInvoice(new Invoice(date, amountInt, type, id));
    }

    // MODIFIES: this
    // EFFECTS: save invoices to the list
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
    // EFFECTS: close and reopen the window
    public void handleWindows() {
        invoicesWindow.dispose();
        new InvoicesWindow(invoices);
        this.dispose();
    }
}

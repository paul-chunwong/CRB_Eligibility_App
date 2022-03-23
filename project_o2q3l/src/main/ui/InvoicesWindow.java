package ui;

import model.Invoice;
import model.Invoices;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

// An invoices window for the CRB Application
public class InvoicesWindow extends JFrame implements ActionListener {
    private static final String JSON_STORE = "./data/invoices.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private DefaultTableModel tableModel;
    private JTable table;
    private static Invoices invoices;
    private static final String ADD_INVOICE_ACTION = "ADD_INVOICE_ACTION";
    private static final String REMOVE_INVOICE_ACTION = "REMOVE_INVOICE_ACTION";
    private static final String FIND_LENGTH_ACTION = "FIND_LENGTH_ACTION";

    // MODIFIES: this
    // EFFECTS: construct the invoices window
    public InvoicesWindow(Invoices invoices) {
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        this.invoices = invoices;

        this.createWindow();
        this.adjustWindow();
    }

    // MODIFIES: this
    // EFFECTS: set window size and layout
    public void createWindow() {
        setPreferredSize(new Dimension(1200, 458));
        ((JPanel) getContentPane()).setBorder(new EmptyBorder(10, 10, 10, 10));
        this.createBackgroundImage();
        final String[] columnLabels = new String[]{
                "Date",
                "Amount",
                "Type",
                "ID"
        };
        tableModel = new DefaultTableModel(null, columnLabels) {
        };
        table = new JTable(tableModel);
        this.createTableRows();
        add(new JScrollPane(table));
        this.createButtons();
        setTitle("Invoices list");
        setLayout(new FlowLayout());
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
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
    // EFFECTS: set the background image to this main window
    private void createBackgroundImage() {
        try {
            BufferedImage backgroundImage = ImageIO.read(new File("src/main/ui/images/background2.jpg"));
            setContentPane(new BackgroundImage(backgroundImage));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // MODIFIES: this
    // EFFECTS: set the table that shows the invoices
    private void createTableRows() {
        for (int i = 0; i < invoices.getInvoices().size(); i++) {
            Invoice invoice = invoices.getInvoices().get(i);
            Object[] tableRow = new Object[]{
                    invoice.getDate(),
                    invoice.getAmount(),
                    invoice.getType(),
                    invoice.getId(),
            };
            tableModel.addRow(tableRow);
        }
    }

    // MODIFIES: this
    // EFFECTS: set the buttons
    private void createButtons() {
        JButton addItemButton = new JButton(("Add New Invoice"));
        add(addItemButton);
        addItemButton.setActionCommand(ADD_INVOICE_ACTION);
        addItemButton.addActionListener(this);
        addItemButton.setForeground(Color.BLUE);

        JButton markItemAsDoneButton = new JButton("Remove Invoice By ID");
        add(markItemAsDoneButton);
        markItemAsDoneButton.setActionCommand(REMOVE_INVOICE_ACTION);
        markItemAsDoneButton.addActionListener(this);
        markItemAsDoneButton.setForeground(Color.BLUE);

        JButton findLengthButton = new JButton("Find Length Of Invoices List");
        add(findLengthButton);
        findLengthButton.setActionCommand(FIND_LENGTH_ACTION);
        findLengthButton.addActionListener(this);
        findLengthButton.setForeground(Color.BLUE);
    }

    // MODIFIES: this, AddInvoiceWindow, RemoveInvoiceWindow
    // EFFECTS: perform the functions according to the user interactions
    @Override
    public void actionPerformed(ActionEvent e) {
        String action = e.getActionCommand();
        if (action.equals(ADD_INVOICE_ACTION)) {
            new AddInvoiceWindow(this, invoices);
        } else if (action.equals(REMOVE_INVOICE_ACTION)) {
            new RemoveInvoiceWindow(this, invoices);
        } else if (action.equals(FIND_LENGTH_ACTION)) {
            JOptionPane.showMessageDialog(null, "The length of your invoices list: " + invoices.length());
        }
    }
}

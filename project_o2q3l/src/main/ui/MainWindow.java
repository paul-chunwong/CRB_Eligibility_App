package ui;

import model.Event;
import model.EventLog;
import model.Invoice;
import model.Invoices;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

// A main window for the CRB Application
public class MainWindow extends JFrame implements ActionListener {
    private static final String JSON_STORE = "./data/invoices.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private static final int POSITION_STANDARD = 385;
    private static final int WIDTH_STANDARD = 500;
    private static final int HEIGHT_STANDARD = 35;
    private static final String SHOW_INVOICES_ACTION = "SHOW_INVOICES_ACTION";
    private static final String CLEAR_INVOICES_ACTION = "CLEAR_INVOICES_ACTION";
    private static final String TOTAL_EARNING_ACTION = "TOTAL_EARNING_ACTION";
    private static final String CHECKLIST_ACTION = "CHECKLIST_ACTION";
    private static final String QUIT_APP_ACTION = "QUIT_APP_ACTION";
    private static final String SAVE_ACTION = "SAVE_ACTION";
    private static final String LOAD_ACTION = "LOAD_ACTION";
    private Invoices invoices = new Invoices();
    private InvoicesWindow invoicesWindow;
    private ChecklistWindow checklistWindow;

    // MODIFIES: this
    // EFFECTS: construct the main window
    public MainWindow() {
        super("CRB Eligibility Application");
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);

//        this.readInvoices();

        this.createWindow();
        this.createBackgroundImage();
        this.setButtonsSet1();
        this.setButtonsSet3();
        this.setButtonsSet2();
        this.adjustWindow();
    }

    // MODIFIES: this
    // EFFECTS: set window size and layout
    private void createWindow() {
        setPreferredSize(new Dimension(1270, 800));
        ((JPanel) getContentPane()).setBorder(new EmptyBorder(10, 10, 10, 10));
        setLayout(null);
    }

    // MODIFIES: this
    // EFFECTS: pack and further set the properties of the window
    public void adjustWindow() {
        pack();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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
    // EFFECTS: read the invoices from the json file
    private void readInvoices() {
        try {
            invoices = jsonReader.read();
            System.out.println("Loaded " + "invoices" + " from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: save the invoices to the json file
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
    // EFFECTS: set buttons with properties
    private void setButtonsSet1() {
        JButton viewListButton = new JButton("Show All Invoices");
        viewListButton.setBounds(POSITION_STANDARD, 40, WIDTH_STANDARD, HEIGHT_STANDARD);
        add(viewListButton);
        viewListButton.setActionCommand(SHOW_INVOICES_ACTION);
        viewListButton.addActionListener(this);
        viewListButton.setForeground(Color.BLUE);

        JButton emptyListButton = new JButton("Clear All Invoices");
        emptyListButton.setBounds(POSITION_STANDARD, 120, WIDTH_STANDARD, HEIGHT_STANDARD);
        add(emptyListButton);
        emptyListButton.setActionCommand(CLEAR_INVOICES_ACTION);
        emptyListButton.addActionListener(this);
        emptyListButton.setForeground(Color.BLUE);

        JButton totalEarningButton = new JButton("Find Total Earning From The Invoices");
        totalEarningButton.setBounds(POSITION_STANDARD, 80, WIDTH_STANDARD, HEIGHT_STANDARD);
        add(totalEarningButton);
        totalEarningButton.setActionCommand(TOTAL_EARNING_ACTION);
        totalEarningButton.addActionListener(this);
        totalEarningButton.setForeground(Color.BLUE);
    }

    // MODIFIES: this
    // EFFECTS: set buttons with properties
    public void setButtonsSet2() {
        JButton checklistButton = new JButton("Checklist For CRB Eligibility");
        checklistButton.setBounds(POSITION_STANDARD, 260, WIDTH_STANDARD, HEIGHT_STANDARD);
        add(checklistButton);
        checklistButton.setActionCommand(CHECKLIST_ACTION);
        checklistButton.addActionListener(this);
        checklistButton.setForeground(Color.BLUE);

        JButton quitAppButton = new JButton("Quit Application");
        quitAppButton.setBounds(POSITION_STANDARD, 300, WIDTH_STANDARD, HEIGHT_STANDARD);
        add(quitAppButton);
        quitAppButton.setActionCommand(QUIT_APP_ACTION);
        quitAppButton.addActionListener(this);
        quitAppButton.setForeground(Color.BLUE);
    }

    // MODIFIES: this
    // EFFECTS: set buttons with properties
    public void setButtonsSet3() {
        JButton saveButton = new JButton("Save Invoices");
        saveButton.setBounds(POSITION_STANDARD, 180, WIDTH_STANDARD, HEIGHT_STANDARD);
        add(saveButton);
        saveButton.setActionCommand(SAVE_ACTION);
        saveButton.addActionListener(this);
        saveButton.setForeground(Color.BLUE);

        JButton loadButton = new JButton("Load Invoices");
        loadButton.setBounds(POSITION_STANDARD, 220, WIDTH_STANDARD, HEIGHT_STANDARD);
        add(loadButton);
        loadButton.setActionCommand(LOAD_ACTION);
        loadButton.addActionListener(this);
        loadButton.setForeground(Color.BLUE);
    }

    // MODIFIES: this, InvoicesWindow, ChecklistWindow,
    // EFFECTS: perform the functions according to the user interactions
    @Override
    public void actionPerformed(ActionEvent e) {
        String action = e.getActionCommand();
        if (action.equals(SHOW_INVOICES_ACTION)) {
            invoicesWindow = new InvoicesWindow(invoices);
        } else if (action.equals(CLEAR_INVOICES_ACTION)) {
            this.invoices.clearInvoices();
            invoicesWindow.dispose();
//            invoicesWindow = new InvoicesWindow(invoices);
//            saveInvoice();      // this is auto save
            JOptionPane.showMessageDialog(null, "Invoices List Cleared!");
        } else if (action.equals((TOTAL_EARNING_ACTION))) {
            JOptionPane.showMessageDialog(null, "Your total earning from the "
                    + "invoices list is: " + totalEarning(invoices));
        } else if (action.equals((CHECKLIST_ACTION))) {
            checklistWindow = new ChecklistWindow(invoices);
        } else if (action.equals(SAVE_ACTION)) {
            saveInvoice();
            JOptionPane.showMessageDialog(null, "The Invoices are saved!");
        } else if (action.equals(LOAD_ACTION)) {
            readInvoices();
            JOptionPane.showMessageDialog(null, "The Invoices are loaded!");
        } else if (action.equals(QUIT_APP_ACTION)) {
//            invoicesWindow.dispose();      // might cause quit issue
            printLog(EventLog.getInstance());   // log on console
            dispose();
        }
    }

//    // EFFECTS: clear the invoices
//    public void clearInvoices() {
//        invoices = new Invoices();
////        saveInvoice();      // this is auto save
//        invoicesWindow.dispose();
////        invoicesWindow = new InvoicesWindow(invoices);
//        JOptionPane.showMessageDialog(null, "Invoices List Cleared!");
//    }

    // EFFECTS: return the total earning from the invoices list
    public double totalEarning(Invoices ivos) {
        double total = 0;
        for (Invoice i : ivos.getInvoices()) {
            total = total + i.getAmount();
        }
        return total;
    }

    // EFFECTS: print the log on the console
    public void printLog(EventLog el) {
        for (Event next : el) {
            System.out.println();
            System.out.println(next);
        }
        repaint();
    }
}

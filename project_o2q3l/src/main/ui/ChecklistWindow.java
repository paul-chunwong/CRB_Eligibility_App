package ui;

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

// A checklist window for the CRB application
public class ChecklistWindow extends JFrame implements ActionListener {
    private static final int FIELD_POSITION = 60;
    private static final int FIELD_WIDTH = 800;
    JTextField incomeReductionField;
    JTextField didNotAppleField;
    JTextField notEligibleEIField;
    JTextField resideCanadaField;
    JTextField presentCanadaField;
    JTextField atLeast15Field;
    JTextField validSinField;
    JTextField earnMoreThan5000Field;
    JTextField notQuitJobField;
    JTextField notTurnDownField;
    JTextField seekingJobField;
    JTextField notIsolatingField;
    JTextField filledTaxField;

    InvoicesWindow invoicesWindow;
    Invoices invoices;
    private static final String FINISH_ACTION = "FINISH_ACTION";

    // MODIFIES: this
    // EFFECTS: construct the checklist window
    public ChecklistWindow(Invoices invoices) {
        super("Check Final Eligibility!");
        this.invoices = invoices;


        this.createWindow();
        this.createLabelsSet1();
        this.createLabelsSet2();
        this.createLabelsSet3();
        this.createLabelsSet4();
        this.createFieldsSet1();
        this.createFieldsSet2();

        this.createButtons();
        this.adjustWindow();
    }

    // MODIFIES: this
    // EFFECTS: set window size and layout
    private void createWindow() {
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        setPreferredSize(new Dimension(1000, 900));
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
    private void createLabelsSet1() {
        JLabel incomeReductionLabel = new JLabel("(ENTER T/F) You had a 50% reduction in your average "
                + "weekly income compared to the previous year due to COVID-19");
        incomeReductionLabel.setBounds(FIELD_POSITION, 60, 1000, 20);
        add(incomeReductionLabel);
        incomeReductionLabel.setForeground(Color.BLUE);

        JLabel didNotAppleLabel = new JLabel("(ENTER T/F) You did not apply for or receive other benefit");
        didNotAppleLabel.setBounds(FIELD_POSITION, 110, 1000, 20);
        add(didNotAppleLabel);
        didNotAppleLabel.setForeground(Color.BLUE);

        JLabel notEligibleEILabel = new JLabel("(ENTER T/F) You were not eligible for EI benefits");
        notEligibleEILabel.setBounds(FIELD_POSITION, 160, 1000, 20);
        add(notEligibleEILabel);
        notEligibleEILabel.setForeground(Color.BLUE);

        JLabel resideCanadaLabel = new JLabel("(ENTER T/F) You reside in Canada");
        resideCanadaLabel.setBounds(FIELD_POSITION, 210, 600, 20);
        add(resideCanadaLabel);
        resideCanadaLabel.setForeground(Color.BLUE);
    }

    // MODIFIES: this
    // EFFECTS: set labels
    public void createLabelsSet2() {
        JLabel presentCanadaLabel = new JLabel("(ENTER T/F) You were present in Canada");
        presentCanadaLabel.setBounds(FIELD_POSITION, 260, 400, 20);
        add(presentCanadaLabel);
        presentCanadaLabel.setForeground(Color.BLUE);

        JLabel atLeast15Label = new JLabel("(ENTER T/F) You are at least 15 years old");
        atLeast15Label.setBounds(FIELD_POSITION, 310, 600, 20);
        add(atLeast15Label);
        atLeast15Label.setForeground(Color.BLUE);

        JLabel validSinLabel = new JLabel("(ENTER T/F) You have a valid Social Insurance Number (SIN)");
        validSinLabel.setBounds(FIELD_POSITION, 360, 1000, 20);
        add(validSinLabel);
        validSinLabel.setForeground(Color.BLUE);

        JLabel earnMoreThan5000Label = new JLabel("(ENTER T/F) You earned at least $5,000 in 2019, 2020, "
                + "or in the 12 months before the date you apply");
        earnMoreThan5000Label.setBounds(FIELD_POSITION, 410, 1000, 20);
        add(earnMoreThan5000Label);
        earnMoreThan5000Label.setForeground(Color.BLUE);
    }

    // MODIFIES: this
    // EFFECTS: set labels
    public void createLabelsSet3() {
        JLabel notQuitJobLabel = new JLabel("(ENTER T/F) You have not quit your job or reduced your "
                + "hours voluntarily on or after September 27, 2020");
        notQuitJobLabel.setBounds(FIELD_POSITION, 460, 1000, 20);
        add(notQuitJobLabel);
        notQuitJobLabel.setForeground(Color.BLUE);

        JLabel seekingJobLabel = new JLabel("(ENTER T/F) You were seeking work during the period, "
                + "either as an employee or in self-employment");
        seekingJobLabel.setBounds(FIELD_POSITION, 560, 1000, 20);
        add(seekingJobLabel);
        seekingJobLabel.setForeground(Color.BLUE);

        JLabel notTurnDownLabel = new JLabel("(ENTER T/F) You have not turned down reasonable "
                + "work during the 2-week period you’re applying for");
        notTurnDownLabel.setBounds(FIELD_POSITION, 510, 1000, 20);
        add(notTurnDownLabel);
        notTurnDownLabel.setForeground(Color.BLUE);

        JLabel notIsolatingLabel = new JLabel("(ENTER T/F) You were not self-isolating or in "
                + "quarantine due to international travel");
        notIsolatingLabel.setBounds(FIELD_POSITION, 610, 1000, 20);
        add(notIsolatingLabel);
        notIsolatingLabel.setForeground(Color.BLUE);
    }

    // MODIFIES: this
    // EFFECTS: set labels
    public void createLabelsSet4() {
        JLabel filledTaxLabel = new JLabel("(ENTER T/F) You filed a 2019 or 2020 tax return");
        filledTaxLabel.setBounds(FIELD_POSITION, 660, 600, 20);
        add(filledTaxLabel);
        filledTaxLabel.setForeground(Color.BLUE);
    }

    // MODIFIES: this
    // EFFECTS: set fields
    private void createFieldsSet1() {
        incomeReductionField = new JTextField(30);
        incomeReductionField.setBounds(FIELD_POSITION, 80, FIELD_WIDTH, 20);
        add(incomeReductionField);

        didNotAppleField = new JTextField(30);
        didNotAppleField.setBounds(FIELD_POSITION, 130, FIELD_WIDTH, 20);
        add(didNotAppleField);

        notEligibleEIField = new JTextField(30);
        notEligibleEIField.setBounds(FIELD_POSITION, 180, FIELD_WIDTH, 20);
        add(notEligibleEIField);

        resideCanadaField = new JTextField(30);
        resideCanadaField.setBounds(FIELD_POSITION, 230, FIELD_WIDTH, 20);
        add(resideCanadaField);

        presentCanadaField = new JTextField(30);
        presentCanadaField.setBounds(FIELD_POSITION, 280, FIELD_WIDTH, 20);
        add(presentCanadaField);

        atLeast15Field = new JTextField(30);
        atLeast15Field.setBounds(FIELD_POSITION, 330, FIELD_WIDTH, 20);
        add(atLeast15Field);
    }

    // MODIFIES: this
    // EFFECTS: set fields
    public void createFieldsSet2() {
        validSinField = new JTextField(30);
        validSinField.setBounds(FIELD_POSITION, 380, FIELD_WIDTH, 20);
        add(validSinField);

        earnMoreThan5000Field = new JTextField(30);
        earnMoreThan5000Field.setBounds(FIELD_POSITION, 430, FIELD_WIDTH, 20);
        add(earnMoreThan5000Field);

        notQuitJobField = new JTextField(30);
        notQuitJobField.setBounds(FIELD_POSITION, 480, FIELD_WIDTH, 20);
        add(notQuitJobField);

        seekingJobField = new JTextField(30);
        seekingJobField.setBounds(FIELD_POSITION, 530, FIELD_WIDTH, 20);
        add(seekingJobField);

        notTurnDownField = new JTextField(30);
        notTurnDownField.setBounds(FIELD_POSITION, 580, FIELD_WIDTH, 20);
        add(notTurnDownField);

        notIsolatingField = new JTextField(30);
        notIsolatingField.setBounds(FIELD_POSITION, 630, FIELD_WIDTH, 20);
        add(notIsolatingField);

        filledTaxField = new JTextField(30);
        filledTaxField.setBounds(FIELD_POSITION, 680, FIELD_WIDTH, 20);
        add(filledTaxField);
    }

    // MODIFIES: this
    // EFFECTS: set buttons
    private void createButtons() {
        JButton finishButton = new JButton("Click Here To Check Final Eligibility!");
        finishButton.setBounds(250, 779, 500, 20);
        add(finishButton);
        finishButton.setActionCommand(FINISH_ACTION);
        finishButton.addActionListener(this);
        finishButton.setForeground(Color.RED);
    }

    // MODIFIES: this
    // EFFECTS: perform the function with the user interactions
    @Override
    public void actionPerformed(ActionEvent e) {
        String action = e.getActionCommand();
        if (action.equals(FINISH_ACTION)) {
            if (incomeReductionField.getText().equals("T") && didNotAppleField.getText().equals("T")
                    && notEligibleEIField.getText().equals("T") && resideCanadaField.getText().equals("T")
                    && presentCanadaField.getText().equals("T") && atLeast15Field.getText().equals("T")
                    && validSinField.getText().equals("T") && earnMoreThan5000Field.getText().equals("T")
                    && notQuitJobField.getText().equals("T") && seekingJobField.getText().equals("T")
                    && notTurnDownField.getText().equals("T") && notIsolatingField.getText().equals("T")
                    && filledTaxField.getText().equals("T")) {
                JOptionPane.showMessageDialog(null, "CONGRATS! You are eligible for CRB!!!!");
            } else {
                JOptionPane.showMessageDialog(null, "Sorry! You are not eligible for CRB....");
            }
            handleWindows();
        }
    }

    // MODIFIES: this
    // EFFECTS: close and reopen windows
    public void handleWindows() {
        invoicesWindow.dispose();
        new InvoicesWindow(invoices);
        this.dispose();
    }
}

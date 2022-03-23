package ui;

import model.Invoice;
import model.Invoices;

import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;

import java.util.Scanner;


// This [CanadaRecoveryBenefitEligibilityAPP class] references code from these:
// [https://github.students.cs.ubc.ca/CPSC210/TellerApp]
// [https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo]
// Application that determine the CRB eligibility
public class CanadaRecoveryBenefitEligibilityAPP {
    private static final String JSON_STORE = "./data/invoices.json";
    private Invoices ivos;
    private Scanner input;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    // EFFECTS: constructs invoices and runs application
    public CanadaRecoveryBenefitEligibilityAPP() throws FileNotFoundException {
        input = new Scanner(System.in);
        ivos = new Invoices();
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        input.useDelimiter("\n");
        runCanadaRecoveryBenefitEligibilityAPP();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    private void runCanadaRecoveryBenefitEligibilityAPP() {
        boolean keepGoing = true;
        String command = null;
        input = new Scanner(System.in);

        while (keepGoing) {
            displayMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }

        System.out.println("\nGoodbye!");
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommand(String command) {
        if (command.equals("a")) {
            doAddInvoice();
        } else if (command.equals("r")) {
            doRemoveInvoice();
        } else if (command.equals("f")) {
            doLength();
        } else if (command.equals("c")) {
            doIsContain();
        } else if (command.equals("g")) {
            doGetEarningWithType();
        } else if (command.equals("t")) {
            doToString();
        } else if (command.equals("l")) {
            loadInvoices();
        } else if (command.equals("s")) {
            saveInvoices();
        } else {
            System.out.println("Selection not valid...");
        }
    }

    // EFFECTS: saves the invoices to file
    private void saveInvoices() {
        try {
            jsonWriter.open();
            jsonWriter.write(ivos);
            jsonWriter.close();
            System.out.println("Saved " + "invoices" + " to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads invoices from file
    private void loadInvoices() {
        try {
            ivos = jsonReader.read();
            System.out.println("Loaded " + "invoices" + " from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

    // EFFECTS: displays menu of options to user
    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\ta -> Add Invoice");
        System.out.println("\tr -> Remove Invoice");
        System.out.println("\tf -> Find Length");
        System.out.println("\tc -> Check If Contained");
        System.out.println("\tg -> Get Earning With Type");
        System.out.println("\tt -> Show all Invoice");
        System.out.println("\tl -> Load the file");
        System.out.println("\ts -> Save the file");
        System.out.println("\tq -> Quit");
    }

    // EFFECTS: show the current number of invoice object in the invoices list
    private void doLength() {
        System.out.println();
        System.out.println("The current total number of invoice object in the invoices list is: " + ivos.length());
        System.out.println();

        System.out.println("Information was shown above, press q then press enter to continue!");
        String ending = input.next();
    }

    //EFFECTS: show the total earning for all the invoice from the invoices list that has same type as the user input
    public void doGetEarningWithType() {
        double totalEarning = 0;
        System.out.println();
        System.out.println("Enter the type of the invoices that you want to check: ");
        input.nextLine();
        String type = input.nextLine();

        totalEarning = ivos.getEarningWithType(type);
        System.out.println("The total earning for all the invoices that have the same type given by the user is: $"
                + totalEarning);
        System.out.println();

        System.out.println("Information was shown above, press q then press enter to continue!");
        String ending = input.next();
    }

    // EFFECTS: show all the invoice in the invoices list
    private void doToString() {
        System.out.println();
        for (int i = 0; i < ivos.getInvoices().size(); i++) {
            System.out.println(i + 1 + ") " + ivos.getInvoices().get(i));
        }
        if (ivos.getInvoices().size() == 0) {
            System.out.println("The invoices list is empty!");
        }
        System.out.println();

        System.out.println("Information was shown above, press q then press enter to continue!");
        String ending = input.next();
    }

    // EFFECTS: show whether the user given invoice object is in the invoices list
    private void doIsContain() {
        System.out.println();
        System.out.println("Enter the date of the invoice follows the format \"yyyy,mm,dd\" such as \"2020,05,20\": ");
        input.nextLine();
        String date = input.nextLine();
        System.out.println("Enter the amount of the invoice: ");
        double amount = input.nextDouble();
        input.nextLine();
        System.out.println("Enter the type of the invoice: ");
        String type = input.nextLine();
        System.out.println("Enter the id of the invoice: ");
        String id = input.nextLine();
        System.out.println();


        Invoice i = new Invoice(date, amount, type, id);
        System.out.println("Inputted invoice has the values: " + i.toString());
        boolean isContain = ivos.isContain(i);
        System.out.println("Does the invoices list contain the invoice? : " + isContain);
        System.out.println();

        System.out.println("Information was shown above, enter q here then press enter to continue!");
        String ending = input.next();
    }

    // MODIFIES: this
    // EFFECTS: add the invoice provided by the user to the invoices list
    private void doAddInvoice() {
        System.out.println();
        System.out.println("Enter the date of the invoice follows the format \"yyyy,mm,dd\" such as \"2020,05,20\": ");
        input.nextLine();
        String date = input.nextLine();
        System.out.println("Enter the amount of the invoice: ");
        double amount = input.nextDouble();
        input.nextLine();
        System.out.println("Enter the type of the invoice: ");
        String type = input.nextLine();
        System.out.println("Enter the id of the invoice: ");
        String id = input.nextLine();
        System.out.println();


        Invoice i = new Invoice(date, amount, type, id);
        System.out.println("Inputted invoice has the values: " + i.toString());
        ivos.addInvoice(i);
        System.out.println("Inputted invoice has been added in to the invoices list!");
        System.out.println();

        System.out.println("Information was shown above, enter q here then press enter to continue!");
        String ending = input.next();
    }

    //MODIFIES: this
    //EFFECTS: removes the Invoice if the Invoice from the Invoices list has the same id as the id given by user,
    //         no action will be made if no matched Invoice found
    private void doRemoveInvoice() {
        System.out.println();
        System.out.println("Enter the id of the invoice that you want to remove from the invoices list: ");
        input.nextLine();
        String id = input.nextLine();
        System.out.println();

        boolean isRemoved = ivos.removeInvoice(id);
        if (isRemoved) {
            System.out.println("The invoice with the id " + id + " has been removed from the invoices list!");
        } else {
            System.out.println("No action made because there is no invoice that has the same id "
                    + "in the invoices list!");
        }
        System.out.println();

        System.out.println("Information was shown above, enter q here then press enter to continue!");
        String ending = input.next();
    }
}
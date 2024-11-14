package Views.Administrator;

import Models.Medication;

import java.util.List;
import java.util.Scanner;
public class AdminsitratorInventoryView {
    private Scanner scanner = new Scanner(System.in);

    public void displayInventoryMenu() {
        System.out.println("\n--- Manage Medication Inventory ---");
        System.out.println("1. View inventory");
        System.out.println("2. Add new medication");
        System.out.println("3. Update stock");
        System.out.println("4. Delete medication");
        System.out.println("5. Return to main menu");
        System.out.print("Enter your choice: ");
    }

    public String getChoice() {
        return scanner.nextLine();
    }

    public String getMedicationName() {
        System.out.print("Enter medication name: ");
        return scanner.nextLine();
    }

    public int getInitialStock() {
        System.out.print("Enter initial stock: ");
        return Integer.parseInt(scanner.nextLine());
    }

    public int getLowStockAlertLevel() {
        System.out.print("Enter low stock alert level: ");
        return Integer.parseInt(scanner.nextLine());
    }

    public int getUpdatedStockLevel() {
        System.out.print("Enter new stock level: ");
        return Integer.parseInt(scanner.nextLine());
    }

    public void displayInventory(Medication medication) {
            System.out.println("Medication Name: " + medication.getName());
            System.out.println("Stock: " + medication.getStockLevel());
            System.out.println("Low Stock Alert Level: " + medication.getLowStockAlert());
    }

    public void displayMedicationAdded() {
        System.out.println("New medication added successfully.");
    }

    public void displayMedicationUpdated() {
        System.out.println("Medication stock updated successfully.");
    }

    public void displayMedicationDeleted() {
        System.out.println("Medication deleted successfully.");
    }

    public void displayError(String message) {
        System.out.println("Error: " + message);
    }
}

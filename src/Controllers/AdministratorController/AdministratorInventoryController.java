package Controllers.AdministratorController;

import DataManager.InventoryRepo;
import Models.Administrator;
import Models.Inventory;
import Models.Medication;
import Views.Administrator.AdminsitratorInventoryView;

import java.io.IOException;
import java.util.List;

public class AdministratorInventoryController {

    private AdminsitratorInventoryView inventoryView;
    private InventoryRepo inventoryRepo;

    public AdministratorInventoryController() {
        this.inventoryView = new AdminsitratorInventoryView();
        this.inventoryRepo = new InventoryRepo();
    }

    public void manageInventory(Administrator administrator) throws IOException, ClassNotFoundException {
        while (true) {
            inventoryView.displayInventoryMenu();
            String choice = inventoryView.getChoice();

            switch (choice) {
                case "1":
                    viewInventory();
                    break;
                case "2":
                    addNewMedication();
                    break;
                case "3":
                    updateMedicationStock();
                    break;
                case "4":
                    deleteMedication();
                    break;
                case "5":
                    return;
                default:
                    inventoryView.displayError("Invalid choice. Please try again.");
            }
        }
    }

    private void viewInventory() throws IOException, ClassNotFoundException {
        inventoryRepo.loadData();
        Inventory inventory = inventoryRepo.getData();
        List<Medication> medications = inventory.getMedications();

        if (medications.isEmpty()) {
            inventoryView.displayError("Inventory is empty.");
        } else {
            for (Medication medication : medications) {
                inventoryView.displayInventory(medication);
            }
        }
    }

    private void addNewMedication() throws IOException, ClassNotFoundException {
        inventoryRepo.loadData();
        Inventory inventory = inventoryRepo.getData();
        List<Medication> medications = inventory.getMedications();

        String name = inventoryView.getMedicationName();
        int initialStock = inventoryView.getInitialStock();
        int lowStockAlertLevel = inventoryView.getLowStockAlertLevel();

        Medication newMedication = new Medication(name, initialStock, lowStockAlertLevel);
        medications.add(newMedication);
        inventoryView.displayMedicationAdded();

        // Save the updated inventory
        inventoryRepo.setInventory(inventory);
        inventoryRepo.saveData();
    }

    private void updateMedicationStock() throws IOException, ClassNotFoundException {
        inventoryRepo.loadData();
        Inventory inventory = inventoryRepo.getData();
        List<Medication> medications = inventory.getMedications();

        String name = inventoryView.getMedicationName();
        Medication medication = findMedicationByName(name);

        if (medication != null) {
            int newStockLevel = inventoryView.getUpdatedStockLevel();
            medication.setStockLevel(newStockLevel);
            inventoryView.displayMedicationUpdated();

            // Save the updated inventory
            inventoryRepo.setInventory(inventory);
            inventoryRepo.saveData();
        } else {
            inventoryView.displayError("Medication not found.");
        }
    }

    private void deleteMedication() throws IOException, ClassNotFoundException {
        inventoryRepo.loadData();
        Inventory inventory = inventoryRepo.getData();
        List<Medication> medications = inventory.getMedications();

        String name = inventoryView.getMedicationName();
        Medication medication = findMedicationByName(name);

        if (medication != null) {
            medications.remove(medication);
            inventoryView.displayMedicationDeleted();

            // Save the updated inventory
            inventoryRepo.setInventory(inventory);
            inventoryRepo.saveData();
        } else {
            inventoryView.displayError("Medication not found.");
        }
    }

    private Medication findMedicationByName(String name) throws IOException, ClassNotFoundException {
        inventoryRepo.loadData();
        Inventory inventory = inventoryRepo.getData();
        List<Medication> medications = inventory.getMedications();

        for (Medication med : medications) {
            if (med.getName().equalsIgnoreCase(name)) {
                return med;
            }
        }
        return null;
    }
}

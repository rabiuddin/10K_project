package Views.Pharmacist;

import Controllers.PharmacistController.PharmacistController;
import DataManager.InventoryRepo;
import Models.Inventory;
import Models.Medication;
import Models.Pharmacist;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class PharmacistView {
    Scanner scanner = new Scanner(System.in);
    public void viewAppointmentRecordOutcome(Pharmacist pharmacist){

    }

    public void updatePrescriptionStatus(Pharmacist pharmacist) throws IOException, ClassNotFoundException {
        PharmacistController pharmacistController = new PharmacistController();
        System.out.println("Enter appointment ID: ");
        String appointmentID = scanner.nextLine();

        pharmacistController.updatePrescriptionStatus(appointmentID);
    }

    public void submitReplenishmentRequest(Pharmacist pharmacist) throws IOException, ClassNotFoundException {
        PharmacistController pharmacistController = new PharmacistController();
        viewMedicationInventory(pharmacist);

        System.out.println("Enter the medication:");
        String medicationName = scanner.nextLine();
        pharmacistController.submitReplenishmentRequest(medicationName);
    }

    public void viewMedicationInventory(Pharmacist pharmacist) throws IOException, ClassNotFoundException {
        InventoryRepo inventoryRepo = new InventoryRepo();
        inventoryRepo.loadData();
        Inventory inventory = inventoryRepo.getData();

        List<Medication> medications = inventory.getMedications();

        System.out.println("MEDICATIONS INVENTORY");
        for(Medication medication : medications){
            System.out.println("Name: " + medication.getName() +"\nStock Level: " + medication.getStockLevel());
        }
    }
}

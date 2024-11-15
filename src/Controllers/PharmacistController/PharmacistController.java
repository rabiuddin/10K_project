package Controllers.PharmacistController;

import DataManager.AppointmentsRepo;
import DataManager.InventoryRepo;
import DataManager.ReplenishmentRequestRepo;
import Models.*;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class PharmacistController {
    Scanner scanner = new Scanner(System.in);
    public void updatePrescriptionStatus(String appointmentID) throws IOException, ClassNotFoundException {
        AppointmentsRepo appointmentsRepo = new AppointmentsRepo();
        appointmentsRepo.loadData();
        List<Appointment> appointments = appointmentsRepo.getData();

        Appointment existingAppointment = null;
        for (Appointment appointment : appointments) {
            if (appointment.getAppointmentID().equals(appointmentID)) {
                existingAppointment = appointment;
                break;
            }
        }

        if(existingAppointment == null) {
            System.out.println("Appointment not found!!");
        }
        else{
            AppointmentOutcomeRecord appointmentOutcomeRecord = existingAppointment.getOutcomeRecord();
            if(appointmentOutcomeRecord == null){
                System.out.println("Appointment Outcome Record not found!!");
                return;
            }
            else{
                List<Prescription> prescription = appointmentOutcomeRecord.getPrescriptions();
                prescription.get(0).setStatus("dispenced");

                appointmentsRepo.setAppointments(appointments);
                appointmentsRepo.saveData();
            }
        }
    }
    public void submitReplenishmentRequest(String medicationName) throws IOException, ClassNotFoundException {
        InventoryRepo inventoryRepo = new InventoryRepo();
        inventoryRepo.loadData();
        Inventory inventory = inventoryRepo.getData();
        List<Medication> medications = inventory.getMedications();

        Medication existingMedication = null;
        boolean foundMedication = false;
        for(Medication medication : medications){
            if(medication.getName().equals(medicationName)){
                existingMedication = medication;
                foundMedication = true;
                break;
            }
        }

        if(!foundMedication){
            System.out.println("Medication not found!!\nNo changes made");
            return;
        }
        else{
            System.out.println("Enter quantity: ");
            int quantity = scanner.nextInt();

            ReplenishmentRequest replenishmentRequest = new ReplenishmentRequest(existingMedication, quantity, false);
            ReplenishmentRequestRepo replenishmentRequestRepo = new ReplenishmentRequestRepo();
            replenishmentRequestRepo.loadData();

            List<ReplenishmentRequest> replenishmentRequests = replenishmentRequestRepo.getData();
            replenishmentRequests.add(replenishmentRequest);
            replenishmentRequestRepo.setReplenishmentRequests(replenishmentRequests);
            replenishmentRequestRepo.saveData();
        }
    }
}

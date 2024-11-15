package Controllers.DoctorController;

import DataManager.AppointmentsRepo;
import DataManager.InventoryRepo;
import DataManager.UserRepo;
import Models.*;
import Views.Patient.PatientView;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DoctorController {
    public static Scanner scanner = new Scanner(System.in);
    public void viewPatientRecord(Doctor doctor, String patientID) throws IOException, ClassNotFoundException {
        UserRepo userRepo = new UserRepo();
        userRepo.loadData();
        List<User> users = userRepo.getData();

        PatientView patientView = new PatientView();
        boolean patientExists = false;
        Patient existingPatient = null;
        for(User user : users) {
            if(user.getUserID().equals(patientID)) {
                existingPatient = (Patient) user;
                patientExists = true;
                break;
            }
        }
        if(!patientExists) {
            System.out.println("Patient not found!!");
            return;
        }
        else {
            List<String> pastDiagnosis = existingPatient.getMedicalRecord().getPastDiagnosis();
            List<String> pastTreatments = existingPatient.getMedicalRecord().getPastTreatment();

            System.out.println("PAST DIAGNOSIS");
            for(String diagnosis: pastDiagnosis) {
                System.out.println(diagnosis);
            }System.out.println("PAST TREATMENTS");
            for(String treatment: pastTreatments) {
                System.out.println(treatment);
            }
        }
    }
    public void updatePatientMedicalRecord(String patientID, String diagnosis, String treatment) throws IOException, ClassNotFoundException {
        UserRepo userRepo = new UserRepo();
        userRepo.loadData();
        List<User> users = userRepo.getData();

        boolean patientExists = false;
        Patient existingPatient = null;
        for(User user : users) {
            if(user.getUserID().equals(patientID)) {
                existingPatient = (Patient) user;
                patientExists = true;
                break;
            }
        }

        if(!patientExists) {
            System.out.println("Patient not found!!");
            return;
        }
        else{
            MedicalRecord medicalRecord = existingPatient.getMedicalRecord();
            medicalRecord.getPastDiagnosis().add(diagnosis);
            medicalRecord.getPastTreatment().add(treatment);
            userRepo.setUsers(users);
            userRepo.saveData();
        }
    }
    public void setAvailability(Doctor doctor, LocalDateTime availableTime) throws IOException, ClassNotFoundException {
        UserRepo userRepo = new UserRepo();
        userRepo.loadData();
        List<User> users = userRepo.getData();
        int doctorIndex = 0;
        int size = users.size();
        for(;doctorIndex<size;doctorIndex++) {
            if(users.get(doctorIndex).getUserID().equals(doctor.getUserID())) {
                break;
            }
        }

        Doctor existingDoctor = (Doctor)users.get(doctorIndex);
        existingDoctor.getAvailability().add(availableTime);
        userRepo.setUsers(users);
        userRepo.saveData();
    }
    public void acceptOrDeclineAppointment(Doctor doctor, String appointmentID) throws IOException, ClassNotFoundException {
        AppointmentsRepo appointmentsRepo = new AppointmentsRepo();
        appointmentsRepo.loadData();
        List<Appointment> appointments = appointmentsRepo.getData();
        boolean validAppointment = false;
        Appointment existingAppointment = null;
        for(Appointment appointment: appointments) {
            if(appointment.getAppointmentID().equals(appointmentID)) {
                validAppointment = true;
                existingAppointment = appointment;
                break;
            }
        }
        if(!validAppointment) {
            System.out.println("Invalid Appointment!!");
            return;
        }
        else {
            System.out.print("Accept or Decline? (A/D): ");
            String decision = scanner.nextLine();
            if (decision.equalsIgnoreCase("A")) {
                existingAppointment.setStatus("Confirmed");
                System.out.println("Appointment accepted.");
                appointmentsRepo.setAppointments(appointments);
                appointmentsRepo.saveData();
            } else if (decision.equalsIgnoreCase("D")) {
                existingAppointment.setStatus("Declined");
                System.out.println("Appointment declined.");
                appointmentsRepo.setAppointments(appointments);
                appointmentsRepo.saveData();
            } else {
                System.out.println("Invalid input. No changes made.");
            }
        }
    }
    public void recordAppointmentOutcome(Doctor doctor, String appointmentID) throws IOException, ClassNotFoundException {
        AppointmentsRepo appointmentsRepo = new AppointmentsRepo();
        appointmentsRepo.loadData();
        List<Appointment> appointments = appointmentsRepo.getData();
        boolean validAppointment = false;
        Appointment existingAppointment = null;
        for(Appointment appointment: appointments) {
            if(appointment.getAppointmentID().equals(appointmentID)) {
                validAppointment = true;
                existingAppointment = appointment;
                break;
            }
        }
        if(!validAppointment) {
            System.out.println("Invalid Appointment ID!!");
        }
        else {
            InventoryRepo inventoryRepo = new InventoryRepo();
            inventoryRepo.loadData();
            Inventory inventory = inventoryRepo.getData();
            List<Medication> medications = inventory.getMedications();

            System.out.println("Enter Service Type: ");
            String serviceType = scanner.nextLine();
            System.out.println("Enter consultation Notes: ");
            String consultationNotes = scanner.nextLine();

            Medication existingMedication = null;

            while(true){
                System.out.println("MEDICATION IN THE INVENTORY");
                for(Medication medication: medications) {
                    System.out.println(medication.getName());
                }
                System.out.println("Enter Medication name: ");
                String medicationName = scanner.nextLine();
                for(Medication medication: medications) {
                    if(medication.getName().equals(medicationName)) {
                        existingMedication = medication;
                        break;
                    }
                }

                if(existingMedication == null) {
                    System.out.println("Medication not found!");
                    continue;
                }
                else{
                    break;
                }
            }

            List<Prescription> prescription = new ArrayList<Prescription>();
            prescription.add(new Prescription(existingMedication, "placed"));
            AppointmentOutcomeRecord appointmentOutcomeRecord = new AppointmentOutcomeRecord(appointmentID, LocalDate.now(), serviceType, prescription, consultationNotes);
            existingAppointment.setOutcomeRecord(appointmentOutcomeRecord);
            appointmentsRepo.setAppointments(appointments);
            appointmentsRepo.saveData();
        }
    }
}

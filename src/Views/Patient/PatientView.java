package Views.Patient;

import Controllers.PatientController.PatientController;
import Models.Patient;

import java.io.IOException;
import java.util.Scanner;

public class PatientView {
    public PatientView() {
    }

    static Scanner scanner = new Scanner(System.in);
    public void viewMedicalRecord(Patient patient) {
        System.out.println("Medical Record for " + patient.getName());
        System.out.println("Patient ID: " + patient.getUserID());
        System.out.println("Date of Birth: " + patient.getDateOfBirth());
        System.out.println("Gender: " + patient.getGender());
        System.out.println("Blood Type: " + patient.getBloodType());
        System.out.println("Contact Information:");
        System.out.println("  Phone: " + patient.getPhoneNumber());
        System.out.println("  Email: " + patient.getEmailAddress());
        System.out.println("Past Diagnoses:");
        for (String diagnosis : patient.getMedicalRecord().getPastDiagnosis()) {
            System.out.println("- " + diagnosis);
        }
        System.out.println("Past Treatments:");
        for (String treatment : patient.getMedicalRecord().getPastTreatment()) {
            System.out.println("- " + treatment);
        }
    }
    public void updatePersonalInfo(Patient patient) throws IOException, ClassNotFoundException {
        PatientController patientController = new PatientController();
        System.out.print("Enter new phone number: ");
        String newPhone = scanner.nextLine();
        System.out.print("Enter new email address: ");
        String newEmail = scanner.nextLine();

        patientController.updateContactInfo(newPhone, newEmail, patient);
        System.out.println("Contact information updated successfully.");
    }
    public void changePassword(Patient patient) throws IOException, ClassNotFoundException {
        PatientController patientController = new PatientController();
        System.out.print("Enter new password: ");
        String newPassword = scanner.nextLine();
        patientController.changePassword(newPassword, patient.getUserID());
    }

}

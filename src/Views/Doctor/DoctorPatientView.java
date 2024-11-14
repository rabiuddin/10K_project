package Views.Doctor;

import Controllers.DoctorController.DoctorController;
import DataManager.UserRepo;
import Models.Doctor;
import Models.Patient;
import Models.User;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;
public class DoctorPatientView {
    static Scanner scanner = new Scanner(System.in);
    public void viewPatientRecord(Doctor doctor) throws IOException, ClassNotFoundException {
        DoctorController doctorController = new DoctorController();
        System.out.print("Enter patient ID: ");
        String patientID = scanner.next();

        doctorController.viewPatientRecord(doctor, patientID);
    }

    public void updatePatientRecord(){
        System.out.print("Enter Patient ID: ");
        String patientID = scanner.next();
        System.out.print("Enter diagnosis: ");
        String diagnosis = scanner.next();
        System.out.print("Enter treatment: ");
        String treatment = scanner.next();


    }
}

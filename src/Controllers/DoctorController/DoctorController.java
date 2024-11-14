package Controllers.DoctorController;

import DataManager.UserRepo;
import Models.Doctor;
import Models.Patient;
import Models.User;
import Views.Patient.PatientView;

import java.io.IOException;
import java.util.List;

public class DoctorController {
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
}

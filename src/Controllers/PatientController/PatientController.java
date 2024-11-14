package Controllers.PatientController;

import DataManager.UserRepo;
import Models.MedicalRecord;
import Models.Patient;
import Models.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PatientController {

    public void updateContactInfo(String phoneNumber, String emailAddress, Patient patient) throws IOException, ClassNotFoundException {

        UserRepo userRepo = new UserRepo();
        userRepo.loadData();


        List<User> users = userRepo.getData();

        int userIndex = 0;
        int size = users.size();
        boolean userFound = false;

        for (; userIndex < size; userIndex++) {
            if (users.get(userIndex).getUserID().equals(patient.getUserID())) {
                Patient existingPatient = (Patient) users.get(userIndex);
                existingPatient.setPhoneNumber(phoneNumber);
                existingPatient.setEmailAddress(emailAddress);
                userFound = true;
                break;
            }
        }

        if (userFound) {
            userRepo.setUsers(users);
            userRepo.saveData();
            System.out.println("Contact information updated successfully.");
        } else {
            System.out.println("Patient not found in the records.");
        }
    }
}

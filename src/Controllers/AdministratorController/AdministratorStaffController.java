package Controllers.AdministratorController;

import DataManager.AppointmentsRepo;
import Models.*;
import Views.Administrator.AdministratorStaffView;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AdministratorStaffController {
    private Administrator admin;
    private AdministratorStaffView adminView;
    private List<User> users;
    private List<Appointment> appointments;

    public AdministratorStaffController(){}

    public AdministratorStaffController(Administrator admin, List<User> users, List<Appointment> appointments) {
        this.admin = admin;
        this.adminView = new AdministratorStaffView();
        this.users = users;
        this.appointments = appointments;
    }

    public void manageHospitalStaff() {
        while (true) {
            adminView.displayStaffManagementMenu();
            int choice = adminView.getChoiceInput();

            switch (choice) {
                case 1:
                    viewAllStaff();
                    break;
                case 2:
                    addNewStaff();
                    break;
                case 3:
                    removeStaff();
                    break;
                case 4:
                    updateStaff();
                    break;
                case 5:
                    return;
                default:
                    adminView.displayError("Invalid choice. Please try again.");
            }
        }
    }

    private void viewAllStaff() {
        System.out.println("All Hospital Staff:");
        for (User user : users) {
            if (!(user instanceof Patient)) {
                adminView.displayAllStaff(user);
            }
        }
    }

    private void addNewStaff() {
        String role = adminView.getRoleInput();
        String name = adminView.getNameInput();
        String password = adminView.getPasswordInput();
        String gender = adminView.getGenderInput();
        int age = adminView.getAgeInput();
        String userID = generateUserID(role);

        User newUser;
        switch (role) {
            case "Doctor":
                String specialization = adminView.getSpecializationInput();
                List<LocalDateTime> availability = adminView.getAvailabilityInput();
                newUser = new Doctor(userID, name, password, role, gender, age, specialization, availability);
                break;
            case "Pharmacist":
                newUser = new Pharmacist(userID, name, password, role, gender, age);
                break;
            case "Administrator":
                newUser = new Administrator(userID, name, password, role, gender, age);
                break;
            default:
                adminView.displayError("Invalid role.");
                return;
        }

        users.add(newUser);
        adminView.displayNewStaffAdded(userID);
    }

    private void removeStaff() {
        String userID = adminView.getUserIDToRemove();
        User userToRemove = null;
        for (User user : users) {
            if (user.getUserID().equals(userID) && !(user instanceof Patient)) {
                userToRemove = user;
                break;
            }
        }
        if (userToRemove != null) {
            users.remove(userToRemove);
            adminView.displayStaffRemoved();
        } else {
            adminView.displayError("Invalid User ID or not authorized to remove.");
        }
    }

    private void updateStaff() {
        // Update logic goes here (similar to addNewStaff)
        // The implementation can prompt the admin for updated details
    }

    private String generateUserID(String role) {
        // Generate unique user ID based on role
        return role.substring(0, 2).toUpperCase() + System.currentTimeMillis();
    }

    private List<Appointment> getAllAppointments() throws IOException, ClassNotFoundException {
        AppointmentsRepo app = new AppointmentsRepo();

        app.loadData();

        List<Appointment> appointments1 = new ArrayList<>();

        appointments1 = app.getData();

        return appointments1;
    }
    public void viewAppointmentsDetails() throws IOException, ClassNotFoundException {
        List<Appointment> appointmentsList = new ArrayList<>();
        appointmentsList = getAllAppointments();
        int length = appointmentsList.size();

        if(length == 0){
            System.out.println("No Appointments");
        }
        System.out.println("All Appointments:");
        for (Appointment apt : appointmentsList) {
            adminView.displayAppointments(apt);
        }
    }
}

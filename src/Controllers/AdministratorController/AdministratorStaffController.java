package Controllers.AdministratorController;

import DataManager.AppointmentsRepo;
import DataManager.UserRepo;
import Models.*;
import Views.Administrator.AdministratorStaffView;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AdministratorStaffController {

    Scanner scanner = new Scanner(System.in);
    private Administrator admin;
    private AdministratorStaffView adminView;
    private List<User> users;
    private List<Appointment> appointments;

    private UserRepo userRepo;

  
    public AdministratorStaffController() throws IOException, ClassNotFoundException {
        this.adminView = new AdministratorStaffView();
        this.users = new ArrayList<>();
        this.appointments = new ArrayList<>();

        userRepo = new UserRepo();

        userRepo.loadData();

        users = userRepo.getData();
    }

    public void manageHospitalStaff() throws IOException, ClassNotFoundException {
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

    private void viewAllStaff() throws IOException, ClassNotFoundException {
        System.out.println("All Hospital Staff:");
        for (User user : users) {
            if (!(user instanceof Patient)) {
                adminView.displayAllStaff();
            }
        }
    }

    private void addNewStaff() throws IOException, ClassNotFoundException {

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
        userRepo.setUsers(users);
        userRepo.saveData();
        adminView.displayNewStaffAdded(userID);
    }

    private void removeStaff() throws IOException {
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
            userRepo.setUsers(users);
            userRepo.saveData();
            adminView.displayStaffRemoved();
        } else {
            adminView.displayError("Invalid User ID or not authorized to remove.");
        }
    }

    private void updateStaff() throws IOException, ClassNotFoundException {

        adminView.displayAllStaff();
        System.out.println("Enter User id to update the user");
        String EnteredID = scanner.nextLine();


        User user = adminView.findUserById(EnteredID);
        User userToUpdate = null;

        if (user.getUserID().equals(EnteredID) && !(user instanceof Patient)) {
            userToUpdate = user;
        }

        if (userToUpdate != null) {
            String newName = adminView.getNameInput();
            if (!newName.isEmpty()) {
                userToUpdate.setName(newName);
            }

            // Update password
            System.out.println("Enter New Password");
            String newPassword = scanner.nextLine();
            if (!newPassword.isEmpty()) {
                userToUpdate.setPassword(newPassword);
            }

            // Update gender
            System.out.println("Enter Gender");
            String newGender = scanner.nextLine();
            if (!newGender.isEmpty()) {
                userToUpdate.setGender(newGender);
            }

            // Update age
            Integer newAge = adminView.getAgeInput();
            if (newAge != null) {
                userToUpdate.setAge(newAge);
            }

            // Special handling for Doctor specialization and availability
            if (userToUpdate instanceof Doctor doctor) {

                String newSpecialization = adminView.getSpecializationInput();
                if (!newSpecialization.isEmpty()) {
                    doctor.setSpecialization(newSpecialization);
                }

                List<LocalDateTime> newAvailability = adminView.getAvailabilityInput();
                if (!newAvailability.isEmpty()) {
                    doctor.setAvailability(newAvailability);
                }
            }
            users.add(userToUpdate);
            userRepo.setUsers(users);
            userRepo.saveData();
            adminView.displayAllStaff();
        } else {
            adminView.displayError("Invalid User ID or not authorized to update.");
        }
    }

        private String generateUserID (String role){
            return role.substring(0, 2).toUpperCase() + System.currentTimeMillis();
        }

        private List<Appointment> getAllAppointments () throws IOException, ClassNotFoundException {
            AppointmentsRepo app = new AppointmentsRepo();

            app.loadData();

            List<Appointment> appointments1 = new ArrayList<>();

            appointments1 = app.getData();

            return appointments1;
        }
        public void viewAppointmentsDetails () throws IOException, ClassNotFoundException {
            List<Appointment> appointmentsList = new ArrayList<>();
            appointmentsList = getAllAppointments();
            int length = appointmentsList.size();

            if (length == 0) {
                System.out.println("No Appointments");
            }
            System.out.println("All Appointments:");
            for (Appointment apt : appointmentsList) {
                adminView.displayAppointments(apt);
            }
        }
    }
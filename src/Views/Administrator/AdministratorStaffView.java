package Views.Administrator;

import DataManager.UserRepo;
import Models.Appointment;
import Models.User;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AdministratorStaffView {
    private Scanner scanner = new Scanner(System.in);

    public AdministratorStaffView() {
    }

    public void displayStaffManagementMenu() {
        System.out.println("\n--- Manage Hospital Staff ---");
        System.out.println("1. View all staff");
        System.out.println("2. Add new staff");
        System.out.println("3. Remove staff");
        System.out.println("4. Update staff");
        System.out.println("5. Return to main menu");
        System.out.print("Enter your choice: ");
    }

    public void displayAllStaff() throws IOException, ClassNotFoundException {
        UserRepo userRepo = new UserRepo();
        List<User> users = new ArrayList<>();
        userRepo.loadData();
        users = userRepo.getData();

        for(User user: users) {
            System.out.println(user.getUserID() + " - " + user.getName() + " (" + user.getRole() + ")");
        }
    }
    public User findUserById(String id) throws IOException, ClassNotFoundException {
        UserRepo userRepo = new UserRepo();
        List<User> users = new ArrayList<>();
        userRepo.loadData();
        users = userRepo.getData();

        for(User user: users){
            if(user.getUserID().equals(id)){
                return user;
            }
        }
        System.out.println("User not found");
        return null;
    }

    public String getRoleInput() {
        System.out.print("Enter role (Doctor/Pharmacist/Administrator): ");
        return scanner.nextLine();
    }

    public String getNameInput() {
        System.out.print("Enter name: ");
        return scanner.nextLine();
    }

    public String getPasswordInput() {
        System.out.print("Enter password: ");
        return scanner.nextLine();
    }

    public String getGenderInput() {
        System.out.print("Enter gender: ");
        return scanner.nextLine();
    }

    public int getAgeInput() {
        System.out.print("Enter age: ");
        return Integer.parseInt(scanner.nextLine());
    }

    public String getSpecializationInput() {
        System.out.print("Enter specialization: ");
        return scanner.nextLine();
    }

    public List<LocalDateTime> getAvailabilityInput() {
        List<LocalDateTime> availability = new ArrayList<>();
        System.out.println("Enter availability times (in format yyyy-MM-dd HH:mm). Enter 'done' when finished:");
        while (true) {
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("done")) break;
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                availability.add(LocalDateTime.parse(input, formatter));
            } catch (Exception e) {
                System.out.println("Invalid date format. Please try again.");
            }
        }
        return availability;
    }

    public String getUserIDToRemove() {
        System.out.print("Enter User ID to remove: ");
        return scanner.nextLine();
    }

    public void displayNewStaffAdded(String userID) {
        System.out.println("New staff added successfully. User ID: " + userID);
    }

    public void displayStaffRemoved() {
        System.out.println("Staff member removed successfully.");
    }

    public void displayError(String message) {
        System.out.println("Error: " + message);
    }

    public int getChoiceInput() {
        return Integer.parseInt(scanner.nextLine());
    }

    public void displayAppointments(Appointment apt) {
        System.out.println(apt.getAppointmentID() + " - " + apt.getAppointmentTime() + " (" + apt.getStatus() + ")"
        + apt.getDoctorID());

    }
}

package Controllers.ProcessOptions;

import Controllers.AdministratorController.AdministratorController;
import Controllers.AdministratorController.AdministratorInventoryController;
import Controllers.AdministratorController.AdministratorStaffController;
import Models.Administrator;

import java.io.IOException;

public class ProcessAdministratorChoice implements ProcessUserChoice<Administrator>{

    AdministratorStaffController administratorStaffController = new AdministratorStaffController();
    AdministratorInventoryController administratorInventoryController = new AdministratorInventoryController();
    AdministratorController administratorController = new AdministratorController();
    Administrator admin = new Administrator();

    public ProcessAdministratorChoice() throws IOException, ClassNotFoundException {
    }
    public ProcessAdministratorChoice(Administrator admin) throws IOException, ClassNotFoundException {
        admin = new Administrator();
        this.admin = admin;
    }

    @Override
    public void processUserChoice(Administrator administrator, String choice) throws IOException, ClassNotFoundException {
        switch (choice) {
            case "1": administratorStaffController.manageHospitalStaff(admin); break;
            case "2": administratorStaffController.viewAppointmentsDetails(admin); break;
            case "3": administratorInventoryController.manageInventory(admin); break;
            case "4": administratorController.approveReplenishmentRequests(admin); break;
            default: System.out.println("Invalid choice. Please try again.");
        }
    }
}

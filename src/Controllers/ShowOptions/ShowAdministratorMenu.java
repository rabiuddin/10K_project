package Controllers.ShowOptions;

public class ShowAdministratorMenu implements ShowUserMenu{
    @Override
    public void showUserMenu() {
        System.out.println("1. View and Manage Hospital Staff");
        System.out.println("2. View Appointments Details");
        System.out.println("3. View and Manage Medication Inventory");
        System.out.println("4. Approve Replenishment Requests");
    }
}

package Controllers.ShowOptions;

public class ShowPharmacistMenu implements ShowUserMenu{
    @Override
    public void showUserMenu() {
        System.out.println("1. View Appointment Outcome Record");
        System.out.println("2. Update Prescription Status");
        System.out.println("3. View Medication Inventory");
        System.out.println("4. Submit Replenishment Request");
    }
}

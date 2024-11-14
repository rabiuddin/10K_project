package Views.Administrator;

import Models.ReplenishmentRequest;

import java.util.List;
import java.util.Scanner;

public class AdministratorView {
    private Scanner scanner = new Scanner(System.in);

    public void displayReplenishmentRequests(List<ReplenishmentRequest> requests) {
        System.out.println("\n--- Replenishment Requests ---");
        if (requests.isEmpty()) {
            System.out.println("No replenishment requests to display.");
        } else {
            for (ReplenishmentRequest request : requests) {
                System.out.println("Request ID: " + request.getId());
                System.out.println("Medication: " + request.getMedication());
                System.out.println("Requested Quantity: " + request.getQuantity());
                System.out.println("Status: " + (request.isApproved() ? "Approved" : "Pending"));
                System.out.println("---------------");
            }
        }
    }

    public String getApprovalDecision(String requestId) {
        System.out.print("Approve request ID " + requestId + "? (yes/no): ");
        return scanner.nextLine().trim().toLowerCase();
    }

    public void displayApprovalSuccess(String requestId) {
        System.out.println("Request ID " + requestId + " approved successfully.");
    }

    public void displayApprovalFailure(String requestId) {
        System.out.println("Failed to approve request ID " + requestId + ". Please try again.");
    }

    public void displayInvalidRequestMessage(String requestId) {
        System.out.println("Request ID " + requestId + " not found or already approved.");
    }
}

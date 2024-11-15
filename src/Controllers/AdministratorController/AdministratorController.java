package Controllers.AdministratorController;

import DataManager.ReplenishmentRequestRepo;
import Models.ReplenishmentRequest;
import Views.Administrator.AdministratorView;

import java.io.IOException;
import java.util.List;

public class AdministratorController {
    private AdministratorView requestView;
    private List<ReplenishmentRequest> requests;
    private ReplenishmentRequestRepo requestRepo;

    public AdministratorController() throws IOException, ClassNotFoundException {
        this.requestView = new AdministratorView();
        this.requestRepo = new ReplenishmentRequestRepo();
        loadRequests();
    }

    private void loadRequests() throws IOException, ClassNotFoundException {
        requestRepo.loadData();
        this.requests = requestRepo.getData();
    }

    public void approveReplenishmentRequests() throws IOException {
        requestView.displayReplenishmentRequests(requests);

        for (ReplenishmentRequest request : requests) {
            if (!request.isApproved()) {  // Only prompt for unapproved requests
                String decision = requestView.getApprovalDecision(request.getId());

                if (decision.equals("yes")) {
                    request.setApproved(true);
                    requestView.displayApprovalSuccess(request.getId());
                    saveRequests();  // Save each time a request is approved
                } else if (decision.equals("no")) {
                    System.out.println("Skipping approval for request ID " + request.getId());
                } else {
                    requestView.displayApprovalFailure(request.getId());
                }
            } else {
                requestView.displayInvalidRequestMessage(request.getId());
            }
        }
    }

    private void saveRequests() {
        try {
            requestRepo.setReplenishmentRequests(requests);
            requestRepo.saveData();
        } catch (IOException e) {
            System.out.println("Failed to save replenishment request approvals.");
        }
    }
}

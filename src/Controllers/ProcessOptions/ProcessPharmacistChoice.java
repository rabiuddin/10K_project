package Controllers.ProcessOptions;

import Models.Pharmacist;
import Views.Pharmacist.PharmacistView;

import java.io.IOException;

public class ProcessPharmacistChoice implements ProcessUserChoice<Pharmacist>{
    @Override
    public void processUserChoice(Pharmacist pharmacist, String choice) throws IOException, ClassNotFoundException {
        PharmacistView pharmacistView = new PharmacistView();
        switch (choice) {
            case "1": pharmacistView.viewAppointmentRecordOutcome(pharmacist); break;
            case "2": pharmacistView.updatePrescriptionStatus(pharmacist); break;
            case "3": pharmacistView.viewMedicationInventory(pharmacist); break;
            case "4": pharmacistView.submitReplenishmentRequest(pharmacist); break;
            default: System.out.println("Invalid choice. Please try again.");
        }
    }
}

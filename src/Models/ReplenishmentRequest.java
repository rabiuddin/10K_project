package Models;

import java.io.Serializable;

public class ReplenishmentRequest implements Serializable {
    private Medication medication;
    private int quantity;
    private boolean approved;

    public ReplenishmentRequest() {
    }

    public ReplenishmentRequest(Medication medication, int quantity, boolean approved) {
        this.medication = medication;
        this.quantity = quantity;
        this.approved = approved;
    }

    public Medication getMedication() {
        return medication;
    }

    public void setMedication(Medication medication) {
        this.medication = medication;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }
}

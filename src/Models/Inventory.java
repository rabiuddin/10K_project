package Models;

import java.io.Serializable;
import java.util.List;

public class Inventory implements Serializable {
    private List<Medication> medications;

    public Inventory() {}
    public Inventory(List<Medication> medications) {
        this.medications = medications;
    }

    public List<Medication> getMedications() {
        return medications;
    }

    public void setMedications(List<Medication> medications) {
        this.medications = medications;
    }
}

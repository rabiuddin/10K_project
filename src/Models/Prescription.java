package Models;

import java.io.Serializable;

public class Prescription implements Serializable {
    private Medication medication;
    private String Status;

    public Prescription() {
    }

    public Prescription(Medication medication, String status) {
        this.medication = medication;
        Status = status;
    }

    public Medication getMedication() {
        return medication;
    }

    public void setMedication(Medication medication) {
        this.medication = medication;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }
}

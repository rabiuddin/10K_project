package Models;

import java.io.Serializable;
import java.util.List;

public class MedicalRecord implements Serializable {
    private String patientID;
    private List<String> pastDiagnosis;
    private List<String> pastTreatment;

    public MedicalRecord(String patientID, List<String> pastDiagnosis, List<String> pastTreatment) {
        this.patientID = patientID;
        this.pastDiagnosis = pastDiagnosis;
        this.pastTreatment = pastTreatment;
    }

    public MedicalRecord() {
    }

    public String getPatientID() {
        return patientID;
    }

    public void setPatientID(String patientID) {
        this.patientID = patientID;
    }

    public List<String> getPastDiagnosis() {
        return pastDiagnosis;
    }

    public void setPastDiagnosis(List<String> pastDiagnosis) {
        this.pastDiagnosis = pastDiagnosis;
    }

    public List<String> getPastTreatment() {
        return pastTreatment;
    }

    public void setPastTreatment(List<String> pastTreatment) {
        this.pastTreatment = pastTreatment;
    }
}

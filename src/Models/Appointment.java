package Models;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Appointment implements Serializable {
    private String appointmentID;
    private String patientID;
    private String doctorID;
    private LocalDateTime appointmentTime;
    private String status;
    private AppointmentOutcomeRecord outcomeRecord;

    public Appointment() {}
    public Appointment(String appointmentID, String patientID, String doctorID, LocalDateTime appointmentTime, String status, AppointmentOutcomeRecord outcomeRecord) {
        this.appointmentID = appointmentID;
        this.patientID = patientID;
        this.doctorID = doctorID;
        this.appointmentTime = appointmentTime;
        this.status = status;
        this.outcomeRecord = outcomeRecord;
    }

    public String getAppointmentID() {
        return appointmentID;
    }

    public void setAppointmentID(String appointmentID) {
        this.appointmentID = appointmentID;
    }

    public String getPatientID() {
        return patientID;
    }

    public void setPatientID(String patientID) {
        this.patientID = patientID;
    }

    public String getDoctorID() {
        return doctorID;
    }

    public void setDoctorID(String doctorID) {
        this.doctorID = doctorID;
    }

    public LocalDateTime getAppointmentTime() {
        return appointmentTime;
    }

    public void setAppointmentTime(LocalDateTime newDateTime) {
        this.appointmentTime = appointmentTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public AppointmentOutcomeRecord getOutcomeRecord() {
        return outcomeRecord;
    }

    public void setOutcomeRecord(AppointmentOutcomeRecord outcomeRecord) {
        this.outcomeRecord = outcomeRecord;
    }


}

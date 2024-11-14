package Models;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

public class AppointmentOutcomeRecord implements Serializable {
    private String appointmentId;
    private LocalDate date;
    private String serviceType;
    private List<Prescription> prescriptions;
    private String consultaionNotes;

    public AppointmentOutcomeRecord() {
    }

    public AppointmentOutcomeRecord(String appointmentId, LocalDate date, String serviceType, List<Prescription> prescriptions, String consultaionNotes) {
        this.appointmentId = appointmentId;
        this.date = date;
        this.serviceType = serviceType;
        this.prescriptions = prescriptions;
        this.consultaionNotes = consultaionNotes;
    }

    public String getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(String appointmentId) {
        this.appointmentId = appointmentId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public List<Prescription> getPrescriptions() {
        return prescriptions;
    }

    public void setPrescriptions(List<Prescription> prescriptions) {
        this.prescriptions = prescriptions;
    }

    public String getConsultaionNotes() {
        return consultaionNotes;
    }

    public void setConsultaionNotes(String consultaionNotes) {
        this.consultaionNotes = consultaionNotes;
    }
}

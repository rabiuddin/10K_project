package Models;

import java.io.Serializable;
import java.time.LocalDate;

public class Patient extends User implements Serializable {
    private String bloodType;
    private String phoneNumber;
    private String emailAddress;
    private LocalDate dateOfBirth;
    private MedicalRecord medicalRecord;

    public Patient(String userID, String name, String password,String role, String gender, int age, String bloodType, String phoneNumber, String emailAddress, LocalDate dateOfBirth) {
        super(userID, name, password, role, gender, age);
        this.bloodType = bloodType;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
        this.dateOfBirth = dateOfBirth;
    }

    public String getBloodType() {
        return bloodType;
    }

    public void setBloodType(String bloodType) {
        this.bloodType = bloodType;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public MedicalRecord getMedicalRecord() {
        return medicalRecord;
    }

    public void setMedicalRecord(MedicalRecord medicalRecord) {
        this.medicalRecord = medicalRecord;
    }

    @Override
    public String toString() {
        return super.toString() + "," + bloodType + "," + phoneNumber + "," + emailAddress + "," + String.valueOf(dateOfBirth);
    }
}

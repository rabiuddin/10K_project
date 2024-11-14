package Models;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

public class Doctor extends User implements Serializable {
    private String specialization;
    private List<LocalDateTime> availability;

    public Doctor() {
    }

    public Doctor(String userID, String name, String password, String role, String gender, int age, String specialization, List<LocalDateTime> availability) {
        super(userID, name ,password , role, gender, age);
        this.specialization = specialization;
        this.availability = availability;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public List<LocalDateTime> getAvailability() {
        return availability;
    }

    public void setAvailability(List<LocalDateTime> availability) {
        this.availability = availability;
    }

    @Override
    public String toString() {
        return super.toString() + "," + specialization + "," + availability;
    }
}

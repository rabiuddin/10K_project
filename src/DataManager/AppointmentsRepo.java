package DataManager;

import Models.Appointment;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class AppointmentsRepo implements SerializableRepo<List<Appointment>> {
    private static final String APPOINTMENTS_FILE = "C:\\Users\\Rabiuddin\\IdeaProjects\\untitled\\src\\DataManager\\appointments.txt";
    private List<Appointment> appointments = new ArrayList<>();

    @Override
    public void saveData() throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(APPOINTMENTS_FILE))) {
            oos.writeObject(appointments);
            System.out.println("Appointments data saved successfully.");
        }
    }

    @Override
    public void loadData() throws IOException, ClassNotFoundException {
        if (new File(APPOINTMENTS_FILE).exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(APPOINTMENTS_FILE))) {
                appointments = (List<Appointment>) ois.readObject();
                System.out.println("Appointments data loaded successfully.");
            }
        }
    }

    @Override
    public List<Appointment> getData() {
        return appointments;
    }

    public void setAppointments(List<Appointment> appointments) {
        this.appointments = appointments;
    }
}

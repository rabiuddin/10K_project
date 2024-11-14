package Views.Patient;

import Controllers.PatientController.PatientAppointmentController;
import DataManager.AppointmentsRepo;
import DataManager.UserRepo;
import Models.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class PatientAppointmentView {
    static Scanner scanner = new Scanner(System.in);
    public static void viewAvailableAppointmentSlots(List<User> users) {
        System.out.println("Available Appointment Slots:");
        for (User user : users) {
            if (user instanceof Doctor doctor) {
                System.out.println("Doctor: " + doctor.getName() + " (" + doctor.getSpecialization() + ")");
                for (LocalDateTime slot : doctor.getAvailability()) {
                    System.out.println("- " + slot.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
                }
            }
        }
    }
    public static void scheduleAppointment(Patient patient) throws IOException, ClassNotFoundException {
        PatientAppointmentController patientAppointmentController = new PatientAppointmentController();
        List<User> users = new ArrayList<>();
        UserRepo userRepo = new UserRepo();
        userRepo.loadData();
        users = userRepo.getData();
        viewAvailableAppointmentSlots(users);

        System.out.print("Enter Doctor ID: ");
        String doctorID = scanner.nextLine();
        System.out.print("Enter date (YYYY-MM-DD): ");
        String date = scanner.nextLine();
        System.out.print("Enter time (HH:MM): ");
        String time = scanner.nextLine();
        LocalDateTime appointmentDateTime = LocalDateTime.parse(date + "T" + time);

        patientAppointmentController.scheduleAppointment(doctorID, appointmentDateTime, patient);
    }
    public static void viewScheduledAppointments(Patient patient) throws IOException, ClassNotFoundException {
        System.out.println("Scheduled Appointments:");
        AppointmentsRepo appointmentsRepo = new AppointmentsRepo();
        appointmentsRepo.loadData();

        List<Appointment> appointments = appointmentsRepo.getData();

        for (Appointment apt : appointments) {
            if(apt.getPatientID().equals(patient.getUserID())){
                System.out.println("ID: " + apt.getAppointmentID() + ", Doctor: " + apt.getDoctorID() +
                        ", Date/Time: " + apt.getAppointmentTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")) +
                        ", Status: " + apt.getStatus());
            }
        }
    }
    private static Appointment findAppointment(String appointmentID,List<Appointment> appointments) {
        for (Appointment apt : appointments) {
            if (apt.getAppointmentID().equals(appointmentID)) {
                return apt;
            }
        }
        return null;
    }
    public static void rescheduleAppointment(Patient patient) throws IOException, ClassNotFoundException {

        PatientAppointmentController patientAppointmentController = new PatientAppointmentController();
        viewScheduledAppointments(patient);
        System.out.print("Enter the Appointment ID to reschedule: ");
        String appointmentID = scanner.nextLine();
        System.out.print("Enter new date (YYYY-MM-DD): ");
        String date = scanner.nextLine();
        System.out.print("Enter new time (HH:MM): ");
        String time = scanner.nextLine();
        LocalDateTime newDateTime = LocalDateTime.parse(date + "T" + time);
        patientAppointmentController.rescheduleAppointment(patient, appointmentID, newDateTime);

    }
    public static void cancelAppointment(Patient patient) throws IOException, ClassNotFoundException {
        PatientAppointmentController patientAppointmentController = new PatientAppointmentController();
        AppointmentsRepo appointmentsRepo = new AppointmentsRepo();
        appointmentsRepo.loadData();
        List<Appointment> appointments = appointmentsRepo.getData();

        System.out.println("Scheduled Appointments:");
        for (Appointment apt : appointments) {
            if (apt.getPatientID().equals(patient.getUserID())) {
                System.out.println("ID: " + apt.getAppointmentID() + ", Doctor: " + apt.getDoctorID() +
                        ", Date/Time: " + apt.getAppointmentTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")) +
                        ", Status: " + apt.getStatus());
            }
        }

        System.out.print("Enter the Appointment ID to cancel: ");
        String appointmentID = scanner.nextLine();
        patientAppointmentController.cancelAppointment(patient, appointmentID);
    }
}

package Views.Doctor;

import Controllers.DoctorController.DoctorController;
import DataManager.AppointmentsRepo;
import Models.Appointment;
import Models.Doctor;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class DoctorAppointmentView {
    Scanner scanner = new Scanner(System.in);
    public void acceptOrDeclineAppointment(Doctor doctor) throws IOException, ClassNotFoundException {
        DoctorController doctorController = new DoctorController();
        AppointmentsRepo appointmentsRepo = new AppointmentsRepo();
        appointmentsRepo.loadData();
        List<Appointment> appointments = appointmentsRepo.getData();
        for(Appointment appointment : appointments) {
            if(!appointment.getDoctorID().equals(doctor.getUserID())){
                appointments.remove(appointment);
            }
        }
        System.out.println("APPOINTMENTS");
        for(Appointment apt : appointments) {
            System.out.println("ID: " + apt.getAppointmentID() + ", Patient: " + apt.getPatientID() +
                    ", Date/Time: " + apt.getAppointmentTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
        }

        System.out.println("Enter appointment ID: ");
        String appointmentID = scanner.nextLine();

        doctorController.acceptOrDeclineAppointment(doctor, appointmentID);
    }
    public void viewUpcomingAppointments(Doctor doctor) throws IOException, ClassNotFoundException {
        AppointmentsRepo appointmentsRepo = new AppointmentsRepo();
        appointmentsRepo.loadData();
        List<Appointment> appointments = appointmentsRepo.getData();
        LocalDateTime currentTime = LocalDateTime.now();
        for(Appointment apt : appointments) {
            //This check if the appointment is of the doctor and is it before the current date and time
            if(!apt.getAppointmentTime().isAfter(currentTime) || !apt.getDoctorID().equals(doctor.getUserID())){
                appointments.remove(apt);
            }
        }

        System.out.println("UPCOMING APPOINTMENTS");
        for(Appointment apt : appointments) {
            System.out.println("ID: " + apt.getAppointmentID() + ", Patient: " + apt.getPatientID() +
                    ", Date/Time: " + apt.getAppointmentTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
        }
    }
    public void recordAppointmentOutcome(Doctor doctor) throws IOException, ClassNotFoundException {
        DoctorController doctorController = new DoctorController();
        AppointmentsRepo appointmentsRepo = new AppointmentsRepo();
        appointmentsRepo.loadData();
        List<Appointment> appointments = appointmentsRepo.getData();
        LocalDateTime currentTime = LocalDateTime.now();

        for(Appointment apt : appointments) {
            //This check if the appointment is of the doctor and is it before the current date and time
            if(!apt.getAppointmentTime().isBefore(currentTime) || !apt.getDoctorID().equals(doctor.getUserID())){
                appointments.remove(apt);
            }
        }

        for(Appointment apt : appointments) {
            System.out.println("ID: " + apt.getAppointmentID() + ", Patient: " + apt.getPatientID() +
                    ", Date/Time: " + apt.getAppointmentTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
        }

        System.out.println("Enter appointment ID: ");
        String appointmentID = scanner.nextLine();

        doctorController.recordAppointmentOutcome(doctor, appointmentID);
    }
}

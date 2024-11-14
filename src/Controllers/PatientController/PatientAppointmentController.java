package Controllers.PatientController;

import DataManager.AppointmentsRepo;
import DataManager.UserRepo;
import Models.*;
import Views.Patient.PatientAppointmentView;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class PatientAppointmentController{

    public void scheduleAppointment(String doctorID, LocalDateTime apointmentTime, Patient patient) throws IOException, ClassNotFoundException {
        List<User> users = new ArrayList<>();
        UserRepo userRepo = new UserRepo();
        userRepo.loadData();
        users = userRepo.getData();
        List<Doctor> doctors = new ArrayList<>();
        for(User user : users) {
            if (user instanceof Doctor doctor) {
                doctors.add(doctor);
            }
        }
        Doctor selectedDoctor = new Doctor();
        boolean doctorExists = false;

        for(Doctor doctor : doctors) {
            if(doctorID.equals(doctor.getUserID())){
                doctorExists = true;
                selectedDoctor = doctor;
                break;
            }
        }
        if(!doctorExists) {
            System.out.println("Doctor not found");
            return;
        }

        List<LocalDateTime> availability = selectedDoctor.getAvailability();
        boolean isAvailable = false;

        for(LocalDateTime available: availability){
            if(available.isEqual(apointmentTime)){
                isAvailable = true;
                break;
            }
        }
        if(!isAvailable){
            System.out.println("The doctor is not available at the selected time!!");
            return;
        }
        AppointmentsRepo appointmentsRepo = new AppointmentsRepo();
        appointmentsRepo.loadData();
        List<Appointment> appointments = new ArrayList<>();
        appointments = appointmentsRepo.getData();

        String appointmentID = "APT" + (appointments.size() + 1);
        Appointment newAppointment = new Appointment(appointmentID, patient.getUserID(),selectedDoctor.getUserID() , apointmentTime,"Confirmed", new AppointmentOutcomeRecord());
        appointments.add(newAppointment);
        System.out.println("Appointment scheduled successfully.");
        appointmentsRepo.setAppointments(appointments);
        appointmentsRepo.saveData();
    }

    public void rescheduleAppointment(Patient patient, String appointmentID, LocalDateTime newApointmentTime) throws IOException, ClassNotFoundException {
        AppointmentsRepo appointmentsRepo = new AppointmentsRepo();
        appointmentsRepo.loadData();
        List<Appointment> appointments = appointmentsRepo.getData();

        UserRepo userRepo = new UserRepo();
        List<Doctor> doctors = new ArrayList<>();
        userRepo.loadData();
        List<User> users = userRepo.getData();
        for (User user : users) {
            if (user instanceof Doctor doctor) {
                doctors.add(doctor);
            }
        }

        Appointment appointment = new Appointment();
        LocalDateTime previousTime = LocalDateTime.now();
        boolean apointmentExists = false;
        int appointmentIndex = 0;
        int size = appointments.size();
        for(; appointmentIndex < size; appointmentIndex++) {
            if(appointments.get(appointmentIndex).getAppointmentID().equals(appointmentID) && appointments.get(appointmentIndex).getPatientID().equals(patient.getUserID())){
                appointment = appointments.get(appointmentIndex);
                previousTime = appointment.getAppointmentTime();
                apointmentExists = true;
            }
        }

        if(!apointmentExists){
            System.out.println("Invalid Appointment ID!!");
            return;
        }


        Doctor selectedDoctor = new Doctor();
        int doctorsIndex = 0;
        for(; doctorsIndex < doctors.size(); doctorsIndex++) {
            if(doctors.get(doctorsIndex).getUserID().equals(appointment.getDoctorID())){
                selectedDoctor = doctors.get(doctorsIndex);
                break;
            }
        }

        List<LocalDateTime> timeList = selectedDoctor.getAvailability();

        if (appointment != null && appointment.getPatientID().equals(patient.getUserID())) {

            boolean isAvailable = false;
            for(LocalDateTime localDateTime: timeList){
                if(localDateTime.isEqual(newApointmentTime)){
                    isAvailable = true;
                }
            }
            if(!isAvailable){
                System.out.println("The doctor is not available at the selected time!!");
                return;
            }
            appointments.get(appointmentIndex).setAppointmentTime(newApointmentTime);
            appointmentsRepo.saveData();
            doctors.get(doctorsIndex).getAvailability().add(previousTime);
            userRepo.saveData();
            System.out.println("Appointment rescheduled successfully.");
        } else {
            System.out.println("Invalid appointment ID or not authorized to reschedule.");
        }
    }

    public void cancelAppointment(Patient patient, String appointmentID) throws IOException, ClassNotFoundException {
        AppointmentsRepo appointmentsRepo = new AppointmentsRepo();
        appointmentsRepo.loadData();
        List<Appointment> appointments = appointmentsRepo.getData();
        boolean appointmentFound = false;
        int size = appointments.size();
        for (int i = 0; i < size; i++) {
            Appointment apt = appointments.get(i);
            if (apt.getAppointmentID().equals(appointmentID) && apt.getPatientID().equals(patient.getUserID())) {
                appointments.remove(i);
                appointmentFound = true;
                System.out.println("Appointment canceled successfully.");
                break;
            }
        }

        if (!appointmentFound) {
            System.out.println("Invalid Appointment ID or you are not authorized to cancel this appointment.");
        } else {
            appointmentsRepo.setAppointments(appointments);
            appointmentsRepo.saveData();
        }
    }
}

package Views.Doctor;

import Controllers.DoctorController.DoctorController;
import Models.Doctor;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;



public class DoctorView {
    public static Scanner scanner = new Scanner(System.in);
    public void viewPersonalSchedule(Doctor doctor) {
        List<LocalDateTime> availability = doctor.getAvailability();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        System.out.println("SLOTS AVAILABLE:");
        for(LocalDateTime date : availability) {
            String dateTimeString = date.format(formatter);
            System.out.println(dateTimeString);
        }
    }
    public void setAvailability(Doctor doctor) throws IOException, ClassNotFoundException {
        DoctorController doctorController = new DoctorController();
        System.out.println("Enter date: ");
        String date = scanner.nextLine();
        System.out.println("Enter time: ");
        String time = scanner.nextLine();

        String dateTimeString = date + " " + time;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime avaialbleTime = LocalDateTime.parse(dateTimeString, formatter);
        doctorController.setAvailability(doctor, avaialbleTime);

        System.out.println("Availability added successfully.");
    }

}

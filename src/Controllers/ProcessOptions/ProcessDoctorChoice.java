package Controllers.ProcessOptions;

import Controllers.DoctorController.DoctorController;
import Models.Doctor;
import Views.Doctor.DoctorAppointmentView;
import Views.Doctor.DoctorPatientView;
import Views.Doctor.DoctorView;

import java.io.IOException;

public class ProcessDoctorChoice implements ProcessUserChoice<Doctor>{
    @Override
    public void processUserChoice(Doctor doctor, String choice) throws IOException, ClassNotFoundException {
        DoctorView doctorView = new DoctorView();
        DoctorAppointmentView appointmentView = new DoctorAppointmentView();
        DoctorPatientView patientView = new DoctorPatientView();
        switch (choice) {
            case "1": patientView.viewPatientRecord(doctor); break;
            case "2": patientView.updatePatientRecord(); break;
            case "3": doctorView.viewPersonalSchedule(doctor); break;
            case "4": doctorView.setAvailability(doctor); break;
            case "5": appointmentView.acceptOrDeclineAppointment(doctor); break;
            case "6": appointmentView.viewUpcomingAppointments(doctor); break;
            case "7": appointmentView.recordAppointmentOutcome(doctor); break;
            default: System.out.println("Invalid choice. Please try again.");
        }
    }
}

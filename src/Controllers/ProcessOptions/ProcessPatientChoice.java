package Controllers.ProcessOptions;

import Models.Patient;
import Views.Patient.PatientView;
import Views.Patient.PatientAppointmentView;

import java.io.IOException;


public class ProcessPatientChoice implements ProcessUserChoice<Patient>{
    private final PatientView patientView = new PatientView();
    private final PatientAppointmentView patientAppointmentView = new PatientAppointmentView();

    @Override
    public void processUserChoice(Patient patient, String choice) throws IOException, ClassNotFoundException {

        switch (choice) {
            case "1": patientView.viewMedicalRecord(patient); break;
            case "2": patientView.updatePersonalInfo(patient); break;
            case "3": patientAppointmentView.viewAvailableAppointmentSlots(); break;
            case "4": patientAppointmentView.scheduleAppointment(patient); break;
            case "5": patientAppointmentView.rescheduleAppointment(patient); break;
            case "6": patientAppointmentView.cancelAppointment(patient); break;
            case "7": patientAppointmentView.viewScheduledAppointments(patient); break;
            case "8": patientAppointmentView.viewPastAppointmentOutcomeRecords(patient); break;
            case "9": patientView.changePassword(patient); break;
            default: System.out.println("Invalid choice. Please try again.");
        }
    }
}

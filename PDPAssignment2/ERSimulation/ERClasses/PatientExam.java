package edu.neu.ccs.cs5010.assignment2.ersim;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;


/**
 * Created when a room is filled and an exam begins. Once exam starts, patient and emergency room
 * statistics are updated, once the once the visit time has elapsed, additional patient statistics
 * are updated, the patient is removed from the patient examination queue, and the exam room
 * becomes available again.
 **/
public class PatientExam extends Thread implements Callable<Boolean> {

    public Patient patient;
    private ExamRoom examRoom;
    private EmergencyRoom emergencyRoom;
    private ERStatisticsGatherer statisticsGatherer;

    /**
     * Manages patient exam, including visit and exam room statistics and routing patients
     *
     * @param emergencyRoom the emergency room in which the patient is being examined.
     * @param patient       patient who is being examined
     * @param examRoom      exam room where patient is being examined
     */
    PatientExam(EmergencyRoom emergencyRoom, Patient patient, ExamRoom examRoom,
        ERStatisticsGatherer statisticsGatherer) {

        this.emergencyRoom = emergencyRoom;
        this.patient = patient;
        this.examRoom = examRoom;
        this.statisticsGatherer = statisticsGatherer;

    }

    /* Run patient through the examination process until visit duration has elapsed */
    @Override
    public Boolean call() throws Exception {

        boolean isAvailable = false;

        if (this.patient != null && this.examRoom != null && this.emergencyRoom != null) {

            /* calculate and set patient visit duration and projected departure time */
            calcProjVisitDurationAndDepartureTime(this.patient);

            /* set visit exam room number */
            this.patient.getPatientHistory().getCurrentVisit().setExamRoomNumber(this.examRoom.getRoomNumber());

            /* update patient to treatment statistics */
            this.statisticsGatherer.updatePatientToTreatmentStatistics(this.patient);

            /* set exam room projected depature time as same as patient's projected departure time */
            this.examRoom.setProjectedDepartureTime(this.patient.getPatientHistory().getCurrentVisit().getDepartureTime());

            /* add patient to patient examination queue */
            this.emergencyRoom.getPatientExaminationQueue().insert(this.patient);

            /* set the time when the room will become available--between now and
            the projected departure time for the room, which is set based on urgency. */
            long timeUntilRoomAvailable = Duration.between(LocalDateTime.now(),
                this.examRoom.getProjectedDepartureTime()).toMillis();

            this.examRoom.updateExamRoomStatistics(this.emergencyRoom); // update room stats (num visits, avg util)

            try {
                Thread.sleep(timeUntilRoomAvailable); // put thread to sleep for amount of time the room will be busy
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }

            /* update the emergency room statistics (in this case visit duration) */
            this.statisticsGatherer.updatePatientToReleaseStatistics(this.patient);

            /*  remove patient from patient examination queue */
            this.emergencyRoom.getPatientExaminationQueue().patientExitsExamRoom(this.patient);

            this.examRoom.setAvailability(true); // set exam room to available

            // re-sort exam room queue
            this.emergencyRoom.getExamRoomQueue().sortExamRoomQueue(this.examRoom);

            isAvailable = true;
        }

        return isAvailable; // Report back that room is now available
    }


    /** Sets the exam start time as now, retrieves expected visit duration (in minutes)
     *  based on the urgency of the patient's condition, then calculates and sets
     *  the patient's projected departure time based on the exam start time
     *  and expected visit duration.
     * @param patient patient who is being examined
     */
    private void calcProjVisitDurationAndDepartureTime(Patient patient) {

        /* Get and set the exam start time for this visit */
        LocalDateTime examStartTime = LocalDateTime.now();
        patient.getPatientHistory().getCurrentVisit().setExamStartTime(examStartTime);

        long visitDurationInMin = patient.getPatientHistory().getCurrentVisit()  // calc the projected visit duration
           .calcProjectedVisitDurationInMin();

        patient.getPatientHistory().getCurrentVisit().setVisitDurationInMinutes(visitDurationInMin); // set duration

        /* Calculate the projected departure time based on the projected duration */
        LocalDateTime projectedDepartureTime = patient.getPatientHistory().getCurrentVisit()
                                                   .calcProjectedDepartureTime(visitDurationInMin);

        // set visit projected departure time
        patient.getPatientHistory().getCurrentVisit().setDepartureTime(projectedDepartureTime);

    }
}

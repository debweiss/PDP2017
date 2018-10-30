package edu.neu.ccs.cs5010.assignment2.ersim;

import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

import java.time.LocalDateTime;


public class EmergencyRoomTest {



    private EmergencyRoom testRoom;
    private EmergencyRoom testRoom1;
    private Patient patient1;
    private Patient patient2;
    private Patient patient3;
    private Patient patient4;

    private LocalDateTime startTime = LocalDateTime.now();
    private LocalDateTime endTime10Min = startTime.plusMinutes(10);
    private LocalDateTime endTime60Min = startTime.plusHours(1);

    @Before
    public void setUp() throws Exception {

        testRoom = new EmergencyRoom(4, new ERStatisticsGatherer(startTime));
        testRoom1 = new EmergencyRoom(5, new ERStatisticsGatherer(startTime));

        patient1 = EmergencyRoom.generateNewPatient(1, 5);
        patient2 = EmergencyRoom.generateNewPatient(2, 5);
        patient3 = EmergencyRoom.generateNewPatient(3, 5);

        patient4 = EmergencyRoom.generateNewPatient(4, 5);

        patient1.getPatientHistory().getCurrentVisit().setUrgency(testRoom.getPatientArrivalQueue()
            .calcPatientVisitUrgency(patient1));
        patient2.getPatientHistory().getCurrentVisit().setUrgency(testRoom.getPatientArrivalQueue()
            .calcPatientVisitUrgency(patient2));
        patient3.getPatientHistory().getCurrentVisit().setUrgency(testRoom.getPatientArrivalQueue()
             .calcPatientVisitUrgency(patient3));
        patient4.getPatientHistory().getCurrentVisit().setUrgency(testRoom.getPatientArrivalQueue()
             .calcPatientVisitUrgency(patient4));

        testRoom.getPatientArrivalQueue().insert(patient1);
        testRoom.getPatientArrivalQueue().insert(patient2);
        testRoom.getPatientArrivalQueue().insert(patient3);
        testRoom.getPatientArrivalQueue().insert(patient4);


        testRoom.getPatientExaminationQueue().getQueue().add(patient1);
        testRoom.getPatientExaminationQueue().getQueue().add(patient2);
        testRoom.getPatientExaminationQueue().getQueue().add(patient3);
        testRoom.getPatientExaminationQueue().getQueue().add(patient4);

    }

    @Test
    public void generateNewPatient() throws Exception {

        Patient newPatient = EmergencyRoom.generateNewPatient(5, 5);

        Assert.assertTrue("patient has an id of 5", newPatient.getId() == 5);
        Assert.assertTrue("patient first name has  5 characters",
            newPatient.getFirstName().length() == 5);
        Assert.assertTrue("patient last name has  5 characters",
            newPatient.getLastName().length() == 5);

    }

    @Test
    public void testUpdatePatientToTreatmentStatistics() {

        testRoom1.getStatisticsGatherer().setTotalPatientVisits(1);
        testRoom1.getStatisticsGatherer().setTotalWaitTimeInMinutes(10);

        patient4.getPatientHistory().getCurrentVisit().setArrivalTime(startTime);
        patient4.getPatientHistory().getCurrentVisit().setExamStartTime(endTime10Min); // wait time is 10 min

        patient4.getPatientHistory().getCurrentVisit().setDepartureTime(endTime60Min); // visit duration is 50 min

        patient4.getPatientHistory().getCurrentVisit().setUrgency(1);

        testRoom1.getStatisticsGatherer().updatePatientToTreatmentStatistics(patient4);

        Assert.assertEquals("total patient visits should be 2 ", 2,
            testRoom1.getStatisticsGatherer().getTotalPatientVisits());
        Assert.assertEquals("total patient wait time should be 20 ", 20,
            testRoom1.getStatisticsGatherer().getTotalWaitTimeInMinutes());
        Assert.assertEquals("total high urgency visits should be 1 ", 1,
            testRoom1.getStatisticsGatherer().getTotalHighUrgencyPatientVisits());
        Assert.assertEquals("total wait time by high urgency should be 10", 10,
            testRoom1.getStatisticsGatherer().getTotalHUPWaitTimeInMinutes());
    }

    @Test
    public void testUpdatePatientToReleaseStatistics() {


        patient4.getPatientHistory().getCurrentVisit().setVisitDurationInMinutes(60);
        testRoom1.getStatisticsGatherer().updatePatientToReleaseStatistics(patient4);

        Assert.assertEquals("total visit duration should be 60 ", 60,
            testRoom1.getStatisticsGatherer().getTotalVisitDurationInMinutes());

      }

}

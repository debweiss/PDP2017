package edu.neu.ccs.cs5010.assignment2.ersim;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;


public class PatientExaminationQueueTest {

    private Patient patient1;
    private Patient patientEarlyDepartureTime;
    private Patient patientLateDepartureTime;
    private Patient patientSameDepTimeAsPat1;
    private PatientExaminationQueue patQueue;
    private LocalDateTime patient1DepartureTime;
    private LocalDateTime earlyDepartureTime;
    private LocalDateTime lateDepartureTime;

    @Before
    public void setUp() throws Exception {

        patient1 = EmergencyRoom.generateNewPatient(4, 4);
        patientEarlyDepartureTime = EmergencyRoom.generateNewPatient(5, 5);
        patientLateDepartureTime = EmergencyRoom.generateNewPatient(6, 6);
        patientSameDepTimeAsPat1 = EmergencyRoom.generateNewPatient(7, 6);

        patient1DepartureTime = LocalDateTime.now();
        earlyDepartureTime = patient1DepartureTime.plusMinutes(1);
        lateDepartureTime = earlyDepartureTime.plusMinutes(1);

        patient1.getPatientHistory().getCurrentVisit().setDepartureTime(patient1DepartureTime);
        patientEarlyDepartureTime.getPatientHistory().getCurrentVisit().setDepartureTime(earlyDepartureTime);
        patientLateDepartureTime.getPatientHistory().getCurrentVisit().setDepartureTime(lateDepartureTime);
        patientSameDepTimeAsPat1.getPatientHistory().getCurrentVisit().setDepartureTime(patient1DepartureTime);

        patQueue = new PatientExaminationQueue();

        patQueue.insert(patient1); // id 4
        patQueue.insert(patientEarlyDepartureTime);  // 5
        patQueue.insert(patientLateDepartureTime); // 6
        patQueue.insert(patientSameDepTimeAsPat1); // 7

    }


    @Test
    public void testConstructor() {

        PatientExaminationQueue patientExaminationQueue = new PatientExaminationQueue();

        PatientExaminationQueue.ERPatientExaminationQueueComparator patientExaminationQueueComparator =
            new PatientExaminationQueue.ERPatientExaminationQueueComparator();

        Assert.assertTrue("the queue comparator is an ERPatientExaminationQueueComparator",
            patientExaminationQueue.getQueueComparator().getClass()
                .equals(patientExaminationQueueComparator.getClass()));

        Assert.assertTrue("queue list isn't null", patientExaminationQueue.getQueue() != null);

    }

    @Test
    public void testPatientExamQueueComparator()   {

        PatientExaminationQueue.ERPatientExaminationQueueComparator patexamcomparator = new PatientExaminationQueue.ERPatientExaminationQueueComparator();

        Assert.assertEquals("result should be zero, because both patients have the same departure time",
            0, patexamcomparator.compare(patient1, patientSameDepTimeAsPat1), 0);

        Assert.assertEquals("result should be 1, because the second patient has an earlier departure time",
            1, patexamcomparator.compare(patientLateDepartureTime, patientEarlyDepartureTime), 0);

        Assert.assertEquals("result should be -1, because the second patient has a later departure time",
            -1, patexamcomparator.compare(patientEarlyDepartureTime, patientLateDepartureTime), 0);

}

    @Test
    public void testPatientExitsExamRoom() throws Exception {


        patQueue.patientExitsExamRoom(patientSameDepTimeAsPat1);

        Assert.assertFalse("should return false, because after the patient exits exam rm, they exit pat exam queue also",
            patQueue.getQueue().contains(patientSameDepTimeAsPat1));
    }

}










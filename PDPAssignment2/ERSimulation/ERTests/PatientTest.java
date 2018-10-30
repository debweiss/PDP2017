package edu.neu.ccs.cs5010.assignment2.ersim;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class PatientTest {

    /* set visit duration for each urgency level for
    simulation purposes */
    private static final int HU_EXAM_DURATION_IN_MIN = 5;
    private static final int MU_EXAM_DURATION_IN_MIN = 3;
    private static final int LU_EXAM_DURATION_IN_MIN = 2;

    private Patient constructorPatient;
    private Patient.PatientHistory constructorPatientHistory;
    private Patient.PatientHistory.Visit constructorPatientVisit;
    private Patient constructorPatientCompare;
    private Patient setterPatient;
    private Patient setterPatient2;
    private Patient earlyArrivalHighUrgencyPatient;
    private Patient lateArrivalHighUrgencyPatient;
    private Patient earlyArrivalMiddleUrgencyPatient;
    private Patient lateArrivalMiddleUrgencyPatient;
    private Patient earlyArrivalLowUrgencyPatient;
    private Patient lateArrivalLowUrgencyPatient;


    private LocalDate arrivalDate = LocalDate.now();
    private LocalDateTime earlyArrivalTime = LocalDateTime.now();
    private LocalDateTime lateArrivalTime = LocalDateTime.now().plusHours(1);
    private LocalDateTime earlyDepartureTime = LocalDateTime.now().plusMinutes(90);
    private LocalDateTime lateDepartureTime = earlyDepartureTime.plusMinutes(30);

    private LocalDateTime startTime = LocalDateTime.now();
    private LocalDateTime endTime10Min = startTime.plusMinutes(10);
    private LocalDateTime endTime1Min = startTime.plus(60000, ChronoUnit.MILLIS);
    private LocalDateTime endTime60Min = startTime.plusHours(1);


    private Patient.urgencyComparator urgencyComparator;
    private Patient.arrivalTimeComparator patientArrivalTimeComparator;
    private Patient.departureTimeComparator patientDepartureTimeComparator;


    @Before
    public void setUp() throws Exception {

        constructorPatient = new Patient(81, "George", "Washington", 34);
        constructorPatientVisit = new Patient.PatientHistory.Visit(LocalDate.now(), LocalDateTime.now(),
            0.0, 0, 0);
        constructorPatientHistory = new Patient.PatientHistory(constructorPatientVisit);
        constructorPatient.setPatientHistory(constructorPatientHistory);

        constructorPatientCompare = new Patient(81, "George", "Washington", 34);
        constructorPatientCompare.setPatientHistory(constructorPatientHistory);

        setterPatient = new Patient(82, "Fred", "Meyer", 44);
        setterPatient.setPatientHistory(constructorPatientHistory);

        setterPatient2 = setterPatient;

        earlyArrivalHighUrgencyPatient = new PatientGenerator(75, 5, arrivalDate,
            earlyArrivalTime, 1).getNewPatient();

        lateArrivalHighUrgencyPatient = new PatientGenerator(76, 5, arrivalDate,
            lateArrivalTime,1).getNewPatient();

        earlyArrivalMiddleUrgencyPatient = new PatientGenerator(77, 5, arrivalDate,
            earlyArrivalTime,2).getNewPatient();

        lateArrivalMiddleUrgencyPatient = new PatientGenerator(78, 5, arrivalDate,
            lateArrivalTime,2).getNewPatient();

        earlyArrivalLowUrgencyPatient = new PatientGenerator(79, 5, arrivalDate,
            earlyArrivalTime,3).getNewPatient();

        lateArrivalLowUrgencyPatient = new PatientGenerator(80, 5, arrivalDate,
            lateArrivalTime,3).getNewPatient();

        patientArrivalTimeComparator = new Patient.arrivalTimeComparator();
        patientDepartureTimeComparator = new Patient.departureTimeComparator();
        urgencyComparator = new Patient.urgencyComparator();

    }

    @Test
    public void testConstructors() throws Exception {
        // test Patient Constructor
        Assert.assertEquals("Id should be 81, because that's what was passed to the constructor",
            81, constructorPatient.getId(), 0);
        Assert.assertTrue("First name should be George, because that's what was passed to the constructor",
            constructorPatient.getFirstName().equals("George"));
        Assert.assertTrue("Last name should be Washington, because that's what was passed to the constructor",
            constructorPatient.getLastName().equals("Washington"));
        Assert.assertEquals("Age in years should be 34, because that's what was passed to the constructor",
            34, constructorPatient.getAgeInYrs(), 0);
    }

    @Test
    public void testSetters() throws Exception {

        setterPatient.setFirstName("Joe");
        setterPatient.setLastName("Bob");
        setterPatient.setAgeInYrs(90);

        List<Patient.PatientHistory.Visit> constructorVisitList = earlyArrivalMiddleUrgencyPatient
                                                                           .getPatientHistory().getVisitList();

        constructorVisitList.add(new Patient.PatientHistory.Visit(arrivalDate, earlyArrivalTime,
                                                                     0.0, 0, 0));

        Patient.PatientHistory testSetterHistory = new Patient.PatientHistory(constructorVisitList);

        Patient.PatientHistory.Visit setterVisit = new Patient.PatientHistory.Visit(arrivalDate, lateArrivalTime,
                                                                                       .01, 1, 2);

        setterVisit.setExamStartTime(lateArrivalTime);
        setterVisit.setExamRoomNumber(3);

        List<Patient.PatientHistory.Visit> testVisitList = earlyArrivalMiddleUrgencyPatient
                                                                    .getPatientHistory().getVisitList();

        testVisitList.get(testVisitList.size() - 1).setExamStartTime(earlyArrivalTime);
        testVisitList.get(testVisitList.size() - 1).setExamRoomNumber(1);

        setterPatient.setPatientHistory(testSetterHistory);
        setterPatient2.setPatientHistory(testSetterHistory);
        setterPatient2.getPatientHistory().setVisitList(testVisitList);
        setterPatient2.getPatientHistory().getCurrentVisit().setExamStartTime(earlyArrivalTime);
        setterPatient2.getPatientHistory().getCurrentVisit().setExamRoomNumber(1);

        setterVisit.setVisitDurationInMinutes(20);

        Assert.assertTrue("First name should be 'Joe' because that's what was set",
            setterPatient.getFirstName().equals("Joe"));
        Assert.assertTrue("Last name should be 'Bob' because that's what was set",
            setterPatient.getLastName().equals("Bob"));
        Assert.assertEquals("Age in years should be 90, because that's what was set",
            90, setterPatient.getAgeInYrs(), 0);

        Assert.assertTrue("setterPatient patient history should be the same as testSetterHistory, "
             + "because that's what was set", setterPatient.getPatientHistory().equals(testSetterHistory));

        Assert.assertTrue("setterPatient2 visitlist should be the same as testVisitList, "
             + "because that's what was set", setterPatient2.getPatientHistory().getVisitList().equals(testVisitList));

        Assert.assertTrue("setterPatient2 currentVisit examStartTime should be the same as, "
             + " earlyArrivalTime, because that's what was set", setterPatient2.getPatientHistory()
             .getCurrentVisit().getExamStartTime().equals(earlyArrivalTime));

        Assert.assertEquals("setterPatient2 currentVisit examRoom number should be 1, "
             + "because that's what was set", 1, setterPatient2.getPatientHistory()
             .getCurrentVisit().getExamRoomNumber());

        Assert.assertEquals("setterVisit examStartTime should be the same as, "
            + " lateArrivalTime, because that's what was set", lateArrivalTime, setterVisit.getExamStartTime());

        Assert.assertEquals("setterVisit visit duration in minutes should be 20, "
            + "because that's what was set", 20, setterVisit.getVisitDurationInMinutes());

        Assert.assertEquals("setterVisit examRoom number should be 3, "
           + "because that's what was set", 3, setterVisit.getExamRoomNumber());
    }

    @Test
    public void testVisitDurationInMinutes() {

        constructorPatient.getPatientHistory().getCurrentVisit().setExamStartTime(endTime10Min);
        constructorPatient.getPatientHistory().getCurrentVisit().setDepartureTime(endTime60Min);


        Assert.assertEquals("the time difference should be 50 minutes", 50,
            constructorPatient.getPatientHistory().getCurrentVisit()
                .calcVisitDurationInMinutes());

    }

    @Test
    public void testVisitWaitTimeInMinutes() {


        constructorPatient.getPatientHistory().getCurrentVisit().setArrivalTime(startTime);
        constructorPatient.getPatientHistory().getCurrentVisit().setExamStartTime(endTime60Min);


        Assert.assertEquals("visit wait time should be 60 minutes", 60,
            constructorPatient.getPatientHistory().getCurrentVisit()
                .calcVisitWaitTimeInMinutes());

    }

    @Test
    public void testCalculateTimeDifferenceInMinutes() throws Exception{


        Assert.assertEquals("the time difference should be 10 minutes", 10,
            constructorPatient.getPatientHistory().getCurrentVisit()
                .calculateTimeDifferenceInMinutes(startTime, endTime10Min));
        Assert.assertEquals("the time difference should be 1 minute", 1,
            constructorPatient.getPatientHistory().getCurrentVisit()
                .calculateTimeDifferenceInMinutes(startTime, endTime1Min));
        Assert.assertEquals("the time difference should be 60 minutes", 60,
            constructorPatient.getPatientHistory().getCurrentVisit()
                .calculateTimeDifferenceInMinutes(startTime, endTime60Min));;

    }

    @Test
    public void testProjectedDepartureTime() throws Exception {

        constructorPatient.getPatientHistory().getCurrentVisit().setExamStartTime(startTime);


        Assert.assertTrue("the projected departure time should equal endTime10Min",
            constructorPatient.getPatientHistory()
                .getCurrentVisit().calcProjectedDepartureTime(10)
                .equals(endTime10Min));

    }

    @Test
    public void testDepartureTimeComparator() throws Exception {

        lateArrivalMiddleUrgencyPatient.getPatientHistory().getCurrentVisit()
            .setDepartureTime(earlyDepartureTime);
        earlyArrivalMiddleUrgencyPatient.getPatientHistory().getCurrentVisit()
            .setDepartureTime(lateDepartureTime);
        earlyArrivalHighUrgencyPatient.getPatientHistory().getCurrentVisit()
            .setDepartureTime(earlyDepartureTime);

        Assert.assertEquals("-1 should be returned because first patient's departure is earlier " +
            "than the next patient's departure",-1, patientDepartureTimeComparator
            .compare(earlyArrivalHighUrgencyPatient, earlyArrivalMiddleUrgencyPatient), 0);
        Assert.assertEquals("0 should be returned because first patient's departure is the same as " +
            "the second patient's departure",0, patientDepartureTimeComparator
            .compare(lateArrivalMiddleUrgencyPatient, earlyArrivalHighUrgencyPatient), 0);
        Assert.assertEquals("1 should be returned because the first patient's departure " +
            "is later than the second patient's departure",1, patientDepartureTimeComparator
            .compare(earlyArrivalMiddleUrgencyPatient, lateArrivalMiddleUrgencyPatient));
    }

    @Test
    public void testCalcProjectedVisitDuration() {

        Assert.assertEquals("HU_EXAM_DURATION_IN_MIN should be returned because the patient's urgency is 1 ",
            HU_EXAM_DURATION_IN_MIN, lateArrivalHighUrgencyPatient
                                         .getPatientHistory().getCurrentVisit().calcProjectedVisitDurationInMin());
        Assert.assertEquals("MU_EXAM_DURATION_IN_MIN should be returned because the patient's urgency is 2 ",
            MU_EXAM_DURATION_IN_MIN, lateArrivalMiddleUrgencyPatient
                                         .getPatientHistory().getCurrentVisit().calcProjectedVisitDurationInMin());
        Assert.assertEquals("LU_EXAM_DURATION_IN_MIN should be returned because the patient's urgency is 3 ",
            LU_EXAM_DURATION_IN_MIN, lateArrivalLowUrgencyPatient
                                         .getPatientHistory().getCurrentVisit().calcProjectedVisitDurationInMin());

    }

    /** Tests whether patients are being sorted correctly based on patient arrival times
     * earliest time should come first
     * returns -1 if first patient is earlier than second patient,
     * 0, if patient arrival times are equal, and
     * 1 if second patient is earlier than first patient
     * @throws Exception
     */

    @Test
    public void testArrivalTimeComparator() throws Exception {

        Assert.assertEquals("-1 should be returned because first patient's arrival is earlier " +
            "than the second patient's arrival",-1, patientArrivalTimeComparator
            .compare(earlyArrivalHighUrgencyPatient, lateArrivalHighUrgencyPatient), 0);
        Assert.assertEquals("0 should be returned because first patient's arrival time " +
            "is the same as the second patient's arrival time",0, patientArrivalTimeComparator
            .compare(earlyArrivalMiddleUrgencyPatient, earlyArrivalHighUrgencyPatient), 0);
        Assert.assertEquals("1 should be returned because the first patient's arrival " +
            "is later than the second patient's arrival",1, patientArrivalTimeComparator
            .compare(lateArrivalMiddleUrgencyPatient, earlyArrivalMiddleUrgencyPatient));
    }


    /** Tests whether patients are being sorted correctly based on urgency
     * most urgent (lowest number) should come first (High == 1, Middle == 2, Low == 3)
     * returns -1 if first patient is more urgent than second patient,
     * 0, if patient urgencies are equal, and
     * 1 if second patient is more urgent than first patient
     * @throws Exception
     */

    @Test
    public void testUrgencyComparator() throws Exception {

        Assert.assertEquals("-1 should be returned because the first patient is more urgent than the second patient ",
            -1, urgencyComparator.compare(lateArrivalHighUrgencyPatient, lateArrivalLowUrgencyPatient), 0);

        Assert.assertEquals("0 should be returned because the two patients have the same urgency",
            0, urgencyComparator.compare(lateArrivalHighUrgencyPatient, earlyArrivalHighUrgencyPatient), 0);
        Assert.assertEquals("1 should be returned because the first patient is less urgent than the second patient",
            1, urgencyComparator.compare(lateArrivalLowUrgencyPatient, lateArrivalHighUrgencyPatient), 0);
    }

    @Test
    public void testCompareTo() throws Exception {

        Assert.assertEquals("should be 0 because the two patients are equal",
            0, constructorPatient.compareTo(constructorPatientCompare));
        Assert.assertEquals("should return 1 because the two patients are not equal",
            1, constructorPatient.compareTo(lateArrivalHighUrgencyPatient));
        Assert.assertEquals("should return -1 because the two patients are not equal",
            -1, lateArrivalHighUrgencyPatient.compareTo(constructorPatient));
    }

}



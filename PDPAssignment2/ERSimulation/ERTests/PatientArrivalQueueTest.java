package edu.neu.ccs.cs5010.assignment2.ersim;

import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;
import edu.neu.ccs.cs5010.assignment2.ersim.PatientTest;

import java.time.LocalDate;
import java.time.LocalDateTime;


public class PatientArrivalQueueTest {

    private PatientArrivalQueue patientArrivalQueue;
    private Patient severeSysPatient;
    private Patient severeSysPatientLateArrival;
    private Patient severeDiaPatient;
    private Patient highSysRiskAgePatient;
    private Patient highSysRiskAgePatientLateArrival;
    private Patient highTempHighBPPatient;
    private Patient normalBPHighTempHighRiskAge;
    private Patient normalBPHighTempNonRiskAge;
    private Patient severeTempHighRiskAge;
    private Patient severeTempNonRiskAge;
    private Patient normalPatient;
    private Patient normalPatientLateArrival;
    private LocalDate arrivalDate;
    private LocalDateTime earlyArrivalTime;
    private LocalDateTime lateArrivalTime;
    private PatientArrivalQueue.ERPatientArrivalQueueComparator patientArrivalQueueComparator;


    private static final int SEVERE_BP_MIN_SYS = 141;
    private static final int SEVERE_BP_MIN_DIA = 130;
    private static final int HIGH_BP_MIN_SYS = 130;
    private static final int HIGH_BP_MIN_DIA = 120;
    private static final double MIN_HIGH_TEMP_NON_RISK_AGE =  102;
    private static final double MIN_HIGH_TEMP_RISK_AGE = 101;
    private static final double MIN_SEV_TEMP_NON_RISK_AGE =  103;
    private static final double MIN_SEV_TEMP_RISK_AGE = 102;
    private static final int MAX_YOUNG_AGE = 5;
    private static final int MIN_OLD_AGE = 70;

    private static boolean isSevereBPRange = false;
    private static boolean isHighBPRange = false;

    @Before
    public void setUp() throws Exception {

        earlyArrivalTime = LocalDateTime.now();
        lateArrivalTime = earlyArrivalTime.plusHours(1);

        patientArrivalQueue = new PatientArrivalQueue();

        // set up patient with severe systolic BP
        severeSysPatient = new Patient(8, "Benjamin", "Franklin", 24);

        Patient.PatientHistory.Visit severeSysPatientVisit = new Patient.PatientHistory.Visit(arrivalDate,
            earlyArrivalTime, 100.00, 141, 80);

        Patient.PatientHistory severeSysPH = new Patient.PatientHistory(severeSysPatientVisit);

        severeSysPatient.setPatientHistory(severeSysPH);

        // set up patient with severe systolic BP
        severeSysPatientLateArrival = new Patient(20, "Benifer", "Franklock", 24);

        Patient.PatientHistory.Visit severeSysPatientVisitLateArrival = new Patient.PatientHistory.Visit(arrivalDate,
            lateArrivalTime, 100.00, 141, 80);

        Patient.PatientHistory severeSysPHLA = new Patient.PatientHistory(severeSysPatientVisitLateArrival);

        severeSysPatientLateArrival.setPatientHistory(severeSysPHLA);

        // severe dia patient
        severeDiaPatient = new Patient(9, "Benji", "Frank", 26);

        Patient.PatientHistory.Visit severeDiaPatientVisit = new Patient.PatientHistory.Visit(arrivalDate,
            earlyArrivalTime, 100.00, 129, 92);

        Patient.PatientHistory severeDiaPH = new Patient.PatientHistory(severeDiaPatientVisit);

        severeDiaPatient.setPatientHistory(severeDiaPH);

        // high systolic, risk age patient -- middle urgency
        highSysRiskAgePatient = new Patient(10, "Brian", "Ferber", 76);

        Patient.PatientHistory.Visit highSysPatientVisit = new Patient.PatientHistory.Visit(arrivalDate,
            earlyArrivalTime, 100.00, 135, 90);

        Patient.PatientHistory highSysPH = new Patient.PatientHistory(highSysPatientVisit);

        highSysRiskAgePatient.setPatientHistory(highSysPH);

        // same except arriving later
        highSysRiskAgePatientLateArrival = new Patient(28, "Briana", "Foster", 76);

        Patient.PatientHistory.Visit highSysPatientVisitLateArrival = new Patient.PatientHistory.Visit(arrivalDate,
            lateArrivalTime, 100.00, 135, 90);

        Patient.PatientHistory highSysPHLA = new Patient.PatientHistory(highSysPatientVisitLateArrival);

        highSysRiskAgePatient.setPatientHistory(highSysPH);

        // high dia patient
        highTempHighBPPatient = new Patient(11, "Brandon", "Fischer", 29);

        Patient.PatientHistory.Visit highTempBPPatientVisit = new Patient.PatientHistory.Visit(arrivalDate,
            earlyArrivalTime, 102.00, 100, 120);

        Patient.PatientHistory highTempBPPH = new Patient.PatientHistory(highTempBPPatientVisit);

        highTempHighBPPatient.setPatientHistory(highTempBPPH);

        // high temp non-risk age
        normalBPHighTempNonRiskAge = new Patient(12, "Bradley", "Frank", 30);

        Patient.PatientHistory.Visit highTempNonRiskVisit = new Patient.PatientHistory.Visit(arrivalDate,
            earlyArrivalTime, 102.00, 100, 91);

        Patient.PatientHistory highTempNonRiskPH = new Patient.PatientHistory(highTempNonRiskVisit);

        normalBPHighTempNonRiskAge.setPatientHistory(highTempNonRiskPH);

        // high temp high-risk age
        normalBPHighTempHighRiskAge = new Patient(13, "Braddle", "Paddle", 4);

        Patient.PatientHistory.Visit highTempHighRiskVisit = new Patient.PatientHistory.Visit(arrivalDate,
            earlyArrivalTime, 101.00, 100, 91);

        Patient.PatientHistory highTempHighRiskPH = new Patient.PatientHistory(highTempHighRiskVisit);

        normalBPHighTempNonRiskAge.setPatientHistory(highTempHighRiskPH);

        // severe temp non-risk age
        severeTempNonRiskAge = new Patient(14, "Barney", "Fife", 30);

        Patient.PatientHistory.Visit severeTempNonRiskVisit = new Patient.PatientHistory.Visit(arrivalDate,
            earlyArrivalTime, 103.00, 100, 91);

        Patient.PatientHistory severeTempNonRiskPH = new Patient.PatientHistory(severeTempNonRiskVisit);

        severeTempNonRiskAge.setPatientHistory(severeTempNonRiskPH);

        //  severe temp high-risk age
        severeTempHighRiskAge = new Patient(15, "Brae", "Pad", 4);

        Patient.PatientHistory.Visit severeTempHighRiskVisit = new Patient.PatientHistory.Visit(arrivalDate,
            earlyArrivalTime, 102.00, 100, 91);

        Patient.PatientHistory severeTempHighRiskPH = new Patient.PatientHistory(severeTempHighRiskVisit);

        severeTempHighRiskAge.setPatientHistory(severeTempHighRiskPH);

        // normal patient
        normalPatient = new Patient(16, "Normal", "Patient", 30);

        Patient.PatientHistory.Visit normalVisit = new Patient.PatientHistory.Visit(arrivalDate,
            earlyArrivalTime, 98.00, 120, 80);

        Patient.PatientHistory normalPH = new Patient.PatientHistory(normalVisit);

        normalPatient.setPatientHistory(normalPH);

        // normal patient late arrival

        normalPatientLateArrival = new Patient(36, "Normally", "Late", 30);

        Patient.PatientHistory.Visit normalVisitLateArrival = new Patient.PatientHistory.Visit(arrivalDate,
            lateArrivalTime, 98.00, 120, 80);

        Patient.PatientHistory normalPHLA = new Patient.PatientHistory(normalVisitLateArrival);

        normalPatientLateArrival.setPatientHistory(normalPHLA);

    }


    @Test
    public void testArrivalQueueComparator() throws Exception {

        /* 1 if the second patient has a higher urgency or has the same urgency and
            * an earlier arrival time, -1 if the first patient has a higher urgency or
         * has the same urgency and an earlier arrival time */

        patientArrivalQueueComparator = new PatientArrivalQueue.ERPatientArrivalQueueComparator();

        severeSysPatient.getPatientHistory().getCurrentVisit().setUrgency(1);
        severeTempNonRiskAge.getPatientHistory().getCurrentVisit().setUrgency(1);
        highSysRiskAgePatient.getPatientHistory().getCurrentVisit().setUrgency(2);
        severeSysPatientLateArrival.getPatientHistory().getCurrentVisit().setUrgency(1);
        normalPatient.getPatientHistory().getCurrentVisit().setUrgency(3);
        normalPatientLateArrival.getPatientHistory().getCurrentVisit().setUrgency(3);


        // same urgency, same arrival time
        Assert.assertEquals("the patients have the same urgency and the same arrival time",
            0, patientArrivalQueueComparator.compare(severeSysPatient, severeTempNonRiskAge));
        // first patient is more urgent
        Assert.assertEquals("the first patient is more urgent",
            -1, patientArrivalQueueComparator.compare(severeSysPatient, highSysRiskAgePatient));
        // second patient is more urgent
        Assert.assertEquals("the second patient is more urgent",
            1, patientArrivalQueueComparator.compare(highSysRiskAgePatient, severeSysPatient));
        // same urgency, first patient arrived earlier
        Assert.assertEquals("the patients have the same urgency but the first patient arrived earlier",
            -1, patientArrivalQueueComparator.compare(severeSysPatient, severeSysPatientLateArrival));
        // same urgency, second patient arrived earlier
        Assert.assertEquals("the patients have the same urgency but the second patient arrived earlier",
            1, patientArrivalQueueComparator.compare(normalPatientLateArrival, normalPatient));

    }


    @Test
    public void testCalcPatientVisitUrgency() throws Exception {

        Assert.assertEquals("this patient is middle urgency because they have high blood pressure and are at risk age",
            2, patientArrivalQueue.calcPatientVisitUrgency(highSysRiskAgePatient));
        Assert.assertEquals("this patient is high urgency because they have severely high blood pressure",
                1, patientArrivalQueue.calcPatientVisitUrgency(severeSysPatient));
        Assert.assertEquals("this patient is high urgency because they have severely high temp",
            1, patientArrivalQueue.calcPatientVisitUrgency(severeTempNonRiskAge));
        Assert.assertEquals("this patient is high urgency because they have high temp AND high bp",
            1, patientArrivalQueue.calcPatientVisitUrgency(highTempHighBPPatient));
        Assert.assertEquals("this patient is lowest urgency because temp and bp are normal",
            3, patientArrivalQueue.calcPatientVisitUrgency(normalPatient));


    }

}
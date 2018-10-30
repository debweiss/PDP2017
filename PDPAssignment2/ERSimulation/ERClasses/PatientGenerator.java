package edu.neu.ccs.cs5010.assignment2.ersim;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Random;


/**
 * Generates a new patient for use in the ER simulation
 */
class PatientGenerator {

    /* age and blood pressure variables used to calc patient urgency */

    private final int MIN_AGE = 1; // min possible age of patient for purposes of simulation
    private final int MAX_AGE = 100; // max possible age of patient for purposes of simulation
    private final int MIN_TEMP = 93; // min possible temp of patient for purposes of simulation
    private final int MAX_TEMP = 105; // max possible temp of patient for purposes of simulation
    private final int MIN_SYSTOLIC = 60; // min systolic BP of patient for purposes of simulation
    private final int MAX_SYSTOLIC = 100; // max systolic BP of patient for purposes of simulation
    private final int MIN_DIASTOLIC = 60; // min diastolic BP of patient for purposes of simulation
    private final int MAX_DIASTOLIC = 200; // max diastolic BP of patient for purposes of simulation
    private Patient newPatient; // new patient that gets generated
    private static RandomString randomString; // random string generator for patient names
    private Random numRandom; // random number that generates the age, temp, blood pressure numbers above


    /** Constructor for PatientGenerator
     * @param id id of the new patient
     * @param stringLength length of the random first name and last time to be generated
     */
    PatientGenerator(int id, int stringLength) {

        /* Generate random first name and last name for new patient */
        randomString = new RandomString();
        this.numRandom = new Random();
        String firstName = randomString.generateRandomString(stringLength);
        String lastName = randomString.generateRandomString(stringLength);

        /* Generate random age and blood pressure numbers for patient urgency calculation */
        int ageInYrs = numRandom.nextInt(MAX_AGE - MIN_AGE + 1) + MIN_AGE;
        double tempInDegs = (numRandom.nextInt(MAX_TEMP - MIN_TEMP + 1) + MIN_TEMP) * 1.00;
        int systolicBP = numRandom.nextInt(MAX_SYSTOLIC - MIN_SYSTOLIC + 1) + MIN_SYSTOLIC;
        int diastolicBP = numRandom.nextInt(MAX_DIASTOLIC - MIN_DIASTOLIC) + MIN_DIASTOLIC;

        /* Set arrival date and arrival time coinciding with patient creation */
        LocalDate arrivalDate = LocalDate.now();
        LocalDateTime arrivalTime = LocalDateTime.now();

        /* create a new patient with the data generated above as input */
        this.newPatient = new Patient(id, firstName, lastName, ageInYrs);

        /* create a new visit and add that visit to the patient's patient history */
        Patient.PatientHistory.Visit newVisit = new Patient.PatientHistory.Visit(arrivalDate,
            arrivalTime, tempInDegs, systolicBP, diastolicBP);

        Patient.PatientHistory newPatientHistory = new Patient.PatientHistory(newVisit);
        this.newPatient.setPatientHistory(newPatientHistory);
    }

    /**
     * Constructor for testing purposes--can explicitly set the urgency and arrival time/date
     *
     * @param id             unique identifier for the patient
     * @param stringLength   length of string from which to generate random firstname and lastname
     * @param arrivalDate    date that the patient arrives at the ER for the visit
     * @param arrivalTime    time that the patient arrives at the ER for the visit
     * @param patientUrgency urgency of the patient's condition when coming into the ER
     */
    public PatientGenerator(int id, int stringLength, LocalDate arrivalDate,
        LocalDateTime arrivalTime, int patientUrgency) {

         /* Generate random first name and last name for new patient */
        randomString = new RandomString();
        this.numRandom = new Random();
        String firstName = randomString.generateRandomString(stringLength);
        String lastName = randomString.generateRandomString(stringLength);

        /* Default to zero for all the numbers because urgency is set explicitly */
        int ageInYrs = 0;
        double tempInDegs = 0.0;
        int systolicBP = 0;
        int diastolicBP = 0;

         /* Create a new patient with the data generated above as input */
        this.newPatient = new Patient(id, firstName, lastName, ageInYrs);

         /* Create a new visit and add that visit to the patient's patient history */
        Patient.PatientHistory.Visit newVisit = new Patient.PatientHistory.Visit(arrivalDate, arrivalTime,
            tempInDegs, systolicBP, diastolicBP);
        Patient.PatientHistory newPatientHistory = new Patient.PatientHistory(newVisit);
        this.newPatient.setPatientHistory(newPatientHistory);

        /* Explicitly set the patient urgency */
        this.newPatient.getPatientHistory().getCurrentVisit().setUrgency(patientUrgency);

    }

    /* Getter and Setter */

    public Patient getNewPatient() {

        return newPatient;
    }

    public void setNewPatient(Patient newPatient) {

        this.newPatient = newPatient;
    }

    /**
     * Creates a random string for use in generating new patient first and last name
     */
    private static class RandomString {

        private final char[] letters = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j',
            'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};

        private final Random stringRandom = new Random();

        String generateRandomString(int stringLength) {
            char[] randomString = new char[stringLength];
            for (int stringIndex = 0; stringIndex < randomString.length; stringIndex++) {
                randomString[stringIndex] = letters[this.stringRandom.nextInt(letters.length)];

            }

            return new String(randomString);
        }
    }
}
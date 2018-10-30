package edu.neu.ccs.cs5010.assignment2.ersim;

import java.util.Comparator;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Duration;
import java.util.List;
import java.util.ArrayList;

/**
 * Represents a patient, with patient history that records
 * current and past ER visits
 */
public class Patient implements Comparable<Patient> {

    /* set visit duration for each urgency level for
    simulation purposes */
    private static final int HU_EXAM_DURATION_IN_MIN = 5;
    private static final int MU_EXAM_DURATION_IN_MIN = 3;
    private static final int LU_EXAM_DURATION_IN_MIN = 2;

    private final int id; // patient identifier
    private String firstName;
    private String lastName;
    private int ageInYrs;
    private PatientHistory patientHistory;

    /** Constructor for patient
     * @param id patient identifier
     * @param firstName patient first name
     * @param lastName patient last name
     * @param ageInYrs patient age in years
     */
    /* , sets id, first name,
     * last name, and age */
    Patient(int id, String firstName, String lastName, int ageInYrs) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.ageInYrs = ageInYrs;
    }

    /* Getters and setters for the above attributes */

    int getId() {

        return id;
    }

    String getFirstName() {

        return firstName;
    }

    void setFirstName(String firstName) {

        this.firstName = firstName;
    }

    String getLastName() {

        return lastName;
    }

    void setLastName(String lastName) {

        this.lastName = lastName;
    }

    int getAgeInYrs() {

        return ageInYrs;
    }

    void setAgeInYrs(int ageInYrs) {

        this.ageInYrs = ageInYrs;
    }

    public PatientHistory getPatientHistory() {

        return patientHistory;
    }

    void setPatientHistory(PatientHistory patientHistory) {

        this.patientHistory = patientHistory;
    }

    /** Compares a patient to the current patient based on
     * id, first name, last name, & age
     * @param comparePatient patient being compared to
     * @return 0 if patients are equal, 1 if comparePatient comes first, -1 if original patient comes first
     */
    @Override
    public int compareTo(Patient comparePatient) {

        return Comparator.comparing(Patient::getId)
                   .thenComparing(Patient::getFirstName)
                   .thenComparing(Patient::getLastName)
                   .thenComparing(Patient::getAgeInYrs)
                   .compare(this, comparePatient);
    }

    /** UrgencyComparator compares two patients based on urgency
     * */
    public static class urgencyComparator implements Comparator<Patient> {

        /**
         * @param patient1 first patient to be compared
         * @param patient2 second patient to be compared
         * @return 0 if both patients have the same urgency, -1 if the first patient
         * has a higher urgency (lower #) than the second patient, 1 if the second
         * patient has a higher urgency than the first patient
         */
        @Override
        public int compare(Patient patient1, Patient patient2) {
            return Integer.compare(patient1.getPatientHistory().getCurrentVisit().getUrgency(),
                patient2.getPatientHistory().getCurrentVisit().getUrgency());

        }

    }

    /** Compares two patients based on arrival time
     */
    public static class arrivalTimeComparator implements Comparator<Patient> {

        /**
         * @param patient1 first patient to be compared
         * @param patient2 second patient to be compared
         * @return 0 if both patients have the same arrival time,
         * -1 if the first patient has an arrival time before the second
         * 1 if the second patient has an arrival time before the first
         */
        @Override
        public int compare(Patient patient1, Patient patient2) {

            LocalDateTime arrivalTime1 = patient1.patientHistory.getCurrentVisit().getArrivalTime();
            LocalDateTime arrivalTime2 = patient2.patientHistory.getCurrentVisit().getArrivalTime();

            return arrivalTime1.equals(arrivalTime2) ? 0 : (arrivalTime1.isBefore(arrivalTime2) ? -1 : 1);

        }
    }

    /**
     * Compares two patients based on departure time
     */
    public static class departureTimeComparator implements Comparator<Patient> {

        /**
         * @param patient1 first patient to be compared
         * @param patient2 second patient to be compared
         * @return 0 if both patients have the same departure time,
         * -1 if the first patient has a departure time before the second
         * 1 if the second patient has a departure time before the first
         */
        @Override
        public int compare(Patient patient1, Patient patient2) {

            LocalDateTime departureTime1 = patient1.getPatientHistory()
                .getCurrentVisit()
                 .getDepartureTime();

            LocalDateTime departureTime2 = patient2.getPatientHistory()
                .getCurrentVisit()
                .getDepartureTime();

            return departureTime1.equals(departureTime2) ? 0 : (departureTime1.isBefore(departureTime2) ? -1 : 1);

        }
    }

    /**
     * History of this patient's visits to the ER
     */
    public static class PatientHistory {

        private List<Visit> visitList; // list of visits

        /** Patient history constructor, creates a visit list
         * and adds the first visit
         * @param visit visit to the ER
         */
        PatientHistory(Visit visit) {

            visitList = new ArrayList<>();
            visitList.add(visit);

        }

        /** Patient history constructor, creates an empty visit list
         * @param visitList empty visit list
         */
        PatientHistory(List<Visit> visitList) {

            this.visitList = visitList;
        }

        /* Getters and Setters for the above attributes */

        List<Visit> getVisitList() {

            return visitList;
        }

        void setVisitList(List<Visit> visitList) {

            this.visitList = visitList;
        }

        public Visit getCurrentVisit() { // /gets the last (most current visit) in the visit list

            return this.visitList.get(this.visitList.size() - 1);
        }

        /**
         * Represents a patient's visit to the ER
         */
        public static class Visit {

            private final LocalDate arrivalDate; // date patient arrives in ER
            private LocalDateTime arrivalTime; // time patient arrives in ER
            private final double tempInDegs; // patient temperature
            private final int systolicBP;
            private final int diastolicBP;
            private LocalDateTime examStartTime; // when the examination starts
            private LocalDateTime departureTime; // when the exam is over
            private int urgency; // urgency of patient's condition
            private int examRoomNumber; // no. of the exam room used during the visit
            private long visitDurationInMinutes; // how long the visit lasted (in minutes)


            /** Visit constructor
             * @param arrivalDate patient arrival date for the visit
             * @param arrivalTime patient arrival time for the visit
             * @param tempInDegs  patient temperature
             * @param systolicBP  patient systolic blood pressure reading
             * @param diastolicBP patient diastolic blood pressure readin
             */
            Visit(LocalDate arrivalDate, LocalDateTime arrivalTime, double tempInDegs,
                  int systolicBP, int diastolicBP) {

                this.arrivalDate = arrivalDate;
                this.arrivalTime = arrivalTime;
                this.tempInDegs = tempInDegs;
                this.systolicBP = systolicBP;
                this.diastolicBP = diastolicBP;

            }

            /* Getters and setters for the above attributes */
            long getVisitDurationInMinutes() {

                return visitDurationInMinutes;
            }

            void setVisitDurationInMinutes(long visitDurationInMinutes) {

                this.visitDurationInMinutes = visitDurationInMinutes;
            }

            LocalDate getArrivalDate() {

                return arrivalDate;
            }

            void setArrivalTime(LocalDateTime arrivalTime) {

                this.arrivalTime = arrivalTime;

            }

            LocalDateTime getArrivalTime() {

                return arrivalTime;
            }

            double getTempInDegs() {

                return tempInDegs;
            }

            int getSystolicBP() {

                return systolicBP;
            }

            int getDiastolicBP() {

                return diastolicBP;
            }

            int getUrgency() {

                return urgency;
            }

            void setUrgency(int urgency) {

                this.urgency = urgency;
            }

            LocalDateTime getExamStartTime() {

                return examStartTime;
            }

            void setExamStartTime(LocalDateTime examStartTime) {

                this.examStartTime = examStartTime;
            }

            LocalDateTime getDepartureTime() {

                return departureTime;
            }

            void setDepartureTime(LocalDateTime departureTime) {

                this.departureTime = departureTime;
            }

            int getExamRoomNumber() {

                return examRoomNumber;
            }

            void setExamRoomNumber(int examRoomNumber) {

                this.examRoomNumber = examRoomNumber;
            }

            /** Calculates how long the patient had to wait before being
             * examined
             * @return long representing the wait time in minutes
             */
            long calcVisitWaitTimeInMinutes() {

                return calculateTimeDifferenceInMinutes(this.arrivalTime, this.examStartTime);

            }

            /** Calculates the duration of the patient's visit in minutes
             * @return long representing visit duration in minutes
             */
            long calcVisitDurationInMinutes() {

                return calculateTimeDifferenceInMinutes(this.examStartTime, this.departureTime);
            }

            /** Helper function to take a starting time and ending time and calculate
             * the time difference in minutes
             * @param startTime the start time being measured from
             * @param endTime the end time
             * @return long representing the time difference in minutes
             */
            long calculateTimeDifferenceInMinutes(LocalDateTime startTime, LocalDateTime endTime) {

                return Duration.between(startTime, endTime).toMinutes();
            }

            /** Calculates a patient's projected departure time once exam starts,
             * based on the urgency of the patient's condition
             * @param projectedVisitDuration how long the visit is projected to be
             *                               based on the patient's condition
             * @return time when it is expected that the patient's exam will be over
             */
            LocalDateTime calcProjectedDepartureTime(long projectedVisitDuration) {

                return this.examStartTime.plusMinutes(projectedVisitDuration);
            }

            /**
             * Calculates the projected duration of the visit in minutes
             * based on urgency.
             *
             * @return the projected duration of the visit in minutes
             */

            long calcProjectedVisitDurationInMin() {

                int patientUrgency = this.getUrgency();

                long visitDuration;

                switch (patientUrgency) {
                    case 1:
                        visitDuration = HU_EXAM_DURATION_IN_MIN;
                        break;
                    case 2:
                        visitDuration = MU_EXAM_DURATION_IN_MIN;
                        break;
                    case 3:
                        visitDuration = LU_EXAM_DURATION_IN_MIN;
                        break;
                    default:
                        visitDuration = 0;
                        break;
                }
                return visitDuration;
            }

            @Override
            public boolean equals(Object o) { // Visit equals
                if (this == o) {
                    return true;
                }
                if (!(o instanceof Visit)) {
                    return false;
                }

                Visit visit = (Visit) o;

                if (Double.compare(visit.tempInDegs, tempInDegs) != 0) {
                    return false;
                }
                if (systolicBP != visit.systolicBP) {
                    return false;
                }
                if (diastolicBP != visit.diastolicBP) {
                    return false;
                }
                if (urgency != visit.urgency) {
                    return false;
                }
                if (examRoomNumber != visit.examRoomNumber) {
                    return false;
                }
                if (visitDurationInMinutes != visit.visitDurationInMinutes) {
                    return false;
                }
                if (!arrivalDate.equals(visit.arrivalDate)) {
                    return false;
                }
                if (!arrivalTime.equals(visit.arrivalTime)) {
                    return false;
                }
                if (!examStartTime.equals(visit.examStartTime)) {
                    return false;
                }
                return departureTime.equals(visit.departureTime);
            }

            @Override
            public int hashCode() { // Visit hashcode
                int result;
                long temp;
                result = arrivalDate.hashCode();
                result = 31 * result + arrivalTime.hashCode();
                temp = Double.doubleToLongBits(tempInDegs);
                result = 31 * result + (int) (temp ^ (temp >>> 32));
                result = 31 * result + systolicBP;
                result = 31 * result + diastolicBP;
                result = 31 * result + examStartTime.hashCode();
                result = 31 * result + departureTime.hashCode();
                result = 31 * result + urgency;
                result = 31 * result + examRoomNumber;
                result = 31 * result + (int) (visitDurationInMinutes ^ (visitDurationInMinutes >>> 32));
                return result;
            }
        }


        @Override
        public boolean equals(Object o) { // PatientHistory equals
            if (this == o) {
                return true;
            }
            if (!(o instanceof PatientHistory)) {
                return false;
            }

            PatientHistory that = (PatientHistory) o;

            return visitList.equals(that.visitList);
        }

        @Override
        public int hashCode() { // PatientHistory hashCode

            return visitList.hashCode();
        }

    }

    @Override
    public boolean equals(Object o) { // Patient equals
        if (this == o) {
            return true;
        }
        if (!(o instanceof Patient)) {
            return false;
        }

        Patient patient = (Patient) o;

        if (id != patient.id) {
            return false;
        }
        if (ageInYrs != patient.ageInYrs) {
            return false;
        }
        if (!firstName.equals(patient.firstName)) {
            return false;
        }
        if (!lastName.equals(patient.lastName)) {
            return false;
        }
        return patientHistory.equals(patient.patientHistory);
    }

    @Override
    public int hashCode() { // Patient hashcode
        int result = id;
        result = 31 * result + firstName.hashCode();
        result = 31 * result + lastName.hashCode();
        result = 31 * result + ageInYrs;
        result = 31 * result + patientHistory.hashCode();
        return result;
    }

    @Override
    public String toString() { // Patient toString()
        return "Patient [Id=" + this.id + ", firstName=" + this.firstName + ", lastName=" + this.lastName + "]";
    }
}


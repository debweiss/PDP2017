package edu.neu.ccs.cs5010.assignment2.ersim;


import java.util.ArrayList;
import java.util.Comparator;


/**
 * Holds and routes patients who are being examined
 */
class PatientExaminationQueue extends MyPriorityQueue<Patient> {

    /**
     * Constructor -- creates a new arraylist and
     * assigns the queue comparator for the patient examination queue
     */
    PatientExaminationQueue() {

        super();
        this.queueComparator = new ERPatientExaminationQueueComparator();
    }

    /**
     * The patient exits the exam room and leaves the
     * patient examination queue
     */
    synchronized void patientExitsExamRoom(Patient patient) {

        this.queue.remove(patient);

    }

    /**
     * Compares patients in examination queue based on departure time (earlier goes first)
     */
    public static class ERPatientExaminationQueueComparator implements Comparator<Patient> {

        private Patient.departureTimeComparator departureTimeComparator;

        ERPatientExaminationQueueComparator() {

            this.departureTimeComparator = new Patient.departureTimeComparator();
        }

        /**
         * @param patient1 first patient to be compared
         * @param patient2 second patient to be compared
         * @return 0 if both patients have the same departure time,
         * -1 if the first patient has an earlier departure time than the second
         * 1 if the second patient has an earlier departure time than the first
         */
        @Override
        public int compare(Patient patient1, Patient patient2) {

            return this.departureTimeComparator.compare(patient1, patient2);
        }
    }

}







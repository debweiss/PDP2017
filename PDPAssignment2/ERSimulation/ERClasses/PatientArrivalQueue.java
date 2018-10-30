package edu.neu.ccs.cs5010.assignment2.ersim;

import java.util.*;


class PatientArrivalQueue extends MyPriorityQueue<Patient> {

    private static final int SEVERE_BP_MIN_SYS = 141;
    private static final int SEVERE_BP_MIN_DIA = 130;
    private static final int HIGH_BP_MIN_SYS = 130;
    private static final int HIGH_BP_MIN_DIA = 120;
    private static final double MIN_HIGH_TEMP_NON_RISK_AGE = 102;
    private static final double MIN_HIGH_TEMP_RISK_AGE = 101;
    private static final double MIN_SEV_TEMP_NON_RISK_AGE = 103;
    private static final double MIN_SEV_TEMP_RISK_AGE = 102;
    private static final int MAX_YOUNG_AGE = 5;
    private static final int MIN_OLD_AGE = 70;

    private static boolean isSevereBPRange = false;
    private static boolean isHighBPRange = false;


    /**
     * Constructor -- creates an empty arrival queue and
     * sets the comparator to sort the queue based on urgency
     * then on arrival time
     */
    PatientArrivalQueue() {

        super();
        this.queueComparator = new ERPatientArrivalQueueComparator();
    }

    /**
     * ERPatientArrivalQueueComparator compares two patients, first sorting by
     * higher urgency (lower number == higher urgency), then by arrival time
     * (earlier arrival time goes first)
     */
    public static class ERPatientArrivalQueueComparator implements Comparator<Patient> {

        private Patient.urgencyComparator urgencyComparator;
        private Patient.arrivalTimeComparator arrivalTimeComparator;

        ERPatientArrivalQueueComparator() {

            this.urgencyComparator = new Patient.urgencyComparator();
            this.arrivalTimeComparator = new Patient.arrivalTimeComparator();
        }

        /**
         * @param patient1 first patient to be compared
         * @param patient2 second patient to be compared
         * @return 0 if both patients have the same urgency & same arrival time,
         * 1 if the second patient has a higher urgency or has the same urgency and
         * an earlier arrival time, -1 if the first patient has a higher urgency or
         * has the same urgency and an earlier arrival time
         */
        @Override
        public int compare(Patient patient1, Patient patient2) {

            if (this.urgencyComparator.compare(patient1, patient2) == 0) {

                return this.arrivalTimeComparator.compare(patient1, patient2);

            }
            else {

                return this.urgencyComparator.compare(patient1, patient2);
            }

        }
    }

    /**
     * Calculates whether a given patient has severe, high, or normal BP during a given visit
     * to the ER. If either systolic or diastolic BP is considered severe, the patient will
     * be categorized as severe. If either systolic or diastolic BP is considered high, the
     * patient will be categorized with high BP. If not severe or high, patient
     * is considered normal. This method sets the isSevereBPRange and isHighBPRange booleans above.
     *
     * @param patient            Patient to be checked
     * @param minSevSystolicBP   minimum systolicBP to be considered severe
     * @param minSevDiastolicBP  minimum diastolicBP to be considered severe
     * @param minHighSystolicBP  minimum systolicBP to be considered high
     * @param minHighDiastolicBP minimum diastolicBP to be considered high
     * @requires patient != null
     */

    private void calcPatientBPRange(Patient patient, int minSevSystolicBP, int minSevDiastolicBP,
                                    int minHighSystolicBP, int minHighDiastolicBP) {

        int patientSysBP = patient.getPatientHistory().getCurrentVisit().getSystolicBP();
        int patientDiaBP = patient.getPatientHistory().getCurrentVisit().getDiastolicBP();

        if (patientSysBP >= minSevSystolicBP || patientDiaBP >= minSevDiastolicBP) {
            isSevereBPRange = true;
            isHighBPRange = false;

        }

        else if ((patientSysBP >= minHighSystolicBP && patientSysBP < minSevSystolicBP)
                     || (patientDiaBP >= minHighDiastolicBP && patientDiaBP < minSevDiastolicBP)) {

            isHighBPRange = true;
            isSevereBPRange = false;

        }

        else {
            isSevereBPRange = false;
            isHighBPRange = false;
        }
    }

    /**
     * Calculates the urgency of a patient's condition so that the patient may be prioritized
     * for an exam room after arriving at the hospital.
     *
     * This method returns <tt>1</tt>, i.e., highest urgency, if:
     * - patient is at a risk or a non-risk age AND has either severely high Blood Pressure for their age
     * OR has a severe temperature for their age.
     * - patient is at a risk or a non-risk age AND has high Blood Pressure for their age
     * AND high temperature for their age
     *
     * returns <tt>2</tt>, i.e. middle urgency if:
     * - patient is at a risk age and has either High BP or High temp for their age, but not both
     *
     * returns <tt>3</tt>, i.e., low urgency if:
     * - patient is at a non-risk age and has either High BP or High temp for their age,
     * but not both
     * - patient is at any age and has normal BP and temp for their age
     *
     * @param patient patient to be checked
     * @return 1 if patient's condition is urgent, 2 if patient's condition is medium,
     * and 3 if patient's condition is not urgent
     * @requires patient != null
     * */

     int calcPatientVisitUrgency(Patient patient) {

        // sets the blood pressure severity
        calcPatientBPRange(patient, SEVERE_BP_MIN_SYS, SEVERE_BP_MIN_DIA, HIGH_BP_MIN_SYS,
            HIGH_BP_MIN_DIA);

        // calculates whether patient is at an at-risk age
        boolean isRiskAge;
        isRiskAge = patient.getAgeInYrs() <= MAX_YOUNG_AGE
             || patient.getAgeInYrs() >= MIN_OLD_AGE;

        // retrieves temperature of patient during visit
        double temp = patient.getPatientHistory().getCurrentVisit().getTempInDegs();

        if ((isRiskAge
            && ((isSevereBPRange
            || temp >= MIN_SEV_TEMP_RISK_AGE)
            || (isHighBPRange
            && temp >= MIN_HIGH_TEMP_RISK_AGE))

            || (!isRiskAge
            && ((isSevereBPRange
            || temp >= MIN_SEV_TEMP_NON_RISK_AGE)
            || (isHighBPRange
            && temp >= MIN_HIGH_TEMP_NON_RISK_AGE))))) {

            return 1; // return high urgency

        } else if (isRiskAge
               && (isHighBPRange
               || temp >= MIN_HIGH_TEMP_RISK_AGE)) {

                    return 2; // return medium urgency
        } else {

            return 3; // else return low urgency
        }
    }
}
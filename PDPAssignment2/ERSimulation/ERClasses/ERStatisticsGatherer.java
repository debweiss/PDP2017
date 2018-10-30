package edu.neu.ccs.cs5010.assignment2.ersim;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.Duration;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Collects the ERSimulator statistics to generate the simulation report at the end of the simulation
 */
class ERStatisticsGatherer {

    /* Simulation start-time, end-time, duration */
    private LocalDateTime simulationStartTime; // time simulation started
    private LocalDateTime simulationEndTime; // time simulation ended
    private double simulationDurationInHours; // duration of simulation (in hours)

    /* ER Statistics: total patient visits, total wait time, wait time by urgency, total visit duration */
    private AtomicInteger totalPatientVisits = new AtomicInteger(); // tot num of pat exam room visits during sim
    private AtomicInteger totalHighUrgencyPatientVisits = new AtomicInteger(); // tot num high urgency pat visits
    private AtomicInteger totalMediumUrgencyPatientVisits = new AtomicInteger(); // tot num medium urgency pat visits
    private AtomicInteger totalLowUrgencyPatientVisits = new AtomicInteger(); // tot num low urgency pat visits
    private AtomicLong totalWaitTimeInMinutes = new AtomicLong(); // tot pat wait time(min) btw arriving & exam start
    private AtomicLong totalHUPWaitTimeInMinutes = new AtomicLong(); // tot wait time high urgency pats
    private AtomicLong totalMUPWaitTimeInMinutes = new AtomicLong(); // tot wait time medium urgency pats
    private AtomicLong totalLUPWaitTimeInMinutes = new AtomicLong(); // tot wait time low urgency pats
    private AtomicLong totalVisitDurationInMinutes = new AtomicLong(); // tot duration (min) of all visits

    /** Constructor - when the ERStatisticsGatherer is created, it uses simulation duration
     * @param startTime simulation start time
     */
    ERStatisticsGatherer(LocalDateTime startTime) {

        /* set start time, end time, duration, based on cmd line inputs */
        this.simulationStartTime = startTime;

        /* set all initial statistics to 0 */
        this.totalPatientVisits.set(0);
        this.totalHighUrgencyPatientVisits.set(0);
        this.totalMediumUrgencyPatientVisits.set(0);
        this.totalLowUrgencyPatientVisits.set(0);
        this.totalWaitTimeInMinutes.set(0);
        this.totalVisitDurationInMinutes.set(0);
        this.totalHUPWaitTimeInMinutes.set(0);
        this.totalMUPWaitTimeInMinutes.set(0);
        this.totalLUPWaitTimeInMinutes.set(0);

    }

     /* Getters and Setters */

    LocalDateTime getSimulationStartTime() {

        return simulationStartTime;
    }

    void setSimulationStartTime(LocalDateTime simulationStartTime) {

        this.simulationStartTime = simulationStartTime;
    }

    LocalDateTime getSimulationEndTime() {

        return simulationEndTime;
    }

    void setSimulationEndTime(LocalDateTime simulationEndTime) {

        this.simulationEndTime = simulationEndTime;
    }

    double getSimulationDuration() {

        return simulationDurationInHours;
    }

    void setSimulationDurationInHours(double simulationDurationInHours) {

        this.simulationDurationInHours = simulationDurationInHours;
    }

    int getTotalPatientVisits() {

        return totalPatientVisits.get();
    }

    void setTotalPatientVisits(int totalPatientVisits) {

        this.totalPatientVisits.set(totalPatientVisits);
    }

    int getTotalHighUrgencyPatientVisits() {

        return totalHighUrgencyPatientVisits.get();
    }

    void setTotalHighUrgencyPatientVisits(int totalHighUrgencyPatientVisits) {

        this.totalHighUrgencyPatientVisits.set(totalHighUrgencyPatientVisits);
    }

    int getTotalMediumUrgencyPatientVisits() {

        return totalMediumUrgencyPatientVisits.get();
    }

    void setTotalMediumUrgencyPatientVisits(int totalMediumUrgencyPatientVisits) {

        this.totalMediumUrgencyPatientVisits.set(totalMediumUrgencyPatientVisits);
    }

    int getTotalLowUrgencyPatientVisits() {

        return totalLowUrgencyPatientVisits.get();
    }

    void setTotalLowUrgencyPatientVisits(int totalLowUrgencyPatientVisits) {

        this.totalLowUrgencyPatientVisits.set(totalLowUrgencyPatientVisits);
    }

    long getTotalWaitTimeInMinutes() {

        return totalWaitTimeInMinutes.get();
    }

    void setTotalWaitTimeInMinutes(long totalWaitTimeInMinutes) {

        this.totalWaitTimeInMinutes.set(totalWaitTimeInMinutes);
    }

    long getTotalVisitDurationInMinutes() {

        return totalVisitDurationInMinutes.get();
    }

    void setTotalVisitDurationInMinutes(long totalVisitDurationInMinutes) {

        this.totalVisitDurationInMinutes.set(totalVisitDurationInMinutes);
    }

    long getTotalHUPWaitTimeInMinutes() {

        return totalHUPWaitTimeInMinutes.get();
    }

    void setTotalHUPWaitTimeInMinutes(long totalHUPWaitTimeInMinutes) {

        this.totalHUPWaitTimeInMinutes.set(totalHUPWaitTimeInMinutes);
    }

    long getTotalMUPWaitTimeInMinutes() {

        return totalMUPWaitTimeInMinutes.get();
    }

    void setTotalMUPWaitTimeInMinutes(long totalMUPWaitTimeInMinutes) {

        this.totalMUPWaitTimeInMinutes.set(totalMUPWaitTimeInMinutes);
    }

    long getTotalLUPWaitTimeInMinutes() {

        return totalLUPWaitTimeInMinutes.get();
    }

    void setTotalLUPWaitTimeInMinutes(long totalLUPWaitTimeInMinutes) {

        this.totalLUPWaitTimeInMinutes.set(totalLUPWaitTimeInMinutes);
    }

    /* Increment/Update statistics */

    private void incrementTotalPatientVisits() {

        this.totalPatientVisits.getAndIncrement();
    }

    private void updateTotalWaitTimeInMinutes(long waitTimeToAdd) {

        this.totalWaitTimeInMinutes.getAndAdd(waitTimeToAdd);
    }

    private void incrementTotalHighUrgencyPatientVisits() {

        this.totalHighUrgencyPatientVisits.getAndIncrement();
    }

    private void updateTotalHUPWaitTimeInMinutes(long waitTimeToAdd) {

        this.totalHUPWaitTimeInMinutes.getAndAdd(waitTimeToAdd);
    }

    private void incrementTotalMediumUrgencyPatientVisits() {

        this.totalMediumUrgencyPatientVisits.getAndIncrement();
    }

    private void updateTotalMUPWaitTimeInMinutes(long waitTimeToAdd) {

        this.totalMUPWaitTimeInMinutes.getAndAdd(waitTimeToAdd);
    }

    private void incrementTotalLowUrgencyPatientVisits() {

        this.totalLowUrgencyPatientVisits.getAndIncrement();
    }

    private void updateTotalLUPWaitTimeInMinutes(long waitTimeToAdd) {

        this.totalLUPWaitTimeInMinutes.getAndAdd(waitTimeToAdd);
    }

    private void updateTotalVisitDurationInMinutes(long durationTimeToAdd) {

        this.totalVisitDurationInMinutes.getAndAdd(durationTimeToAdd);
    }

    /** When the patient's exam is over and they are about to be released,
     * updates the total visit duration in minutes with that patient's
     * visit duration.
     *
     * @param patient patient whose visit duration is added to the total
     */
    void updatePatientToReleaseStatistics(Patient patient) {

        long visitDurationInMinutes = patient.getPatientHistory().getCurrentVisit()
            .getVisitDurationInMinutes();

        this.updateTotalVisitDurationInMinutes(visitDurationInMinutes);
    }

    /**
     * Updates the total patient visits and total patient wait time by patient urgency
     * (1, 2, and 3)
     *
     * @param patientUrgency  the urgency of a particular patient
     * @param patientWaitTime how long that patient had to wait to get an exam room
     */
    private void updatePatientToTreatmentStatisticsByUrgency(int patientUrgency,
        long patientWaitTime) {

        switch (patientUrgency) {

            case 1: // if urgency 1, update high urgency patient visits and wait time

                incrementTotalHighUrgencyPatientVisits();
                this.updateTotalHUPWaitTimeInMinutes(patientWaitTime);
                break;

            case 2: // if urgency 2, update medium urgency patient visits and wait time

                incrementTotalMediumUrgencyPatientVisits();
                this.updateTotalMUPWaitTimeInMinutes(patientWaitTime);
                break;

            case 3: // if urgency 3, update low urgency patient visits and wait time

                incrementTotalLowUrgencyPatientVisits();
                this.updateTotalLUPWaitTimeInMinutes(patientWaitTime);
                break;

            default:
                break;
        }

    }

    /**
     * Updates emergency room statistics before the patient moves from
     * the arrival queue to the patient examination queue (total patient visits,
     * total wait time for all patients, total wait time for patients by urgency)
     *
     * @param patient patient who is moving from arrival queue to treatment
     */
    void updatePatientToTreatmentStatistics(Patient patient) {

        this.incrementTotalPatientVisits(); // count this patient's visit in the total ER visits

        int patientUrgency = patient.getPatientHistory().getCurrentVisit().getUrgency();

        /* calc total visit wait time in minutes based on patient arrival time
         and exam start time for current visit */
        long patientWaitTime = patient.getPatientHistory().getCurrentVisit()
            .calcVisitWaitTimeInMinutes();
        this.updateTotalWaitTimeInMinutes(patientWaitTime);
        
        // update stats by patient urgency
        updatePatientToTreatmentStatisticsByUrgency(patientUrgency, patientWaitTime);

    }

    /**
     * Generates a report of statistics for this Emergency Room
     * once simulation completes.
     */
    String generateSimulationReport(EmergencyRoom emergencyRoom) {

        /* Calc actual simulation duration in hours */
        this.simulationEndTime = LocalDateTime.now();
        this.simulationDurationInHours = (double) (Duration.between(
            this.simulationStartTime, this.simulationEndTime).toMinutes())/60;

        DecimalFormat percentage = new DecimalFormat("#.#");

        StringBuilder simulationReport = new StringBuilder();

        simulationReport.append("*******************ER SIMULATION REPORT**************************")
            .append("\n")
            .append("\n")
            .append("\n")
            .append("Total number of examination rooms in the system: ")
            .append(emergencyRoom.getExamRoomQueue().getQueue().size())
            .append("\n")
            .append("\n")
            .append("Total simulation duration (in hours): ")
            .append(percentage.format(this.simulationDurationInHours))
            .append("\n")
            .append("\n")
            .append("Total number of patients treated: ")
            .append(this.totalPatientVisits)
            .append("\n")
            .append("\n")
            .append("Total high urgency patients: ")
            .append(this.totalHighUrgencyPatientVisits)
            .append("\n")
            .append("\n")
            .append("Total medium urgency patients: ")
            .append(this.totalMediumUrgencyPatientVisits)
            .append("\n")
            .append("\n")
            .append("Total low urgency patients: ")
            .append(this.totalLowUrgencyPatientVisits)
            .append("\n")
            .append("\n")
            .append("Average wait time for all patients (in minutes): ")
            .append(percentage.format((double) this.totalWaitTimeInMinutes.get()
                / this.totalPatientVisits.get()))
            .append("\n")
            .append("\tAverage wait time for high urgency patients (in minutes): ");

            if((double)this.totalHUPWaitTimeInMinutes.get() == 0.0) {

                simulationReport.append("0.0 (no high urgency patients)")
                .append("\n ");

            } else {

                simulationReport.append(percentage.format((double) this
                    .totalHUPWaitTimeInMinutes.get()/ this.totalHighUrgencyPatientVisits.get()))
                    .append("\n");
            }

            simulationReport.append("\tAverage wait time for medium urgency patients (in minutes): ");

            if((double)this.totalMUPWaitTimeInMinutes.get() == 0.0) {

                simulationReport.append("0.0 (no medium urgency patients)")
                    .append("\n");

             } else {

                simulationReport.append(percentage.format((double) this
                .totalMUPWaitTimeInMinutes.get() / this.totalMediumUrgencyPatientVisits.get()))
                .append("\n");
             }

            simulationReport.append("\tAverage wait time for low urgency patients (in minutes): ");

            if((double)this.totalLUPWaitTimeInMinutes.get() == 0.0) {

                simulationReport.append("0.0 (no low urgency patients)")
                    .append("\n");

            } else {

                simulationReport.append(percentage.format((double) this
                    .totalLUPWaitTimeInMinutes.get()/ this.totalLowUrgencyPatientVisits.get()))
                    .append("\n");
            }

            simulationReport
                .append("\n")
                .append("Average treatment duration (in minutes):\t\t")
                .append(percentage.format((double) this.totalVisitDurationInMinutes.get()
                    / this.totalPatientVisits.get()))
                .append("\n")
                .append("Total number of patients treated & percentage utilization by examination room: ")
                .append("\n")
                .append("\n");

        for (ExamRoom examRoom : emergencyRoom.getExamRoomQueue().getQueue()) {

            simulationReport.append("\tRoom Number: ").append(examRoom.getRoomNumber())
                .append("\n")
                .append("\tTotal patients treated: ").append(examRoom.getNumVisits())
                .append("\n")
                .append("\tPercentage utilization: ")
                .append(percentage.format(examRoom
                    .calcAvgUtilizationPerPatientSelection(this.totalPatientVisits.get()) * 100))
                .append("%")
                .append("\n")
                .append("\n")
                .append("\n");

        }

        return simulationReport.toString();
    }

}
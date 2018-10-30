package edu.neu.ccs.cs5010.assignment2.ersim;

import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * ExamRoom
 * This class sets up an individual empty exam room
 *
 */
public class ExamRoom implements Comparable<ExamRoom> {
    
    // number that identifies the room
    private final int roomNumber;
    // whether the room is busy or available
    private AtomicBoolean isAvailable = new AtomicBoolean();
    // total number of times the room has been visited during the simulation
    private AtomicInteger numVisits = new AtomicInteger(0);
    // what percentage of time has the room been used when avail
    private Double averageUtilizationPerPatientSelection;
    // set when visited by a patient based on pat's projected visit duration
    private LocalDateTime projectedDepartureTime;

    /**
     * Constructor
     *
     * @param roomNumber number that identifies the room
     * @default isAvailable is set to false (available)
     * @default numVisits is already set to 0 (no one has visited yet)
     * @default avgUtilization is set to 0.00 (hasn't been used yet)
     */
    ExamRoom(int roomNumber) {

        this.roomNumber = roomNumber;
        this.isAvailable.set(true);
        this.setAverageUtilizationPerPatientSelection(0.0);

    }

    /* Getters and Setters */

    int getRoomNumber() {

        return roomNumber;
    }

    int getNumVisits() {

        return numVisits.get();
    }

    void setNumVisits(int numVisits) {

        this.numVisits.set(numVisits);
    }

    synchronized double getAverageUtilizationPerPatientSelection() {

        return this.averageUtilizationPerPatientSelection;
    }

    synchronized void setAverageUtilizationPerPatientSelection
        (double averageUtilizationPerPatientSelection) {

        this.averageUtilizationPerPatientSelection =
            averageUtilizationPerPatientSelection;
    }

    LocalDateTime getProjectedDepartureTime() {

        return projectedDepartureTime;
    }

    void setProjectedDepartureTime(LocalDateTime projectedDepartureTime) {

        this.projectedDepartureTime = projectedDepartureTime;
    }

    boolean getAvailability() {

        return this.isAvailable.get();
    }

    void setAvailability(boolean isAvailable) {

        this.isAvailable.set(isAvailable);

    }

    /**
     * Compares two exam rooms based on busy status
     *
     * @param compareExamRm other exam room to which this exam room is compared
     * @return 0 if both rooms are not busy or both rooms are busy,
     * 1 if this room is not busy and compared room is busy
     * -1 if this room is busy and compared room is available
     */
    @Override
    public int compareTo(ExamRoom compareExamRm) {

        if ((this.getAvailability() && compareExamRm.getAvailability())
                || (!this.getAvailability() && !compareExamRm.getAvailability())) {

            return 0;
        }

        else if (this.getAvailability() && !compareExamRm.getAvailability()) {

            return -1;
        }

        else {

            return 1;
        }

    }

    /**
     * Marks the room as busy and updates the visit count and
     * the projected departure time
     *
     * @param patient that will be using the exam room
     */
    void fillRoom(Patient patient) {

        // mark exam room as not available
        this.setAvailability(false);
        // set the time when the room is expected to become available again
        this.projectedDepartureTime = patient.getPatientHistory()
            .getCurrentVisit().getDepartureTime();

    }

    /**
     * Updates the number of times a room was visited as well as
     * the percentage of time that the room has been used
     *
     * @param ER emergency room to get total patient visits
     */
    void updateExamRoomStatistics(EmergencyRoom ER) {

        // add one to the number of times the exam room was visited
        this.incrementNumVisits();

        //use exam room visits over total patient visits to update average utilization time
        this.setAverageUtilizationPerPatientSelection
                 (this.calcAvgUtilizationPerPatientSelection
                     (ER.getStatisticsGatherer().getTotalPatientVisits()));
    }

    private void incrementNumVisits() {

        this.numVisits.getAndIncrement();
    }

    /**
     * Calculates a running average of how many visits this room got
     * as a percentage of total patient visits during the simulation
     * -- measure of patient routing efficiency
     *
     * @param totalPatientVisits total number of patients who visited any
     *                           exam room during the simulation
     * @return the average of room visits/total patient visits
     */
    double calcAvgUtilizationPerPatientSelection(int totalPatientVisits) {

        return (double) this.numVisits.get() / (double) totalPatientVisits;
    }

    /**
     * Release Room changes a given room's status to empty (not busy)
     */
    void releaseRoom() {

        this.setAvailability(true);
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = roomNumber;
        result = 31 * result + (isAvailable.get() ? 1 : 0);
        result = 31 * result + numVisits.get();
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ExamRoom examRoom = (ExamRoom) o;

        return roomNumber == examRoom.roomNumber;
    }

    @Override
    public String toString() {

        return Integer.toString(this.getRoomNumber());
    }
}
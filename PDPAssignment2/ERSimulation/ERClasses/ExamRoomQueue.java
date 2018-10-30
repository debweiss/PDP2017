package edu.neu.ccs.cs5010.assignment2.ersim;

import java.util.*;


/**
 * ExamRoomQueue holds and routes exam rooms
 * that are used in the simulation
 *
 */
class ExamRoomQueue extends MyPriorityQueue<ExamRoom> {

    /**
     * Constructor - populates the queue with the specified number
     * of exam rooms, and sets the queue comparator as ERExamRoomComparator,
     * which compares based on room empty or full status (empty is first), then
     * utilization number (lower comes first)
     *
     * @param numExamRooms number of exam rooms to be used in the simulation
     */
    ExamRoomQueue(int numExamRooms) {

        super();
        this.queueComparator = new ExamRoomComparator(); // create new Exam Room comparator

        for (int i = 1; i <= numExamRooms; i++) { // populate list with the specified number of rooms

            this.queue.add(new ExamRoom(i));
        }
    }

    /**
     * Compares two exam rooms based first on busy status (empty first) and
     * then based on avg utilization (lower first)
     */
    public static class ExamRoomComparator implements Comparator<ExamRoom> {

        /**
         * @param exam1 first room to compare
         * @param exam2 second room to compare
         * @return 0 if both rooms are equal, -1 if the first room should come
         * before the second room in the queue, 1, if the second room should come
         * before the first
         */
        @Override
        public int compare(ExamRoom exam1, ExamRoom exam2) {

            ExamRoomQueue.AverageUtilizationComparator averageUtilizationComparator =
                new ExamRoomQueue.AverageUtilizationComparator();

            if (exam1.compareTo(exam2) == 0) {
                return averageUtilizationComparator.compare(exam1, exam2);
            }
            else {
                return exam1.compareTo(exam2);
            }

        }
    }

    /**
     * This class compares two exam rooms based on how much each has
     * been used (avg utilization percentage)
     */
    public static class AverageUtilizationComparator implements Comparator<ExamRoom> {
        /**
         * @param examRoom1 first room to be compared
         * @param examRoom2 second room to be compared
         * @return 0 if both rooms are equal (same utilization), -1 if the first room
         * has lower utilization, room in the queue, 1, if the second room should come
         * before the first
         */
        @Override
        public int compare(ExamRoom examRoom1, ExamRoom examRoom2) {
            Double averageUtilization1 = examRoom1.getAverageUtilizationPerPatientSelection();
            Double averageUtilization2 = examRoom2.getAverageUtilizationPerPatientSelection();

            //ascending order
            return Double.compare(averageUtilization1, averageUtilization2);
        }
    }

    /**
     * Returns whether the ER has any available exam rooms
     *
     * @return true if a room is available, false if not
     */
    synchronized boolean checkRoomAvail() {

        return this.front().getAvailability();
    }

    /**
     * Used when a room's status is changed to make sure
     * it is in the right order in the exam room queue
     *
     * @param examRoom room whose status has been changed
     */
    synchronized void sortExamRoomQueue(ExamRoom examRoom) {

        // when a room's status is changed, minheapify to preserve min heap
        this.minHeapify(this.getQueue().indexOf(examRoom));
    }
}









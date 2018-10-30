package edu.neu.ccs.cs5010.assignment2.ersim;

import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;


public class ExamRoomQueueTest {

    private ExamRoomQueue examRoomQueue;

    @Before
    public void setUp() throws Exception {

        examRoomQueue = new ExamRoomQueue(3);

    }

    @Test
    public void testConstructor() throws Exception {

        Assert.assertEquals("examRoomQueue should have 3 rooms, since 3 was passed to the constructor",
            3, examRoomQueue.getQueue().size(), 0);

    }

    @Test
    public void testExamRoomComparator() throws Exception {

        ExamRoomQueue.ExamRoomComparator examRoomComparator = new ExamRoomQueue.ExamRoomComparator();

        ExamRoom fullExamRoomHighUtilization = new ExamRoom(4);
        fullExamRoomHighUtilization.setAvailability(false);
        fullExamRoomHighUtilization.setAverageUtilizationPerPatientSelection(.75);

        ExamRoom fullExamRoomLowUtilization = new ExamRoom(5);
        fullExamRoomLowUtilization.setAvailability(false);
        fullExamRoomLowUtilization.setAverageUtilizationPerPatientSelection(.25);

        ExamRoom emptyExamRoomHighUtilization = new ExamRoom(6);
        emptyExamRoomHighUtilization.setAverageUtilizationPerPatientSelection(.75);

        ExamRoom emptyExamRoomLowUtilization = new ExamRoom(7);
        emptyExamRoomLowUtilization.setAverageUtilizationPerPatientSelection(.25);

        Assert.assertEquals("result should be zero, because both rooms are the same",
            0, examRoomComparator.compare(emptyExamRoomHighUtilization, emptyExamRoomHighUtilization), 0);

        Assert.assertEquals("result should be 1, because the second room has lower utilization",
            1, examRoomComparator.compare(emptyExamRoomHighUtilization, emptyExamRoomLowUtilization), 0);

        Assert.assertEquals("result should be 1, because the second room is empty, and the first room is full",
            1, examRoomComparator.compare(fullExamRoomLowUtilization, emptyExamRoomHighUtilization), 0);

        Assert.assertEquals("result should be -1, because the second room has higher utilization",
            -1, examRoomComparator.compare(emptyExamRoomLowUtilization, emptyExamRoomHighUtilization), 0);

        Assert.assertEquals("result should be -1, because the second room is full, and the first room is empty",
            -1, examRoomComparator.compare(emptyExamRoomLowUtilization, fullExamRoomLowUtilization), 0);
    }

    @Test
    public void testCheckAvail() {

        ExamRoomQueue examRoomQueue = new ExamRoomQueue(5);
        ExamRoomQueue examRoomQueue2 = new ExamRoomQueue(1);

        examRoomQueue2.getQueue().get(0).setAvailability(false);

        Assert.assertTrue("there are 1 or more rooms available in examRoomQueue",
            examRoomQueue.checkRoomAvail());

        Assert.assertFalse("There are no rooms available in examRoomQueue2",
            examRoomQueue2.checkRoomAvail());
    }

    @Test
    public void testSortExamQueue() {

        ExamRoomQueue examRoomQueue3 = new ExamRoomQueue(5);

        examRoomQueue3.front().setAvailability(false);

        examRoomQueue3.sortExamRoomQueue(examRoomQueue3.front());

        Assert.assertEquals("since the first room has been made busy, it should now be" +
            "in the 3rd position in the queue", 1, examRoomQueue3.getQueue().get(2).getRoomNumber(), 0);

     }

}


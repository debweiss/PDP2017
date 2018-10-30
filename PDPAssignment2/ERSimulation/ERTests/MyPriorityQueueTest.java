package edu.neu.ccs.cs5010.assignment2.ersim;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class MyPriorityQueueTest<E> {
    
    
    private MyPriorityQueue<Patient> priorityQueueArrival;
    private MyPriorityQueue<Patient> priorityQueueArrival2;
    private MyPriorityQueue<Patient> priorityQueueEmpty;
    private Patient constructorPatient;
    private Patient.PatientHistory constructorPatientHistory;
    private Patient.PatientHistory.Visit constructorPatientVisit;
    private Patient constructorPatientCompare;
    private Patient setterPatient;
    private Patient setterPatient2;
    private Patient reallyEarlyArrivalHighUrgencyPatient;
    private Patient lateArrivalHighUrgencyPatient;
    private Patient earlyArrivalMiddleUrgencyPatient;
    private Patient reallyLateArrivalLowUrgencyPatient;
    private Patient earlyArrivalLowUrgencyPatient;
    private Patient lateArrivalLowUrgencyPatient;
    
    
    private LocalDate arrivalDate = LocalDate.now();
    private LocalDateTime earlyArrivalTime = LocalDateTime.now();
    private LocalDateTime reallyEarlyArrivalTime = earlyArrivalTime.minusHours(4);
    private LocalDateTime lateArrivalTime = earlyArrivalTime.plusHours(1);
    private LocalDateTime reallyLateArrivalTime = lateArrivalTime.plusHours(1);
    private LocalDateTime earlyDepartureTime = LocalDateTime.now().plusMinutes(90);
    private LocalDateTime lateDepartureTime = earlyDepartureTime.plusMinutes(30);
    
    private Patient.urgencyComparator urgencyComparator;
    private Patient.arrivalTimeComparator patientArrivalTimeComparator;
    private Patient.departureTimeComparator patientDepartureTimeComparator;
    
    
    @Before
    public void setUp() {
        
        priorityQueueArrival = new MyPriorityQueue<Patient>();
        priorityQueueArrival2 = new MyPriorityQueue<Patient>();
        priorityQueueEmpty = new MyPriorityQueue<Patient>();
        
        ArrayList<Patient> emptyArrayList = new ArrayList<>();
        
        priorityQueueEmpty.setQueue(emptyArrayList);
        
        constructorPatient = new Patient(81, "George",
            "Washington", 34);
        constructorPatientVisit = new Patient.PatientHistory.Visit(LocalDate.now(),
            LocalDateTime.now(),
            0.0, 0, 0);
        constructorPatientHistory = new Patient.PatientHistory
            (constructorPatientVisit);
        constructorPatient.setPatientHistory(constructorPatientHistory);
        
        constructorPatientCompare = new Patient(81, "George",
            "Washington", 34);
        constructorPatientCompare.setPatientHistory(constructorPatientHistory);
        
        setterPatient = new Patient(82, "Fred", "Meyer", 44);
        setterPatient.setPatientHistory(constructorPatientHistory);
        
        setterPatient2 = setterPatient;
        
        reallyEarlyArrivalHighUrgencyPatient = new PatientGenerator(75,
            5, arrivalDate, reallyEarlyArrivalTime, 1)
            .getNewPatient();
        
        lateArrivalHighUrgencyPatient = new PatientGenerator(76, 5,
            arrivalDate, lateArrivalTime, 1).getNewPatient();
        
        earlyArrivalMiddleUrgencyPatient = new PatientGenerator(77, 5,
            arrivalDate, earlyArrivalTime, 2).getNewPatient();
        
        reallyLateArrivalLowUrgencyPatient = new PatientGenerator(78,
            5, arrivalDate, reallyLateArrivalTime, 3)
            .getNewPatient();
        
        earlyArrivalLowUrgencyPatient = new PatientGenerator(79, 5,
            arrivalDate, earlyArrivalTime, 3).getNewPatient();
        
        lateArrivalLowUrgencyPatient = new PatientGenerator(80, 5,
            arrivalDate, lateArrivalTime, 3).getNewPatient();
        
        patientArrivalTimeComparator = new Patient.arrivalTimeComparator();
        patientDepartureTimeComparator = new Patient.departureTimeComparator();
        urgencyComparator = new Patient.urgencyComparator();
        
        ArrayList<Patient> patientArrayList = new ArrayList<>();
        
        priorityQueueArrival.setQueue(patientArrayList);
        
        priorityQueueArrival.getQueue().add(lateArrivalHighUrgencyPatient); // id 76
        priorityQueueArrival.getQueue().add(earlyArrivalLowUrgencyPatient); // id 79
        priorityQueueArrival.getQueue().add(reallyLateArrivalLowUrgencyPatient); // id 78
        priorityQueueArrival.getQueue().add(reallyEarlyArrivalHighUrgencyPatient); // id 75
        
        ArrayList<Patient> patientArrayList2 = new ArrayList<>();
        
        priorityQueueArrival2.setQueue(patientArrayList2);
        
        priorityQueueArrival2.getQueue().add(lateArrivalHighUrgencyPatient); // id 76
        priorityQueueArrival2.getQueue().add(earlyArrivalLowUrgencyPatient); // id 79
        priorityQueueArrival2.getQueue().add(reallyLateArrivalLowUrgencyPatient); // id 78
        
        priorityQueueArrival.setQueueComparator(patientArrivalTimeComparator);
        priorityQueueArrival2.setQueueComparator(patientArrivalTimeComparator);
    }
    
    @Test
    public void testFront() throws Exception {
        
        Patient patientFront = priorityQueueArrival.front();
        
        Assert.assertEquals("late arrival patient should be in the front " +
            "of the queue", 76, patientFront.getId());
        
    }
    
    @Test
    public void testIsEmpty() throws Exception {
        
        Assert.assertTrue("should be true because queue is empty ",
            priorityQueueEmpty.isEmpty());
        Assert.assertFalse("should be false because queue is not empty ",
            priorityQueueArrival.isEmpty());
        
    }
    
    @Test
    public void testInsert() throws Exception {
        
        MyPriorityQueue<Integer> integerQueue = new MyPriorityQueue<>();
        
        priorityQueueArrival2.insert(reallyEarlyArrivalHighUrgencyPatient); // id 75

        /* with queue comparator */
        Assert.assertEquals("really early patient should be the first patient in the queue",
            75, priorityQueueArrival2.getQueue().get(0).getId());
        Assert.assertEquals("early patient should be the second patient in the queue",
            76, priorityQueueArrival2.getQueue().get(1).getId());
        Assert.assertEquals("really late patient should be the third patient in the queue",
            78, priorityQueueArrival2.getQueue().get(2).getId());
        Assert.assertEquals("late patient should be the fourth patient in the queue",
            79, priorityQueueArrival2.getQueue().get(3).getId());
        
        /* without queue comparator (natural ordering) */
        
        integerQueue.getQueue().add(8);
        integerQueue.getQueue().add(10);
        integerQueue.getQueue().add(17);
        integerQueue.getQueue().add(16);
        integerQueue.getQueue().add(15);
        integerQueue.getQueue().add(20);
        integerQueue.getQueue().add(25);
        
        integerQueue.insert(9);
        
        Assert.assertTrue("should be 8", integerQueue.getQueue().get(0) == 8);
        Assert.assertTrue("should be 9", integerQueue.getQueue().get(1) == 9);
        Assert.assertTrue("should be 17", integerQueue.getQueue().get(2) == 17);
        Assert.assertTrue("should be 10", integerQueue.getQueue().get(3) == 10);
        Assert.assertTrue("should be 15", integerQueue.getQueue().get(4) == 15);
        Assert.assertTrue("should be 20", integerQueue.getQueue().get(5) == 20);
        Assert.assertTrue("should be 25", integerQueue.getQueue().get(6) == 25);
        Assert.assertTrue("should be 16", integerQueue.getQueue().get(7) == 16);
        
    }
    
    @Test
    public void testRemove() throws Exception {
        
        MyPriorityQueue<Patient> testRemoveQueue = new MyPriorityQueue<>();
        MyPriorityQueue<Integer> testIntegerRemove = new MyPriorityQueue<>();
        
        testIntegerRemove.getQueue().add(1);
        testIntegerRemove.getQueue().add(2);
        testIntegerRemove.getQueue().add(3);
        
        Integer removedInteger = testIntegerRemove.remove();
    
        Assert.assertEquals("removed integer should be the former min integer",
            1, removedInteger, 0);

        Assert.assertEquals("first integer should be 2", 2,
            testIntegerRemove.front(), 0);
    
        Assert.assertEquals("second integer should be 3",
        3, testIntegerRemove.getQueue().get(1), 0);
        
        
        testRemoveQueue.setQueueComparator(new PatientArrivalQueue
            .ERPatientArrivalQueueComparator());
        testRemoveQueue.getQueue().add(lateArrivalHighUrgencyPatient); // id 76
        testRemoveQueue.getQueue().add(earlyArrivalLowUrgencyPatient); // id 79
        testRemoveQueue.getQueue().add(reallyLateArrivalLowUrgencyPatient); // id 78
        
        Patient removedPatient = testRemoveQueue.remove();
        
        Assert.assertEquals("removed patient should be the former min patient",
            lateArrivalHighUrgencyPatient, removedPatient);
        
        Assert.assertEquals("early patient should be the first patient in the queue",
            earlyArrivalLowUrgencyPatient, testRemoveQueue.getQueue().get(0));
        
        Assert.assertEquals("really late patient should be the secondpatient in the queue",
            reallyLateArrivalLowUrgencyPatient, testRemoveQueue.getQueue().get(1));
        
        testRemoveQueue.getQueue().clear();
        
        testRemoveQueue.getQueue().add(lateArrivalHighUrgencyPatient); // id 76
        testRemoveQueue.getQueue().add(reallyLateArrivalLowUrgencyPatient); // id 78
        testRemoveQueue.getQueue().add(earlyArrivalLowUrgencyPatient); // id 79
        
        Patient removedPatient2 = testRemoveQueue.remove();
        
        Assert.assertEquals("removed patient2 should be the former min patient",
            lateArrivalHighUrgencyPatient, removedPatient2);
        
        Assert.assertEquals("early patient should be the first patient in the queue",
            earlyArrivalLowUrgencyPatient, testRemoveQueue.getQueue().get(0));
        
        Assert.assertEquals("really late patient should be the secondpatient in the queue",
            reallyLateArrivalLowUrgencyPatient, testRemoveQueue.getQueue().get(1));
        
    }
    
    @Test
    public void testMinHeapify() throws Exception {
        
        ArrayList<Patient> testMinHeapifyQueue = new ArrayList<Patient>();
        
        testMinHeapifyQueue.add(lateArrivalHighUrgencyPatient); // id 76
        testMinHeapifyQueue.add(earlyArrivalLowUrgencyPatient); // id 79
        testMinHeapifyQueue.add(reallyLateArrivalLowUrgencyPatient); // id 78
        
        priorityQueueArrival.setQueue(testMinHeapifyQueue);
        
        priorityQueueArrival.minHeapify(0);
        
        Assert.assertEquals("early patient should be the first patient in the queue",
            79, priorityQueueArrival.getQueue().get(0).getId());
        
        Assert.assertEquals("late patient should be the second patient in the queue",
            76, priorityQueueArrival.getQueue().get(1).getId());
        
        Assert.assertEquals("really late patient should be the third patient in the queue",
            78, priorityQueueArrival.getQueue().get(2).getId());
    }
    
    
    @Test
    public void testReverseTraversal() throws Exception {
       
       
       /* test one level */
        List<Patient> testReverseTraversalArrayList = new ArrayList<>();
        
        MyPriorityQueue<Patient> testReverseQueue = new MyPriorityQueue<>();
        
        testReverseQueue.getQueue().add(lateArrivalHighUrgencyPatient);
        testReverseQueue.getQueue().add(earlyArrivalLowUrgencyPatient);
        testReverseQueue.getQueue().add(reallyLateArrivalLowUrgencyPatient);
        testReverseQueue.getQueue().add(reallyEarlyArrivalHighUrgencyPatient);
        
        /* reverse should be:
            reallyEarlyArrivalHighUrgencyPatient
            reallyLateArrivalLowUrgency
            earlyArrivalLowUrgency
            late arrival high urgency
         */
        
        testReverseTraversalArrayList = testReverseQueue.testReverseTraversal();
        
        Assert.assertEquals("really early arrival HU patient should be " +
            "the first patient in the queue", reallyEarlyArrivalHighUrgencyPatient, 
            testReverseTraversalArrayList.get(0));
        
        Assert.assertEquals("really late arrival low urgency should be the second " +
            "patient in the queue", reallyLateArrivalLowUrgencyPatient, 
            testReverseTraversalArrayList.get(1));
        
        Assert.assertEquals("early arrival low urgency patient should be the third patient in " +
            "the queue", earlyArrivalLowUrgencyPatient, testReverseTraversalArrayList.get(2));
        
        Assert.assertEquals("late arrival high urgency pateint should be the fourth patient in " +
            "the queue", lateArrivalHighUrgencyPatient, testReverseTraversalArrayList.get(3));
    
        /* test multiple levels even */
        
        MyPriorityQueue<Integer> testMultiLevelIntegerQueue = new MyPriorityQueue<>();
        
        testMultiLevelIntegerQueue.getQueue().add(1);
        testMultiLevelIntegerQueue.getQueue().add(2);
        testMultiLevelIntegerQueue.getQueue().add(3);
        testMultiLevelIntegerQueue.getQueue().add(4);
        testMultiLevelIntegerQueue.getQueue().add(5);
        testMultiLevelIntegerQueue.getQueue().add(6);
        testMultiLevelIntegerQueue.getQueue().add(7);
        
        List<Integer> testMultiLevelIntegerList = new ArrayList<>();
        
        testMultiLevelIntegerList = testMultiLevelIntegerQueue.testReverseTraversal();
        
        Assert.assertEquals("first integer should be 7",
            7, testMultiLevelIntegerList.get(0), 0);
        
        Assert.assertEquals("second integer should be 6",
            6, testMultiLevelIntegerList.get(1), 0);
        
        Assert.assertEquals("third integer should be 5",
            5, testMultiLevelIntegerList.get(2), 0);
        
        Assert.assertEquals("fourth integer should be 4",
            4, testMultiLevelIntegerList.get(3), 0);
        
        Assert.assertEquals("fifth integer should be 3",
            3, testMultiLevelIntegerList.get(4), 0);
        
        Assert.assertEquals("sixth integer should be 2",
            2, testMultiLevelIntegerList.get(5), 0);
        
        Assert.assertEquals("seventh integer should be 1",
            1, testMultiLevelIntegerList.get(6), 0);
        
        /* test multiple levels odd */
        
        MyPriorityQueue<Integer> testMultiLevelIntegerQueueOdd = new MyPriorityQueue<>();
        
        testMultiLevelIntegerQueueOdd.getQueue().add(1);
        testMultiLevelIntegerQueueOdd.getQueue().add(2);
        testMultiLevelIntegerQueueOdd.getQueue().add(3);
        testMultiLevelIntegerQueueOdd.getQueue().add(4);
        testMultiLevelIntegerQueueOdd.getQueue().add(5);
        testMultiLevelIntegerQueueOdd.getQueue().add(6);
        
        List<Integer> testMultiLevelIntegerListOdd = new ArrayList<>();
        
        testMultiLevelIntegerListOdd = testMultiLevelIntegerQueueOdd.testReverseTraversal();
        
        Assert.assertEquals("first integer should be 6",
            6, testMultiLevelIntegerListOdd.get(0), 0);
        
        Assert.assertEquals("second integer should be 5",
            5, testMultiLevelIntegerListOdd.get(1), 0);
        
        Assert.assertEquals("third integer should be 4",
            4, testMultiLevelIntegerListOdd.get(2), 0);
        
        Assert.assertEquals("fourth integer should be 3",
            3, testMultiLevelIntegerListOdd.get(3), 0);
        
        Assert.assertEquals("fifth integer should be 2",
            2, testMultiLevelIntegerListOdd.get(4), 0);
        
        Assert.assertEquals("sixth integer should be 1",
            1, testMultiLevelIntegerListOdd.get(5), 0);
        
    }
    
    @Test
    public void testForwardTraversal() throws Exception {
    
        MyPriorityQueue<Integer> testMultiLevelIntegerQueue = new MyPriorityQueue<>();
        MyPriorityQueue<Patient> testPatientQueueForwardTraversal = new MyPriorityQueue<>();
    
        List<Integer> testForwardTraversalArrayList = new ArrayList<>();
        List<Patient>testForwardTraversalPatientList = new ArrayList<>();
    
        testMultiLevelIntegerQueue.getQueue().add(1);
        testMultiLevelIntegerQueue.getQueue().add(2);
        testMultiLevelIntegerQueue.getQueue().add(3);
        testMultiLevelIntegerQueue.getQueue().add(4);
        testMultiLevelIntegerQueue.getQueue().add(5);
        testMultiLevelIntegerQueue.getQueue().add(6);
        testMultiLevelIntegerQueue.getQueue().add(7);
    
        testPatientQueueForwardTraversal.getQueue().add(reallyEarlyArrivalHighUrgencyPatient);
        testPatientQueueForwardTraversal.getQueue().add(earlyArrivalLowUrgencyPatient);
        testPatientQueueForwardTraversal.getQueue().add(lateArrivalLowUrgencyPatient);
        testPatientQueueForwardTraversal.getQueue().add(reallyLateArrivalLowUrgencyPatient);
    
        testForwardTraversalPatientList = testPatientQueueForwardTraversal.testForwardTraversal();
        

        testForwardTraversalArrayList = testMultiLevelIntegerQueue.testForwardTraversal();
    
        Assert.assertEquals("first integer should be 1",
            1, testForwardTraversalArrayList.get(0), 0);
    
        Assert.assertEquals("second integer should be 2",
            2, testForwardTraversalArrayList.get(1), 0);
    
        Assert.assertEquals("third integer should be 3",
            3, testForwardTraversalArrayList.get(2), 0);
    
        Assert.assertEquals("fourth integer should be 4",
            4, testForwardTraversalArrayList.get(3), 0);
    
        Assert.assertEquals("fifth integer should be 5",
            5, testForwardTraversalArrayList.get(4), 0);
    
        Assert.assertEquals("sixth integer should be 6",
            6, testForwardTraversalArrayList.get(5), 0);
    
        Assert.assertEquals("seventh integer should be 7",
            7, testForwardTraversalArrayList.get(6), 0);
    
    
        Assert.assertEquals("really early arrival HU patient should be " +
                "the first patient in the queue", reallyEarlyArrivalHighUrgencyPatient,
            testForwardTraversalPatientList.get(0));
    
        Assert.assertEquals("early arrival low urgency patient should be the second patient in " +
            "the queue", earlyArrivalLowUrgencyPatient, testForwardTraversalPatientList.get(1));
    
        Assert.assertEquals("late arrival low urgency patient should be the third patient in " +
            "the queue", lateArrivalLowUrgencyPatient, testForwardTraversalPatientList.get(2));
    
        Assert.assertEquals("really late arrival low urgency should be the second " +
                "patient in the queue", reallyLateArrivalLowUrgencyPatient,
            testForwardTraversalPatientList.get(3));
        
    }
}


  package edu.neu.ccs.cs5010.assignment2.ersim;

  import org.junit.Assert;
  import org.junit.Before;
  import org.junit.Test;

  import java.time.LocalDateTime;
  import java.time.temporal.ChronoUnit;

  import java.io.File;


  public class ExamRoomTest {


      private ExamRoom constructorExamRoom;
      private ExamRoom emptyExamRoomLowUtilization;
      private ExamRoom emptyExamRoomHighUtilization;
      private ExamRoom fullExamRoomLowUtilization;
      private ExamRoom fullExamRoomHighUtilization;
      private ExamRoom roomToBeFilled;
      private ExamRoom calcUtilizationRoom;
      private Patient fillPatient;
      private EmergencyRoom emergencyRoom;

      private LocalDateTime startTime = LocalDateTime.now();
      private LocalDateTime endTime10Min = startTime.plusMinutes(10);
      private LocalDateTime departureTime = startTime.plus(60000, ChronoUnit.MILLIS);
      private LocalDateTime endTime60Min = startTime.plusHours(1);

      @Before
      public void setUp() throws Exception {

          constructorExamRoom = new ExamRoom(1);

          emptyExamRoomLowUtilization = new ExamRoom(2);
          emptyExamRoomLowUtilization.setAverageUtilizationPerPatientSelection(.20);

          emptyExamRoomHighUtilization = new ExamRoom(3);
          emptyExamRoomHighUtilization.setAverageUtilizationPerPatientSelection(.75);

          fullExamRoomLowUtilization = new ExamRoom(4);
          fullExamRoomLowUtilization.setAvailability(false);
          fullExamRoomLowUtilization.setAverageUtilizationPerPatientSelection(.20);

          fullExamRoomHighUtilization = new ExamRoom(5);
          fullExamRoomHighUtilization.setAvailability(false);
          fullExamRoomHighUtilization.setAverageUtilizationPerPatientSelection(.80);

          roomToBeFilled = new ExamRoom(6);

          calcUtilizationRoom = new ExamRoom(7);

          departureTime = LocalDateTime.now();

          fillPatient = EmergencyRoom.generateNewPatient(5, 5);

          fillPatient.getPatientHistory().getCurrentVisit().setDepartureTime(departureTime);

          fillPatient.getPatientHistory().getCurrentVisit().setArrivalTime(startTime);
          fillPatient.getPatientHistory().getCurrentVisit().setDepartureTime(departureTime);
          fillPatient.getPatientHistory().getCurrentVisit().setExamStartTime(endTime10Min);

          emergencyRoom = new EmergencyRoom(5, new ERStatisticsGatherer(startTime));

      }

      @Test
      public void testConstructor() throws Exception {

          String ret = System.getProperty("file.separator");
          ret = String.valueOf(File.separatorChar);

          String workingDir = System.getProperty("user.dir");

          System.out.println("file separator " + ret + "working dir " + workingDir);

          Assert.assertEquals("Room number should be 1, because that's what was passed to the constructor",
              1, this.constructorExamRoom.getRoomNumber(), 0);

          Assert.assertEquals("Availability should be true, because that's the default",
              true, this.constructorExamRoom.getAvailability());

          Assert.assertEquals("AverageUtilization should be 0.0, because that's the default",
              0.0, this.constructorExamRoom.getAverageUtilizationPerPatientSelection(), 0.0);

      }

      @Test
      public void getRoomNumber() throws Exception {

          int roomNumber = 1;

          Assert.assertEquals("Room Numbers should be equal", roomNumber,
              constructorExamRoom.getRoomNumber());

      }

      @Test
      public void setAndGetIsAvailable() throws Exception {

          Assert.assertEquals("Room should be busy (availability == false)", false,
              fullExamRoomLowUtilization.getAvailability());

          Assert.assertEquals("Room should be avail (availability == true)", true,
              emptyExamRoomLowUtilization.getAvailability());

      }

      @Test
      public void setAndGetNumVisits() throws Exception {

          int numVisits = 5;

          constructorExamRoom.setNumVisits(numVisits);

          Assert.assertEquals("Number of visits should be equal", numVisits,
              constructorExamRoom.getNumVisits());

      }

      @Test
      public void setAndGetAverageUtilizationPerPatientSelection1() throws Exception {

          double avgPatientUtilization =  .65;

          constructorExamRoom.setAverageUtilizationPerPatientSelection(avgPatientUtilization);

          double myDouble = constructorExamRoom.getAverageUtilizationPerPatientSelection();

          Assert.assertTrue("Avg utilization should be equal", avgPatientUtilization ==
              constructorExamRoom.getAverageUtilizationPerPatientSelection());
      }

      @Test
      public void setAndGetProjectedDepartureTime() throws Exception {

          LocalDateTime nowTime = LocalDateTime.now();

          constructorExamRoom.setProjectedDepartureTime(nowTime);

          Assert.assertEquals("DateTime should be equal", nowTime,
              constructorExamRoom.getProjectedDepartureTime());

      }

      @Test
      public void testCompareEmpty() throws Exception {

          Assert.assertEquals("Both rooms are empty, so 0 should be returned",
                0, this.emptyExamRoomLowUtilization.compareTo(emptyExamRoomHighUtilization), 0);
      }

      @Test
      public void testCompareFull() throws Exception {

          Assert.assertEquals("Both rooms are full, so 0 should be returned",
              0, this.fullExamRoomLowUtilization.compareTo(fullExamRoomHighUtilization), 0);
      }


      @Test
      public void testCompareFullEmpty() throws Exception {

          // based on availability (available is first)

          Assert.assertEquals("0 should be returned because both rooms are empty",
              0, this.emptyExamRoomHighUtilization.compareTo(emptyExamRoomLowUtilization), 0);

          Assert.assertEquals("0 should be returned because both rooms are full",
              0, this.fullExamRoomHighUtilization.compareTo(fullExamRoomLowUtilization), 0);

          Assert.assertEquals("-1 should be returned because the compared room (full) is compared to the empty room",
              -1, this.emptyExamRoomHighUtilization.compareTo(fullExamRoomLowUtilization), 0);

          Assert.assertEquals("1 should be returned because the compared room (empty) is compared to the full room",
                1, this.fullExamRoomLowUtilization.compareTo(emptyExamRoomLowUtilization), 0);

      }


      @Test
      public void testReleaseRoom() throws Exception {

          fullExamRoomHighUtilization.releaseRoom();

          Assert.assertEquals("availability should be true,", true,
              fullExamRoomHighUtilization.getAvailability());

      }

      @Test
      public void testFillRoom() {

          roomToBeFilled.fillRoom(fillPatient);

          Assert.assertEquals("room availability should be false", false,
              roomToBeFilled.getAvailability());
          Assert.assertEquals("patient departureTime should be the same as departureTime that was "
            + "set for fill patient", departureTime, fillPatient.getPatientHistory()
            .getCurrentVisit().getDepartureTime());


      }

      @Test
      public void testCalcAvgUtilizationPerPatientSelection() {

         calcUtilizationRoom.setNumVisits(7);
         int totalPatientVisits = 30;

          Assert.assertTrue("the avg utilization per patient selection should be .23",
              calcUtilizationRoom.calcAvgUtilizationPerPatientSelection(totalPatientVisits) == .23333333333333334);
      }

      @Test
      public void  testUpdateExamRoomStatistics() {

          emergencyRoom.getStatisticsGatherer().updatePatientToTreatmentStatistics(fillPatient);

          roomToBeFilled.updateExamRoomStatistics(emergencyRoom);

          Assert.assertEquals("number of visits for this room should be 1, because 1 was added",
              1, roomToBeFilled.getNumVisits());
          Assert.assertTrue("the avg utilization per patient selection should be 1.0",
              roomToBeFilled.getAverageUtilizationPerPatientSelection() == 1.0);

      }


      @Test
      public void testToString() throws Exception {

          Assert.assertEquals("should be number 6 as a string", "6",
              roomToBeFilled.toString());

      }

      @Test
      public void testEquals() throws Exception {

          Integer testInteger = 3;

          Assert.assertFalse("objects are of different classes, so equals should be false",
              constructorExamRoom.equals(testInteger));

          Assert.assertFalse("rooms are different, so equals should be false",
              constructorExamRoom.equals(roomToBeFilled));

          Assert.assertTrue("rooms are the same, so equals should be true",
              constructorExamRoom.equals(constructorExamRoom));

      }

      @Test
      public void testHashCode() throws Exception {


          Assert.assertTrue("the hashcode for the same objects should be the same",
              constructorExamRoom.hashCode() == constructorExamRoom.hashCode());


      }

      @Test
      public void testAverageUtilizationComparator() throws Exception {

          ExamRoomQueue.AverageUtilizationComparator averageUtilizationComparator = new ExamRoomQueue.AverageUtilizationComparator();

          Assert.assertEquals("1 should be returned because the second room has lower utilization than the first room",
              1, averageUtilizationComparator.compare(emptyExamRoomHighUtilization, fullExamRoomLowUtilization), 0);

          Assert.assertEquals("-1 should be returned because the second room has higher utilization than the first room",
              -1, averageUtilizationComparator.compare(fullExamRoomLowUtilization, emptyExamRoomHighUtilization), 0);

          Assert.assertEquals("0 should be returned because both rooms have the same utilization",
              0, averageUtilizationComparator.compare(fullExamRoomLowUtilization, emptyExamRoomLowUtilization), 0);

          Assert.assertEquals("1 should be returned because the second room has lower utilization than the first room",
              1, averageUtilizationComparator.compare(emptyExamRoomHighUtilization, emptyExamRoomLowUtilization), 0);

      }

  }
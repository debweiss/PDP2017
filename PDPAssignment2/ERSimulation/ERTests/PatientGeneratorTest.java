  package edu.neu.ccs.cs5010.assignment2.ersim;

  import org.junit.Assert;
  import org.junit.Test;
  import org.junit.Before;

  import java.time.LocalDate;
  import java.time.LocalDateTime;

  public class PatientGeneratorTest {

      private LocalDate arrivalDate;
      private LocalDateTime earlyArrivalTime;
      private LocalDateTime earlyDepartureTime;
      private Patient constructorPatient;
      private Patient.PatientHistory.Visit constructorVisit;
      private Patient.PatientHistory constructorPatientHistory;
      private PatientGenerator newPatientGenerator;
      private PatientGenerator testPatientGenerator;


      @Before
      public void setUp() throws Exception {

          // create
          this.arrivalDate = LocalDate.now();
          this.earlyArrivalTime = LocalDateTime.now();
          this.earlyDepartureTime = LocalDateTime.now().plusMinutes(90);

          // create a new Patient to compare to the generated one
          this.constructorPatient = new Patient(1, "Benjamin", "Franklin", 75);

          this.constructorVisit = new Patient.PatientHistory.Visit(arrivalDate,
              earlyArrivalTime, 100.00, 120, 80);


          this.constructorPatientHistory = new Patient.PatientHistory(constructorVisit);
          this.constructorPatient.setPatientHistory(this.constructorPatientHistory);
          this.constructorPatient.getPatientHistory().getCurrentVisit().setDepartureTime(this.earlyDepartureTime);

          // generate a new Patient
          this.newPatientGenerator = new PatientGenerator(44, 9);

          this.testPatientGenerator = new PatientGenerator(45, 10, arrivalDate,
              earlyArrivalTime, 1);

      }


      @Test
      public void testGenerator() throws Exception {

          Assert.assertTrue("The patient generated should be a Patient class",
              this.newPatientGenerator.getNewPatient().getClass().equals(constructorPatient.getClass()));

          Assert.assertEquals("The patient Id should be 44, because that was set by the generator",
              44, this.newPatientGenerator.getNewPatient().getId(), 0);

          Assert.assertEquals("The string length for first name should be 9, because that was set by the generator",
              9, this.newPatientGenerator.getNewPatient().getFirstName().length(), 0);

          Assert.assertEquals("The string length for last name should be 10, because that was set by the generator",
              9, this.newPatientGenerator.getNewPatient().getLastName().length(), 0);


      }

      @Test
      public void testPatientSetter() throws Exception {

          this.newPatientGenerator.setNewPatient(constructorPatient);

          Assert.assertTrue("The patient should be equal to the one set by the setter",
              this.newPatientGenerator.getNewPatient().equals(this.constructorPatient));
      }

      @Test
      public void testTestGenerator() throws Exception {

          Assert.assertTrue("The patient generated should be a Patient class",
              this.testPatientGenerator.getNewPatient().getClass().equals(constructorPatient.getClass()));

          Assert.assertEquals("The patient Id should be 45, because that was set by the generator",
              45, this.testPatientGenerator.getNewPatient().getId(), 0);

          Assert.assertEquals("The string length for first name should be 10, because that was set by the generator",
              10, this.testPatientGenerator.getNewPatient().getFirstName().length(), 0);

          Assert.assertEquals("The string length for last name should be 10, because that was set by the generator",
              10, this.testPatientGenerator.getNewPatient().getLastName().length(), 0);

          Assert.assertEquals("The patient urgency should be 1, because that was set by the generator",
              1, this.testPatientGenerator.getNewPatient().getPatientHistory().getCurrentVisit().getUrgency(), 0);


           /*public PatientGenerator(int id, int stringLength, LocalDate arrivalDate,
                                              LocalDateTime arrivalTime, int patientUrgency)  {

              this.randomString = new RandomString();
              this.numRandom = new Random();
              69
              70             String firstName = randomString.generateRandomString(stringLength);
              71             String lastName = randomString.generateRandomString(stringLength);
              72
              73             int ageInYrs = 0;
              74
              75             double tempInDegs = 0.0;
              76
              77             int systolicBP = 0;
              78
              79             int diastolicBP = 0;
              80
              81             // create a new patient
              82             this.newPatient = new Patient(id, firstName, lastName, ageInYrs);
              83
              84             // create a new visit and patient history
              85             Patient.PatientHistory.Visit newVisit = new Patient.PatientHistory.Visit(arrivalDate, arrivalTime,
                  86                 tempInDegs, systolicBP, diastolicBP);
              87
              88             Patient.PatientHistory newPatientHistory = new Patient.PatientHistory(newVisit);
              89             // and set the patient history for this patient
              90             this.newPatient.setPatientHistory(newPatientHistory);
              91
              92             this.newPatient.getPatientHistory().getCurrentVisit().setUrgency(patientUrgency);
              93
              94         }
      }

*/

      }
  }

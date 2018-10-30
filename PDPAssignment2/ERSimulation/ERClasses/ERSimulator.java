package edu.neu.ccs.cs5010.assignment2.ersim;


import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.time.LocalDateTime;

/** ERSimulator --
 * Runs the emergency room simulation. Takes input from the user on the number of
 * exam rooms and the amount of time in minutes the simulation should run,
 * creates an emergency room and exam rooms, generates patients, and puts
 * them through the simulation. When time is up, the patients remaining in
 * the queue are processed and a report on simulation statistics is generated.
 */
public class ERSimulator {
    
    // default length of first/last name for fake patient data generation
    private static final int NAMES_LENGTH = 8;

    public static void main(String[] args) {

        /* Get user input on the number of exam rooms to be used in the simulation */
        int numExamRooms = 0;
        Scanner readNumExamRooms = null;
        try {
            readNumExamRooms = new Scanner(System.in);
        }
        catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        System.out.println("Enter the number of examination rooms for the simulation: ");
        
        if (readNumExamRooms != null) {

            numExamRooms = readNumExamRooms.nextInt();
        }

        /* Get user input on how long (in minutes) the simulation should last */
        Scanner readLengthOfSimulation = new Scanner(System.in);
        System.out.println("How long should the simulation last (in minutes)?");
        int simulationInMinutes = readLengthOfSimulation.nextInt();

        /* Set the start time of the simulation to now, then add the number of minutes
        input by the user to the start time. */
        LocalDateTime startTime = LocalDateTime.now();
        LocalDateTime end = startTime.plusMinutes(simulationInMinutes);

        // Create a statistics gatherer to hold the updated simulation statistics
        ERStatisticsGatherer simStatistics = new ERStatisticsGatherer(startTime);

        /* Set up a new emergency room with the specified number of exam rooms and
        the statistics gatherer */
        EmergencyRoom simEmergencyRoom = new EmergencyRoom(numExamRooms, simStatistics);
    
        // set up the initial patient id to be incremented as more patients are created
        int patientId = 0;

        
        while (LocalDateTime.now().isBefore(end)) { // for the duration of the simulation

            patientId = patientId + 1; // Create a new patient id
    
            // create a new patient
            Patient newPatient = EmergencyRoom.generateNewPatient(patientId, NAMES_LENGTH);

            if (newPatient != null) {
                
                    // get the current visit and calc and set patient urgency
                    newPatient.getPatientHistory().getCurrentVisit()
                        .setUrgency(simEmergencyRoom.getPatientArrivalQueue()
                        .calcPatientVisitUrgency(newPatient));
            } else {

                throw(new NullPointerException());
            }
    
            // Move patient to patient arrival queue
            simEmergencyRoom.getPatientArrivalQueue().insert(newPatient);
    
            // process the patient(move to exam once a room is available)
            simEmergencyRoom.processPatient();

        }

        /* After official simulation time is over, wait for the patients currently
        being examined to finish before generating the simulation report */
        simEmergencyRoom.getExamRoomExecutor().shutdown();

        try {
            /* put in a long timeout to make sure all threads can finish */
            simEmergencyRoom.getExamRoomExecutor()
                .awaitTermination(1, TimeUnit.DAYS);
            System.out.print(simStatistics
                .generateSimulationReport(simEmergencyRoom)); // print report
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
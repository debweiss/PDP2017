package edu.neu.ccs.cs5010.assignment2.ersim;

import java.util.concurrent.*;


/**
 * Emergency room -
 * Emergency room to be used in the simulation. Consists of a patient
 * arrival queue, where 1) new patients arrive, 2) their condition is assessed,
 * 3) they are moved to an available exam room, examined, and then exit the ER.
 */
class EmergencyRoom {

    /* Three queues, one for exam room availability, one for patients arriving,
    one for exam rooms, and one for patients being examined */
    
    // patients arrive here and are sorted by arr time & urgency
    private PatientArrivalQueue patientArrivalQueue;
    // exam rooms sorted by busy status & % utilization (lowest first)
    private ExamRoomQueue examRoomQueue;
    // patients being examined, sorted by dep time
    private PatientExaminationQueue patientExaminationQueue;

    /* Collects overall stats and generates ER efficiency report:
    - total # of exam rooms
    - total simulation duration in hours
    - total number of patients treated
    - total treatment wait time, and total treatment wait time per patient urgency
    - average duration of treatment in minutes
    - how many patients treated in each exam room
    - percentage of time that each exam room was busy
    (calculated by dividing total number of exam
    room visits by total number of patient visits) */
    private ERStatisticsGatherer statisticsGatherer;

    /* Needed to kick off Patient Exam threads and to allow main thread
    to know when an exam room is available again. */
    private ExecutorService examRoomExecutor;
    private CompletionService<Boolean> examRoomCompletionService;

    /**
     * Constructor for EmergencyRoom class
     *
     * @param numExamRooms indicates the number of examination rooms in the ER
     * @param statisticsGatherer allows updating of ER stats (e.g, total no of visits)
     */
    EmergencyRoom(int numExamRooms, ERStatisticsGatherer statisticsGatherer) {

        /* create patient arrival and examination queues */
        this.patientArrivalQueue = new PatientArrivalQueue();
        this.patientExaminationQueue = new PatientExaminationQueue();

         /* create and populate exam room queue */
        this.examRoomQueue = new ExamRoomQueue(numExamRooms);
    
        // to update statistics as simulation executes
        this.statisticsGatherer = statisticsGatherer;

        /// Set up completion service to coordinate patient exam threads
        this.examRoomExecutor = Executors.newFixedThreadPool(numExamRooms);
        this.examRoomCompletionService = new ExecutorCompletionService<>
            (examRoomExecutor);

    }

    /* Getters & setters */

    ERStatisticsGatherer getStatisticsGatherer() {

        return statisticsGatherer;
    }

    public void setStatisticsGatherer(ERStatisticsGatherer statisticsGatherer) {

        this.statisticsGatherer = statisticsGatherer;
    }


    public CompletionService<Boolean> getExamRoomCompletionService() {

        return examRoomCompletionService;
    }

    public void setExamRoomCompletionService(CompletionService<Boolean>
        examRoomCompletionService) {

        this.examRoomCompletionService = examRoomCompletionService;
    }

    /* Synchronized methods for data being read/written by multiple exam threads */

    synchronized PatientArrivalQueue getPatientArrivalQueue() {

        return patientArrivalQueue;
    }

    synchronized void setPatientArrivalQueue(PatientArrivalQueue
        patientArrivalQueue) {

        this.patientArrivalQueue = patientArrivalQueue;
    }

    synchronized ExamRoomQueue getExamRoomQueue() {

        return examRoomQueue;
    }

    synchronized void setExamRoomQueue(ExamRoomQueue examRoomQueue) {

        this.examRoomQueue = examRoomQueue;
    }

    synchronized PatientExaminationQueue getPatientExaminationQueue() {

        return patientExaminationQueue;
    }

    synchronized void setPatientExaminationQueue(PatientExaminationQueue
        patientExaminationQueue) {

        this.patientExaminationQueue = patientExaminationQueue;
    }

    ExecutorService getExamRoomExecutor() {

        return examRoomExecutor;
    }

    /**
     * Generates a new patient using the patient generator, which creates
     * fake patient data to use in simulation and testing
     *
     * @param patientId id of the patient
     * @param namesLength length of string needed to generate a random name
     * @return a new Patient
     */
    static Patient generateNewPatient(int patientId, int namesLength) {

        PatientGenerator patientGenerator = new PatientGenerator(patientId, namesLength);

        return patientGenerator.getNewPatient();

    }

    /**
     * Kicks off a separate thread that will start the patient's exam and
     * report back when exam is done and exam room becomes available
     *
     * @param emergencyRoom emergency room where the exam is taking place
     * @param examPatient   patient that is being examined
     * @param examRoom      exam room that is being used by the patient
     * @param statisticsGatherer allows the exam room and patient treatment
     *                           stats to be updated by the thread
     */
    private void startExam(EmergencyRoom emergencyRoom, Patient examPatient,
        ExamRoom examRoom, ERStatisticsGatherer statisticsGatherer) {

        // kick off new patient exam for the patient to complete their examination
        PatientExam patientExam = new PatientExam(emergencyRoom, examPatient,
            examRoom, statisticsGatherer);
        
        this.examRoomCompletionService.submit(patientExam);
    }

    /**
     * Moves the patient from the arrival queue to the exam queue, setting the
     * exam start time, exam room number and projected departure time for the patient,
     * and filling the exam room (sets projected departure time and availability(busy))
     */
    private void advancePatientToExam() {

        Patient examPatient;
        ExamRoom examRoom;

        synchronized (this) {

            // take the highest priority patient and remove from arrival queue
            examPatient = this.getPatientArrivalQueue().remove();

            // mark the room as busy
            this.getExamRoomQueue().front().setAvailability(false);

            // get a reference to the room to pass to exam thread
            examRoom = this.getExamRoomQueue().front();

            /* sort exam room queue so that occupied exam room goes to the
            back of the line based on utilization */
            this.getExamRoomQueue().sortExamRoomQueue(this.getExamRoomQueue().front());

        }
        
        // start the exam
        startExam(this, examPatient, examRoom, this.statisticsGatherer);

    }

    /**
     * Checks to see if there are any exam rooms available, and if so,
     * advances the patient to the examination queue/kick off patient exam.
     * Otherwise waits for a room to become available.
     */
    void processPatient() {
        
        // if there are patients in the queue
        if (!this.getPatientArrivalQueue().isEmpty()) {
           
            // and a room is available
            if (this.getExamRoomQueue().checkRoomAvail()) {

                advancePatientToExam(); // move the patient to exam room and start exam

            }

            else { // if room isn't available now
                try {
    
                    // wait for a room to become avail
                    if (this.examRoomCompletionService.take().isDone()) {
    
                        // move patient to exam room and start exam
                        advancePatientToExam();

                    }
                }
                catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
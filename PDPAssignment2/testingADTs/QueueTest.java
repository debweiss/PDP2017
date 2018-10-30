package edu.neu.ccs.cs5010.assignment2.testingADTs;

 import org.junit.Assert;
 import org.junit.Before;
 import org.junit.Test;

 public class QueueTest {
     private MyQueue emptyQueue;
     private MyQueue seven45Queue;
     private MyQueue six4Queue;

 @Before
 public void setUp() throws Exception {
     this.emptyQueue = new MyQueue();
     this.seven45Queue = new MyQueue();
     this.six4Queue = new MyQueue();

     // set up a queue with 7, 4, 5
     this.seven45Queue.enqueue(7);
     this.seven45Queue.enqueue(4);
     this.seven45Queue.enqueue(5);

     // set up a queue with 5, 6, 4
     this.six4Queue.enqueue(5);
     this.six4Queue.enqueue(6);
     this.six4Queue.enqueue(4);
     // then remove the 5
     this.six4Queue.dequeue();

 }

 @Test
 public void testSevenFrontAfterEnqueue() throws Exception {
     Assert.assertEquals("The least-recently added element in the queue is 7",
                         7, this.seven45Queue.front(), 0);
 }

 @Test
 public void testSixFrontAfterDequeue() throws Exception {
      Assert.assertEquals("The front element after dequeuing is 6",
                          6, this.six4Queue.front(), 0);
 }

 @Test
 public void testEmptyFront() throws Exception {
     Assert.assertEquals("This queue is empty so front() should return 0",
                         0, this.emptyQueue.front(), 0);
}

 @Test
 public void testIsEmptyEmpty() throws Exception {
     Assert.assertEquals("This queue is empty, so isEmpty() should return true",
                         true, this.emptyQueue.isEmpty());
 }

 @Test
 public void testIsEmptyNotEmpty() throws Exception {
     Assert.assertEquals("This queue is not empty, so isEmpty() should return false",
                         false, this.seven45Queue.isEmpty());
 }
}

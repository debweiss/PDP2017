/**
 * Created by zontakm on 9/12/2017.
 * This is a DUMMY implementation of Queue ADT
 */

package edu.neu.ccs.cs5010.assignment2.testingADTs;

public class MyQueue implements IQueue {
    
    public IQueue enqueue(int elt) {
        return this;
    }
    public IQueue dequeue() {
        return this;
    }
    public int front() {
        return 0;
    }
    public boolean isEmpty() {
        return true;
    }
}

/**
 * Created by zontakm on 9/12/2017.
 * This interface captures Queue ADT
 */

package edu.neu.ccs.cs5010.assignment2.testingADTs;

public interface IQueue {
    IQueue enqueue(int elt);
    IQueue dequeue();
    int front();
    boolean isEmpty();
}


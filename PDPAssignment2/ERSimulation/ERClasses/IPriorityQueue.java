package edu.neu.ccs.cs5010.assignment2.ersim;

import java.util.List;

public interface IPriorityQueue<E> {
    
    /** insert - * inserts an element into the priority queue, ensuring that
     smallest/most important element is first, and that all parent elements
     are smaller/more important than their children
     * @param e element to insert
     */
    void insert(E e);
    
    /** remove - removes the first element from the priority queue, ensuring that for
     the remaining elements, the smallest/most important element is first, and that all
     parent elements are smaller/more important than their children
     * @return first element from priority queue
     */
    E remove();
    
    /** front - allows reading/updating of first element in priority queue
     * before removing it from queue
     * @return first element in priority queue
     */
    E front();
    
    /** isEmpty returns true if priority queue is empty, false if not
     * @return true if priority queue is empty, false if not
     */
    boolean isEmpty();
    
    /**  testForwardTraversal returns a list of the elements in the queue, ascending order
     * @return list of elements in the queue in ascending order
     */
    List<E> testForwardTraversal();
    
    
    /** testReverseTraversal - returns a list of the elements in the queue,
     * in descending order
     * @return list of the elements in the queue, in descending order
     */
    List<E> testReverseTraversal();
}


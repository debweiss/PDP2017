package edu.neu.ccs.cs5010.assignment2.ersim;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Priority Queue for any element that supports Comparable
 *
 * @param <E> Any element that supports Comparable
 */
public class MyPriorityQueue<E extends Comparable<E>> implements IPriorityQueue<E> {
    
    protected List<E> queue;  // ArrayList that holds queue elements
    Comparator<E> queueComparator; // Custom comparator to use
    
    /**
     * Default Constructor
     * Creates a new Priority Queue with an empty ArrayList.
     */
    MyPriorityQueue() {
        
        this.queue = new ArrayList<>();
    }
    
    public MyPriorityQueue(ArrayList<E> queueList) {
        
        this.queue = queueList;
        
    }
    
    public MyPriorityQueue(ArrayList<E> queueList,
        Comparator<E> queueComparator) {
        
        this.queue = queueList;
        this.queueComparator = queueComparator;
    }
    
    /* Getters and Setters */
    public List<E> getQueue() {
        
        return queue;
    }
    
    public void setQueue(ArrayList<E> queue) {
        
        this.queue = queue;
    }
    
    Comparator<E> getQueueComparator() {
        
        return this.queueComparator;
    }
    
    void setQueueComparator(Comparator<E> queueComparator) {
        
        this.queueComparator = queueComparator;
    }
    
    /**
     * Helper method to retrieve the index of an element's left child,
     * based on the Heap algorithms.
     * Running time O(1).
     *
     * @param parentIndex int representing the index of the parent element
     * @return int representing the index of the element's left child
     * @throws IllegalArgumentException if (parentIndex * 2) + 1
     * is larger than heap size -1
     */
    private int findLeftChildIndex(int parentIndex) {
        
        if (((parentIndex * 2) + 1) > this.getQueue().size()) {
            
            throw (new IllegalArgumentException());
        }
        
        return (parentIndex * 2) + 1;
    }
    
    /**
     * Helper method to retrieve the index of an element's right child,
     * based on the Heap algorithms.
     * Running time O(1).
     *
     * @param parentIndex int representing the index of the parent element
     * @return int representing the index of the element's left child
     * @throws IllegalArgumentException if (parentIndex * 2) + 2 is larger than heap size -1
     */
    private int findRightChildIndex(int parentIndex) {
        
        if (((parentIndex * 2) + 2) > this.getQueue().size()) {
            
            throw (new IllegalArgumentException());
        }
        
        return (parentIndex * 2) + 2;
    }
    
    /**
     * After an element is added to the queue, minheapify makes sure that the properties
     * of a minheap are preserved, that is, all the parent elements (an element that comes
     * before its children) are either higher or equal in priority to its children.
     * Running time: O(logn) or depth of the array.
     *
     * @param index int representing the index where the element has been inserted.
     * @requires this.queue != null
     */
    void minHeapify(int index) {
        
        int minIndex = -1;

        /* If the element at index 'index' has a left child, and that child is
        smaller(more important) than its parent element, make the min index the index
        of the left child. */
        if (hasLeftChild(index)) {
            
            if (this.queueComparator != null) {
                
                if (this.getQueueComparator()
                    .compare(this.queue.get(findLeftChildIndex(index)),
                        this.queue.get(index)) < 0) {
                    
                    minIndex = findLeftChildIndex(index);
                    
                } else { // if not, make the index of the parent element the min index
                    
                    minIndex = index;
                }
                
            } else { // use natural ordering
                
                if (this.queue.get(findLeftChildIndex(index))
                    .compareTo(this.queue.get(index)) < 0) {
                    
                    minIndex = findLeftChildIndex(index);
                    
                } else {
                    
                    minIndex = index;
                }
                
            }
            
        }
        
         /* If the element at index 'index' has a right child, and that child is
         smaller(more important) than the element that was the smaller of the
         elements above, make the min index the index of the right child. */
        
        if (hasRightChild(index)) {
            
            if (this.queueComparator != null) {
                
                if (this.getQueueComparator().compare(this.queue
                        .get(findRightChildIndex(index)), this.queue.get(index)) < 0) {
                    
                    minIndex = findRightChildIndex(index);
                    
                }
                
            } else { // use natural ordering
                
                if (this.queue.get(findRightChildIndex(index)).compareTo(this.queue
                    .get(index)) < 0) {
                    
                    minIndex = findRightChildIndex(index);
                }
            }
        }

        /* If the smallest(most important) element is not the element whose index
        was passed to minheapify, swap the position of the smallest element and
        the original element, and then pass the smallest element's index to min heapify.
        This will go through the check again until the elements are in the proper order. */
        
        if (minIndex != index && minIndex != -1) {
            
            swapElements(index, minIndex);
            minHeapify(minIndex);
        }
    }
    
    /**
     * Creates a PriorityQueue based on a minHeap. Starts in the middle
     * and applies <tt>minHeapify(insertIndex)</tt>, which compares parents and
     * and ensures that parents are always <= children. When complete, the
     * min/"lowest" Object is at the top/front, accessible in O(1) through <tt>front()</tt>
     * If the queue is only 1 element, then not necessary to build a minHeap out of it.
     * Running time: O(n) where n is the length of array/size of heap - 1.
     *
     * @throws IllegalStateException if the queue is null or empty
     * @requires queue != null
     */
    private void buildMinQueue() {
        
        if (this.queue != null && !this.queue.isEmpty()) {
            
            for (int i = this.queue.size() / 2; i >= 0; i--) {
                
                minHeapify(i);
            }
            
        } else {
            
            throw (new IllegalStateException());
        }
    }
    
    /**
     * Inserts an element at the end of the queue and then calls bubbleup
     * to ensure that the new element is compared to its parents to ensure that
     * the minHeap property (parents are always <= children). If the heap is empty,
     * it just adds the element and finishes.
     * Running time: O(logn) where n is the length of array/size of heap - 1.
     *
     * @param e Element to be inserted into the queue.
     * @throws NullPointerException if try to add to a null queue
     * @requires this.queue != null
     */
    public synchronized void insert(E e) {
        
        if (this.queue != null) { // queue must not be null to insert
            
            if (!this.queue.isEmpty()) { // if the queue isn't empty

                /* insert the element at the end of the queue */
                int insertIndex = this.queue.size();
                this.queue.add(e);

                /* Start at the end/bottom and go up, comparing to ensure the minHeap
                property is preserved. */
                
                try {
                    this.bubbleUp(insertIndex);
                } catch ( Exception e1 ) {
                    e1.printStackTrace();
                }
                
            } else { // if empty, just add the element at the end/bottom.
                
                this.queue.add(e);
            }
            
        } else {
            
            throw (new NullPointerException());
        }
    }
    
    /**
     * After an element is inserted at the end/bottom of the queue, bubbleup
     * takes the index of that element as a parameter and compares with its parents,
     * continuing up if the added element is smaller/more important than the elements above it.
     * Ensures that the minheap property is preserved once an element is added.
     * Running time: O(logn) where n is the length of array/size of heap - 1.
     *
     * @param insertIndex int representing the index where the element has been inserted.
     * @requires this.queue != null
     */
    private void bubbleUp(int insertIndex) throws Exception {
        
        if (hasParent(insertIndex)) { // if the element at insertIndex has an element that is before it
            
            if (this.queueComparator != null) {
                
                while ( hasParent(insertIndex)
                    && this.queueComparator.compare(this.queue.get(findParentIndex(insertIndex)),
                    this.queue.get(insertIndex)) > 0 ) { // keep comparing with the parent element
                    // swap w/parent if child is smaller
                    swapElements(insertIndex, findParentIndex(insertIndex));
                    insertIndex = findParentIndex(insertIndex);
                }
                
            } else {
                 
                 /* use natural ordering */
                while ( hasParent(insertIndex) && this.queue.get(findParentIndex(insertIndex))
                    .compareTo(this.queue.get(insertIndex)) > 0 ) {
                    // swap w/parent if child is smaller
                    swapElements(insertIndex, findParentIndex(insertIndex));
                    insertIndex = findParentIndex(insertIndex);
                    
                }
            }
        }
    }
    
    /**
     * Helper method to determine if an element has a parent.
     *
     * @param index int representing the index of the potential child element
     * @return boolean true if the element has a parent, false if not
     */
    private boolean hasParent(int index) {
        
        return findParentIndex(index) >= 0;
    }
    
    /**
     * Helper method to retrieve the index of an element's parent, based on the
     * Heap algorithms.
     * <p>
     * Running time O(1).
     *
     * @param childIndex int representing the index of the child element
     * @return int value representing the index of the parent element
     * @throws IllegalArgumentException if childIndex is <=0
     */
    private int findParentIndex(int childIndex) throws IllegalArgumentException {
        
        if (childIndex <= 0) {
            
            return -1;
            
            // throw(new IllegalArgumentException());
            
        } else if (childIndex % 2 == 0) {
            
            return (childIndex / 2) - 1;
            
        } else {
            return (childIndex / 2);
        }
    }
    
    /**
     * Helper method to swap two elements in a queue.
     * <p>
     *
     * @param objectIndex1 int representing the index of the first object to swap
     * @param objectIndex2 int representing the index of the first object to swap
     */
    private void swapElements(int objectIndex1, int objectIndex2) {
        // swap
        E tempElement = this.queue.get(objectIndex1);
        this.queue.set(objectIndex1, this.queue.get(objectIndex2));
        this.queue.set(objectIndex2, tempElement);
    }
    
    /**
     * Removes the highest priority element from the queue and returns it after
     * calling minHeapify to preserve the priority order of the queue.
     * <p>
     * If the queue size is 1 or 2, the method removes and returns the highest
     * priority element without calling minHeapify first.
     * <p>
     * Running time O(logn) where n = queue size.
     *
     * @return Object representing the highest priority element from the queue
     * (in this case, the "min" element)
     * @throws NoSuchElementException if the queue is null or empty
     */
    
    public synchronized E remove() {
        
        E min;
        
        if (this.queue != null && !this.queue.isEmpty()) {
            
            if (this.queue.size() == 1 || this.queue.size() == 2) { // if queue only has 1 or 2 elements
                min = this.queue.get(0);
                this.queue.remove(0); // just remove the first element

            /* Otherwise after 1st element is removed, the others need to be sorted to make sure that
            the smallest/most important element is still up front */
            } else {

                /* Get the first element, then swap it with the last element */
                min = this.front();
                this.swapElements(0, this.queue.size() - 1);

                /* then remove the last element in the heap (was formerly the first) */
                this.queue.remove(this.queue.get(this.queue.size() - 1));

                /* Then min-heapify (re-sort) starting with the new element at root */
                minHeapify(0);
            }
            
        } else {
            
            throw (new NoSuchElementException());
        }
        
        return min;  // return the min element
    }
    
    /**
     * Returns the highest priority element from the queue without removing it from
     * the queue.
     * <p>
     * Running time O(1);
     * <p>
     *
     * @return Element representing the highest priority element from the queue
     * (in this case, the "min" element)
     * @throws NoSuchElementException if the queue is null or empty
     */
    public synchronized E front() {
        
        if (this.queue != null && !this.queue.isEmpty()) {
            
            return this.queue.get(0);
            
        } else {
            throw (new NoSuchElementException());
        }
        
    }
    
    /**
     * Determines if the priority queue is empty.
     * <p>
     *
     * @return boolean true if the queue is empty, false if not.
     */
    public synchronized boolean isEmpty() {
        
        return this.queue.isEmpty();
    }
    
    /**
     * Returns a list that contains all the elements in the priority queue in
     * ascending order.
     * <p>
     *
     * @return List containing elements in ascending order.
     */
    public List<E> testForwardTraversal() {
        
        int firstElementIndex = 0;
        
        List<E> forwardTraversalList = new ArrayList<>();
        
        return recursiveForwardTraversal(firstElementIndex, forwardTraversalList);
        
    }
    
    /**
     * Returns a list that contains all the elements in the priority queue in
     * reverse (descending) order.
     * <p>
     *
     * @return List containing elements in reverse (descending) order.
     */
    public List<E> testReverseTraversal() {
        
        int lastElementIndex = this.queue.size() - 1;
        
        List<E> reverseTraversalList = new ArrayList<>();
        
        return recursiveReverseTraversal(lastElementIndex, reverseTraversalList);
        
    }
    
    /**
     * Moves recursively through the queue starting with first element, and
     * produces a list that contains all the queue elements in ascending order
     *
     * @param firstElementIndex    index of the first element
     * @param forwardTraversalList list that will contain all the queue elements
     * @return list of all queue elements in ascending order
     */
    private List<E> recursiveForwardTraversal(int firstElementIndex,
        List<E> forwardTraversalList) {
        
        if (firstElementIndex == 0) { // if the root
            
            forwardTraversalList.add(this.queue.get(firstElementIndex)); // add to list
            
            // if root doesn't have children
            if (!hasRightChild(firstElementIndex) &&
                !hasLeftChild(firstElementIndex)) {
                
                return forwardTraversalList; // return list
                
                // if root and only has left child
            } else if (!hasRightChild(firstElementIndex)) {
                
                // add left child
                forwardTraversalList.add(this.queue
                    .get(findLeftChildIndex(firstElementIndex)));
                // return list
                return forwardTraversalList;
                
            } else { // if root has left and right child
                
                // add left child and right child, then recurse with left child
                forwardTraversalList.add(this.queue
                    .get(findLeftChildIndex(firstElementIndex)));
                forwardTraversalList.add(this.queue
                    .get(findRightChildIndex(firstElementIndex)));
                recursiveForwardTraversal(findLeftChildIndex
                    (firstElementIndex), forwardTraversalList);
            }
            //if firstElementIndex is not root index and is a left child
        } else if (isLeftChild(firstElementIndex,
            findParentIndex(firstElementIndex))) {
            
            // and if that element doesn't have children
            if (!hasRightChild(firstElementIndex) &&
                !hasLeftChild(firstElementIndex)) {
                
                // just return the list
                return forwardTraversalList;
                
                // if the left child only has a left child
            } else if (!hasRightChild(firstElementIndex)) {
                
                // add the left child and return the list
                forwardTraversalList.add(this.queue
                    .get(findLeftChildIndex(firstElementIndex)));
                
                return forwardTraversalList;
            }
            // if left child has both children and left childs's parent has right child
            else if (hasRightChild(findParentIndex(firstElementIndex))) {
                
                // add left child's left and right children
                forwardTraversalList.add(this.queue.get
                    (findLeftChildIndex(firstElementIndex)));
                forwardTraversalList.add(this.queue.get
                    (findRightChildIndex(firstElementIndex)));
                
                // and recurse with left child's right sibling
                int rightSiblingIndex = findRightChildIndex
                    (findParentIndex(firstElementIndex));
                recursiveForwardTraversal(rightSiblingIndex,
                    forwardTraversalList);
                
            }
            // if element is a right child
        } else if (isRightChild(firstElementIndex,
            findParentIndex(firstElementIndex))) {
            
            // if element doesn't have children
            
            if (!hasRightChild(firstElementIndex) &&
                !hasLeftChild(firstElementIndex)) {
                
                return forwardTraversalList; // return list
                // if right child only has a left child
            } else if (!hasRightChild(firstElementIndex)) {
                
                // add left child
                forwardTraversalList.add(this.queue.get
                    (findLeftChildIndex(firstElementIndex)));
                // return list
                return forwardTraversalList;
                
            } else {
                
                // add left child and right child,
                forwardTraversalList.add(this.queue.get(findLeftChildIndex(firstElementIndex)));
                forwardTraversalList.add(this.queue.get(findRightChildIndex(firstElementIndex)));
                recursiveForwardTraversal(findLeftChildIndex(firstElementIndex), forwardTraversalList);
                
                // if rightChild is the last element in the queue
                if (findRightChildIndex(firstElementIndex) == this.queue.size() - 1) {
                    
                    return forwardTraversalList; // return list
                    
                }
                // else recurse with leftSibling's left child.
                else {
                    recursiveForwardTraversal(findLeftChildIndex(findParentIndex
                        (firstElementIndex)), forwardTraversalList);
                    
                }
                
                
            }
        }
        return forwardTraversalList;
    }
    
    /**
     * Moves recursively through the queue starting with last element, and
     * produces a list that contains all the queue elements in reverse
     * (descending) order
     *
     * @param lastElementIndex     index of the last element
     * @param reverseTraversalList list containing queue elements in reverse(descending)
     *                             order
     * @return list containing queue elements in reverse(descending) order
     */
    private List<E> recursiveReverseTraversal(int lastElementIndex, List<E> reverseTraversalList) {
        
        if (!hasParent(lastElementIndex)) {
            
            reverseTraversalList.add(this.getQueue().get(lastElementIndex));
            
            return reverseTraversalList;
            
        }
        // if lastElement index is a right child
        else if (isRightChild(lastElementIndex, findParentIndex(lastElementIndex))) {
            
            // get the parent index
            int parentIndex = findParentIndex(lastElementIndex);
            
            // add element and it's sibling
            reverseTraversalList.add(this.getQueue().get(lastElementIndex));
            reverseTraversalList.add(this.getQueue().get(findLeftChildIndex(parentIndex)));
            
            /* if my parent has a parent and is a right child, get the parent's sibling
            (grandparent's left child) and children.
            
            If my parent's left sibling has a right child, recurse with right child
            if not, recurse with left child.
            
            If my parent has a parent and is a left child, if my parent has a right sibling,
            recurse with right sibling, else recurse with parent
             */
            parentReverseTraversal(parentIndex, reverseTraversalList);
            
        } else { // i must be an only left child
            
            reverseTraversalList.add(this.getQueue().get(lastElementIndex)); // add myself
            
            int parentIndex = findParentIndex(lastElementIndex);
            
            parentReverseTraversal(parentIndex, reverseTraversalList); // same logic as above
            
        }
        
        return reverseTraversalList;
        
    }
    
    /**
     * Determines whether it is appropriate to go to the parent level when
     * traversing the priorityQueue from the bottom.
     *
     * @param parentIndex index of the parent element
     * @param reverseTraversalList list that will contain the queue elements in reverse order
     */
    private void parentReverseTraversal(int parentIndex, List<E> reverseTraversalList) {
        
         /* if my parent has a parent and my parent is a right child */
        if (hasParent(parentIndex) && isRightChild(parentIndex, findParentIndex(parentIndex))) {
            
            int grandParent = findParentIndex(parentIndex); // find grandparent
            
            int parentLeftSiblingIndex = findLeftChildIndex(grandParent); // find left sibling
            
            // recurse with left sibling's right child
            recursiveReverseTraversal(findRightChildIndex(parentLeftSiblingIndex),
                reverseTraversalList);
            
        } else {
            
            // if my parent has a parent and my parent is a left child with a right sibling
            if (hasParent(parentIndex) && hasRightChild(findParentIndex(parentIndex))) {
                // recurse with that parent's sibling
                int grandParent = findParentIndex(parentIndex);
                int parentsRightSibling = findRightChildIndex(grandParent);
                recursiveReverseTraversal(parentsRightSibling, reverseTraversalList);
                
            }
            // parent is an only left child so move forward with the next level
            recursiveReverseTraversal(parentIndex, reverseTraversalList);
        }
    }
    
    private boolean isRightChild(int childIndex, int parentIndex) {
        
        return (parentIndex * 2) + 2 == childIndex;
        
    }
    
    private boolean isLeftChild(int childIndex, int parentIndex) {
        
        return (parentIndex * 2) + 1 == childIndex;
        
    }
    
    private boolean hasChild(int index) {
        
        return hasLeftChild(index) || hasRightChild(index);
    }
    
    /**
     * Helper method to determine if an element has a left child.
     *
     * @param parentIndex int representing the index of the potential parent element
     * @return boolean true if the element has a left child, false if not
     */
    private boolean hasLeftChild(int parentIndex) {
        
        return (parentIndex * 2) + 1 <= this.queue.size() - 1;
    }
    
    /**
     * Helper method to determine if an element has a right child.
     *
     * @param parentIndex int representing the index of the potential parent element
     * @return boolean true if the element has a right child, false if not
     */
    private boolean hasRightChild(int parentIndex) {
        
        return (parentIndex * 2) + 2 <= this.queue.size() - 1;
        
    }
    
    private boolean hasRightSibling(int childIndex) {
        
        return hasRightChild(findParentIndex(childIndex));
    }
}


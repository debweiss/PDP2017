  package edu.neu.ccs.cs5010.assignment2.testingADTs;

  import org.junit.Assert;
 import org.junit.Before;
 import org.junit.Test;

  public class StackTest {

    private MyStack emptyStack;
    private MyStack seven45Stack;
    private MyStack five6Stack;

    @Before
    public void setUp() throws Exception {
        this.emptyStack = new MyStack();
        this.seven45Stack = new MyStack();
        this.five6Stack = new MyStack();

        // set up a stack with 7, 4, 5 (5 on top)
        this.seven45Stack.push(7);
        this.seven45Stack.push(4);
        this.seven45Stack.push(5);

        // set up a queue with 5, 6, 4 (4 on top)
        this.five6Stack.push(5);
        this.five6Stack.push(6);
        this.five6Stack.push(4);

        // then remove the 4 (6 now on top)
        this.five6Stack.pop();

    }

    @Test
    public void testFiveTopAfterPush() throws Exception {
        Assert.assertEquals("The most-recently added element in the stack is 5",
                            5, this.seven45Stack.top(), 0);
    }

    @Test
    public void testSixTopAfterPop() throws Exception {
        Assert.assertEquals("The top element after pop is 6",
                            6, this.five6Stack.top(), 0);
    }

    @Test
    public void testEmptyTop() throws Exception {
        Assert.assertEquals("This stack is empty so top() should return 0",
                            0, this.emptyStack.top(), 0);
    }

    @Test
    public void testIsEmptyEmpty() throws Exception {
        Assert.assertEquals("This stack is empty, so isEmpty() should return true",
                            true, this.emptyStack.isEmpty());
        }

    @Test
    public void testIsEmptyNotEmpty() throws Exception {
        Assert.assertEquals("This stack is not empty, so isEmpty() should return false",
                            false, this.seven45Stack.isEmpty());
    }
}

package edu.neu.ccs.cs5010;

/**
 * Basic interface that allows checking a string against an expression.
 */
public interface IExpression {
    /* evaluates context against an expression, returns true if evaluates true,
    false if evaluates false */
    boolean interpret(String context);
}

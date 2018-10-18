package edu.neu.ccs.cs5010;

/**
 * Basic interface that allows checking a string against an expression.
 */
public interface IExpression {

    boolean interpret(String context);

}

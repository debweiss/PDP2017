package edu.neu.ccs.cs5010;

/**
 * Basic expression class that evaluates whether
 * a candy (represented by a string) exists.
 */
public class TerminalExpression implements IExpression {

    private String candyInfo;

    TerminalExpression(String candyInfo) {

        this.candyInfo = candyInfo;
    }

    @Override
    public boolean interpret(String context) {

        return context.contains(candyInfo);
    }

}

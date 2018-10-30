package edu.neu.ccs.cs5010;


/**
 * Basic expression class that evaluates an and expression
 */
public class AndExp implements IExpression { // basic code structure taken from Design Patterns tutorial

    private IExpression expr1 = null;
    private IExpression expr2 = null;

    AndExp(IExpression expr1, IExpression expr2) {
        this.expr1 = expr1;
        this.expr2 = expr2;
    }

    @Override
    public boolean interpret(String context) {

        return expr1.interpret(context) && expr2.interpret(context);
    }

}

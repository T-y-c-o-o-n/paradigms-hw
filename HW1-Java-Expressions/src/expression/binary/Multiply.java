package expression.binary;

import expression.CommonExpression;
import expression.Oper;

import java.util.EnumSet;

public class Multiply extends AbstractBinarOper {
    private static final EnumSet<Oper> firstArgsToAllow = EnumSet.of(Oper.MUL, Oper.DIV);
    private static final EnumSet<Oper> secondArgsToAllow = EnumSet.of(Oper.MUL);

    public Multiply(CommonExpression first, CommonExpression second) {
    	super(first, second, Oper.MUL);
    }

    public int calculate(int a, int b) { return a * b; }

    public String toMiniString() {
        return super.toMiniString(firstArgsToAllow, secondArgsToAllow);
    }
}
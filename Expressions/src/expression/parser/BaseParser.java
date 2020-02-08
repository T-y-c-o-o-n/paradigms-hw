package expression.parser;

import expression.Oper;
import expression.exceptions.ParsingException;

public abstract class BaseParser {
    private final Source source;
    protected char tempCh;

    protected BaseParser(Source source) {
        this.source = source;
    }

    protected void nextChar() {
        tempCh = source.nextChar();
    }

    protected char getChar() {
        char ch = tempCh;
        nextChar();
        return ch;
    }

    protected boolean test(char expected) {
        if (tempCh == expected) {
            nextChar();
            return true;
        }
        return false;
    }

    protected void expect(char ch)  {
        if (ch != tempCh) {
            throw new ParsingException("expected: '" + ch + "' , but found: " + tempCh);
        }
        nextChar();
    }

    protected void expect(String expected) {
        for (char ch : expected.toCharArray()) {
            expect(ch);
        }
    }

    protected void skipWhitespace() {
        while (Character.isWhitespace(tempCh)) {
            nextChar();
        }
    }

    protected boolean testCloseBracket() { return tempCh == ')'; }

    protected boolean testDigit() {
        return '0' <= tempCh && tempCh <= '9';
    }

    protected boolean testVariable() {
        return 'x' <= tempCh && tempCh <= 'z';
    }
}
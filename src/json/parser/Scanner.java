package json.parser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static json.parser.TokenType.*;

public class Scanner {

    private static final Map<String, TokenType> keywords;

    static {
        keywords = new HashMap<>();
        keywords.put("false", FALSE);
        keywords.put("true", TRUE);
        keywords.put("null", NULL);
    }

    private String source;
    private List<Token> tokens;
    private int current, start;

    public Scanner(String source) {
        this.source = source;
        this.tokens = new ArrayList<>();
    }

    public List<Token> scanTokens() {
        tokens = new ArrayList<>();

        while (!isAtEnd()) {
            start = current;
            scanToken();
        }
        return tokens;
    }

    private void scanToken() {
        char c = advance();
//        System.out.println("scan token: " + c);

        switch (c) {
            case '{':
                addToken(LEFT_BRACE);
                break;
            case '}':
                addToken(RIGHT_BRACE);
                break;
            case '[':
                addToken(LEFT_SQUARE_BRACKET);
                break;
            case ']':
                addToken(RIGHT_SQUARE_BRACKET);
                break;
            case ',':
                addToken(COMMA);
                break;
            case ':':
                addToken(COLON);
                break;
            case ' ':
            case '\r':
            case '\t':
            case '\n':
                break;
            case '"':
                string();
                break;
            case '-':
                if (isDigit(peek())) {
                    number();
                } else {
                    throw new RuntimeException("Unexpected character");
                }
                break;
            default:
                if (isDigit(c)) number();
                else if (isAlpha(c)) identifier();
                else throw new RuntimeException("Unexpected character");

        }
    }

    private void identifier() {
        while (isAlphaNumeric(peek())) advance();

        String text = source.substring(start, current);

        TokenType type = keywords.get(text);

        if (type == TRUE) addToken(type, true);
        else if (type == FALSE) addToken(type, false);
        else if (type == NULL) addToken(type, null);
        else addToken(type);
    }

    private void negativeNumber() {

        while (isDigit(peek())) advance();

        if (peek() == '.' && isDigit(peekNext())) {
            advance();

            while (isDigit(peek())) advance();
        }


        addToken(NUMBER, -Float.parseFloat(source.substring(start, current)));
    }



    private void number() {
        while (isDigit(peek())) advance();
        if (peek() == '.' && isDigit(peekNext())) {
            advance();

            while (isDigit(peek())) advance();
        }

        addToken(NUMBER, Float.parseFloat(source.substring(start, current)));
    }

    private void string() {
        while (peek() != '"' && !isAtEnd()) {
            if (peek() == '\\') {
                advance();
            }

            advance();
        }

        if (isAtEnd()) {
            throw new RuntimeException("Unterminated string");
        }
        advance();

        String value = source.substring(start + 1, current - 1);
        addToken(STRING, value);
    }

    private boolean isAtEnd() {
        return current >= source.length();
    }

    private char advance() {
        current++;
        return source.charAt(current - 1);
    }

    private char previous() {
        return source.charAt(current - 1);
    }

    private char peek() {
        if (isAtEnd()) return '\0';
        return source.charAt(current);
    }

    private char peekNext() {
        if (current + 1 >= source.length()) return '\0';
        return source.charAt(current + 1);
    }

    private boolean match(char expected) {
        if (isAtEnd()) return false;
        if (source.charAt(current) != expected) return false;

        current++;
        return true;
    }

    private boolean isDigit(char c) {
        return c >= '0' && c <= '9';
    }

    private boolean isAlpha(char c) {
        return (c >= 'A' && c <= 'Z') ||
                (c >= 'a' && c <= 'z') ||
                c == '_';
    }

    private boolean isAlphaNumeric(char c) {
        return isDigit(c) || isAlpha(c);
    }

    private void addToken(TokenType type) {
        addToken(type, null);
    }

    private void addToken(TokenType type, Object literal) {
        String text = source.substring(start, current);
        tokens.add(new Token(type, text, literal));
    }

    private void addToken(TokenType type, String text, Object literal) {
        tokens.add(new Token(type, text, literal));
    }
}

package json.parser;

public class Token {
    TokenType type;
    String lexeme;
    Object literal;

    public Token(TokenType type, String lexeme, Object literal) {
        this.type = type;
        this.lexeme = lexeme;
        this.literal = literal;
    }

    public String toString() {
        return type.toString() + " " + lexeme + " " + literal;
    }
}

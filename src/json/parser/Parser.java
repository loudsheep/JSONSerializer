package json.parser;

import json.dataTypes.*;

import java.util.List;

import static json.parser.TokenType.*;

public class Parser {
    private List<Token> tokens;
    private int current = 0;

    public Parser(List<Token> tokens) {
        this.tokens = tokens;
    }

    public JSONData parse() {
        return value();
    }

    private JSONData value() {
        Token type = advance();
        if (type.type == STRING) {
            if (peek().type == COLON) {
                advance();
                Token token = advance();
                if (token.type == NUMBER) {
                    return new JSONNumber(type.literal.toString(), Float.parseFloat(token.literal.toString()));
                } else if (token.type == STRING) {
                    return new JSONString(type.literal.toString(), token.literal.toString());
                } else if (token.type == TRUE) {
                    return new JSONBoolean(type.literal.toString(), true);
                } else if (token.type == FALSE) {
                    return new JSONBoolean(type.literal.toString(), false);
                } else if (token.type == NULL) {
                    return new JSONNull(type.literal.toString());
                } else if (token.type == LEFT_BRACE) {
                    return objectDeclaration(type);
                } else if (token.type == LEFT_SQUARE_BRACKET) {
                    return arrayDeclaration(type);
                }
            } else {
                return new JSONString("inArray", type.literal.toString());
            }
        } else if (type.type == NUMBER) {
            return new JSONNumber(type.literal.toString(), Float.parseFloat(type.literal.toString()));
        } else if (type.type == TRUE) {
            return new JSONBoolean(type.literal.toString(), true);
        } else if (type.type == FALSE) {
            return new JSONBoolean(type.literal.toString(), false);
        } else if (type.type == NULL) {
            return new JSONNull(type.literal.toString());
        } else if (type.type == LEFT_BRACE) {
            Token t = new Token(EMPTY, "", "");
            return objectDeclaration(t);
        } else if (type.type == LEFT_SQUARE_BRACKET) {
            Token t = new Token(EMPTY, "", "");
            return arrayDeclaration(t);
        }

        return new Unknown();
    }

    private JSONObject objectDeclaration(Token name) {
        Token type = peek();
        JSONObject jsonObject = new JSONObject(name.literal.toString());

        if (type.type == RIGHT_BRACE) { // empty object
            advance();
            return jsonObject;
        }

        do {
            jsonObject.addChildren(value());
            type = advance();
        } while (type.type != RIGHT_BRACE);
        return jsonObject;
    }

    private JSONArray arrayDeclaration(Token name) {
        Token type = peek();
        JSONArray jsonArray = new JSONArray(name.literal.toString());

        if (type.type == RIGHT_SQUARE_BRACKET) { // empty array
            advance();
            return jsonArray;
        }

        do {
            jsonArray.addChildren(value());
            type = advance();
        } while (type.type != RIGHT_SQUARE_BRACKET);

        return jsonArray;
    }

    private Token advance() {
        if (!isAtEnd()) current++;
        return previous();
    }

    private boolean match(TokenType... types) {
        for (TokenType type : types) {
            if (check(type)) {
                advance();
                return true;
            }
        }
        return false;
    }

    private Token consume(TokenType type, String message) {
        if (check(type)) return advance();

        throw new RuntimeException(message);
    }

    private boolean isAtEnd() {
        return current >= tokens.size();
    }

    private Token peek() {
        return tokens.get(current);
    }

    private Token previous() {
        return tokens.get(current - 1);
    }

    private boolean check(TokenType type) {
        if (isAtEnd()) return false;
        return peek().type == type;
    }
}

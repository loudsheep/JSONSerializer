public class JSONParser {
    private String json;
    private char currentChar;
    private int current = -1, start;

    public JSONParser(String json) {
        this.json = json;
    }

    public JSONObject parse() {
        while (!isAtEnd()) {
            char c = advance();

            switch (c) {
                case '{':
                    break;
                case '[':
                    break;
                    
            }
        }

        return null;
    }

    private boolean isAtEnd() {
        return current >= json.length();
    }

    private char peekNext() {
        if (current + 1 < json.length()) {
            return json.charAt(current + 1);
        }
        return '\0';
    }

    private char advance() {
        if (!isAtEnd()) {
            current++;
            return json.charAt(current);
        }
        return '\0';
    }
}

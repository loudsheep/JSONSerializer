package json;

import json.dataTypes.JSONData;

public class JSONReader {
    public static JSONData toJSONObject(String text) {
        return new Parser(new Scanner(text).scanTokens()).parse();
    }
}

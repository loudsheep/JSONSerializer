package json;

import json.dataTypes.JSONData;

public class JSONReader {
    /**
     * @param text source text containing json data
     * @return JSONData object
     */
    public static JSONData toJSONObject(String text) {
        return new Parser(new Scanner(text).scanTokens()).parse();
    }
}

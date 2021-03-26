package json;

import json.dataTypes.JSONData;
import json.parser.Parser;
import json.parser.Scanner;

public class JSON {
    /**
     * @param text source text containing json data
     * @return JSONData object
     */
    public static JSONData parse(String text) {
        return new Parser(new Scanner(text).scanTokens()).parse();
    }
}

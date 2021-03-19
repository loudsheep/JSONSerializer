package test.dataTypes;

import test.Parser;

import java.util.List;

public class JSONObject extends JSONDataType {
    public JSONObject(String name, List<JSONDataType> elements) {
        this.name = name;
        this.children = elements;
    }

    public JSONObject(String name) {
        this.name = name;
    }

    @Override
    public Object getData() {
        return children;
    }

    @Override
    public String toString() {
        String result = "JSON Object name=" + name + " value={";
        for (int i = 0; i < children.size(); i++) {
            result += children.get(i).toString();
            if (i != children.size() - 1) {
                result += ", \n";
            }
        }
        result += "}";

        return result;
    }

    @Override
    public String repr() {
//        return "json.Object\"" + name + "\"";
        return toString();
    }
}

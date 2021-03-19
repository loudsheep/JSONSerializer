package test.dataTypes;

import java.util.List;

public class JSONArray extends JSONDataType {
    public JSONArray(String name, List<JSONDataType> elements) {
        this.name = name;
        this.children = elements;
        this.childrenCount = elements.size();
    }

    public JSONArray(String name) {
        this.name = name;
        this.childrenCount = 0;
    }

    public int length() {
        return childrenCount;
    }

    @Override
    public Object getData() {
        return children;
    }

    @Override
    public String toString() {
        String result = "JSON Array name=" + name + " value=[";
        for (int i = 0; i < children.size(); i++) {
            result += children.get(i).repr();
            if (i != children.size() - 1) {
                result += ", ";
            }
        }
        result += "]";

        return result;
    }

    @Override
    public String repr() {
        return this.toString();
    }
}

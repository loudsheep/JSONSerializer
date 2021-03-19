package test.dataTypes;

public class JSONString extends JSONDataType {
    String value;

    public JSONString(String name, String value) {
        this.name = name;
        this.value = value;
    }

    @Override
    public Object getData() {
        return value;
    }

    @Override
    public String toString() {
        return "JSON String name=" + name + " value=" + value;
    }

    @Override
    public String repr() {
        return "\"" + value + "\"";
    }
}

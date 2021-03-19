package json.dataTypes;

public class JSONBoolean extends JSONDataType {
    boolean value;

    public JSONBoolean(String name, boolean value) {
        this.name = name;
        this.value = value;
    }

    @Override
    public Object getData() {
        return value;
    }

    @Override
    public String toString() {
        return "JSON Boolean name=" + name + " value=" + value;
    }

    @Override
    public String repr() {
        return "" + value;
    }
}

package json.dataTypes;

public class JSONNumber extends JSONData {
    float value;

    public JSONNumber(String name, float value) {
        this.name = name;
        this.value = value;
    }

    @Override
    public Object getData() {
        return value;
    }

    @Override
    public String toString() {
        return "JSON Number name=" + name + " value=" + value;
    }

    @Override
    public String repr() {
        return "" + value;
    }
}

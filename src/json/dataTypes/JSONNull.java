package json.dataTypes;

public class JSONNull extends JSONData {
    public JSONNull(String name) {
        this.name = name;
    }

    @Override
    public Object getData() {
        return null;
    }

    @Override
    public String toString() {
        return "JSON Null name=" + name + " value=null";
    }

    @Override
    public String repr() {
        return "null";
    }
}

package json.dataTypes;

public class Unknown extends JSONData {
    public Unknown() {
        name = "unknown";
    }

    @Override
    public Object getData() {
        return "unknown";
    }

    @Override
    public String toString() {
        return "<unknown>";
    }

    @Override
    public String repr() {
        return "<unknown>";
    }
}

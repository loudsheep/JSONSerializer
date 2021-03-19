package json.dataTypes;

public class Unknown extends JSONData {
    public Unknown() {

    }

    @Override
    public Object getData() {
        return null;
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

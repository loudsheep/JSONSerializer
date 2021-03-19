package test.dataTypes;

public class Unknown extends JSONDataType {
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

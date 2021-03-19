import java.util.ArrayList;
import java.util.List;

public class JSONObject {
    public enum ObjectType {
        ARRAY,
        BOOL,
        NULL,
        NUMBER,
        OBJECT,
        STRING,
    }

    String name;
    final ObjectType type;
    float floatValue;
    String strValue;
    List<JSONObject> listValue;
    boolean boolValue;
    JSONObject objectValue;

    public JSONObject(String name, ObjectType type) {
        this.name = name;
        this.type = type;

        if (type == ObjectType.ARRAY) {
            listValue = new ArrayList<>();
        }
    }

    public ObjectType getType() {
        return this.type;
    }

    public String getName() {
        return this.name;
    }
}

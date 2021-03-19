package test.dataTypes;

import java.util.ArrayList;
import java.util.List;

public abstract class JSONDataType {
    protected String name;
    protected List<JSONDataType> children = new ArrayList<>();
    protected int childrenCount = 0;

    public final void addChildren(JSONDataType dataType) {
        children.add(dataType);
        childrenCount++;
    }

    public final void removeChildren(JSONDataType dataType) {
        if (children.contains(dataType)) {
            children.remove(dataType);
            childrenCount--;
        }
    }

    public final JSONDataType get(String path) {
        JSONDataType result = null;
        String[] arr = path.split("\\.");

        if (arr.length == 0) {
            return null;
        }

        String[] childPath = new String[arr.length - 1];

        if (arr.length == 1) {
            for (JSONDataType data : children) {
                if (data.name.equals(arr[0])) {
                    return data.request(childPath);
                }
            }
            return null;
        }

        for (int i = 1; i < arr.length; i++) {
            childPath[i - 1] = arr[i];
        }

        for (JSONDataType data : children) {
            if (data.name.equals(arr[0])) {
                result = data.request(childPath);
            }
        }
        return result;
    }

    protected JSONDataType request(String[] path) {
        JSONDataType result = null;

        if (path.length == 0) {
            return this;
        }

        String[] childPath = new String[path.length - 1];

        if (path.length == 1) {
            for (JSONDataType data : children) {
                if (data.name.equals(path[0])) {
                    return data.request(childPath);
                }
            }
            return null;
        }

        for (int i = 1; i < path.length; i++) {
            childPath[i - 1] = path[i];
        }

        for (JSONDataType data : children) {
            if (data.name.equals(path[0])) {
                result = data.request(childPath);
            }
        }
        return result;
    }

    public abstract Object getData();

    @Override
    public abstract String toString();

    public abstract String repr();
}

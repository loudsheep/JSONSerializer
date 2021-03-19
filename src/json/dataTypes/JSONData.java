package json.dataTypes;

import java.util.ArrayList;
import java.util.List;

public abstract class JSONData {
    protected String name;
    protected List<JSONData> children = new ArrayList<>();
    protected int childrenCount = 0;

    public final void addChildren(JSONData dataType) {
        children.add(dataType);
        childrenCount++;
    }

    public final void removeChildren(JSONData dataType) {
        if (children.contains(dataType)) {
            children.remove(dataType);
            childrenCount--;
        }
    }

    public final JSONData get(String path) {
        JSONData result = null;
        String[] arr = path.split("\\.");

        if (arr.length == 0) {
            return null;
        }

        String[] childPath = new String[arr.length - 1];

        for (int i = 1; i < arr.length; i++) {
            childPath[i - 1] = arr[i];
        }

        if (arr[0].contains("[")) {
            if (this instanceof JSONArray) {
                if (arr[0].contains("]")) {
                    if (count(arr[0], '[') > 1 || count(arr[0], ']') > 1) throw new RuntimeException("too much ");

                    int index = Integer.parseInt(arr[0].substring(arr[0].indexOf('[') + 1, arr[0].indexOf(']')));
                    return children.get(index).request(childPath);
                } else {
                    throw new RuntimeException("expected \"]\"");
                }
            } else {
                return null;
            }
        }

        if (arr.length == 1) {
            for (JSONData data : children) {
                if (data.name.equals(arr[0])) {
                    return data.request(childPath);
                }
            }
            return null;
        }

        for (JSONData data : children) {
            if (data.name.equals(arr[0])) {
                result = data.request(childPath);
            }
        }
        return result;
    }

    protected JSONData request(String[] path) {
        JSONData result = null;

        if (path.length == 0) {
            return this;
        }

        String[] childPath = new String[path.length - 1];


        for (int i = 1; i < path.length; i++) {
            childPath[i - 1] = path[i];
        }

        if (path[0].contains("[")) {
            if (this instanceof JSONArray) {
                if (path[0].contains("]")) {
                    if (count(path[0], '[') > 1 || count(path[0], ']') > 1) throw new RuntimeException("too much ");

                    int index = Integer.parseInt(path[0].substring(path[0].indexOf('[') + 1, path[0].indexOf(']')));
                    return children.get(index).request(childPath);
                } else {
                    throw new RuntimeException("expected \"]\"");
                }
            } else {
                return null;
            }
        }

        if (path.length == 1) {
            for (JSONData data : children) {
                if (data.name.equals(path[0])) {
                    return data.request(childPath);
                }
            }
            return null;
        }

        for (JSONData data : children) {
            if (data.name.equals(path[0])) {
                result = data.request(childPath);
            }
        }


        return result;
    }

    private int count(String str, char chr) {
        int count = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == chr) count++;
        }
        return count;
    }

    public abstract Object getData();

    @Override
    public abstract String toString();

    public abstract String repr();
}

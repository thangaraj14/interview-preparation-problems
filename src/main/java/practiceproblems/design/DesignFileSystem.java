package practiceproblems.design;

import java.util.HashMap;

/**
 * https://leetcode.com/problems/design-file-system/
 */
public class DesignFileSystem {

    private final HashMap<String, Integer> cache = new HashMap<>();

    /**
     * Initialization of class.
     * Use a hash map to store the path and value.
     */
    public DesignFileSystem() {
        cache.put("", -1);      // avoid initially when path is "/a" regarded as false
    }

    /**
     * Creates a new path and associates a value to it if possible and returns True.
     * The valid path's parent is the path before the last "/".
     * Hence, check parent and then put the path into map if it is valid.
     * If the path has already exist, return false.
     *
     * @param path  given path
     * @param value given value
     * @return true to create a new path with value, false if the path already exists or its parent path doesn't exist
     */
    public boolean create(String path, int value) {
        if (path.charAt(0) != '/') {
            return false;
        }
        String parent = path.substring(0, path.lastIndexOf("/"));
        if (!cache.containsKey(parent)) {
            return false;
        }

        return cache.putIfAbsent(path, value) == null;      // if the path exist, m.putIfAbsent(path, value) will be null
    }

    public int get(String path) {
        return cache.getOrDefault(path, -1);
    }

}
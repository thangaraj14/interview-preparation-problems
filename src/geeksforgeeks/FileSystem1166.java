package geeksforgeeks;

import java.util.HashMap;

/**
 * You are asked to design a file system which provides two functions:
 *
 * createPath(path, value): Creates a new path and associates a value to it if possible and returns True.
 *                          Returns False if the path already exists or its parent path doesn't exist.
 *
 * get(path): Returns the value associated with a path or returns -1 if the path doesn't exist.
 * 
 *      ["FileSystem","createPath","createPath","get","createPath","get"]
 *      [[],["/leet",1],["/leet/code",2],["/leet/code"],["/c/d",1],["/c"]]
 * Output:
 *      [null,true,true,2,false,-1]
 * Explanation:
 *        FileSystem fileSystem = new FileSystem();
 *
 *      fileSystem.createPath("/leet", 1); // return true
 *      fileSystem.createPath("/leet/code", 2); // return true
 *      fileSystem.get("/leet/code"); // return 2
 *      fileSystem.createPath("/c/d", 1); // return false because the parent path "/c" doesn't exist.
 *      fileSystem.get("/c"); // return -1 because this path doesn't exist.
 */
public class FileSystem1166 {

    private HashMap<String, Integer> m = new HashMap<>();

    /**
     * Initialization of class.
     * Use a hash map to store the path and value.
     */
    public  FileSystem1166() {
        m.put("", -1);      // avoid initially when path is "/a" regarded as false
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
        if (!m.containsKey(parent)) {
            return false;
        }

        return m.putIfAbsent(path, value) == null;      // if the path exist, m.putIfAbsent(path, value) will be null
    }

    public int get(String path) {
        return m.getOrDefault(path, -1);
    }

}
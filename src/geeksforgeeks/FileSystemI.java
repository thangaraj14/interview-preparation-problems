package geeksforgeeks;
/**
 * Design an in-memory file system to simulate the following functions:
 *
 * ls: Given a path in string format. If it is a file path, return a list that only contains this file's name.
 * If it is a directory path, return the list of file and directory names in this directory. Your output (file and directory names together) should in lexicographic order.
 *
 * mkdir: Given a directory path that does not exist, you should make a new directory according to the path.
 * If the middle directories in the path don't exist either, you should create them as well. This function has void return type.
 *
 * addContentToFile: Given a file path and file content in string format.
 * If the file doesn't exist, you need to create that file containing given content.
 * If the file already exists, you need to append given content to original content. This function has void return type.
 *
 * readContentFromFile: Given a file path, return its content in string format.
 */
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class FileSystemI {
    class Dir {
        HashMap<String, Dir> directory = new HashMap<>();
        HashMap<String, String> files = new HashMap<>();
    }

    Dir root;

    public FileSystemI() {
        root = new Dir();
    }


    public List<String> ls(String path) {
        Dir tempRoot = root;
        List<String> files = new ArrayList<>();
        if (!path.equals("/")) {
            String[] directoriesList = path.split("/");
            for (int i = 1; i < directoriesList.length - 1; i++) {
                tempRoot = tempRoot.directory.get(directoriesList[i]);
            }
            //If the last level in the input happens to be a file name, we simply need to return the file name.
            // So, we directly return the last entry in the array.
            if (tempRoot.files.containsKey(directoriesList[directoriesList.length - 1])) {
                files.add(directoriesList[directoriesList.length - 1]);
                return files;
            } else {
                tempRoot = tempRoot.directory.get(directoriesList[directoriesList.length - 1]);
            }
        }
        //If the last level entry happens to be a directory,
        // we can obtain its subdirectory list from the list of keys in its hashmap.
        // Similarly, we can obtain the list of files in the last directory from the keys in the corresponding hashmap.
        files.addAll(new ArrayList<>(tempRoot.directory.keySet()));
        files.addAll(new ArrayList<>(tempRoot.files.keySet()));
        Collections.sort(files);
        return files;
    }

    public void mkdir(String path) {
        Dir t = root;
        String[] d = path.split("/");
        for (int i = 1; i < d.length; i++) {
            t.directory.putIfAbsent(d[i], new Dir());
            t = t.directory.get(d[i]);
        }
    }

    public void addContentToFile(String filePath, String content) {
        Dir t = root;
        String[] d = filePath.split("/");
        for (int i = 1; i < d.length - 1; i++) {
            t = t.directory.get(d[i]);
        }
        t.files.put(d[d.length - 1], t.files.getOrDefault(d[d.length - 1], "") + content);
    }

    public String readContentFromFile(String filePath) {
        Dir t = root;
        String[] d = filePath.split("/");
        for (int i = 1; i < d.length - 1; i++) {
            t = t.directory.get(d[i]);
        }
        return t.files.get(d[d.length - 1]);
    }
}

/**
 * Your FileSystem object will be instantiated and called as such:
 * FileSystem obj = new FileSystem();
 * List<String> param_1 = obj.ls(path);
 * obj.mkdir(path);
 * obj.addContentToFile(filePath,content);
 * String param_4 = obj.readContentFromFile(filePath);
 */
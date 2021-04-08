package geeksforgeeks;
/**
 * Design an in-memory file system to simulate the following functions:
 * <p>
 * ls: Given a path in string format. If it is a file path, return a list that only contains this file's name.
 * If it is a directory path, return the list of file and directory names in this directory. Your output (file and directory names together) should in lexicographic order.
 * <p>
 * mkdir: Given a directory path that does not exist, you should make a new directory according to the path.
 * If the middle directories in the path don't exist either, you should create them as well. This function has void return type.
 * <p>
 * addContentToFile: Given a file path and file content in string format.
 * If the file doesn't exist, you need to create that file containing given content.
 * If the file already exists, you need to append given content to original content. This function has void return type.
 * <p>
 * readContentFromFile: Given a file path, return its content in string format.
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class DesignInMemoryFileSystem {
    class Trie {
        boolean isFile = false;
        HashMap<String, Trie> folders = new HashMap<>();
        String content = "";
    }

    Trie root;

    public DesignInMemoryFileSystem() {
        root = new Trie();
    }

    public List<String> ls(String path) {
        Trie trie = root;
        List<String> files = new ArrayList<>();
        if (!path.equals("/")) {
            String[] arr = path.split("/");
            for (int i = 1; i < arr.length; i++) {
                trie = trie.folders.get(arr[i]);
            }
            if (trie.isFile) {
                files.add(arr[arr.length - 1]);
                return files;
            }
        }
        List<String> res_files = new ArrayList<>(trie.folders.keySet());
        Collections.sort(res_files);
        return res_files;
    }

    public void mkdir(String path) {
        Trie trie = root;
        String[] arr = path.split("/");
        for (int i = 1; i < arr.length; i++) {
            if (!trie.folders.containsKey(arr[i])) {
                trie.folders.put(arr[i], new Trie());
            }
            trie = trie.folders.get(arr[i]);
        }
    }

    public void addContentToFile(String filePath, String content) {
        Trie trie = root;
        String[] arr = filePath.split("/");
        for (int i = 1; i < arr.length - 1; i++) {
            trie = trie.folders.get(arr[i]);
        }
        if (!trie.folders.containsKey(arr[arr.length - 1])) {
            trie.folders.put(arr[arr.length - 1], new Trie());
        }
        trie = trie.folders.get(arr[arr.length - 1]);
        trie.isFile = true;
        trie.content = trie.content + content;
    }

    public String readContentFromFile(String filePath) {
        Trie trie = root;
        String[] arr = filePath.split("/");
        for (int i = 1; i < arr.length - 1; i++) {
            trie = trie.folders.get(arr[i]);
        }
        return trie.folders.get(arr[arr.length - 1]).content;
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
package practiceproblems.design;
/**
 * tricky trie
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
        // we start from 1st index because "/a/b/c".split("/") =>  ["", "a", "b", "c"]
        for (int i = 1; i < arr.length; i++) {
            trie.folders.putIfAbsent(arr[i], new Trie());
            trie = trie.folders.get(arr[i]);
        }
    }

    public void addContentToFile(String filePath, String content) {
        Trie trie = root;
        String[] arr = filePath.split("/");
        for (int i = 1; i < arr.length - 1; i++) {
            trie = trie.folders.get(arr[i]);
        }
        trie.folders.putIfAbsent(arr[arr.length - 1], new Trie());
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
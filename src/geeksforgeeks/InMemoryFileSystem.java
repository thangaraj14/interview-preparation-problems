package geeksforgeeks;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * http://lixinchengdu.github.io/algorithmbook/leetcode/design-in-memory-file-system.html
 */
public class InMemoryFileSystem {

    class Trie {
        boolean isfile = false;
        HashMap<String, Trie> files = new HashMap<>();
        String content = "";
    }

    Trie root;

    public InMemoryFileSystem() {
        root = new Trie();
    }

    public List<String> ls(String path) {
        Trie trie = root;
        List<String> files = new ArrayList<>();
        if (!path.equals("/")) {
            String[] arr = path.split("/");
            for (int i = 1; i < arr.length; i++) {
                trie = trie.files.get(arr[i]);
            }
            if (trie.isfile) {
                files.add(arr[arr.length - 1]);
                return files;
            }
        }
        List<String> res_files = new ArrayList<>(trie.files.keySet());
        Collections.sort(res_files);
        return res_files;
    }

    public void mkdir(String path) {
        Trie trie = root;
        String[] arr = path.split("/");
        for (int i = 1; i < arr.length; i++) {
            if (!trie.files.containsKey(arr[i])) {
                trie.files.put(arr[i], new Trie());
            }
            trie = trie.files.get(arr[i]);
        }
    }

    public void addContentToFile(String filePath, String content) {
        Trie trie = root;
        String[] arr = filePath.split("/");
        for (int i = 1; i < arr.length - 1; i++) {
            trie = trie.files.get(arr[i]);
        }
        if (!trie.files.containsKey(arr[arr.length - 1])) {
            trie.files.put(arr[arr.length - 1], new Trie());
        }
        trie = trie.files.get(arr[arr.length - 1]);
        trie.isfile = true;
        trie.content = trie.content + content;
    }

    public String readContentFromFile(String filePath) {
        Trie trie = root;
        String[] arr = filePath.split("/");
        for (int i = 1; i < arr.length - 1; i++) {
            trie = trie.files.get(arr[i]);
        }
        return trie.files.get(arr[arr.length - 1]).content;
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
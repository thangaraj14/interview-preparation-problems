package geeksforgeeks;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * http://lixinchengdu.github.io/algorithmbook/leetcode/design-in-memory-file-system.html
 */
public class DesignInMemoryFileSystem {

    class Trie {
        boolean isfile = false;
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
            if (trie.isfile) {
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
        trie.isfile = true;
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

    public static void main(String[] args) {
        DesignInMemoryFileSystem inMemoryFileSystem = new DesignInMemoryFileSystem();
        inMemoryFileSystem.mkdir("/a/b/c");
        inMemoryFileSystem.addContentToFile("/a", "1.txt");
        inMemoryFileSystem.addContentToFile("/a/b", "2.txt");
        System.out.println(inMemoryFileSystem.ls("/a"));
        System.out.println(inMemoryFileSystem.readContentFromFile("/a/b"));
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
package geeksforgeeks;

import java.util.Arrays;

/**
 * https://leetcode.com/articles/desing-compressed-string-iterator/
 * <p>
 * 604. Design Compressed String Iterator
 */
public class DesignCompressedStringIterator {

    String res;

    int ptr = 0, num = 0;
    char ch = ' ';

    public DesignCompressedStringIterator(String s) {
        res = s;
    }

    public char next() {
        if (!hasNext()) {
            return ' ';
        }
        if (num == 0) {
            ch = res.charAt(ptr++);
            while (ptr < res.length() && Character.isDigit(res.charAt(ptr))) {
                num = num * 10 + res.charAt(ptr++) - '0';
            }
        }
        num--;
        return ch;
    }

    public boolean hasNext() {
        return ptr != res.length() || num != 0;
    }

    public static void main(String[] args) {
        DesignCompressedStringIterator compressedStringIterator = new DesignCompressedStringIterator("L1e21t1C1o1d1e1");
        System.out.println(compressedStringIterator.next());
        System.out.println(compressedStringIterator.next());
        System.out.println(compressedStringIterator.next());
    }

}

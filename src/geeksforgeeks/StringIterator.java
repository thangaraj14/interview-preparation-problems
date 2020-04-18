package geeksforgeeks;

//StringIterator iterator = new StringIterator("L1e2t1C1o1d1e1");
// iterator.next(); // return 'L'
// iterator.next(); // return 'e'
// iterator.next(); // return 'e'
// iterator.next(); // return 't'
// iterator.next(); // return 'C'
// iterator.next(); // return 'o'
// iterator.next(); // return 'd'
// iterator.hasNext(); // return true
// iterator.next(); // return 'e'
// iterator.hasNext(); // return false
// iterator.next(); // return ' '
public class StringIterator {
    String res;
    int ptr = 0, num = 0;
    char ch = ' ';
    public StringIterator(String s) {
        res = s;
    }
    public char next() {
        if (!hasNext())
            return ' ';
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
}
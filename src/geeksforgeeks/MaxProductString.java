package geeksforgeeks;

public class MaxProductString {
    public int maxProduct(String[] words) {
        int[] checker = new int[words.length];
        int max = 0;
        // populating the checker array with their respective numbers
        for (int i = 0; i < checker.length; i++) {
            int num = 0;
            for (int j = 0; j < words[i].length(); j++) {

                // a 1->1
                // b 2->10
                // c 4->100
                // ab 3->11
                // ac 5->101
                // abc 7->111
                // az 33554433->10000000000000000000000001

                num |= 1 << (words[i].charAt(j) - 'a');
                System.out.println(words[i].charAt(j)+"->"+num+ "->"+Integer.toBinaryString(num) );
            }

            checker[i] = num;
        }

        for (int i = 0; i < words.length; i++) {
            for (int j = i + 1; j < words.length; j++) {
                // abcd efgd
                // 11110000 -> abcd
                // 00011110 -> efgd
                // and-ing these two might say if even a single char is present in other
                if ((checker[i] & checker[j]) == 0) //checking if the two strings have common character
                    max = Math.max(max, words[i].length() * words[j].length());
            }
        }
        System.out.println(max);
        return max;
    }

    
    public static void main(String[] args) {
        new MaxProductString().maxProduct(new String[]{"abcw", "baz", "foo", "bar", "xtfn", "abcdef"});
    }
}
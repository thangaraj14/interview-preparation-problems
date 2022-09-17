package practiceproblems;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/text-justification
 */
public class StringJustify {
    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> result = new ArrayList<>();

        int start = 0;
        int end = 0;
        while (start < words.length) {
            end = findLastWordIndex(words, start, maxWidth);

            String line = justify(words, start, end, maxWidth);
            result.add(line);

            start = end + 1;
        }

        return result;
    }

    private int findLastWordIndex(String[] words, int i, int maxWidth) {
        int j = i;

        int currWidth = words[j].length(); //took first word at j, notice first one doesn't need a space.
        j++;
         // +1 is considered as space between the words
        while (j < words.length && (currWidth + 1 + words[j].length() <= maxWidth)) {
            currWidth = currWidth + 1 + words[j].length();
            j++;
        }

        return j - 1; //end now points at the next index so -1
    }

    private String justify(String[] words, int i, int j, int maxWidth) {
        // if there is only one word possible in this window, we simply pad extra spaces.
        if (j - i == 0) return padResult(words[i], maxWidth);

        // For last line, j will always point to the last element.
        boolean isLastLine = j == words.length - 1;

        // find the length of words.
        int l = 0;
        for (int k = i; k <= j; k++) {
            l += words[k].length();
        }

        int numSpaces = maxWidth - l;
        int numWordsToPad = j - i; // for total of 3 words, j=2, i=0 so 2 words to pad (since we don't pad last one)

        StringBuilder sb = new StringBuilder();

        // SpaceStr is the string with right num of spaces that should be attached to each word (numWordsToPad)
        String spaceStr = isLastLine ? " " : blank(numSpaces / numWordsToPad); // simple separation in last line
        // remainderSpaceCount is the number of extra space that need to be attached to first words (from left)
        // if we need to add 5 space and, we have 3 words we need to add extra space in round-robin manner
        int remainderSpaceCount = isLastLine ? 0 : numSpaces % numWordsToPad;

        for (int k = i; k <= j; k++) {
            sb.append(words[k]).append(spaceStr); // notice we also end up attaching to the last word, we will trim it later

            // also append extra spaces
            if (remainderSpaceCount > 0) {
                sb.append(" ");
                remainderSpaceCount--;
            }
        }

        String line = sb.toString().trim(); // the last word will also have spaces, so need to tirm.

        return padResult(line, maxWidth); // if the last word still needs to be padded.
    }

    private String padResult(String result, int maxWidth) {
        return result + blank(maxWidth - result.length());
    }

    private String blank(int count) {
        StringBuilder sb = new StringBuilder();
        while (count > 0) {
            sb.append(" ");
            count--;
        }

        return sb.toString();
    }

    public static void main(String[] args) {
       // System.out.println(new StringJustify().fullJustify(new String[]{"This", "is", "an", "example", "of", "text", "justification."}, 16));
        System.out.println(new StringJustify().fullJustify(new String[]{"What","must","be","acknowledgment","shall","be"}, 16));
        System.out.println(new StringJustify().fullJustify(new String[]{"Science","is","what","we","understand","well","enough","to","explain","to","a","computer.","Art","is","everything","else","we","do"}, 20));
    }
}

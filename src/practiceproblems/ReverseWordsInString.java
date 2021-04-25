package practiceproblems;

import java.util.Arrays;

public class ReverseWordsInString {

    public char[] reverse(char[] arr, int i, int j) {
        while (i < j) {
            char tmp = arr[i];
            arr[i++] = arr[j];
            arr[j--] = tmp;
        }
        return arr;
    }
    
    public String reverseWords(String s) {
        // reverse the whole string and convert to char array
        char[] str = reverse(s.toCharArray(), 0, s.length()-1);
        int start = 0, end = 0; // start and end positions of a current word
        //for input "the sky is blue"
        // the char arr contians
        //[e u l b  s i  y k s  e h t]
        
        System.out.println(Arrays.toString(str));
        for (int i = 0; i < str.length; i++) {
            if (str[i] != ' ') { // if the current char is letter 
                str[end++] = str[i]; // just move this letter to the next free pos
            } else if (i > 0 && str[i-1] != ' ') { // if the first space after word
                reverse(str, start, end-1); // reverse the word
                str[end++] = ' '; // and put the space after it
                start = end; // move start position further for the next word
            }
        }
        reverse(str, start, end-1); // reverse the tail word if it's there
        // here's an ugly return just because we need to return Java's String
        // also as there could be spaces at the end of original string 
        // we need to consider redundant space we have put there before
        return new String(str, 0, end > 0 && str[end-1] == ' ' ? end-1 : end);
    }
}
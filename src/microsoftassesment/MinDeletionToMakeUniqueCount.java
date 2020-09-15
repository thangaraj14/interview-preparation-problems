package microsoftassesment;

import java.util.Arrays;
import java.util.Collections;

/**
 * Given a string s consisting of n lowercase letters, you have to delete the minimum number of characters from s so that every letter in s appears a unique number of times.
 * We only care about the occurrences of letters that appear at least once in result.
 */
public class MinDeletionToMakeUniqueCount {

//    Input: "eeeeffff"
//    Output: 1
//    Explanation:
//    We can delete one occurence of 'e' or one occurence of 'f'. Then one letter will occur four times and the other three times.


//    The trick is to figure out the frequencies of each letter and sort them in descending order. We can have at the most one letter with a given frequency. We compare the
//    frequency of i-1 letter with the frequency of i'th letter. if the frequency of the i'th letter is the same or equal to the frequency of the i-1'th letter we decrease the
//    frequency of the i'th letter to frequency[i-1]-1.
//    Special case would be if the frequency of the i-1'th letter is 0.
//    In that scenario we make the frequency of the i'th letter to 0 (effectively deleting that particular character).
//    For example- if the frequencies in descending order are 4,4,3,2,1,1.
//    Start from i=1;
//    freq[1]=freq[0] (4=4) so reduce freq[i]->3 .... 4,3,3,2,1,1 -numdelete =1;
//    i=2
//    freq[2]==freq1 so reduce freq[2]->2...4,3,2,2,1,1 numdelete=2;
//
//    i=3
//    proceeding in a similar fashion the array would be 4.3.2,1,1,1 numdelete=3;
//
//    i=4
//            4,3,2,1,0,1 numdelete=4
//
//    i=5
//    we cannot have a frequency lesser than 0 ... it means that we would have to effectively delete that character making its frequency 0
//            4,3,2,1,0,0 numdelete =5
//
//    and that's the solution


    public int minDeletions(String str) {
        Integer[] freqOfLetter;
      //  String str="qqqsaaaaadddddfafaafafeqqq";
        freqOfLetter = new Integer[26];

        Arrays.fill(freqOfLetter,0);

        char[] s=str.toCharArray();
        for (int i = 0; i < s.length; i++)
        {
            freqOfLetter[s[i] - 'a']++;
        }


        int numDelete=0;

        Arrays.sort(freqOfLetter, Collections.reverseOrder());

        for(int i=1;i<freqOfLetter.length;i++){
            if(freqOfLetter[i]==0) // since sorted descending, if 0 comes there's no more search needed
                break;
            if(freqOfLetter[i]>=freqOfLetter[i-1]) {
                if(freqOfLetter[i-1]==0) {
                    numDelete += freqOfLetter[i];
                    freqOfLetter[i]=0;
                }
                else {
                    numDelete += freqOfLetter[i] - freqOfLetter[i - 1] + 1;

                    freqOfLetter[i] = freqOfLetter[i - 1] - 1;
                }
            }
        }
        return numDelete;
    }

    public static void main(String[] args) {
        MinDeletionToMakeUniqueCount m = new MinDeletionToMakeUniqueCount();
        String[] testCases = new String[] {"qqqsaaaaadddddfafaafafeqqq", "aaaabbbb", "aabbbbcccdddd", "aaaaaabbbbbccccddddeeeeee", "abcdefghijkl", "aaaaaa", "aabbffddeaee", "llll", "example"};
        for (String test : testCases) System.out.println(test + ": " + m.minDeletions(test));
    }
}

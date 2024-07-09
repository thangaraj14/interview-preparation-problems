package practiceproblems;

import java.util.TreeSet;

//https://leetcode.com/problems/plates-between-candles/
public class PlatesBetweenCandles {
    public int[] platesBetweenCandles(String s, int[][] queries) {
        int n = s.length();
        int[] nearestRightCandle = new int[n], nearestLeftCandle = new int[n] , starCount = new int[n];
        int candles = -1, stars = 0;
        char[] ch = s.toCharArray();

        for(int i = 0; i< n; i++){
            if(ch[i] == '*'){
                stars++;
            } else{
                candles = i;
            }
            nearestLeftCandle[i] = candles;
            starCount[i] = stars;
        }

        candles = -1;
        for(int i = n-1; i>= 0; i--){
            if(ch[i] == '|'){
                candles = i;
            }
            nearestRightCandle[i] = candles;
        }
        int m = queries.length;
        int[] res = new int[m];

        for(int i = 0; i< m; i++){
            int lw = nearestRightCandle[queries[i][0]];
            int rw = nearestLeftCandle[queries[i][1]];

            if(lw == -1 || rw == -1 || lw >= rw){
                res[i] = 0;
                continue;
            }

            res[i] = starCount[rw] - starCount[lw];
        }

        return res;
    }
    public int[] platesBetweenCandlesWithTreeSet(String s, int[][] queries) {
        int len = s.length();
        int[] plates = new int[len];

        TreeSet<Integer> candles = new TreeSet<>();

        int plateCount = 0;

        //get the platecount and the indices of the candles
        for (int i = 0; i < len; i++) {
            if (s.charAt(i) == '|') {
                candles.add(i);
                plates[i] = plateCount;
            } else {
                plateCount++;
            }
        }

        //iterating through the queries
        int[] result = new int[queries.length];
        int i = 0;

        for (int[] query : queries) {
            Integer leftCandle = candles.ceiling(query[0]);
            Integer rightCandle = candles.floor(query[1]);

            if (leftCandle != null && rightCandle != null && leftCandle < rightCandle) {
                result[i] = plates[rightCandle] - plates[leftCandle];
            }
            i++;

        }

        return result;
    }
}

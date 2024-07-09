package cess.sort_search;

import graph.leetcode.Pair;

import java.util.*;

// similar to intervals problem but with a twist
public class MovieFestival {

    public static int moviesWatchedCount(int[][] movieTimings, int persons){
        int unWatchableCount = 0;
        Arrays.sort(movieTimings, Comparator.comparingInt(a -> a[1]));
        TreeSet<Pair<Integer,Integer>> set = new TreeSet<>(Comparator.comparingInt(a -> a.key));

        for(int i=0;i<movieTimings.length;i++){
            if(set.isEmpty()){
                set.add(new Pair<>(movieTimings[i][1]*-1,i));
                continue;
            }
            Pair<Integer,Integer> p = set.lower(new Pair<>(movieTimings[i][0]*-1,-1));
            if (p!=null && !Objects.equals(p.value, set.last().value)){
                set.remove(p);
                set.add(new Pair<>(movieTimings[i][1]*-1,i));
                continue;
            }
            if (set.size() < persons){
                set.add(new Pair<>(movieTimings[i][1]*-1,i));
            }else {
                unWatchableCount++;
            }

        }

        return movieTimings.length - unWatchableCount;
    }

    public static void main(String[] args) {
        System.out.println(moviesWatchedCount(new int[][]{{1,5},{8,10},{3,6},{2,5},{6,9}},2));
    }

}

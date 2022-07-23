package practiceproblems;

import java.util.Arrays;

/**
 * TODO
 * http://www.lintcode.com/en/problem/nuts-bolts-problem/
 */
public class NutsAndBoltsMatch {
    /**
     * @param nuts:    an array of integers
     * @param bolts:   an array of integers
     * @param compare: a instance of Comparator
     */
    public void sortNutsAndBolts(String[] nuts, String[] bolts, NBComparator compare) {
        if (nuts == null || bolts == null) return;
        if (nuts.length != bolts.length) return;

        qsort(nuts, bolts, compare, 0, nuts.length - 1);

        System.out.println(Arrays.toString(nuts));
        System.out.println(Arrays.toString(bolts));
    }

    private void qsort(String[] nuts, String[] bolts, NBComparator compare,
                       int l, int u) {
        if (l >= u) return;
        // find the partition index for nuts with bolts[l]
        int partitionIndex = partition(nuts, bolts[l], compare, l, u);
        // partition bolts with nuts[part_inx]
        partition(bolts, nuts[partitionIndex], compare, l, u);
        // qsort recursively
        qsort(nuts, bolts, compare, l, partitionIndex - 1);
        qsort(nuts, bolts, compare, partitionIndex + 1, u);
    }

    private int partition(String[] str, String pivot, NBComparator compare,
                          int lowerIndex, int upperIndex) {
        //
        int m = lowerIndex;
        for (int i = lowerIndex + 1; i <= upperIndex; i++) {
            if (compare.cmp(str[i], pivot) == -1 ||
                    compare.cmp(pivot, str[i]) == 1) {
                //
                m++;
                swap(str, i, m);
            } else if (compare.cmp(str[i], pivot) == 0 ||
                    compare.cmp(pivot, str[i]) == 0) {
                // swap nuts[l]/bolts[l] with pivot
                swap(str, i, lowerIndex);
                i--;
            }
        }
        // move pivot to proper index
        swap(str, m, lowerIndex);

        return m;
    }

    private void swap(String[] str, int l, int r) {
        String temp = str[l];
        str[l] = str[r];
        str[r] = temp;
    }

    public static void main(String[] args) {
        new NutsAndBoltsMatch().sortNutsAndBolts(new String[]{"ab", "bc", "dd", "gg"}, new String[]{"AB", "GG", "DD", "BC"}, new NBComparator());
    }

    static class NBComparator {
        public int cmp(String a, String b) {
            return a.compareTo(b);
        }
    }
}




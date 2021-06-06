package dp.oiknapsack;

import java.util.Arrays;
import java.util.Comparator;

public class FractionalKnapSack {

    private static double getMaxValue(int[] wt, int[] val, int capacity) {
        ItemValue[] iVal = new ItemValue[wt.length];

        for (int i = 0; i < wt.length; i++) {
            iVal[i] = new ItemValue(wt[i], val[i], i);
        }

        Arrays.sort(iVal, Comparator.comparing(ItemValue::getCost));

        double totalValue = 0d;

        for (ItemValue i : iVal) {

            int curWt = (int) i.wt;
            int curVal = (int) i.val;

            if (capacity - curWt >= 0) {
                // this weight can be picked while
                capacity = capacity - curWt;
                totalValue += curVal;
            } else {
                // item cant be picked whole
                double fraction = ((double) capacity / (double) curWt);
                totalValue += (curVal * fraction);
                capacity = (int) (capacity - (curWt * fraction));
                break;
            }
        }
        return totalValue;
    }

    static class ItemValue {
        Double cost;
        double wt;
        double val;
        double ind;

        public ItemValue(int wt, int val, int ind) {
            this.wt = wt;
            this.val = val;
            this.ind = ind;
            this.cost = (double) val / (double) wt;
        }

        public Double getCost() {
            return cost;
        }
    }

    public static void main(String[] args) {
        int[] wt = { 2, 3, 5, 7, 1, 4, 1 };
        int[] val = { 10, 5, 15, 7, 6, 18, 3 };
        int capacity = 15;

        double maxValue = getMaxValue(wt, val, capacity);

        System.out.println("Maximum value we can obtain = " + maxValue);
    }
}

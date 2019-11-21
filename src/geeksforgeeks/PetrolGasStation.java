package geeksforgeeks;

public class PetrolGasStation {

    // gas means amount of gas which we have in our car
    // cost means amount of gas to reach next station
    public static int canCompleteCircuit(int[] gas, int[] cost) {
        int sumGas = 0;
        int sumCost = 0;
        int start = 0;
        int tank = 0;
        for (int i = 0; i < gas.length; i++) {
            sumGas += gas[i];
            sumCost += cost[i];
        }
        if (sumGas < sumCost) {
            return -1;
        }

        for (int i = 0; i < gas.length; i++) {
            tank += gas[i] - cost[i];
            if (tank < 0) {
                start = i + 1;
                tank = 0;
            }
        }

        return start;
    }

    public static void main(String[] args) {

        int[] gas = {4, 6, 7, 4};
        int[] cost = {6, 5, 3, 5};
        int start = canCompleteCircuit(gas, cost);
        System.out.println(start == -1 ? "No Solution" : "Start = " + start);
    }
}

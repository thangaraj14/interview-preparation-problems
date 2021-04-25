package thread.basics;

public class Sum {

    int sum(int... vals) {

        int total = 0;
        for (int i = 0; i < vals.length; i++) {
            total += vals[i];
        }
        return total;
    }

    public static void main(String[] args) {
        Sum sum = new Sum();
        System.out.println(sum.sum(1, 2, 3));
    }
}
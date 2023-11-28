package cess;

public class TowerOfHanoi {

    public static void solve(String from, String to, String aux, int n) {
        if (n == 1) {
            System.out.println("Take disk 1 from rod " + from + " to rod " + to);
            return;
        }
        solve(from, aux, to, n - 1);
        System.out.println("Take disk " + n + " from rod " + from + " to rod " + to);

        System.out.println();
        solve(aux, to, from, n - 1);
    }

    public static void main(String[] args) {
        solve("A", "C", "B", 3);
    }
}

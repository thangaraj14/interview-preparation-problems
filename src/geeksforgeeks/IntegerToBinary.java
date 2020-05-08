package geeksforgeeks;

class IntegerToBinary {
    // function to convert decimal to binary
    static void decToBinary(int n) {
        // array to store binary number
        int[] binaryNum = new int[1000];

        // counter for binary array
        int i = 0;
        while (n > 0) {
            // storing remainder in binary array
            binaryNum[i] = n % 2;
            n = n / 2;
            i++;
        }

        // printing binary array in reverse order
        for (int j = i - 1; j >= 0; j--)
            System.out.print(binaryNum[j]);
    }

    public static void main(String[] args) {
        int n = 4;
        // decToBinary(n);
        //  System.out.println("Default method :" + Integer.toBinaryString(4));
        System.out.println(intToBinary(4));
    }

    public static String intToBinary(int n) {
        String s = "";
        while (n > 0) {
            s = ((n % 2) == 0 ? "0" : "1") + s;
            n = n / 2;
        }
        return s;
    }
}
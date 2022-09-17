package strings.stringProblems;

public class ShiftingLetters {
    public String shiftingLetters(String s, int[] shifts) {
        int total = 0;
        shifts[shifts.length - 1] %= 26;
        for (int i = shifts.length - 2; i >= 0; i--) {
            shifts[i] += shifts[i + 1];
            shifts[i] %= 26;
        }

        //System.out.println(Arrays.toString(shifts));
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            int temp = s.charAt(i) - 'a';
            temp = (temp + shifts[i]) % 26;
            char val = (char) (temp + 'a');
            sb.append("" + val);
        }

        return sb.toString();
    }

    public String shiftingLettersEffective(String S, int[] shifts) {
        char[] arr = S.toCharArray();
        int sum = 0;
        for (int i = shifts.length - 1; i >= 0; i--) {
            int cur = arr[i] - 'a';
            sum += shifts[i] % 26;
            cur += sum;
            arr[i] = (char) ('a' + cur % 26);
        }

        return new String(arr);
    }
}

package practiceproblems;

/**
 * https://www.geeksforgeeks.org/check-two-expressions-brackets/
 */
//unresolved
public class SimilarExpressions {

    public static void main(String[] args) {

        System.out.println('+' * '-');

        String query = "-(a+b+c)";
        String response = "-a-b-c";

        checkExpression(query.toCharArray(), response.toCharArray());
    }

    private static void checkExpression(char[] query, char[] response) {

        char symbol = 0;
        int startIndex = 0;
        int endIndex = 0;
        for (int i = 0; i < query.length; i++) {
            if (query[i] == '(') {
                symbol = query[i - 1];
                startIndex = i + 1;
            } else if (query[i] == ')') {
                endIndex = i - 1;
                solve(symbol, startIndex, endIndex, query);

            }
        }
    }

    private static void solve(char symbol, int startIndex, int endIndex, char[] query) {

        for (int i = startIndex; i <= endIndex; i++) {
            char temp = 0;
            if (query[startIndex] == '-') {
                temp = '-' * '+';
                query[i] = temp;
            } else if (symbol == '+') {
                temp = '-';
                query[i] = temp;
            } else if (Character.isLetter(query[startIndex])) {
                if (startIndex == i) {
                    query[startIndex - 1] = temp;
                }
                System.out.print(query[startIndex]);
            }
            System.out.print(temp);
        }
    }
}
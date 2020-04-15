package geeksforgeeks;

import java.util.List;

/**
 * https://leetcode.com/problems/different-ways-to-add-parentheses/
 */
class DifferentWaysToAddParenthesis {

  /*  List<Integer> diffWaysToCompute(String input) {

        List<Integer> nums;
        List<Integer> operators;
        int num = 0;
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == '*' || input.charAt(i) == '-' || input.charAt(i) == '+') {
                nums.emplace_back(num);
                operators.emplace_back(input.charAt(i));
                num = 0;
            } else {
                num = num * 10 + (input.charAt(i) - 48);
            }
        }
        nums.emplace_back(num);

        int n = nums.size();
        vector<int> dp[ n][n];

        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                vector<int> x;
                dp[i][j] = x;
                if (i == j) {
                    dp[i][i].push_back(nums[i]);
                }
            }
        }

        for (int l = 1; l <= n; l++) {
            for (int i = 0; i <= n - l; i++) {
                int j = i + l - 1;
                for (int k = i; k < j; k++) {
                    for (int m : dp[i][k]) {
                        for (int o : dp[k + 1][j]) {
                            char operat = operators[k];
                            int result;
                            switch (operat) {
                                case '+':
                                    result = m + o;
                                    break;
                                case '-':

                                    result = m - o;
                                    break;
                                case '*':
                                    result = m * o;
                                    break;

                            }
                            dp[i][j].emplace_back(result);
                        }

                    }

                }
            }
        }

        sort(dp[0][n - 1].begin(), dp[0][n - 1].end());
        return dp[0][n - 1];

    }*/
};
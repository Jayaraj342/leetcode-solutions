// n Cn
// catalan number
class Solution {

    public List<Integer> diffWaysToCompute(String expression) {
        List<Integer>[][] memo = new ArrayList[expression.length()][expression.length()];
        return computeResults(expression, memo, 0, expression.length() - 1);
    }

    private List<Integer> computeResults(
            String expression,
            List<Integer>[][] memo,
            int start,
            int end
    ) {
        if (memo[start][end] != null) {
            return memo[start][end];
        }

        List<Integer> results = new ArrayList<>();

        // Base case: single digit
        if (start == end) {
            results.add(expression.charAt(start) - '0');
            return results;
        }

        // Base case: two-digit number
        if (end - start == 1 && Character.isDigit(expression.charAt(start))) {
            int tens = expression.charAt(start) - '0';
            int ones = expression.charAt(end) - '0';
            results.add(10 * tens + ones);
            return results;
        }

        // Recursive case: split the expression at each operator
        for (int i = start; i <= end; i++) {
            char currentChar = expression.charAt(i);
            if (Character.isDigit(currentChar)) {
                continue;
            }

            List<Integer> leftResults = computeResults(
                    expression,
                    memo,
                    start,
                    i - 1
            );
            List<Integer> rightResults = computeResults(
                    expression,
                    memo,
                    i + 1,
                    end
            );

            for (int leftValue : leftResults) {
                for (int rightValue : rightResults) {
                    switch (currentChar) {
                        case '+':
                            results.add(leftValue + rightValue);
                            break;
                        case '-':
                            results.add(leftValue - rightValue);
                            break;
                        case '*':
                            results.add(leftValue * rightValue);
                            break;
                    }
                }
            }
        }

        return memo[start][end] = results;
    }
}
// space : O(1)
class Solution {
    public boolean validateStackSequences(int[] pushed, int[] popped) {
        int i = 0, j = 0; // i acts as stack top index

        for (int num : pushed) {
            pushed[i++] = num; // push onto stack

            // pop while top matches popped[j]
            while (i > 0 && pushed[i - 1] == popped[j]) {
                i--; // pop
                j++;
            }
        }

        return i == 0; // if stack is empty, sequences are valid
    }
}

// space : O(n)
class Solution {
    public boolean validateStackSequences(int[] pushed, int[] popped) {
        Stack<Integer> stack = new Stack<>();
        int j = 0;
        for (int num : pushed) {
            stack.push(num);
            while (!stack.empty() && stack.peek() == popped[j]) {
                stack.pop();
                j++;
            }
        }

        return stack.empty();
    }
}
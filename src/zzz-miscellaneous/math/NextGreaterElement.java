import java.util.*;

class NextGreaterElement {

    static int[] arr = {73, 74, 75, 71, 69, 72, 76, 73};

    public static void printNGE() {
        Stack<Integer> stack = new Stack<>();
        int[] res = new int[arr.length];

        for(int i=0; i< arr.length; i++) {
            if(!stack.isEmpty()) {
                while (!stack.isEmpty() && arr[stack.peek()] < arr[i]) {
                    int lastSmallerElementIndex = stack.pop();
                    res[lastSmallerElementIndex] = i - lastSmallerElementIndex;
                }
            }
            stack.add(i);
        }

        System.out.println(Arrays.toString(res));
    }

    public static void main(String[] args) {
        printNGE();
    }
}
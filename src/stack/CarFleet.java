class Solution {
    public int carFleet(int target, int[] position, int[] speed) {
        List<Integer[]> posAndSpeed = new ArrayList<>();
        for (int i = 0; i < position.length; i++) {
            posAndSpeed.add(new Integer[]{position[i], speed[i]});
        }
        posAndSpeed.sort((a, b) -> b[0] - a[0]);

        List<Double> times = posAndSpeed.stream().map(arr ->  ((target - arr[0]) / (double) arr[1])).collect(Collectors.toList());

        Stack<Double> stack = new Stack<>();

        for (Double timeTaken : times) {
            if (stack.isEmpty()) {
                stack.push(timeTaken);
            } else {
                if (timeTaken > stack.peek()) {
                    stack.push(timeTaken);
                }
            }
        }
        return stack.size();
    }
}
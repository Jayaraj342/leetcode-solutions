class Solution {
    public int carFleet(int target, int[] position, int[] speed) {
        int n = position.length;

        // Create pairs of (position, speed)
        int[][] cars = new int[n][2];
        for (int i = 0; i < n; i++) {
            cars[i][0] = position[i];
            cars[i][1] = speed[i];
        }

        // Sort cars by position in descending order
        Arrays.sort(cars, (a, b) -> Integer.compare(b[0], a[0]));

        int fleets = 0;
        double prevTime = 0;
        // Iterate from the car closest to the target
        for (int i = 0; i < n; i++) {
            double time = (double) (target - cars[i][0]) / cars[i][1];

            // If current car takes more time, it forms a new fleet
            if (time > prevTime) {
                fleets++;
                prevTime = time;
            }
            // Else, it joins the fleet ahead automatically
        }

        return fleets;
    }
}

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
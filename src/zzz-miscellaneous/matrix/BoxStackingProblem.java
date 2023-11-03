class GFG {
    static class Box implements Comparable<Box> {
        int hei, len, bre, area;

        public Box(int hei, int len, int bre) {
            this.hei = hei;
            this.len = len;
            this.bre = bre;
        }

        @Override
        public int compareTo(Box o) {
            return o.area - this.area;
        }

        @Override
        public String toString() {
            return "Box{" +
                    "hei=" + hei +
                    ", len=" + len +
                    ", bre=" + bre +
                    '}';
        }
    }

    static int maxStackHeight(Box[] arr, int n) {
        Box[] rot = new Box[n * 3];

        for (int i = 0; i < n; i++) {
            Box box = arr[i];

            rot[3 * i] = new Box(box.hei, Math.max(box.len, box.bre), Math.min(box.len, box.bre));
            rot[3 * i + 1] = new Box(box.len, Math.max(box.hei, box.bre), Math.min(box.hei, box.bre));
            rot[3 * i + 2] = new Box(box.bre, Math.max(box.len, box.hei), Math.min(box.len, box.hei));
        }

        for (Box box : rot) {
            box.area = box.len * box.bre;
        }

        Arrays.sort(rot);
        int count = 3 * n;

        int[] dp = new int[count];
        for (int i = 0; i < count; i++) {
            dp[i] = rot[i].hei;
        }

        for (int i = count - 1; i >= 0; i--) {
            Box curr = rot[i];
            for (int j = i + 1; j < count; j++) {
                Box bigBox = rot[j];
                if (curr.len > bigBox.len && curr.bre > bigBox.bre) {
                    dp[i] = Math.max(dp[i], curr.hei + dp[j]);
                }
            }
        }

        int max = 0;
        for (int height : dp) {
            max = Math.max(max, height);
        }

        return max;
    }

    public static void main(String[] args) {
        Box[] arr = new Box[4];
        arr[0] = new Box(4, 6, 7);
        arr[1] = new Box(1, 2, 3);
        arr[2] = new Box(4, 5, 6);
        arr[3] = new Box(10, 12, 32);

        System.out.println("The maximum possible height of stack is " + maxStackHeight(arr, 4));
    }
}

//[
//        Box{hei=10, len=32, bre=12},
//        Box{hei=12, len=32, bre=10},
//        Box{hei=32, len=12, bre=10},
//        Box{hei=4, len=7, bre=6},
//        Box{hei=4, len=6, bre=5},
//        Box{hei=6, len=7, bre=4},
//        Box{hei=7, len=6, bre=4},
//        Box{hei=5, len=6, bre=4},
//        Box{hei=6, len=5, bre=4},
//        Box{hei=1, len=3, bre=2},
//        Box{hei=2, len=3, bre=1},
//        Box{hei=3, len=2, bre=1}
//        ]

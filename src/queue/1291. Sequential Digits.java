class Solution {
    public List<Integer> sequentialDigits(int low, int high) {
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 1; i < 10; i++) {
            queue.add(i);
        }

        List<Integer> res = new ArrayList<>();
        while (!queue.isEmpty()) {
            int last = queue.remove();
            if (last > high) {
                continue;
            }

            if (last >= low && last <= high) {
                res.add(last);
            }
            int ones = last % 10;
            if (ones < 9) {
                queue.add(last * 10 + (ones + 1));
            }
        }

        return res;
    }
}

class Solution {
    public List<Integer> sequentialDigits(int low, int high) {
        List<Integer> res = new ArrayList<>();
        for (int i = String.valueOf(low).length(); i <= String.valueOf(high).length(); i++) {
            List<Integer> digs = getForLen(i);
            for (int num : digs) {
                if (num >= low && num <= high) {
                    res.add(num);
                }
            }
        }

        return res;
    }

    private List<Integer> getForLen(int n) {
        List<Integer> res = new ArrayList<>();
        for (int i = 1; i <= 10 - n; i++) {
            int num = 0;
            for (int j = i; j < i + n; j++) {
                num = num * 10 + j;
            }
            res.add(num);
        }

        return res;
    }
}
// sort keeping track of indexes
// re-arrange and count the swaps till everything is sorted back
// use while loop to count swaps if there are duplicates
class Solution {
    public int minSwaps(int[] nums) {
        int n = nums.length;
        List<Pair> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            list.add(new Pair(digitSum(nums[i]), nums[i], i));
        }
        list.sort((a, b) -> {
            if (a.digitSum == b.digitSum) return Integer.compare(a.val, b.val);
            return Integer.compare(a.digitSum, b.digitSum);
        });

        int cnt = 0;
        for (int i = 0; i < n; i++) {
            if (list.get(i).idx != i) {
                cnt++;
                swap(list, i, list.get(i).idx);
                i--;
            }
        }

        return cnt;
    }

    public int digitSum(int num) {
        int res = 0;
        while (num > 0) {
            res += num % 10;
            num = num / 10;
        }

        return res;
    }

    private void swap(List<Pair> list, int i, int j) {
        Pair temp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, temp);
    }
}

class Pair {
    int digitSum, val, idx;

    public Pair(int digitSum, int val, int idx) {
        this.digitSum = digitSum;
        this.val = val;
        this.idx = idx;
    }
}

// if there are duplicates
class Solution {
    public int minSwaps(int[] nums) {
        int n = nums.length;
        List<Pair> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            list.add(new Pair(digitSum(nums[i]), nums[i], i));
        }
        list.sort((a, b) -> {
            if (a.digitSum == b.digitSum) return Integer.compare(a.val, b.val);
            return Integer.compare(a.digitSum, b.digitSum);
        });

        boolean[] visited = new boolean[n];
        int swaps = 0;

        for (int i = 0; i < n; i++) {
            if (visited[i] || list.get(i).idx == i)
                continue;

            int cycleSize = 0;
            int j = i;

            while (!visited[j]) {
                visited[j] = true;
                j = list.get(j).idx;
                cycleSize++;
            }

            if (cycleSize > 1) {
                swaps += (cycleSize - 1);
            }
        }

        return swaps;
    }

    public int digitSum(int num) {
        int res = 0;
        while (num > 0) {
            res += num % 10;
            num = num / 10;
        }

        return res;
    }

    private void swap(List<Pair> list, int i, int j) {
        Pair temp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, temp);
    }
}

class Pair {
    int digitSum, val, idx;

    public Pair(int digitSum, int val, int idx) {
        this.digitSum = digitSum;
        this.val = val;
        this.idx = idx;
    }
}
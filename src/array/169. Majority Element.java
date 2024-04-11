// SC : O(1) - Moore vooting
class Solution {
    public int majorityElement(int[] nums) {
        int candidate = 0, cnt = 0;
        for (int num : nums) {
            if (cnt == 0) {
                candidate = num;
            }

            if (num == candidate) {
                cnt++;
            } else {
                cnt--;
            }
        }

        return candidate;
    }
}

// SC : O(n)
class Solution {
    public int majorityElement(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        int res = 0, maxCnt = 0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() > maxCnt) {
                maxCnt = entry.getValue();
                res = entry.getKey();
            }
        }

        return res;
    }
}

class Solution {
    public int majorityElement(int[] nums) {
        Arrays.sort(nums);
        return nums[nums.length / 2];
    }
}
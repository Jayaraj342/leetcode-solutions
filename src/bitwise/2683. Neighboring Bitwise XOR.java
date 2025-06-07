class Solution {
    // derived should have XOR = 0
    // XOR of any pair is same as SUM % 2
    public boolean doesValidArrayExist(int[] derived) {
        return Arrays.stream(derived).sum() % 2 == 0;
    }
}

class Solution {
    public boolean doesValidArrayExist(int[] derived) {
        return isValid(derived, 0) || isValid(derived, 1);
    }

    private boolean isValid(int[] derived, int firstValue) {
        int current = firstValue;

        for (int num : derived) {
            current ^= num;
        }

        return current == firstValue;
    }
}

class Solution {
    public boolean doesValidArrayExist(int[] derived) {
        return dfs(derived, 0, 0, 0) || dfs(derived, 1, 1, 0);
    }

    private boolean dfs(int[] derived, int zeroIdxVal, int idxVal, int idx) {
        if (idx == derived.length - 1) {
            return (zeroIdxVal ^ idxVal) == derived[idx];
        }

        return dfs(derived, zeroIdxVal, idxVal ^ derived[idx], idx + 1);
    }
}

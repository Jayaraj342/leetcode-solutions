//Any number that can be written as a sum of distinct powers of 3 will have only digits 0 or 1 in its base-3 form.
//If any digit is 2, then it means you'd need to reuse a power of 3 (not allowed)
class Solution {
    public boolean checkPowersOfThree(int n) {
        while (n > 0) {
            if (n % 3 == 2) {
                return false; // digit "2" found in base-3
            }
            n /= 3;
        }
        return true;
    }
}

// log3n
class Solution {
    public boolean checkPowersOfThree(int n) {
        // Generate all powers of 3 up to n and store them in a list
        List<Integer> threePowers = new ArrayList<>();
        int power = 1;

        while (power <= n) {
            threePowers.add(power);
            power *= 3;
        }

        // Reverse the list to process the largest power first
        Collections.reverse(threePowers);

        int remainingSum = n;

        // Try subtracting each power of 3 from the remaining sum
        for (int num : threePowers) {
            if (num <= remainingSum) {
                remainingSum -= num;
            }
            if (num <= remainingSum) { // If remainingSum is still greater - its not possible to create sum
                return false;
            }
        }

        // If we reduced the sum to 0, then n can be represented as a sum of distinct powers of 3
        return remainingSum == 0;
    }
}

// TC : O(2^log3N) == n^log32 == n^.63
// log N to base 3 because its length of all 2 exponents i.e time to generate 3' exponents
class Solution {
    public boolean checkPowersOfThree(int n) {
        // Generate all powers of 3 up to n
        List<Integer> threePowers = new ArrayList<>();
        int power = 1;

        while (power <= n) {
            threePowers.add(power);
            power *= 3;
        }

        // Use backtracking to check if n can be represented as a sum of distinct powers of 3
        return canFormSum(threePowers, 0, n);
    }

    private boolean canFormSum(List<Integer> threePowers, int idx, int remainingSum) {
        // If remaining sum is zero, we found a valid combination
        if (remainingSum == 0) {
            return true;
        }

        // If we have exhausted all powers or remaining sum becomes negative, return false
        if (idx >= threePowers.size() || remainingSum < 0) {
            return false;
        }

        // Try including the current power of 3 and excluding it
        return canFormSum(threePowers, idx + 1, remainingSum - threePowers.get(idx)) ||
                canFormSum(threePowers, idx + 1, remainingSum);
    }
}
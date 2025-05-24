class Solution {
    // note : Given are distinct positive integers
    public int tupleSameProduct(int[] nums) {
        // Map to store product frequency
        Map<Integer, Integer> productCount = new HashMap<>();
        int result = 0;

        // Iterate through all unique pairs (i, j)
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                int product = nums[i] * nums[j];

                // If the product is already seen, count the possible tuples
                if (productCount.containsKey(product)) {
                    result += productCount.get(product) * 8; // Each pair contributes to 8 valid tuples
                }

                // Update the frequency of the product
                productCount.put(product, productCount.getOrDefault(product, 0) + 1);
            }
        }

        return result;
    }
}
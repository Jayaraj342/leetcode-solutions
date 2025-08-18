class Solution {
    public String fractionToDecimal(int numerator, int denominator) {
        if (numerator == 0) return "0";

        StringBuilder res = new StringBuilder();

        // Append sign if needed
        if ((numerator < 0) ^ (denominator < 0)) res.append("-");

        long num = Math.abs((long) numerator);
        long den = Math.abs((long) denominator);

        // Append integer part
        res.append(num / den);
        num %= den;
        if (num == 0) return res.toString();

        res.append(".");
        Map<Long, Integer> seen = new HashMap<>();

        while (num != 0) {
            if (seen.containsKey(num)) {
                res.insert(seen.get(num), "(");
                res.append(")");
                break;
            }
            seen.put(num, res.length());
            num *= 10;
            res.append(num / den);
            num %= den;
        }

        return res.toString();
    }
}

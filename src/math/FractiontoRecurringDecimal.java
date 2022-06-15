class Solution {
    public String fractionToDecimal(int numerator, int denominator) {
        StringBuilder res = new StringBuilder();
        if ((numerator < 0 && denominator > 0) || (numerator > 0 && denominator < 0)) {
            res.append("-");
        }
        long numeratorL = Math.abs((long) numerator);
        long denominatorL = Math.abs((long) denominator);

        numeratorL = Math.abs(numeratorL);
        denominatorL = Math.abs(denominatorL);
        res.append(numeratorL / denominatorL);
        numeratorL %= denominatorL;

        if (numeratorL == 0) {
            return res.toString();
        }
        res.append(".");

        Map<Long, Integer> repeated = new HashMap<>();
        int index = 0;
        while (numeratorL != 0) {
            numeratorL *= 10;
            if (repeated.containsKey(numeratorL)) {
                int indexToRepeat = 1 + repeated.get(numeratorL) + res.indexOf(".");
                res.insert(indexToRepeat, "(");
                res.append(")");
                break;
            } else {
                repeated.put(numeratorL, index++);
                res.append(numeratorL / denominatorL);
                numeratorL %= denominatorL;
            }
        }

        return res.toString();
    }
}
class Solution {
    public int minimizeXor(int num1, int num2) {
        int setBits1 = Integer.bitCount(num1);
        int setBits2 = Integer.bitCount(num2);

        // Adjust the number of set bits in num1 to match num2 by either adding or removing bits optimally
        // if bits are same, then best case i.e XOR = 0
        if (setBits1 == setBits2) {
            return num1;
        }

        // Removes extra set bits by unsetting the least significant set bits
        if (setBits1 > setBits2) {
            return removeSetBits(num1, setBits1 - setBits2);
        } else { // Adds the required set bits by setting the least significant unset bits
            return addSetBits(num1, setBits2 - setBits1);
        }
    }

    private int removeSetBits(int num, int n) {
        for (int i = 0; i < 32 && n > 0; i++) {
            if ((num >> i & 1) == 1) {
                num = num & ~(1 << i);
                n--;
            }
        }

        return num;
    }

    private int addSetBits(int num, int n) {
        for (int i = 0; i < 32 && n > 0; i++) {
            if ((num >> i & 1) == 0) {
                num = num | (1 << i);
                n--;
            }
        }

        return num;
    }
}
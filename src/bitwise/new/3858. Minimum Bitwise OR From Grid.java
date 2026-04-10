// https://leetcode.com/problems/minimum-bitwise-or-from-grid/
// https://github.com/Jayaraj342/leetcode-solutions/blob/master/src/bitwise/new/3858. Minimum Bitwise OR From Grid.java

class Solution {
    public int minimumOR(int[][] grid) {
        int res = 0;
        // From MSB, check if it can be unset
        for (int i = 31; i >= 0; i--) {
            boolean atleastOne = true; // atleast 1 num in each row has zero bit
            for (int[] row : grid) {
                boolean zero = false;
                for (int idx = 0; idx < row.length; idx++) {
                    int num = row[idx];
                    if (((num >> i) & 1) == 0) {
                        zero = true;
                        break;
                    }
                }

                if (!zero) {
                    atleastOne = false;
                    break;
                }
            }

            if (atleastOne) {
                // set bits are not candidates anymore - so make num as 1111...
                for (int[] row : grid) {
                    for (int idx = 0; idx < row.length; idx++) {
                        int num = row[idx];
                        if (((num >> i) & 1) == 1) {
                            row[idx] = -1;
                        }
                    }
                }
            } else {
                res = res | (1 << i);
            }
        }

        return res;
    }
}

class Solution {
    public int minimumOR(int[][] grid) {
        int ans = 0;
        int forbidden = 0; // bits we are trying to keep 0

        for (int bit = 31; bit >= 0; --bit) {
            int testForbidden = forbidden | (1 << bit);

            // forbidden -> all previous larger bits we want to keep 0
            // (1 << bit) -> new bit we're testing

            boolean possible = true;

            for (int[] row : grid) {
                boolean found = false;

                for (int num : row) {
                    // num must not contain any forbidden bit
                    if ((num & testForbidden) == 0) {
                        found = true;
                        break;
                    }
                }

                if (!found) {
                    possible = false;
                    break;
                }
            }

            if (possible) {
                forbidden = testForbidden; // we can keep this bit 0
            } else {
                ans |= (1 << bit); // must set this bit
            }
        }

        return ans;
    }
}
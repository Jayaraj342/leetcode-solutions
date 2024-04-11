class Solution {
    public int countStudents(int[] students, int[] sandwiches) {
        int[] cnts = new int[]{0, 0};
        for (int st : students) {
            cnts[st]++;
        }

        int res = students.length;
        for (int sw : sandwiches) {
            if (cnts[sw] > 0) {
                cnts[sw]--;
                res--;
            } else {
                return res;
            }
        }

        return res;
    }
}
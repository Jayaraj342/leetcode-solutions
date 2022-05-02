class RowWithMax1s {
    static int ROW = 4;
    static int COL = 4;

    static int first(int[] arr, int low, int high) {
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (mid == 0 || (arr[mid - 1] == 0 && arr[mid] == 1)) {
                return mid;
            }

            if (arr[mid] == 0 && arr[mid - 1] == 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return -1;
    }

    static int rowWithMax1s(int[][] mat) {
        int max_row_index = -1;

        int index, prev = mat[0].length;
        for (int i = 0; i < ROW; i++) {
            index = first(mat[i], 0, COL - 1);
            if (index != -1 && index < prev) {
                prev = index;
                max_row_index = i;
            }
        }

        return max_row_index;
    }

    public static void main(String[] args) {
        int[][] mat = {
                {0, 0, 0, 1},
                {0, 1, 1, 1},
                {1, 1, 1, 1},
                {0, 0, 0, 0}
        };

        System.out.print("Index of row with maximum 1s is " + rowWithMax1s(mat));
    }
}

class MergeSort {
    private void mergeSort(int[] arr, int low, int high) {
        if (low < high) {
            int mid = low + (high - low) / 2;

            mergeSort(arr, low, mid);
            mergeSort(arr, mid + 1, high);

            merge(arr, low, mid, high);
        }
    }

    private void merge(int[] arr, int low, int mid, int high) {
        int n1 = mid - low + 1;
        int n2 = high - mid;

        int[] temp1 = new int[n1];
        int[] temp2 = new int[n2];

        for (int i = 0; i < n1; i++) {
            temp1[i] = arr[low + i];
        }
        for (int j = 0; j < n2; j++) {
            temp2[j] = arr[mid + j + 1];
        }

        int i = 0, j = 0;
        int k = low;
        while (i < n1 && j < n2) {
            if (temp1[i] <= temp2[j]) {
                arr[k++] = temp1[i++];
            } else {
                arr[k++] = temp2[j++];
            }
        }
        while (i < n1) {
            arr[k++] = temp1[i++];
        }
        while (j < n2) {
            arr[k++] = temp2[j++];
        }
    }

    public static void main(String[] args) {
        int[] arr = {13, 7, 2, 6, 45, 21, 9, 2, 2, 100};
        new MergeSort().mergeSort(arr, 0, arr.length - 1);

        System.out.println(Arrays.toString(arr));
    }
}

// Time comp: O(n.log(n))
// Space comp: O(n)
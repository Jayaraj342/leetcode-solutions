class QuickSort {
    private void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int partition = getPartition(arr, low, high);

            quickSort(arr, low, partition - 1);
            quickSort(arr, partition + 1, high);
        }
    }

    private int getPartition(int[] arr, int low, int high) {
        int pivot = arr[high];

        int i = low;
        for (int j = low; j < high; j++) {
            if (arr[j] < pivot) {
                swap(arr, i++, j);
            }
        }
        swap(arr, i, high);
        return i;
    }

    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        int[] arr = {13, 7, 2, 6, 45, 21, 9, 2, 2, 100};
        new QuickSort().quickSort(arr, 0, arr.length - 1);

        System.out.println(Arrays.toString(arr));
    }
}

// Time comp: O(n.log(n))
// Space comp: O(n.log(n))

// first element as pivot
class QuickSort {
    private void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int partition = getPartition(arr, low, high);

            quickSort(arr, low, partition - 1);
            quickSort(arr, partition + 1, high);
        }
    }

    private int getPartition(int[] arr, int low, int high) {
        int pivot = arr[low];

        int i = high;
        for (int j = high; j > low; j--) {
            if (arr[j] > pivot) {
                swap(arr, i--, j);
            }
        }
        swap(arr, i, low);

        return i;
    }

    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        int[] arr = {13, 7, 2, 6, 45, 21, 9, 2, 2, 100};
        new QuickSort().quickSort(arr, 0, arr.length - 1);

        System.out.println(Arrays.toString(arr));
    }
}
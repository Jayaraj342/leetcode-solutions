class InsertionSort {
    private void insertionSort(int[] arr, int n) {
        for (int i = 1; i < n; i++) {
            int current = arr[i];
            int j = i - 1;
            while (j >= 0 && arr[j] > current) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j+1] = current;
        }
    }

    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        int[] arr = {13, 7, 2, 6, 45, 21, 9, 2, 2, 100};
        new InsertionSort().insertionSort(arr, arr.length - 1);

        System.out.println(Arrays.toString(arr));
    }
}

//Insertion sort takes maximum time to sort if elements are sorted in reverse order. And it takes minimum time (Order of n) when elements are already sorted
class HeapSort {
    private void heapSort(int[] arr, int n) {
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(arr, n, i);
        }

        for (int i = n - 1; i > 0; i--) {
            swap(arr, i, 0);
            heapify(arr, i, 0);
        }
    }

    private void heapify(int[] arr, int n, int i) {
        int largestIndex = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        if (left < n && arr[left] > arr[largestIndex]) {
            largestIndex = left;
        }
        if (right < n && arr[right] > arr[largestIndex]) {
            largestIndex = right;
        }

        if (largestIndex != i) {
            swap(arr, largestIndex, i);
            heapify(arr, n, largestIndex);
        }
    }

    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        int[] arr = {13, 7, 2, 6, 45, 21, 9, 2, 2, 100};
        new HeapSort().heapSort(arr, arr.length);

        System.out.println(Arrays.toString(arr));
    }
}

//Time complexity of heapify is O(Logn). Overall time complexity of Heap Sort is O(nLogn)
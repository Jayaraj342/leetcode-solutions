class BubbleSort {
    private void bubbleSort(int[] arr, int n) {
        //boolean swapped;
        for (int i = 0; i < n; i++) {
            //swapped = false;
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    swap(arr, j, j + 1);
                    //swapped = true;
                }
            }
//            if (!swapped) {
//                break;
//            }
        }
    }

    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        int[] arr = {13, 7, 2, 6, 45, 21, 9, 2, 2, 100};
        new BubbleSort().bubbleSort(arr, arr.length - 1);

        System.out.println(Arrays.toString(arr));
    }
}

//Worst and Average Case Time Complexity: O(n*n). Worst case occurs when array is reverse sorted.
//Best Case Time Complexity: O(n). Best case occurs when array is already sorted.
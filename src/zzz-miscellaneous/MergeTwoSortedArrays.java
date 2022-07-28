// https://www.geeksforgeeks.org/merge-two-sorted-arrays-o1-extra-space/

class Test {
    static int arr1[] = new int[]{1, 5, 9, 10, 15, 20};
    static int arr2[] = new int[]{2, 3, 8, 13};

    static void merge(int m, int n) {
        for (int i2 = n - 1; i2 >= 0; i2--) {
            int i1, last1 = arr1[m - 1];
            for (i1 = m - 2; i1 >= 0 && arr1[i1] > arr2[i2]; i1--) {
                arr1[i1 + 1] = arr1[i1];
            }

            if (last1 > arr2[i2]) {
                arr1[i1 + 1] = arr2[i2];
                arr2[i2] = last1;
            }
        }
    }

    public static void main(String[] args) {
        merge(arr1.length, arr2.length);
        System.out.print("After Merging nFirst Array: ");
        System.out.println(Arrays.toString(arr1));
        System.out.print("Second Array: ");
        System.out.println(Arrays.toString(arr2));
    }
}
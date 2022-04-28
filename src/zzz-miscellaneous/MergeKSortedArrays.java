class MergeKSortedArrays {

    static class Node {
        int[] arr;
        int value;
        int index;

        Node(int[] arr, int value, int index) {
            this.arr = arr;
            this.value = value;
            this.index = index;
        }
    }

    static void printArray(int[] arr, int size) {
        for (int i = 0; i < size; i++)
            System.out.print(arr[i] + " ");
    }

    public static void main(String[] args) {
        int[][] arr = {
                {2, 6, 12, 34},
                {1, 9, 20, 1000},
                {23, 34, 90, 2000}
        };
        int k = arr.length;
        int n = 4;
        int[] output = new int[n * k];

        PriorityQueue<Node> minHeap = new PriorityQueue<>((a, b) -> a.value - b.value);
        for (int[] row : arr) {
            minHeap.add(new Node(row, row[0], 0));
        }

        int i = 0;
        while (!minHeap.isEmpty()) {
            Node current = minHeap.remove();
            output[i++] = current.value;

            if (current.index < current.arr.length - 1) {
                minHeap.add(new Node(current.arr, current.arr[current.index + 1], current.index + 1));
            }
        }

        System.out.print("Merged array is \n");
        printArray(output, n * k);
    }
}
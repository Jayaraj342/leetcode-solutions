class MyCircularQueue {
    int[] arr;
    int front = 0, rear = -1;
    int maxSize, size = 0;

    public MyCircularQueue(int k) {
        arr = new int[k];
        maxSize = k;
    }

    public boolean enQueue(int value) {
        if (isFull()) {
            return false;
        }
        rear = ++rear % maxSize;
        arr[rear] = value;
        size++;

        return true;
    }

    public boolean deQueue() {
        if (isEmpty()) {
            return false;
        }
        front = ++front % maxSize;
        size--;

        return true;
    }

    public int Front() {
        return isEmpty() ? -1 : arr[front];
    }

    public int Rear() {
        return isEmpty() ? -1 : arr[rear];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == maxSize;
    }
}

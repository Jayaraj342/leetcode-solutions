import java.util.Arrays;
import java.util.EmptyStackException;

class MyQueue<T> {
    private static final int DEFAULT_SIZE = 10;
    private static final double THRESHOLD = 0.7;
    private Object[] elementData;
    private int rear = 0;
    private int front = 0;

    public MyQueue() {
        elementData = new Object[DEFAULT_SIZE];
    }

    public MyQueue(int initialSize) {
        if (initialSize > DEFAULT_SIZE) {
            elementData = new Object[initialSize];
        } else {
            elementData = new Object[DEFAULT_SIZE];
        }
    }

    public void add(T element) {
        ensureCapacity();
        elementData[rear++] = element;
    }

    public void poll() {
        if (isNonEmpty()) {
            elementData[front++] = null;
        } else {
            throw new EmptyStackException();
        }
    }

    @SuppressWarnings("unchecked")
    public T peek() {
        if (isNonEmpty()) {
            return (T) elementData[front];
        }
        return null;
    }

    public int size() {
        return rear - front;
    }

    private void ensureCapacity() {
        // i.e. initially for elementData.length = 10; if array occupancy (size) reaches >7 then increase elementData size by 10 + 10/2
        int oldCapacity = elementData.length;
        int size = rear;
        if (size >= THRESHOLD * oldCapacity) {
            elementData = Arrays.copyOf(elementData, oldCapacity + (oldCapacity >> 1));
        }
    }

    private boolean isNonEmpty() {
        return size() != 0;
    }

    public void print() {
        System.out.println("------ top");
        for (int i = rear - 1; i >= front; i--) {
            System.out.println(elementData[i]);
        }
        System.out.println("------ bottom");
    }
}

class Main {
    public static void main(String[] args) {
        MyQueue<String> myQueue = new MyQueue<>();
        for (int i = 1; i <= 5; i++) {
            myQueue.add(String.valueOf(i));
        }
        myQueue.print();
        myQueue.poll();
        myQueue.print();
    }
}
import java.util.Arrays;
import java.util.EmptyStackException;

class MyStack<T> {
    private static final int DEFAULT_SIZE = 10;
    private static final double THRESHOLD = 0.7;
    private Object[] elementData;
    private int size = 0;

    public MyStack() {
        elementData = new Object[DEFAULT_SIZE];
    }

    public MyStack(int initialSize) {
        if (initialSize > DEFAULT_SIZE) {
            elementData = new Object[initialSize];
        } else {
            elementData = new Object[DEFAULT_SIZE];
        }
    }

    public void push(T element) {
        ensureCapacity();
        elementData[size++] = element;
    }

    public void pop() {
        if (isNonEmpty()) {
            elementData[--size] = null;
        } else {
            throw new EmptyStackException();
        }
    }

    @SuppressWarnings("unchecked")
    public T peek() {
        if (isNonEmpty()) {
            return (T) elementData[size - 1];
        }
        throw new EmptyStackException();
    }

    public int size() {
        return size;
    }

    private void ensureCapacity() {
        // i.e. initially for elementData.length = 10; if array occupancy (size) reaches >7 then increase elementData size by 10 + 10/2
        int oldCapacity = elementData.length;
        if (size >= THRESHOLD * oldCapacity) {
            elementData = Arrays.copyOf(elementData, oldCapacity + (oldCapacity >> 1));
        }
    }

    private boolean isNonEmpty() {
        return size != 0;
    }
}

class Main {
    public static void main(String[] args) {
        MyStack<String> myStack = new MyStack<>();
        for (int i = 1; i <= 31; i++) {
            myStack.push(String.valueOf(i));
        }

        System.out.println(myStack.size());

        System.out.println(myStack.peek());

        myStack.pop();

        System.out.println(myStack.size());

        System.out.println(myStack.peek());
    }
}
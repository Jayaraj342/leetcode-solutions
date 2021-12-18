import java.util.Arrays;

class MyArrayList<T> {
    private static final int DEFAULT_SIZE = 10;
    private static final double THRESHOLD = 0.7;
    private Object[] elementData;
    private int size = 0;

    public MyArrayList() {
        elementData = new Object[DEFAULT_SIZE];
    }

    public MyArrayList(int initialSize) {
        if(initialSize > DEFAULT_SIZE) {
            elementData = new Object[initialSize];
        } else {
            elementData = new Object[DEFAULT_SIZE];
        }
    }

    public boolean add(T element) {
        ensureCapacity();
        elementData[size++] = element;
        return true;
    }

    public void remove(int index) {
        if (arrayInBounds(index)) {
            System.arraycopy(elementData, index + 1, elementData, index, size - index - 1);
            size--;
        } else {
            throw new ArrayIndexOutOfBoundsException(index);
        }
    }

    public T get(int i) {
        if (arrayInBounds(i)) {
            return (T) elementData[i];
        }
        throw new ArrayIndexOutOfBoundsException(i);
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

    private boolean arrayInBounds(int index) {
        return index >= 0 && index < size;
    }
}

class Main {
    public static void main(String[] args) {
        MyArrayList<String> myArrayList = new MyArrayList<>(1);
        for (int i = 0; i < 30; i++) {
            myArrayList.add(String.valueOf(i));
        }

        myArrayList.remove(10);

        System.out.println(myArrayList.size());

        myArrayList.get(1);
    }
}
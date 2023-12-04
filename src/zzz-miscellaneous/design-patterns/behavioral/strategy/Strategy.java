// https://www.baeldung.com/java-strategy-pattern
// Essentially, the strategy pattern allows us to change the behavior of an algorithm at runtime

// we basically decouple the strategies and let the client pass it, calling obj decides what to call during runtime
import java.util.*;

class Solution {
    public static void main(String[] args) {
        List<Integer> list = List.of(1, 2, 3, 4, 5);
        System.out.println("sum nums : " + sumNumbers(list, ignore -> true));
        System.out.println("sum even nums : " + sumNumbers(list, num -> num % 2 == 0));
    }

    private static int sumNumbers(List<Integer> list, Predicate<Integer> strategy) {
        return list.stream().filter(strategy).mapToInt(e -> e).sum();
    }
}

// so we neither implement 2 logics in same class & increase complexity, nor just 2 classes implement logic
// If we create 2 classes that implement their required functionality, there might be a case where 2 classes need same functionality
// when child object is created, it just passes the strategy that it wants to use through parent constructor
// https://www.youtube.com/watch?v=u8DttUrXtEw&list=PL6W8uoQQ2c61X_9e6Net0WdYZidm7zooW&index=4&pp=iAQB

// ----------------------------------------------------------------------------------------

interface IList {
    void sort();

    void print();
}

class ArrayListImpl implements IList {

    @Override
    public void sort() {
        // Quick sort
    }

    @Override
    public void print() {
        // Print
    }
}

class LinkedListImpl implements IList {

    @Override
    public void sort() {
        // Insertion sort
    }

    @Override
    public void print() {
        // Print
    }
}

class ConcurrentListImpl implements IList {

    @Override
    public void sort() {
        // Quick sort
    }

    @Override
    public void print() {
        // Print
    }
}
// here quick sort is duplicated - more the types of list, more will this happen

// so, ----------------------------------------------------------------------------

interface ISort {
    void sort();
}

class QuickSort implements ISort {
    @Override
    public void sort() {
        // quick sort
    }
}

class InsertionSort implements ISort {
    @Override
    public void sort() {
        // insertion sort
    }
}

interface IPrint {
    void print();
}

class SimplePrint implements IPrint {

    @Override
    public void print() {
        // simple print
    }
}

class Strategy {
    private final ISort sortingAlgo;
    private final IPrint printingAlgo;

    public Strategy(ISort sortingAlgo, IPrint printingAlgo) {
        this.sortingAlgo = sortingAlgo;
        this.printingAlgo = printingAlgo;
    }

    public void sort() {
        sortingAlgo.sort();
    }

    public void print() {
        printingAlgo.print();
    }
}

class ArrayList1 {
    Strategy strategy;

    public ArrayList1() {
        this.strategy = new Strategy(new QuickSort(), new SimplePrint());
    }

    public void sort() {
        strategy.sort();
    }

    public void print() {
        strategy.print();
    }
}

// https://www.youtube.com/watch?v=v9ejT8FO-7I&ab_channel=ChristopherOkhravi
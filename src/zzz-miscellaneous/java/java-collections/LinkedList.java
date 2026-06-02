import java.util.LinkedList;
import java.util.ListIterator;

public class Main {
    public static void main(String[] args) {
        LinkedList<String> list = new LinkedList<>();
        list.add("Apple");
        list.add("Banana");
        list.add("Cherry");

        ListIterator<String> it = list.listIterator();

        while (it.hasNext()) {
            String fruit = it.next();
            if (fruit.equals("Banana")) {
                // This adds "Mango" right after "Banana"
                it.add("Mango");
            }
        }

        System.out.println(list); // Output: [Apple, Banana, Mango, Cherry]
    }
}

import java.util.Arrays;

// for deep copy, should create new array in clone method and assign
class CloneObject {
    public static void main(String[] args) {
        var obj1 = new Test(new int[] {1, 2, 3});
        obj1.val = 10;
        var obj2 = obj1.clone();

        obj2.a[0] = 10;
        obj2.val = 100;
        System.out.println(obj1.val);
        System.out.println(Arrays.toString(obj1.getA()));

        HashMap<Integer, String> map = new HashMap<>();
        map.put(1, "one");
        map.put(2, "two");

        var copy = (HashMap<Integer, String>) map.clone();
        copy.put(3, "three");

        System.out.println(map); // old
        System.out.println(copy); // new
    }
}

class Test implements Cloneable {
    int val;
    int[] a;

    public Test(int[] a) {
        this.a = a;
    }

    @Override
    public Test clone() {
        try {
            return (Test) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

    public int[] getA() {
        return a;
    }
}
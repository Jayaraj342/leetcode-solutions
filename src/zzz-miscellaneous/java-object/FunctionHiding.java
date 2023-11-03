class Parent {
    static void show() {
        System.out.println("Parent's show()");
    }
}

class Child extends Parent {
    static void show() {
        System.out.println("Child's show()");
    }
}

class Main {
    public static void main(String[] args) {
        Parent obj1 = new Parent();
        obj1.show();

        Parent obj2 = new Child();
        obj2.show();

        Child obj3 = new Child();
        obj3.show();
    }
}

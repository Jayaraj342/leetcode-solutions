// https://www.java67.com/2015/08/top-10-method-overloading-overriding-interview-questions-answers-java.html

class solution {
    public static void main(String[] args) {
        Parent parent = new Parent();
        parent.show();

        Parent child = new Child();
        child.show();

        parent = child;
        parent.show();
    }
}

class Parent {
    void show() {
        System.out.println("Parent's show()");
    }
}

class Child extends Parent {
    void show() {
        System.out.println("Child's show()");
    }
}

//-----------------------------------------------------------------------------------------------

class Base {
    private void fun() {
        System.out.println("Base fun");
    }
}

class Derived extends Base {
    public void fun() {
        System.out.println("Derived fun");
    }

    // compile error
    public static void main(String[] args) {
        Base obj = new Derived();
        obj.fun();
    }
}

// -----------------------------------------------------------------------------------------------

class Solution implements Parent, Child{
    public static void main(String[] args) {
        new Solution().show();
    }

    @Override
    public int show() {
        System.out.println("Hello!");
        return 1;
    }

    @Override
    public void show() {
        System.out.println("Hello!");
    }
}

interface Parent {
    int show();
}

interface Child {
    void show();
}

// ------------------------------------------------------------------------------------------------
// Covariant overriding

class Parent {
    public Parent getObj() {
        System.out.println("In parent");
        return new Parent();
    }
}

class Child extends Parent {
    // Cannot be void - should have same signature or child class
    public Child getObj() {
        System.out.println("In child");
        return new Child();
    }

    public static void main(String[] args) {
        Parent obj = new Child();
        obj.getObj();
    }
}

// ----------------------------------------------------------------------------------------------------

class Parent {
    public void getObj() throws IOException {
        System.out.println("In parent");
    }
}

class Child extends Parent {
//    public void getObj() throws NullPointerException {
//        System.out.println("In child");
//    }

    // can't throw higher level exceptions
    public void getObj() throws Exception {
        System.out.println("In child");
    }

    public static void main(String[] args) {
        Parent obj = new Child();
        obj.getObj();
    }
}
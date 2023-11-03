class Main {
    public static void main (String[] args) {
        call();
    }

    private static void call() {
        Parent parent = new Parent();
        parent.divide();
    }
}

class Parent {
    void divide() throws IllegalArgumentException {
        throw new ArithmeticException();
    }
}

// ------------------------------------------------------

class CustomException extends Exception {
    public CustomException(String s) {
        super(s);
    }
}

class Main {
    public static void main(String[] args) throws CustomException {
        throw new CustomException("my message");
    }
}
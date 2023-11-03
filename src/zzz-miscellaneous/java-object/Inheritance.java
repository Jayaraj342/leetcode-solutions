interface Parent {
    void show() throws IOException;
}

class Child implements Parent {

    @Override
    public void show() {
        System.out.println("In child class!");
    }

    public static void main(String[] args) {
        new Child().show();
    }
}
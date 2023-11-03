import java.util.Collections;
import java.util.Comparator;
import java.util.List;

class Student implements Comparable<Student> {
    int age;
    String name;

    public Student(int age, String name) {
        this.age = age;
        this.name = name;
    }

    @Override
    public int compareTo(Student obj) {
        return age - obj.age;
    }

    @Override
    public String toString() {
        return "Student{" +
                "age=" + age +
                ", name='" + name + '\'' +
                '}';
    }
}

class NameCompare implements Comparator<Student> {
    public int compare(Student m1, Student m2) {
        return m1.name.compareTo(m2.name);
    }
}

class Main {
    public static void main(String[] args) {
        Student student1 = new Student(2, "me");
        Student student2 = new Student(1, "you");

        List<Student> list = new java.util.ArrayList<>(List.of(student1, student2));
        Collections.sort(list);
        System.out.println(list);

        //list.sort(Comparator.comparing((o1) -> o1.name));
        list.sort(new NameCompare());
        System.out.println(list);
    }
}

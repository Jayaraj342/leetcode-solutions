import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Method;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@interface Todo {
    enum Priority {LOW, MEDIUM, HIGH}

    enum Status {STARTED, NOT_STARTED}

    String author() default "someOne";

    Priority priority() default Priority.LOW;

    Status status() default Status.NOT_STARTED;
}

class Solution {
    public static void main(String[] args) {
        Class solutionClass = Solution.class;
        for(Method method : solutionClass.getMethods()) {
            Todo todoAnnotation = method.getAnnotation(Todo.class);
            if(todoAnnotation != null) {
                System.out.println("Method Name : " + method.getName());
                System.out.println("Author : " + todoAnnotation.author());
                System.out.println("Priority : " + todoAnnotation.priority());
                System.out.println("Status : " + todoAnnotation.status());
            }
        }
    }

    @Todo(priority = Todo.Priority.MEDIUM, author = "Myself", status = Todo.Status.STARTED)
    public void incompleteMethod() {
        System.out.println("Inside method!");
    }
}
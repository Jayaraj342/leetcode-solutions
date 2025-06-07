import java.util.*;

class Solution {
    public static void main(String[] args) {
        System.out.println(getName().orElse("less than 0.5"));
    }

    private static Optional<String> getName() {
        if(Math.random() > 0.5) {
            return Optional.of("greater than 0.5");
        }

        return Optional.empty();
    }
}
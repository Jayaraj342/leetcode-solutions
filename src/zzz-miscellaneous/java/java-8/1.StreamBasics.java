class Solution {
    public static void main(String[] args) {
        Integer[] arr1 = {1, 2, 3};
        Integer[] arr2 = {3, 4, 5};

        System.out.println(Stream.of(arr1, arr2).flatMap(Stream::of).collect(Collectors.toSet()));
    }
}

// IntStream - primitive int
class Solution {
    public static void main(String[] args) {
        Integer[] arr1 = {1, 2, 3};
        Integer[] arr2 = {3, 4, 5};

        Stream<Integer> stream = Stream.of(arr1, arr2).flatMap(Stream::of);
        IntStream intStream = stream.mapToInt(Integer::valueOf);
        int res = intStream.sum();
        System.out.println(res);
    }
}

// IntStream (primitive int) to List
class Solution {
    public static void main(String[] args) {
        Integer[] arr1 = {1, 2, 3};
        Integer[] arr2 = {3, 4, 5};

        var res = Stream.of(arr1, arr2).flatMap(Stream::of).mapToInt(Integer::valueOf).boxed().collect(Collectors.toList());
        System.out.println(res);
    }
}

// Filter even nums
class Solution {
    public static void main(String[] args) {
        Integer[] arr1 = {1, 2, 3};
        Integer[] arr2 = {3, 4, 5};

        Stream<Integer> stream = Stream.of(arr1, arr2).flatMap(Stream::of);
        Stream<Integer> filteredStream = stream.filter(num -> num % 2 == 0);
        var res = filteredStream.toList();
        System.out.println(res);
    }
}

class Solution {
    public static void main(String[] args) {
        List<String> list = List.of("Hello", "Dear");
        Stream<String> stringStream = list.stream().map(String::toUpperCase);
        List<String> res = stringStream.toList();
        System.out.println(res);
    }
}

// First non repeating char
class Solution {
    public static void main(String[] args) {
        String inp = "swiss";

        // Stream.of(inp) - won't give chars. It gives 1 String only

        IntStream intStream = inp.chars();
        Stream<Character> characterStream = intStream.mapToObj(ch -> (char) ch);
        Optional<Character> optional = characterStream
                .filter(ch -> inp.indexOf(ch) == inp.lastIndexOf(ch))
                .findFirst();
        System.out.println(optional.orElse(null));
    }
}

// Count of a char
class Solution {
    public static void main(String[] args) {
        var inp = "swiss";

        // Stream.of(inp) - won't give chars. It gives 1 String only

        IntStream intStream = inp.chars();
        IntStream filteredIntStream = intStream.filter(ch -> ch == 's');
        long cnt = filteredIntStream.count();
        System.out.println(cnt);
    }
}

// Group Employees by Department
class Solution {
    public static void main(String[] args) {
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee("he", "engineering"));
        employees.add(new Employee("she", "engineering"));
        employees.add(new Employee("it", "non-engineering"));

        Map<String, List<Employee>> res = employees.stream().collect(Collectors.groupingBy(employee -> employee.department));
        System.out.println(res);
    }

    static class Employee {
        String name;
        String department;

        public Employee(String name, String department) {
            this.name = name;
            this.department = department;
        }

        @Override
        public String toString() {
            return "[name : " + name + ", department : " + department + "]";
        }
    }
}

// Partitioning (Even vs. Odd) - used when there are only 2 groups (its efficent than grouping. grouping also does the same..)
class Solution {
    public static void main(String[] args) {
        Stream<Integer> stream = Stream.of(1, 2, 3, 4, 5);
        Map<Boolean, List<Integer>> res = stream.collect(Collectors.partitioningBy(num -> num % 2 == 0));
        System.out.println(res);
    }
}

// Department with Highest Employee Count
class Solution {
    public static void main(String[] args) {
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee("he", "engineering"));
        employees.add(new Employee("she", "engineering"));
        employees.add(new Employee("it", "non-engineering"));

        Map<String, Long> countByDepartment = employees.stream().collect(Collectors.groupingBy(employee -> employee.department, Collectors.counting()));
        System.out.println(countByDepartment);

        // Stream of object - always provide a Comparator
        // If Stream of Integer - that's why we always convert to IntStream to do max()
        Stream<Map.Entry<String, Long>> stream = countByDepartment.entrySet().stream();
        Map.Entry<String, Long> max = stream.max(Map.Entry.comparingByValue()).orElse(null);
        System.out.println(max);
    }

    static class Employee {
        String name;
        String department;

        public Employee(String name, String department) {
            this.name = name;
            this.department = department;
        }

        @Override
        public String toString() {
            return "[name : " + name + ", department : " + department + "]";
        }
    }
}


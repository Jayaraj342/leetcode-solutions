class Solution {
    public static void main(String[] args) {
        List<Integer> listFrom0To100 = IntStream.iterate(0, i -> i + 1).limit(101).boxed().collect(Collectors.toList());
        System.out.println(listFrom0To100);
    }
}

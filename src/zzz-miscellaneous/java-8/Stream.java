class Solution {
    public static void main(String[] args) {
        Integer[] arr1 = {1,2,3};
        Integer[] arr2 = {3,4,5};

        System.out.println(Stream.of(arr1, arr2).flatMap(Stream::of).collect(Collectors.toSet()));
    }
}

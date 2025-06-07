//ConcurrentModificationException
class Solution {
    public static void main(String[] args) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(1, 1);
        map.put(2, 2);
        map.put(3, 3);

        for (Integer key : map.keySet()) {
            System.out.println(map.get(key));
            if (key == 2) {
                map.put(4, 4);
            }
        }

        System.out.println(map);
    }
}

class Solution {
    public static void main(String[] args) {
        Map<Integer, Integer> map = new ConcurrentHashMap<>();
        map.put(1, 1);
        map.put(2, 2);
        map.put(3, 3);

        for (Integer key : map.keySet()) {
            System.out.println(map.get(key));
            if (key == 2) {
                map.put(0, 0);
            }
        }

        System.out.println(map);
    }
}

// https://stackoverflow.com/questions/1291836/concurrenthashmap-vs-synchronized-hashmap
// https://crunchify.com/hashmap-vs-concurrenthashmap-vs-synchronizedmap-how-a-hashmap-can-be-synchronized-in-java/#:~:text=You%20should%20use%20ConcurrentHashMap%20when,locking%20at%20the%20object%20level.
// https://stackoverflow.com/questions/43113397/why-need-synchronizedmap-if-there-is-concurrenthashmap
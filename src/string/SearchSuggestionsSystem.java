// n.logn * m
class Solution {
    public List<List<String>> suggestedProducts(String[] products, String searchWord) {
        Arrays.sort(products);

        TreeMap<String, Integer> map = new TreeMap<>();
        for (int i = 0; i < products.length; i++) {
            map.put(products[i], i);
        }

        List<List<String>> res = new ArrayList<>();
        String prefix = "";
        for (char c : searchWord.toCharArray()) {
            prefix += c;

            String start = map.ceilingKey(prefix);
            if (start == null || !start.startsWith(prefix)) {
                break;
            }

            int index = map.get(start);
            List<String> suggestions = new ArrayList<>();

            for (int i = index; i < Math.min(index + 3, products.length); i++) {
                if (!products[i].startsWith(prefix)) {
                    break;
                }
                suggestions.add(products[i]);
            }

            res.add(suggestions);
        }

        while (res.size() < searchWord.length()) {
            res.add(new ArrayList<>());
        }

        return res;
    }
}

// m^2 * n
class Solution {
    public List<List<String>> suggestedProducts(String[] products, String searchWord) {
        int m = searchWord.length();
        int n = products.length;

        PriorityQueue<String> priorityQueue = new PriorityQueue<>(Collections.reverseOrder());

        List<List<String>> result = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            String curr = searchWord.substring(0, i + 1);

            for (String product : products) {
                if (product.startsWith(curr)) {
                    priorityQueue.add(product);
                }
                if (priorityQueue.size() > 3) {
                    priorityQueue.remove();
                }
            }
            LinkedList<String> temp = new LinkedList<>();
            for (int j = 0; j < 3; j++) {
                if (!priorityQueue.isEmpty()) {
                    temp.addFirst(priorityQueue.remove());
                }
            }
            result.add(temp);
            priorityQueue.clear();
        }

        return result;
    }
}
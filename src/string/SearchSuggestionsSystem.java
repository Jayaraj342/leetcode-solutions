class Solution {
    public List<List<String>> suggestedProducts(String[] products, String searchWord) {
        int m = searchWord.length();
        int n = products.length;

        PriorityQueue<String> priorityQueue = new PriorityQueue<>(Collections.reverseOrder());

        //m^2 * n
        List<List<String>> result = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            String curr = searchWord.substring(0, i + 1);

            for (String product : products) {
                if (product.startsWith(curr)) {
                    priorityQueue.add(product);
                }
                if (priorityQueue.size() == 4) {
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
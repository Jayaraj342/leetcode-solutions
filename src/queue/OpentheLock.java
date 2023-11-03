class Solution {
    public int openLock(String[] deadends, String target) {
        Set<String> visited = new HashSet<>(Arrays.asList(deadends));

        Queue<String> queue = new LinkedList<>();
        queue.add("0000");

        int cnt = 0;
        while (!queue.isEmpty()) {
            int lastBatchCount = queue.size();
            for (int i = 0; i < lastBatchCount; i++) {
                String current = queue.poll();
                if(target.equals(current)) {
                    return cnt;
                }
                if (!visited.contains(current)) {
                    visited.add(current);
                    queue.addAll(getNeighbors(current));
                }
            }

            cnt++;
        }

        return -1;
    }

    private List<String> getNeighbors(String curr) {
        List<String> res = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            StringBuilder sb = new StringBuilder(curr);
            int unchanged = curr.charAt(i) - '0';
            int up = (unchanged + 1) % 10;
            int dwn = (unchanged - 1 + 10) % 10;

            sb.setCharAt(i, (char) (up + '0'));
            res.add(sb.toString());

            sb.setCharAt(i, (char) (dwn + '0'));
            res.add(sb.toString());
        }

        return res;
    }
}
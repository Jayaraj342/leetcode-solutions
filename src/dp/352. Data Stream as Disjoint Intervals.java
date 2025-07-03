class SummaryRanges {
    TreeSet<Integer> set;

    public SummaryRanges() {
        set = new TreeSet<>();
    }

    public void addNum(int value) {
        set.add(value);
    }

    public int[][] getIntervals() {
        List<int[]> res = new ArrayList<>();
        for (int val : set) {
            if (!res.isEmpty() && res.get(res.size() - 1)[1] == val - 1) {
                res.get(res.size() - 1)[1] = val;
            } else {
                res.add(new int[]{val, val});
            }
        }

        return res.toArray(new int[0][0]);
    }
}

// will not compile in leetcode - but logic is good
class Interval {
    int start;
    int end;
    Interval(int s, int e) {
        start = s;
        end = e;
    }
}

class SummaryRanges {
    TreeMap<Integer, Interval> tree;

    public SummaryRanges() {
        tree = new TreeMap<>();
    }

    public void addNum(int val) {
        // Find the interval that might contain or touch val
        Integer low = tree.floorKey(val); // Interval that may contain or end before val
        Integer high = tree.ceilingKey(val); // Interval that may start after val

        // Case 1: Already inside an interval
        if (low != null && tree.get(low).end >= val) {
            return;
        }

        boolean mergeLeft = (low != null && tree.get(low).end + 1 == val);
        boolean mergeRight = (high != null && high == val + 1);

        if (mergeLeft && mergeRight) {
            // Merge both intervals
            tree.get(low).end = tree.get(high).end;
            tree.remove(high);
        } else if (mergeLeft) {
            // Merge with left interval
            tree.get(low).end = Math.max(tree.get(low).end, val);
        } else if (mergeRight) {
            // Merge with right interval
            Interval right = tree.get(high);
            tree.remove(high);
            tree.put(val, new Interval(val, right.end));
        } else {
            // Create new interval
            tree.put(val, new Interval(val, val));
        }
    }

    public List<Interval> getIntervals() {
        return new ArrayList<>(tree.values());
    }
}

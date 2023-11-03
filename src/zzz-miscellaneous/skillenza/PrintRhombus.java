import java.util.*;

class solution {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int t = sc.nextInt();
        for (int i = 0; i < t; i++) {
            int h = sc.nextInt() - 1;
            int l = sc.nextInt();
            String s = sc.next();
            List<BiLinkedList> result = printRhombus(s, l, h);
            for (BiLinkedList level : result) {
                int spaces = h - level.max / 2;
                for (int space = 0; space < spaces; space++) {
                    System.out.print(" ");
                }
                System.out.print(level);
                System.out.println();
            }
        }
    }

    private static List<BiLinkedList> printRhombus(String str, int n, int halfHeight) {
        String[] arr = str.split("");

        List<BiLinkedList> res = new ArrayList<>();
        for (int i = 1; i < 2 * halfHeight + 1; i += 2) {
            res.add(new BiLinkedList(true, i));
        }
        res.add(new BiLinkedList(true, 2 * halfHeight + 1));
        for (int i = 2 * halfHeight - 1; i > 0; i -= 2) {
            res.add(new BiLinkedList(false, i));
        }

        int level = halfHeight;
        boolean dirUp = true;
        boolean isDone = false;
        while (true) {
            int i = 0;
            while (i < n) {
                boolean isAdded = false;
                int countItr = 0;
                while (!isAdded) {
                    isAdded = res.get(level).add(arr[i]);
                    if (level == 0) {
                        dirUp = false;
                    }
                    if (level == 2 * halfHeight) {
                        dirUp = true;
                    }
                    if (dirUp) {
                        level--;
                    } else {
                        level++;
                    }
                    countItr++;
                    if (countItr == 2 * halfHeight + 1) {
                        isDone = true;
                        break;
                    }
                }
                i++;
                if (isDone) {
                    break;
                }
            }
            if (isDone) {
                break;
            }
        }

        return res;
    }
}

class BiLinkedList {
    boolean first;
    List<String> listStart;
    List<String> listEnd;
    int max;

    BiLinkedList(boolean first, int max) {
        this.first = first;
        listStart = new LinkedList<>();
        listEnd = new LinkedList<>();
        this.max = max;
    }

    public boolean add(String val) {
        if (listStart.size() + listEnd.size() == max) {
            return false;
        }
        if (first) {
            listStart.add(val);
            first = false;
        } else {
            listEnd.add(0, val);
            first = true;
        }

        return true;
    }

    @Override
    public String toString() {
        StringBuilder combination = new StringBuilder();
        for (String c : listStart) {
            combination.append(c);
        }
        for (String c : listEnd) {
            combination.append(c);
        }
        return combination.toString();
    }
}
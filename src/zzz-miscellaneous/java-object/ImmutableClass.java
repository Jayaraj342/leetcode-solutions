final class FinalClassExample {

    private final int id;
    private final String name;
    private final HashMap<String, String> testMap;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public HashMap<String, String> getTestMap() {
        return (HashMap<String, String>) testMap.clone();
    }

    public FinalClassExample(int i, String n, HashMap<String, String> hm) {
        System.out.println("Performing Deep Copy for Object initialization");
        this.id = i;
        this.name = n;
        HashMap<String, String> tempMap = new HashMap<>();
        String key;
        for (String s : hm.keySet()) {
            key = s;
            tempMap.put(key, hm.get(key));
        }
        this.testMap = tempMap;
    }

    /**
     * public FinalClassExample(int i, String n, HashMap<String,String> hm){
     * System.out.println("Performing Shallow Copy for Object initialization");
     * this.id=i;
     * this.name=n;
     * this.testMap=hm;
     * }
     */

    public static void main(String[] args) {
        HashMap<String, String> map1 = new HashMap<>();
        map1.put("1", "first");
        map1.put("2", "second");

        String str1 = "original";
        int i1 = 10;

        FinalClassExample obj1 = new FinalClassExample(i1, str1, map1);

        //Let's see whether its copy by field or reference
        System.out.println(str1.equals(obj1.getName()));
        System.out.println(map1 == obj1.getTestMap());
        System.out.println(map1.equals(obj1.getTestMap()));
        //print the ce values
        System.out.println("ce id:" + obj1.getId());
        System.out.println("ce name:" + obj1.getName());
        System.out.println("ce testMap:" + obj1.getTestMap());
        //change the local variable values
        i1 = 20;
        str1 = "modified";
        map1.put("3", "third");
        //print the values again
        System.out.println("ce id after local variable change:" + obj1.getId());
        System.out.println("ce name after local variable change:" + obj1.getName());
        System.out.println("ce testMap after local variable change:" + obj1.getTestMap());

        HashMap<String, String> hmTest = obj1.getTestMap();
        hmTest.put("4", "new");

        System.out.println("ce testMap after changing variable from accessor methods:" + obj1.getTestMap());
    }
}
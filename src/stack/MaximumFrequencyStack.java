class FreqStack {
    Map<Integer, Integer> valueToFreqMap;
    Map<Integer, Stack<Integer>> freqToValuesMap;
    int maxFreq;

    public FreqStack() {
        valueToFreqMap = new HashMap<>();
        freqToValuesMap = new HashMap<>();
        maxFreq = 0;
    }

    public void push(int val) {
        int valFreq = valueToFreqMap.getOrDefault(val, 0) + 1;
        valueToFreqMap.put(val, valFreq);
        if (valFreq > maxFreq) {
            maxFreq = valFreq;
            freqToValuesMap.put(maxFreq, new Stack<>());
        }
        freqToValuesMap.get(valFreq).push(val);
    }

    public int pop() {
        int val = freqToValuesMap.get(maxFreq).pop();
        if (freqToValuesMap.get(maxFreq).isEmpty()) {
            maxFreq--;
        }
        valueToFreqMap.put(val, valueToFreqMap.get(val) - 1);
        return val;
    }
}
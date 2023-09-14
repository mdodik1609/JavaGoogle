package org.example.task;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Predictor {
    Map<String, String> predictorMap;
    public void train(List<List<String>> dataSet) {
        Map<String, HashMap<String, Integer>> initMap = new HashMap<>();

        for(List<String> list : dataSet) {
            for(int i = 0; i < list.size() - 1; i++) {
                if(!initMap.containsKey(list.get(i))){
                    initMap.put(list.get(i), new HashMap<>());
                }
                Map<String, Integer> tempMap = initMap.get(list.get(i));
                tempMap.put(list.get(i + 1), tempMap.getOrDefault(list.get(i + 1), 0) + 1);
            }
        }

        predictorMap = new HashMap<>();

        for(Map.Entry<String, HashMap<String, Integer>> entry : initMap.entrySet()) {
            String key = entry.getKey();
            Map<String, Integer> values = entry.getValue();
            Integer maxV = Integer.MIN_VALUE;
            String maxK = "";

            for(Map.Entry<String, Integer> e : values.entrySet()){
                if(e.getValue() > maxV) {
                    maxV = e.getValue();
                    maxK = e.getKey();
                }
            }

            predictorMap.put(key, maxK);
        }
    }

    public String predict(String s) {
        if(s == null || !predictorMap.containsKey(s)) {
            return new String();
        }
        return predictorMap.get(s);
    }
}

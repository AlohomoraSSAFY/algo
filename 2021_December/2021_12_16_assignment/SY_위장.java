package study1219;

import java.util.*;

public class SY_위장 {
    public int solution(String[][] clothes) {
        HashMap<String, Integer> map = new HashMap<>();
        for (int i = 0; i < clothes.length; i++) {
            String kind = clothes[i][1];
            if (map.containsKey(kind)) {
                map.put(kind, map.get(kind) + 1);
            } else {
                map.put(kind, 1);
            }
        }
        
        int answer = 1;
        for (String str : map.keySet()){
            answer *= (map.get(str) + 1);
        }
        return answer - 1;
    }
}

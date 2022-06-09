package study0609;

import java.util.*;

public class SY_보석_쇼핑 {
    public int[] solution(String[] gems) {
        Set<String> set = new HashSet<>();
        for (int i = 0; i < gems.length; i++) {
            set.add(gems[i]);
        }
        
        Queue<String> q = new LinkedList<>();
        Map<String, Integer> map = new HashMap<>();
        int idx = 1;
        int start = 0;
        int distance = Integer.MAX_VALUE;
        for (int i = 0; i < gems.length; i++) {
            String temp = gems[i];
            q.offer(temp);
            map.put(temp, map.getOrDefault(temp, 0) + 1);
            
            while (true) {
                String str = q.peek();
                if (map.getOrDefault(str, 0) > 1) {
                    idx++;
                    q.poll();
                    map.put(str, map.get(str) - 1);
                } else {
                    break;
                }
            }
            
            if (map.size() == set.size() && q.size() < distance) {
                start = idx;
                distance = q.size();
            }
        }
        
        int[] answer = new int[2];
        answer[0] = start;
        answer[1] = start + distance - 1;
        return answer;
    }
}

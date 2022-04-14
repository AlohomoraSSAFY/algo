package study0414;

import java.util.*;

public class SY_1차_캐시 {
    public int solution(int cacheSize, String[] cities) {
        if (cacheSize == 0) return cities.length * 5;
        
        Queue<String> q = new LinkedList<>();
        int answer = 0;
        for (int i = 0; i < cities.length; i++) {
            String city = cities[i].toLowerCase();
            
            if (q.remove(city)) {
                answer++;
            } else {
                answer += 5;
                if (q.size() == cacheSize) q.poll();
            }
            
            q.offer(city);
        }
        
        return answer;
    }
}

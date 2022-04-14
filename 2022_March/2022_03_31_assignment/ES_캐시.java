package net.acmicpc.april.week2;

import java.util.*;

public class ES_캐시 {
    
    public int solution(int cacheSize, String[] cities) {
        if(cacheSize == 0) {
            return 5 * cities.length;
        }
        
        int answer = 0;
        Deque<String> cache = new ArrayDeque<>();
        
        for(int i = 0 ; i < cities.length ; ++i){
            String city = cities[i].toLowerCase();
            
            if(cache.contains(city)){
                cache.remove(city);
                cache.addFirst(city);
                answer += 1;
            
            } else {
                if(cache.size() == cacheSize){
                    cache.pollLast();
                }
                
                cache.addFirst(city);
                answer += 5;
            }
        }
        return answer;
    }
}
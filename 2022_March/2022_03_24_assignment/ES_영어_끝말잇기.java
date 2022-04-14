package net.acmicpc.march.week5;

import java.util.*;

public class ES_영어_끝말잇기 {
    public int[] solution(int n, String[] words) {
        int[] answer = new int[2];
        int gcnt = 1; int pcnt = 0;
        
        String prev = "";
        Map<String, Integer> map = new HashMap<>();
        
        for(int i = 0; i < words.length; i++){
            String now = words[i];
            pcnt++;
            
            if(i > 0){
                char prevChar = prev.charAt(prev.length()-1);
                char nowChar = now.charAt(0);
                if(prevChar != nowChar || map.containsKey(now)){
                    answer[0] = pcnt;
                    answer[1] = gcnt;
                    break;
                }
                
            }
            
            map.put(now, 0);
            prev = now;
            
            if(pcnt == n){
            	pcnt = 0;
                gcnt++;
            }
        }

        return answer;
    }
}
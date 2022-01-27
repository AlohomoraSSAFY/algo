package com.baekjoon.problem48;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class HN_다단계_칫솔_판매 {
	static Map<String, Integer> emap;
    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        emap = new HashMap<>();
        int len = enroll.length;
        int[] parent = new int[len];
        Arrays.fill(parent, -1);
        int[] answer = new int[len];
        for(int l = 0; l < len; l++){
            emap.put(enroll[l], l);
            if(!referral[l].equals("-")){
                parent[l] = emap.get(referral[l]);
            }
        }
        
        len = seller.length;
        for(int l = 0; l< len; l++){
            int cur = emap.get(seller[l]);
            int seed = amount[l]*100;
            while(true){
                if(cur == -1){
                    break;
                }
                if(seed * 0.1 < 1) {
                    answer[cur] += seed;
                    break;
                }
                int none = (int)(seed * 0.1);
                answer[cur] += seed - none;
                seed = none;
                cur = parent[cur];
            }
        }
        
        
        return answer;
    }
}

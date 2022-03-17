package net.acmicpc.march.week3;

import java.util.*;

public class ES_뉴스_클러스터링 {
	
    public int solution(String str1, String str2) {
        int answer = 0;
        str1 = str1.toLowerCase();
        str2 = str2.toLowerCase();
        
        String a, b;
        
        HashMap<String, Integer> map = new HashMap<>();
        int total = 0;
        for(int i = 0 ; i < str1.length()-1; i++){
            if(str1.charAt(i) >= 'a' && str1.charAt(i) <= 'z'
               && str1.charAt(i+1) >= 'a' && str1.charAt(i+1) <= 'z' 
              ){
                String tmp = String.valueOf(str1.charAt(i))+String.valueOf(str1.charAt(i+1));
                map.put(tmp, map.getOrDefault(tmp, 0)+1);
            }
            
        }
        HashMap<String, Integer> bmap = new HashMap<>();
        ArrayList<String> bList = new ArrayList<>();
        for(int i = 0 ; i < str2.length()-1; i++){
            if(str2.charAt(i) >= 'a' && str2.charAt(i) <= 'z'
               && str2.charAt(i+1) >= 'a' && str2.charAt(i+1) <= 'z' 
              ){
                String tmp = String.valueOf(str2.charAt(i))+String.valueOf(str2.charAt(i+1));
                bmap.put(tmp, bmap.getOrDefault(tmp, 0)+1);
            }
        }
        
        int cnt = 0;
        for(String val : map.keySet()){
            if(bmap.containsKey(val)){
                cnt += Math.min(map.get(val), bmap.get(val));
                total += Math.max(map.get(val), bmap.get(val));
                System.out.println(Math.max(map.get(val), bmap.get(val))+val);
            }else{
                total += map.get(val);
                System.out.println(map.get(val)+val);
            }
        }
        
        for(String val : bmap.keySet()){
            if(!map.containsKey(val)){
                System.out.println(bmap.get(val) + val);
                total += bmap.get(val);
            }
        }
        
        if(total == 0){
            return 65536;
        }else{
            answer = (int)(((double)cnt/(double)total) * 65536.0);
        }
        System.out.println(cnt);
        System.out.println(total);
        return answer;
    }
}
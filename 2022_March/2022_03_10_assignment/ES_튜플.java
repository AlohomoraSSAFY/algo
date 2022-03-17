package net.acmicpc.march.week3;

import java.util.*;

public class ES_튜플 {
    public int[] solution(String s) {
        
        HashMap<Integer, String> set = new HashMap<>();
        StringBuilder sb = null;
        for(int i = 1; i < s.length()-1; i++){
            if(s.charAt(i)=='{'){
                sb = new StringBuilder();    
            }else if(s.charAt(i)=='}'){
                String[] tmp = sb.toString().split(",");
                set.put(tmp.length, sb.toString());
            } else{
                sb.append(s.charAt(i));
            }
        }
        
        ArrayList<Integer> list = new ArrayList<>();
        for(int i = 1; i < set.size()+1; i++){
            String[] tmp = set.get(i).split(",");
            for(String a : tmp){
                Integer b = Integer.parseInt(a);
                if(!list.contains(b))    {
                    list.add(b);
                }
            }
            
        }
        
        
        return list.stream().mapToInt(Integer::intValue).toArray();
    }
}
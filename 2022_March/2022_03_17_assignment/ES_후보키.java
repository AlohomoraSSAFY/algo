package net.acmicpc.march.week4;

import java.util.*;

public class ES_후보키 {
    
    public int solution(String[][] relation) {
        int answer = 0;
       
        int R = relation.length;
        int C = relation[0].length;
        
        ArrayList<Integer> list = new ArrayList<>();
        for(int k = 1; k < (1 << C) ; k++){ // 가능한 조합
            // String bit = Integer.toBinaryString(k);
            // System.out.println(bit);
            Set<String> set = new HashSet<>();
            for(int i =0; i < relation.length; i++){
                StringBuilder sb = new StringBuilder();
                for(int j = 0; j < relation[i].length; j++){
                    if( ( k & (1 <<j)) == (1<<j)){
                        sb.append(relation[i][j]);
                    } 
                }
                set.add(sb.toString());
            }
            if(set.size()==R){ // 유일성
                // System.out.println(Integer.toBinaryString(k));                
                boolean flag = false;
                for(Iterator<Integer> iter = list.iterator(); iter.hasNext(); ){
                    Integer comp = iter.next();
                    if( (comp & k) == comp ){
                        flag= true;
                        break;
                    }
                }
                if(!flag){
                    list.add(k);
                }
            }
        }
        
        return list.size();
    }
}
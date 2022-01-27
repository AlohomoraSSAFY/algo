package net.acmicpc.jan.week4;

import java.util.*;

public class ES_문자열_압축 {
    public int solution(String s) {
        int len = s.length();
        int answer = len;
        for(int i = 1; i <= len/2; i++){ // 자를 단위
            int zip = 1;
            String tmp = s.substring(0, i);
            StringBuilder sb = new StringBuilder();
            for(int j = i; j < len+1; j+= i) {
                String next = "";
                if(j+i > len){
                    if(zip ==1){
                        sb.append(tmp);
                    }
                    else {
                        sb.append(zip);
                        sb.append(tmp);
                    }
                    sb.append(s.substring(j, len));
                }else {
                    next= s.substring(j, j+i);
                    if(next.equals(tmp)){
                        zip++;
                    }else{
                        if(zip ==1){
                            sb.append(tmp);
                        }
                        else {
                            sb.append(zip);
                            sb.append(tmp);
                        }
                        tmp = next;
                        zip = 1;
                    }
                }
            }
            System.out.println(sb.toString());
            answer = Math.min(answer, sb.length());
        }
        
        
        return answer;
    }
}
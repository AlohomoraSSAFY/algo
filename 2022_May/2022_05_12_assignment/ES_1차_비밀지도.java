package net.acmicpc.may.week4;

import java.util.*;

public class ES_1차_비밀지도 {

    public String[] solution(int n, int[] arr1, int[] arr2) {
        String[] answer = new String[n];
        for(int i = 0; i < n; i++){
            String num = Integer.toBinaryString(arr1[i] | arr2[i]);
            num = num.replace("1", "#");
            if(num.length() != n){
                num = num.replace("0"," ");
                String tmp = " ".repeat(n - num.length());
                num = tmp + num;
            }else{
                num = num.replace("0"," ");
            }
            
            answer[i] = num;
            System.out.println(answer[i]);
            
        }
        
        return answer;
    }
}
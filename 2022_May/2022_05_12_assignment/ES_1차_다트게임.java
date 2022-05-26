package net.acmicpc.may.week4;

import java.util.*;

public class ES_1차_다트게임 {

    public int solution(String dartResult) {
        int answer = 0;
        int[] round = new int[3];
        int len = dartResult.length();
        int r = -1;
        for(int i = 0; i < dartResult.length(); i++){
            char c = dartResult.charAt(i);
            if( '0' <= c && c <= '9'){
                r++;
                round[r] += Integer.parseInt(String.valueOf(c));
                if(i+1 != len-1 && dartResult.substring(i, i+2).equals("10")){
                    round[r] = 10;
                    i++;
                }
                
            } else if(c=='D') {
                round[r] = round[r] * round[r];
            } else if(c=='T'){
                round[r] = round[r] * round[r] * round[r];
            } else if(c =='*'){
                if(r > 0) {
                    round[r-1] *=2;
                }
                round[r] *= 2;
            } else if(c=='#') {
                round[r] *= -1;
            }
        }
        return round[0]+round[1]+round[2];
    }
}
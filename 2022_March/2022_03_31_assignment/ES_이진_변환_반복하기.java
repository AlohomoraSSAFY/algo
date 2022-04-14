package net.acmicpc.april.week2;

import java.util.*;

public class ES_이진_변환_반복하기 {
    public int[] solution(String s) {
        int[] answer = new int[2];
        
        while(!s.equals("1")){
            answer[0]++;
            String one = s.replace("0", "");
            answer[1] += s.length() - one.length();
            s = Integer.toBinaryString(one.length());
        }
        return answer;
    }
}
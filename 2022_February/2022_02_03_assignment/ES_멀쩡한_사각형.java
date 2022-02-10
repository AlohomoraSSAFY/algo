package net.acmicpc.feb.week2;

import java.util.*;

public class ES_멀쩡한_사각형 {
    private static int gcd(int a, int b){
        while(b!=0){
            int r = a % b;
            a = b;
            b = r;
        }
        return a;
    }
    
    public long solution(int w, int h) {
      
        long answer = 0;
        long r = 0;
        if(w > h)
            r = (long) gcd(w, h);
        else 
            r = (long) gcd(h, w);
      
        answer = (long) w * (long) h - (long) (r * ( w/r + h/r -1));
        return answer;
    }
}


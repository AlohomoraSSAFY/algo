package net.acmicpc.april.week2;

import java.util.*;

public class ES_점프와_순간_이동 {
    public int solution(int n) {
        int ans = 0;
        
        while(n!=0){
            if(n%2==0){
                n /= 2;
            }else{
                n--;
                ans++;
            }
        }

        return ans;
    }
}
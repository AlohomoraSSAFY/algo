package net.acmicpc.jan.week3;

import java.util.*;

public class Solution_단속카메라 {
	
    public int solution(int[][] routes) {
        int answer = 0;
        
        Arrays.sort(routes, (o1, o2) -> o1[1] - o2[1]);
        
        int min = Integer.MIN_VALUE;
        for(int[] r : routes){
            if( min < r[0]){
                min = r[1];
                answer++;
            }
        }
        
        return answer;
    }
}
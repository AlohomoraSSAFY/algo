package study0113;

import java.util.*;

public class SY_징검다리 {
    public int solution(int distance, int[] rocks, int n) {
        Arrays.sort(rocks);
        
        int[] array = new int[rocks.length + 1];
        array[0] = rocks[0];
        for (int i = 1; i < rocks.length; i++) {
            array[i] = rocks[i] - rocks[i - 1];
        }
        array[rocks.length] = distance - rocks[rocks.length - 1];
        
        int left = 1;
        int right = distance;
        int answer = 0;
        while (left <= right) {
            int mid = (left + right) / 2;
            
            int cnt = 0;
            int temp = 0;
            for (int i = 0; i < array.length; i++) {
                if (temp + array[i] < mid) {
                    cnt++;
                    temp += array[i];
                } else {
                    temp = 0;
                }
                
                if (cnt > n) break;
            }
            
            if (cnt > n) {
                right = mid - 1;
            } else {
                answer = mid;
                left = mid + 1;
            }
        }
        
        return answer;
    }
}

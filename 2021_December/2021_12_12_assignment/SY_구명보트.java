package study1216;

import java.util.*;

public class SY_구명보트 {
    public int solution(int[] people, int limit) {
        Arrays.sort(people);
        
        int left = 0;
        int right = people.length - 1;
        int answer = 0;
        while (left <= right) {
            if (people[right--] + people[left] <= limit) {
                left++;
            }
            answer++;
        }
        
        return answer;
    }
}

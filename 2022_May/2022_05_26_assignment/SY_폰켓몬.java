package study0602;

import java.util.*;

public class SY_폰켓몬 {
    public int solution(int[] nums) {
        int len = nums.length / 2;
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            set.add(nums[i]);
        }
        
        int answer = 0;
        if (set.size() > len) {
            answer = len;
        } else {
            answer = set.size();
        }
        return answer;
    }
}

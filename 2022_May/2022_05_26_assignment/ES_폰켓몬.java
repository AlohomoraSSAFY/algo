import java.util.*;

class Solution {
    public int solution(int[] nums) {
        int answer = 0;
        int total = 0;
        HashSet<Integer> set = new HashSet<>();
        for(int i = 0; i < nums.length; i++){
            int val = nums[i];
            if(!set.contains(val)){
                set.add(val);
                answer++;
            }
            
            if(answer == nums.length/2) break;
            
        }        
        return answer;
    }
}
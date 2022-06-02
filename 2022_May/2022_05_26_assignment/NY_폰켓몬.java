import java.util.*;
class Solution {
    public int solution(int[] nums) {
        int answer = nums.length/2;
        HashSet<Integer> hs = new HashSet<Integer>();
        int count =0;
        for(int i=0;i<nums.length;i++){
            if(!hs.contains(nums[i])){
                hs.add(nums[i]);
                count++;
            }
                
        }
        answer = Math.min(count, answer);
        
        return answer;
    }
}
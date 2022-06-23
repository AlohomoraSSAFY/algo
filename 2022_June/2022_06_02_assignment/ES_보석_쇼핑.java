import java.util.*;

class Solution {
    public int[] solution(String[] gems) {
        Set<String> set = new HashSet<>();
        Map<String, Integer> map = new HashMap<>();
        
        int len = gems.length;
        int s = 0, e = 0;
        for(int i = 0; i < len; i++){
            set.add(gems[i]);
        }
        
        int left = 0, right = 0;
        int dist = Integer.MAX_VALUE;
        while(true) {
            
            if(set.size() == map.size()) {
                int cnt = map.get(gems[left])-1;
                map.put(gems[left], cnt);
                // System.out.println(map.size()+" "+cnt);
                if(cnt==0){
                    map.remove(gems[left]);
                }
                
                left++;
                
            } else if (right == len) {
                    break;
                } else {
                    map.put(gems[right], map.getOrDefault(gems[right], 0) + 1);
                    right++;
                }

                if (set.size() == map.size()) {
                    if (right-left < dist){
                        dist= right-left;
                        s = left+1;
                        e = right;
                    }
                }
        }
        
        int[] answer = {s, e};

        return answer;
    }
}
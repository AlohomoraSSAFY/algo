import java.util.*;

class Solution {
    static int[] idx = {781, 156, 31, 6, 1};
    // static int[] idx = {1, 6, 31, 156, 781};
    public int solution(String word) {
        int answer = 0;
        int len = word.length();
        HashMap<Character, Integer> map = new HashMap<>();
        map.put('A', 0);
        map.put('E', 1);
        map.put('I', 2);
        map.put('O', 3);
        map.put('U', 4);
        
        for(int i = 0; i < len; i++){
            int pos = map.get(word.charAt(i));
            answer += idx[i] * pos+1;
        }
        
        return answer;
    }
}
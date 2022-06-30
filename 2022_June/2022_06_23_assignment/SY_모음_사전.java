package study0630;

import java.util.*;

public class SY_모음_사전 {
    public int solution(String word) {
        char[] temp = word.toCharArray();
        int[] cnt = {781, 156, 31, 6, 1};
        Map<Character, Integer> map = new HashMap<>();
        map.put('A', 0);
        map.put('E', 1);
        map.put('I', 2);
        map.put('O', 3);
        map.put('U', 4);

        int answer = 0;
        for (int i = 0; i < temp.length; i++) {
            answer += cnt[i] * map.get(temp[i]) + 1;
        }
        return answer;
    }
}

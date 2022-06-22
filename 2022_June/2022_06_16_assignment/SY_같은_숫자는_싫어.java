package study0623;

import java.util.*;

public class SY_같은_숫자는_싫어 {
    public int[] solution(int []arr) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            if (list.size() != 0 && list.get(list.size() - 1) == arr[i]) continue;
            list.add(arr[i]);
        }
        
        int[] answer = new int[list.size()];
        for (int i = 0; i < answer.length; i++) {
            answer[i] = list.get(i);
        }
        return answer;
    }
}

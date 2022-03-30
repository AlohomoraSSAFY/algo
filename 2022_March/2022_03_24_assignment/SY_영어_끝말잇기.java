package study0331;

import java.util.*;

public class SY_영어_끝말잇기 {
    public int[] solution(int n, String[] words) {
        int[] answer = new int[2];
        Set<String> set = new HashSet<>();
        set.add(words[0]);
        for (int i = 1; i < words.length; i++) {
            String str = words[i];
            if (set.contains(str) || (words[i-1].charAt(words[i-1].length() - 1) != str.charAt(0))) {
                answer[0] = i % n + 1;
                answer[1] = i / n + 1;
                break;
            }
            set.add(str);
        }

        return answer;
    }
}

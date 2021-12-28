package study1230;

import java.util.*;

public class SY_H_Index {
    public int solution(int[] citations) {
        Arrays.sort(citations);
        
        int answer = 0;
        for (int i = citations.length - 1; i >= 0; i--) {
            if (citations.length - i > citations[i]) break;
            answer++;
        }
        
        return answer;
    }
}

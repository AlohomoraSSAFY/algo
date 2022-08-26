package study0830;

import java.util.*;

public class SY_줄_서는_방법 {
    public int[] solution(int n, long k) {
        int[] answer = new int[n];
        List<Integer> list = new ArrayList<>();
        long factorial = 1;
        for (int i = 1; i <= n; i++) {
            list.add(i);
            factorial *= i;
        }
        
        int i = 0;
        k--;
        while (i < n) {
            factorial /= (n - i);
            int no = (int)(k / factorial);
            answer[i++] = list.get(no);
            list.remove(no);
            k %= factorial;
        }
        return answer;
    }
}

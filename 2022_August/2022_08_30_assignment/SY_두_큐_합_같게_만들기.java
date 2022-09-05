package study0906;

import java.util.*;

public class SY_두_큐_합_같게_만들기 {
    public int solution(int[] queue1, int[] queue2) {
        Queue<Integer> q1 = new LinkedList<>();
        Queue<Integer> q2 = new LinkedList<>();
        long sum1 = 0;
        long sum2 = 0;
        for (int i : queue1) {
            q1.add(i);
            sum1 += i;
        }
        for (int i : queue2) {
            q2.add(i);
            sum2 += i;
        }
        
        int idx = 0;
        while (!(sum1 == sum2)) {
            if (sum1 > sum2) {
                int cur = q1.poll();
                q2.offer(cur);
                sum1 -= cur;
                sum2 += cur;
            } else {
                int cur = q2.poll();
                q1.offer(cur);
                sum2 -= cur;
                sum1 += cur;
            }
            
            if (++idx > queue1.length * 3) return -1;
        }
        
        return idx;
    }
}

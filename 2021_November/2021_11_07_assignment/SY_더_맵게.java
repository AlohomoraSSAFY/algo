package study1111;

import java.util.*;

public class SY_더_맵게 {
	public int solution(int[] scoville, int K) {
        int answer = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i = 0; i < scoville.length; i++) {
            pq.offer(scoville[i]);
        }
        
        while (pq.size() >= 2 && pq.peek() < K) {
            answer++;
            int a = pq.poll();
            int b = pq.poll();
            pq.offer(a + b * 2);
        }
        
        if (pq.peek() < K) answer = -1;             
        return answer;
    }
}

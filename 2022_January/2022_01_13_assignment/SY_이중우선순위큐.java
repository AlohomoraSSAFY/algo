package study0120;

import java.util.*;

public class SY_이중우선순위큐 {
    public int[] solution(String[] operations) {
        PriorityQueue<Integer> pq1 = new PriorityQueue<>();
        PriorityQueue<Integer> pq2 = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return Integer.compare(o2, o1);
            }
        });
        
        for (int i = 0; i < operations.length; i++) {
            String str = operations[i];
            String[] temp = str.split(" ");
            if (temp[0].equals("I")) {
                pq1.offer(Integer.parseInt(temp[1]));
                pq2.offer(Integer.parseInt(temp[1]));
            } else {
                if (pq1.isEmpty()) continue;
                
                if (Integer.parseInt(temp[1]) == 1) {
                    int max = pq2.poll();
                    pq1.remove(max);
                } else {
                    int min = pq1.poll();
                    pq2.remove(min);
                }
            }
        }
        
        int[] answer = new int[2];
        if (!pq1.isEmpty()) {
            answer[0] = pq2.peek();
            answer[1] = pq1.peek();
        }
        return answer;
    }
}

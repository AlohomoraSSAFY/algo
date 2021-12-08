package study1209;

import java.util.*;

public class SY_프린터 {
    public int solution(int[] priorities, int location) {
        Queue<int[]> q = new LinkedList<>();
        for (int i = 0; i < priorities.length; i++) {
            int check = 0;
            if (i == location) check = 1;
            q.offer(new int[]{priorities[i], check});
        }
        
        int answer = 0;
        while (!q.isEmpty()) {
            int[] cur = q.peek();
            int value = cur[0];
            int check = cur[1];
            
            boolean flag = false;
            for(int[] arr : q) {
                if (arr[0] > value) {
                    flag = true;
                    break;
                }
            }
            
            if (flag) {
                q.offer(q.poll());
            } else {
                answer++;
                if (check == 1) {
                    break;
                } else {
                    q.poll();
                }
            }
        }

        return answer;
    }
}

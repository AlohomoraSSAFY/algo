package study1223;

import java.util.*;

public class SY_다리를_지나는_트럭 {
	public int solution(int bridge_length, int weight, int[] truck_weights) {
        Queue<Integer> q = new LinkedList<>();
        int idx = 0;
        int cnt = 0;
        int time = 0;
        int sum = 0;
        while (true) {
            time++;
            
            if (q.size() == bridge_length) {
                int temp = q.poll();
                sum -= temp;
                if (temp != 0) cnt++;
            }
            
            if (idx < truck_weights.length && sum + truck_weights[idx] <= weight) {
                sum += truck_weights[idx];
                q.offer(truck_weights[idx++]);
            } else {
                q.offer(0);
            }
            
            if (cnt == truck_weights.length) break;
        }
        
        return time;
    }
}

package study0224;

import java.util.*;

public class SY_1차_셔틀버스 {
    public String solution(int n, int t, int m, String[] timetable) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i = 0; i < timetable.length; i++) {
            String[] sArray = timetable[i].split(":");
            pq.offer(Integer.parseInt(sArray[0]) * 60 + Integer.parseInt(sArray[1]));
        }
        
        List<Integer>[] list = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            list[i] = new ArrayList<>();
        }
        
        int time = 540;
        for (int i = 0; i < n; i++) {
            while (!pq.isEmpty()) {
                int temp = pq.poll();
                
                if (temp > time || list[i].size() >= m) {
                    pq.offer(temp);
                    break;
                }
                
                list[i].add(temp);
            }
            
            time += t;
        }
        
        int result = 0;
        if (list[n-1].size() < m) {
            result = time - t;
        } else {
            result = list[n-1].get(list[n-1].size() - 1) - 1;
        }
        
        String answer = String.format("%02d:%02d", result / 60, result % 60);
        return answer;
    }
}

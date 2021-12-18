package study1219;

import java.util.*;

public class SY_디스크_컨트롤러 {
    
    static class Disk {
        int reqTime;
        int opTime;
        
        public Disk(int reqTime, int opTime) {
            this.reqTime = reqTime;
            this.opTime = opTime;
        }
    }
    
    public int solution(int[][] jobs) {
        int n = jobs.length;
        PriorityQueue<Disk> pq1 = new PriorityQueue<>(new Comparator<Disk>() {
            @Override
            public int compare(Disk d1, Disk d2) {
                return d1.reqTime - d2.reqTime;
            }
        });
        for (int i = 0; i < n; i++) {
            pq1.offer(new Disk(jobs[i][0], jobs[i][1]));
        }
        
        PriorityQueue<Disk> pq2 = new PriorityQueue<>(new Comparator<Disk>() {
            @Override
            public int compare(Disk d1, Disk d2) {
                return d1.opTime - d2.opTime;
            }
        });
        
        int sum = 0;
        int cnt = 0;
        int time = 0;
        while (cnt < n) {
            while (!pq1.isEmpty() && pq1.peek().reqTime <= time) {
                pq2.offer(pq1.poll());
            }
            
            if (!pq2.isEmpty()) {
                Disk temp = pq2.poll();
                sum += (time + temp.opTime - temp.reqTime);
                cnt++;
                time += temp.opTime;
            } else {
                time = pq1.peek().reqTime;
            }
        }
        
        return sum / n;
    }
}

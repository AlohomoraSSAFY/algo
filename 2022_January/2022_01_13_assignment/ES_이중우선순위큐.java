package net.acmicpc.jan.week3;

import java.util.*;

public class Solution_이중우선순위큐 {

	    public int[] solution(String[] operations) {
	        int[] answer = new int[2];
	        PriorityQueue<Integer> pq = new PriorityQueue<>();
	        PriorityQueue<Integer> maxpq = new PriorityQueue<>(Collections.reverseOrder());

	        for (String op : operations) {
	            String[] tmp = op.split(" ");
	            int value = Integer.parseInt(tmp[1]);

	            if(tmp[0].equals("I")){
	                pq.offer(value);
	                maxpq.offer(value);
	            }else{
	                if(pq.size() < 1 ) continue;
	                if(value==-1){
	                    int min = pq.poll();
	                    maxpq.remove(min);
	                }else{
	                    int max = maxpq.poll();
	                    pq.remove(max);
	                }
	            }


	        }
	        
	        if(pq.size() > 0 ) {
	            answer[0] = maxpq.poll();
	            answer[1] = pq.poll();
	        }

	        return answer;
	    }
}


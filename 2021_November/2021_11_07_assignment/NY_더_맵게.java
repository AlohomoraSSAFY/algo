package date1111THU;
import java.util.*;

public class 프로그래머스_더맵게 {

	class Solution {
	    public int solution(int[] scoville, int K) {
	        int answer = 0;
	        
	        PriorityQueue<Integer> pq = new PriorityQueue<>();
	        
	        for(int i : scoville){
	            pq.offer(i);
	        }
	        
	        while(!pq.isEmpty()){
	            if(pq.peek()>K)
	                return answer;
	            if(pq.size()<2)
	                return -1;
	            
	            int a = pq.poll();
	            int b = pq.poll();
	            
	            int c = a + (b*2);
	            
	            answer++;
	            pq.offer(c);
	        }
	        return answer;
	    }
	}
}

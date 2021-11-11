import java.util.*;

class Solution {
    
    static PriorityQueue<Integer> pq = new PriorityQueue<>();
    
    public int solution(int[] scoville, int K) {
         for(int e : scoville){
             pq.offer(e);
         }
        
         int answer = 0;
         while(!pq.isEmpty()){
             int cur = pq.poll();
             if(cur < K){
                 if(pq.size() > 0){
                     int second = pq.poll();
                     pq.offer(cur+second*2);
                     answer++;
                 }else{
                     answer = -1;
                     break;
                 }
             }else{
                 break;
            }
         }
        
        
      
        
        return answer;
    }
}
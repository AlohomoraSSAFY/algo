package date1107SUN;
import java.util.*;

public class 프로그래머스_기능개발 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
     
        int len = progresses.length;
    
        int[] days = new int[len]; 
        Queue<Integer> q = new LinkedList<>();
        
        for(int i=0;i<len;i++){
            int c = (100-progresses[i])/speeds[i];
                if((100-progresses[i])%speeds[i]!=0)
                    c++;
           q.offer(c);
        }
        
        ArrayList<Integer>result = new ArrayList<>();

        int max =0;
        
        while(!q.isEmpty()){
            int c = q.poll();
            int cur = 1;
            while(true){
                if(!q.isEmpty() && q.peek()<=c){
                    q.poll();
                    cur++;
                }
                else{
                    result.add(cur);
                    break;
                }
            } 
        }
       
        int[] answer = new int[result.size()];
        for(int i=0;i<result.size();i++){
            answer[i] = result.get(i);
        }
        
        return answer;
    }
}
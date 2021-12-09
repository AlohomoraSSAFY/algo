package date1209THU;
import java.util.*;

public class 프로그래머스_프린터 {
	   public int solution(int[] priorities, int location) {
	        int len = priorities.length;
	        int orders[] = new int[len];
	        Queue<Integer> list = new LinkedList<>();
	        
	        
	        for(int i=0;i<len;i++){
	            list.offer(i); //인덱스 값
	        }
	        
	        int order =1;
	        while(!list.isEmpty()){
	            int curidx = list.poll();
	            int curp = priorities[curidx];
	            boolean check = false;
	                
	            for(int i=0;i<list.size();i++){
	                int tidx = list.poll();
	                int tp = priorities[tidx];
	                if(tp>curp){
	                    check = true;          
	                }
	                list.offer(tidx);
	            }
	            if(check) //대기목록에 중요도 더 높은 문서 있음
	                list.offer(curidx);
	            else { //그렇지 않으면 인쇄
	                orders[curidx] = order;
	                order++;
	                if(curidx == location)
	                    break;
	            }
	        }
	        
	        
	        return orders[location];
	    }

}
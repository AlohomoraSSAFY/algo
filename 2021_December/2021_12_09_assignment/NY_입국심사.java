package date1211SUN;

public class 프로그래머스_입국심사 {
	    public long solution(int n, int[] times) {
	        long answer = Long.MAX_VALUE;
	        
	        long max =0;
	        
	        for(int i=0;i<times.length;i++){
	            max = Math.max(max,times[i]);
	        }
	        
	        long left = 1;
	        long right = n*max;
	        
	        while(left<=right){
	        
	            long mid = (left+right)/2;
	            long sum =0;
	            for(int i=0;i<times.length;i++){
	                sum+=mid/times[i];
	                if(sum>=n)
	                    break;
	            }
	            if(sum<n){ 
	                left= mid+1;
	            }else{
	                right = mid-1;
	                answer = Math.min(answer, mid);
	            }
	                
	        }
	        
	        return answer;
	    }
	}

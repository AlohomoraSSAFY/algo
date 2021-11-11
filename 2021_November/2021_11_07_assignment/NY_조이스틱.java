package date1111THU;

public class 프로그래머스_조이스틱 {

	class Solution {
	    public int solution(String name) {
	        int answer = 0;
	        int len = name.length();

	        int sum1 =-1;
	        
	        for(int i=0;i<len;i++){
	            int num = Math.min(name.charAt(i) - 'A', 'Z'-name.charAt(i)+1);
	            sum1+=num;
	            sum1++;
	        }

	        int idx = len-1;
	        while(idx>0){
	            if(name.charAt(idx) == 'A'){
	                sum1--;
	                idx--;
	            }else
	                break;
	        }
	        
	        int sum2 = Math.min(name.charAt(0) - 'A', 'Z'-name.charAt(0)+1);
	        for(int i=1;i<len;i++){
	            sum2+=Math.min(name.charAt(i) - 'A', 'Z'-name.charAt(i)+1);
	            sum2++;
	        }
	       idx = 1;
	        while(idx<len){
	            if(name.charAt(idx) == 'A'){
	                sum2--;
	                idx++;
	            }else
	                break;
	        }
	        
	        answer = Math.min(sum1,sum2);
	        return answer;
	    }
	}

}

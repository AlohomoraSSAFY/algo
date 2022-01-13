package date0113THU;

public class 프로그래머스_주식가격 {
	    public int[] solution(int[] prices) {
	        int len = prices.length;
	        int[] answer = new int[len];
	        for(int i=0;i<len;i++){
	            for(int j=i+1;j<len;j++){
	                answer[i]++;
	                if(prices[i]>prices[j])
	                    break;            
	            }
	        }
	        return answer;
	    }
}

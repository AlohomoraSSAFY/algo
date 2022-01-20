package net.acmicpc.jan.week3;

class Solution_카펫 {
    
	public int[] solution(int brown, int yellow) {
    	
        int mul = yellow + brown;
        int sum = brown/2 + 2;
        
        int h =1, w = 0;
        for(h = 1; h < sum/2+1; h++){
            w = sum - h;
            if(w*h==mul){
                break;
            }
        }
        
        int[] answer = new int[2];
        answer[0] = w; answer[1] = h;
        return answer;
    }
}
import java.util.*;

class Solution {
    public int[] solution(int[] lottos, int[] win_nums) {
        int[] answer = new int[2];
        ArrayList<Integer> win = new ArrayList<>();
        
        for(int i = 0 ; i < win_nums.length; i++){
            win.add(win_nums[i]);
        }
        
        int cnt = 0; int zero = 0;
        for(int i = 0 ; i < lottos.length; i++){
            if(win.contains(lottos[i])){
                cnt++;
            }
            if(lottos[i]==0){
                zero++;
            }
        }
        
        // answer[0] = 7 - (cnt+zero);
        switch(cnt+zero){
            case 6 :
                answer[0] = 1;
                break;
            case 5 :
                answer[0] = 2;
                break;
            case 4 :
                answer[0] = 3;
                break;
            case 3 :
                answer[0] = 4;
                break;
            case 2 :
                answer[0] = 5;
                break;
            default :
                answer[0] = 6;
        }
        
        switch(cnt){
            case 6 :
                answer[1] = 1;
                break;
            case 5 :
                answer[1] = 2;
                break;
            case 4 :
                answer[1] = 3;
                break;
            case 3 :
                answer[1] = 4;
                break;
            case 2 :
                answer[1] = 5;
                break;
            default :
                answer[1] = 6;
        }
        
        
        return answer;
    }
}
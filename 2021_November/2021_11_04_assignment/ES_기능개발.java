import java.util.*;

class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        ArrayList<Integer> list = new ArrayList<>();
        
        int len = progresses.length;
        for(int i = 0; i < len; i++){
            int cur = progresses[i];
            int need = (int) Math.ceil((double) (100-progresses[i]) / speeds[i]);
            System.out.println(need);
            int cnt = 1;
            for(int j = i +1; j < len; j++){
                int tmp = (int) Math.ceil((double) (100-progresses[j]) / speeds[j]);
                if(tmp <= need){ // 배포 가능
                    if(j==len-1) i = j;
                    cnt++;
                }else{
                    i = j-1;
                    break;
                }
            }
            list.add(cnt);
        }
        
        int[] answer = new int[list.size()];
        for(int e = 0 ; e < list.size(); e++){
            answer[e] = list.get(e);
        }
        
        return answer;
    }
}
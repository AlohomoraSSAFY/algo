import java.util.*;

public class Solution {
    public int[] solution(int []arr) {
        
        
        ArrayList<Integer> list = new ArrayList<>();
        int prev =-1;
        for (int i=0;i<arr.length;i++) {
           if (arr[i]!=prev){
               prev=arr[i];
               list.add(arr[i]);
           }
            
            
        }               // [실행] 버튼을 누르면 출력 값을 볼 수 있습니다.
    int answer[] = new int[list.size()];
        for(int i=0;i<list.size();i++){
            answer[i] = list.get(i);
            }

        return answer;
    }
}
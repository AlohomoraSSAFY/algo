import java.util.*;

public class Solution {
    public int[] solution(int []arr) {    
        List<Integer> list = new ArrayList<>();
        int last = -1;
        for(int a : arr){
            if(a!=last){
                list.add(a);
                last = a;
            }
        }
        int[] answer = list.stream().mapToInt(Integer::intValue).toArray();
        return answer;
    }
}
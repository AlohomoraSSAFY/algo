import java.util.*;

class Solution {
    public int solution(int left, int right) {
        int answer = 0;
        int rr = right*(right+1)/2;
        int ll = (left-1)*left/2;
        
        int a = (int) Math.sqrt(left);
        double ad = Math.sqrt(left) - a;
        if(ad > 0) {
            a = a + 1;
        }
        
        int b = (int) Math.sqrt(right);
        int sum = 0;
        for(int i = a; i < b+1; i++){
            sum += (i*i);
        }
        answer = rr - ll - 2*(sum);
        
        return answer;
    }
}
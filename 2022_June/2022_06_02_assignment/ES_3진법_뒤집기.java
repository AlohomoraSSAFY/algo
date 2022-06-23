import java.util.*;

class Solution {
    
    public int solution(int n) {
        String con = Integer.toString(n, 3);
        // System.out.println(con);
        int answer = 0;
        for(int i = con.length()-1; i >-1; i--){
            // System.out.println(con.charAt(i)+" "+i+" "+(int)Math.pow(3, i));
            int val = con.charAt(i) -'0';
            // System.out.println(val);
            answer += val*(int)Math.pow(3, i);
        }
        return answer;
    }
}
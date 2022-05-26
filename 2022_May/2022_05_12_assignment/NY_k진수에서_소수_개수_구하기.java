import java.util.*;

class Solution {
    public int solution(int n, int k) {
        int answer = 0;
        String con = Integer.toString(n,k);
        
        StringTokenizer st = new StringTokenizer(con,"0");
        
        while(st.hasMoreTokens()){
            Long cur = Long.parseLong(st.nextToken());
            if(isPrime(cur))
                answer++;
        }
        return answer;
    }
    
    public static boolean isPrime(long n){
        if(n==1)
            return false;
        for(int i=2;i<5000;i++){
            if(n/i != 1 && n%i == 0)
                return  false;
        }
        return true;
    }
}
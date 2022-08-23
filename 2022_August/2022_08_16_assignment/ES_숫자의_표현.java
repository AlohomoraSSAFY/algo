import java.util.*;

class Solution {
    public int solution(int n) {
        int answer = 0;
        HashSet<Integer> set = new HashSet<>();
        set.add(0);
        set.add(1);
        for(int i = 1; i < n+1; i++) {
            int A = i*(i+1) / 2;
            set.add(A);            
        }
        
        for(int j = 1; j < n+1; j++) {
            int B = j*(j+1) / 2 - n;
            if(set.contains(B)) {
                answer++;
            }
        }
        return answer;
    }
}

// a(a+1)/2 - b(b+1)/2 = n
// a(a+1) - b(b+1) = 2*n;
// a(a+1) - 2*n = b(b+1);
    
package study0721;

public class SY_징검다리_건너기 {
    
    int[] stones;
    int k;
    
    public int solution(int[] stones, int k) {
        this.stones = stones;
        this.k = k;
        
        int left = 0;
        int right = 200000000;
        int answer = 0;
        while (left <= right) {
            int mid = (left + right) / 2;
            
            if (check(mid)) {
                answer = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        
        return answer;
    }
    
    private boolean check(int mid) {
        int cnt = 0;
        for (int i = 0; i < stones.length; i++) {
            if (mid > stones[i]) {
                cnt++;
            } else {
                cnt = 0;
            }
            
            if (cnt == k) return false;
        }
        
        return true;
    }
}

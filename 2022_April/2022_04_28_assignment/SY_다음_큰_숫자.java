package study0512;

public class SY_다음_큰_숫자 {
    public int solution(int n) {
        int nCnt = findOne(n++);
        while (true) {
            int newCnt = findOne(n++);
            if (nCnt == newCnt) break;
        } 
        
        return n - 1;
    }
    
    private int findOne(int num) {
        String str = Integer.toBinaryString(num);
        int cnt = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '1') cnt++;
        }
        
        return cnt;
    }
}

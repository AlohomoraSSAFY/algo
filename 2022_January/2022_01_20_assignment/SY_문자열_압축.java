package study0127;

public class SY_문자열_압축 {
    public int solution(String s) {
        if (s.length() == 1) return 1;
        
        int answer = Integer.MAX_VALUE;
        for (int i = 1; i <= s.length() / 2; i++) {
            int sum = 0;
            int cnt = 1;
            int j = 0;
            String temp = s.substring(0, i);
            for (j = i; j <= s.length() - i; j += i) {
                String str = s.substring(j, j + i);
                
                if (str.equals(temp)) {
                    cnt++;
                } else {
                    if (cnt == 1) {
                        sum += i;
                    } else {
                        sum += (i + Integer.toString(cnt).length());
                        cnt = 1;
                    }
                    
                    temp = str;
                }
            }
            
            if (cnt == 1) {
                sum += i;
            } else {
                sum += (i + Integer.toString(cnt).length());
                cnt = 1;
            }
            
            sum += (s.length() - j);
            answer = Math.min(answer, sum);
        }
        
        return answer;
    }
}

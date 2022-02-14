package study0224;

public class SY_124_나라의_숫자 {
    public String solution(int n) {
        String answer = "";
        while (n > 0) {
            int a = n / 3;
            int b = n % 3;
            
            if (b == 0) {
                answer = "4" + answer;
                a--;
            } else if (b == 1) {
                answer = "1" + answer;
            } else if (b == 2) {
                answer = "2" + answer;
            }
            
            n = a;
        }
        return answer;
    }
}

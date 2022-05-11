package study0512;

public class SY_3차_n진수_게임 {
    public String solution(int n, int t, int m, int p) {
        String str = "0";
        int num = 1;
        while (str.length() <= t * m) {
            StringBuilder sb = new StringBuilder();
            int temp = num;
            while (temp > 0) {
                int mod = temp % n;
                temp = temp / n;
                if (mod >= 10) {
                    char c = (char)(mod - 10 + 'A');
                    sb.append(c);
                } else {
                    sb.append(mod);
                }
            }
            
            str += sb.reverse().toString();
            num++;
        }
        
        String answer = "";
        for (int i = p - 1; i < t * m; i += m) {
            answer += str.charAt(i);
        }
        
        return answer;
    }
}

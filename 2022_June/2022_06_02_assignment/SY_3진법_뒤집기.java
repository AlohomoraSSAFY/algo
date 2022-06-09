package study0609;

public class SY_3진법_뒤집기 {
    public int solution(int n) {
        StringBuilder sb = new StringBuilder();
        sb.append(Integer.toString(n, 3));
        int answer = Integer.parseInt(sb.reverse().toString(), 3);
        return answer;
    }
}

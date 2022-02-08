package study0210;

public class SY_멀쩡한_사각형 {
    public long solution(int w, int h) {
        long answer = 0;
        for (int i = 0; i < w; i++) {
            answer += (Long.valueOf(h) * Long.valueOf(i) / Long.valueOf(w));
        }
        answer *= 2;
        return answer;
    }
}

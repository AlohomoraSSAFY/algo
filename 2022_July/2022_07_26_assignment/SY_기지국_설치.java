package study0802;

public class SY_기지국_설치 {
    public int solution(int n, int[] stations, int w) {
        int answer = 0;
        int loc = 1;
        int idx = 0;
        while (loc <= n) {
            if (idx < stations.length && loc >= stations[idx] - w) {
                loc = stations[idx] + w + 1;
                idx++;
            } else {
                answer++;
                loc += (w * 2 + 1);
            }
        }

        return answer;
    }
}

package study0120;

public class SY_카펫 {
    public int[] solution(int brown, int yellow) {
        int total = brown + yellow;
        int[] answer = new int[2];
        for (int i = total / 3; i >= total / i; i--) {
            if (total % i != 0) continue;
            
            int w = i;
            int h = total / i;
            if ((2 * w + 2 * (h - 2)) == brown && ((w - 2) * (h - 2)) == yellow) {
                answer[0] = w;
                answer[1] = h;
                break;
            }
        }
        return answer;
    }
}

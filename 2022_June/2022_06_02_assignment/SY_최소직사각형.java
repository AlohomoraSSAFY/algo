package study0609;

public class SY_최소직사각형 {
    public int solution(int[][] sizes) {
        int w = 0;
        int h = 0;
        for (int i = 0; i < sizes.length; i++) {
            int a = Math.max(sizes[i][0], sizes[i][1]);
            int b = Math.min(sizes[i][0], sizes[i][1]);
            
            w = Math.max(w, a);
            h = Math.max(h, b);
        }
        
        int answer = w * h;
        return answer;
    }
}

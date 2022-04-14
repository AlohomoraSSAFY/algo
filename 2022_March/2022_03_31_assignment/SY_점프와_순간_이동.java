package study0414;

public class SY_점프와_순간_이동 {
    public int solution(int n) {
        int ans = 0;
        while (n > 0) {
            if (n >= 2 && n % 2 == 0) {
                n /= 2;
            } else {
                n -= 1;
                ans++;
            }
        }

        return ans;
    }
}

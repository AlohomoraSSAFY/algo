package study0120;

public class SY_도둑질 {
    public int solution(int[] money) {
        int[] dp = new int[money.length];
        dp[0] = money[0];
        dp[1] = Math.max(money[0], money[1]);
        for (int i = 2; i < money.length - 1; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + money[i]);
        }
        dp[money.length - 1] = dp[money.length - 2];
        
        int[] dp2 = new int[money.length];
        dp2[1] = money[1];
        for (int i = 2; i < money.length; i++) {
            dp2[i] = Math.max(dp2[i - 1], dp2[i - 2] + money[i]);
        }
        
        int answer = Math.max(dp[money.length - 1], dp2[money.length - 1]);
        return answer;
    }
}

package date0120THU;

public class 프로그래머스_도둑질 {
	public int solution(int[] money) {
		int max = 0;
		int n = money.length;
		int dp[] = new int[n];

		dp[0] = money[0];
		dp[1] = Math.max(money[1], money[0]);
		
		for (int i = 2; i < n - 1; i++) {
			dp[i] = Math.max(dp[i - 1], dp[i - 2] + money[i]);
		}

		dp[n - 1] = Math.max(dp[n - 3] - money[0] + money[n - 1], dp[n - 2]);

		max = Math.max(max, dp[n - 1]);

		dp[0] = money[n - 1];
		dp[1] = Math.max(money[n - 1], money[n - 2]);
		
		for (int i = 2; i < n - 1; i++) {
			dp[i] = Math.max(dp[i - 1], dp[i - 2] + money[n - i - 1]);
		}

		dp[n - 1] = Math.max(dp[n - 3] - money[n - 1] + money[0], dp[n - 2]);
		max = Math.max(max, dp[n - 1]);
		return max;
	}
}

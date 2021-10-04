package date1003SUN;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ9465 {
	static int tc, n, answer;
	static int sticker[][];
	static int dp[][];

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		tc = Integer.parseInt(br.readLine());
		for (int t = 0; t < tc; t++) {
			answer = 0;
			n = Integer.parseInt(br.readLine());
			sticker = new int[2][n];
			dp = new int[2][n];

			for (int i = 0; i < 2; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < n; j++) {
					sticker[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			dp[0][0] = sticker[0][0];
			dp[1][0] = sticker[1][0];
			if (n > 1) {
				
			dp[0][1] = Math.max(dp[1][0] + sticker[0][1], dp[0][0]);
			dp[1][1] = Math.max(dp[0][0] + sticker[1][1], dp[1][0]);

			for (int i = 2; i < n; i++) {
				dp[0][i] = Math.max(dp[1][i - 1] + sticker[0][i], dp[0][i - 2] + sticker[0][i]);
				dp[0][i] = Math.max(dp[0][i], dp[0][i - 1]);
				dp[1][i] = Math.max(dp[0][i - 1] + sticker[1][i], dp[1][i - 2] + sticker[1][i]);
				dp[1][i] = Math.max(dp[1][i], dp[1][i - 1]);
			}
			}
			answer = Math.max(dp[0][n - 1], dp[1][n - 1]);
			sb.append(answer + "\n");
		}
		System.out.println(sb.toString());
	}

}

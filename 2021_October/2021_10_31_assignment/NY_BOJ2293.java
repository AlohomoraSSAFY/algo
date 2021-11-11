package date1104THU;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ2293 { // 동전 1
	static int n, k;
	static int dp[][], coin[];

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		dp = new int[n + 1][k + 1];
		coin = new int[n + 1];
		for (int i = 1; i <= n; i++) {
			coin[i] = Integer.parseInt(br.readLine());
		}

		Arrays.sort(coin);

		for (int i = 1; i <= n; i++) {
	        dp[i][0] = 1;
	    }
		
		for (int i = 1; i <= n; i++) {
			int cur = coin[i];
			for (int j = 1; j <= k; j++) {
				if (j >= cur) {
					dp[i][j] = dp[i - 1][j] + dp[i][j - cur];
				} else
					dp[i][j] = dp[i - 1][j];
			}
		}

//		for (int i = 0; i <= n; i++) {
//			for (int j = 0; j <= k; j++) {
//				System.out.print(dp[i][j] + " ");
//			}
//			System.out.println();
//		}
		
		System.out.println(dp[n][k]);
	}

}

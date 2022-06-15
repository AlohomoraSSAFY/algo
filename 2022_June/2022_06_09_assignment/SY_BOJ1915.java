package study0616;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SY_BOJ1915 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int[][] array = new int[n+1][m+1];
		for (int i = 1; i <= n; i++) {
			char[] cArray = br.readLine().toCharArray();
			for (int j = 1; j <= m; j++) {
				array[i][j] = cArray[j-1] - '0';
			}
		}
		
		int max = 0;
		int[][] dp = new int[n+1][m+1];
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= m; j++) {
				if (array[i][j] == 1) {
					dp[i][j] = 1;
					if (array[i-1][j] == 1 && array[i-1][j-1] == 1 && array[i][j-1] == 1) {
						dp[i][j] = (Math.min(Math.min(dp[i-1][j], dp[i-1][j-1]), dp[i][j-1])) + 1;
					}
					
					max = Math.max(max, dp[i][j]);
				}
			}
		}
		
		System.out.println(max * max);
	}

}

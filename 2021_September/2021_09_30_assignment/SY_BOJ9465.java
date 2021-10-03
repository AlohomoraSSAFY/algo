package study1003;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SY_BOJ9465 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for (int tc = 0; tc < T; tc++) {
			int n = Integer.parseInt(br.readLine());
			int[][] array = new int[2][n+1];
			StringTokenizer st;
			for (int i = 0; i < 2; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 1; j < n+1; j++) {
					array[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			int[][] dp = new int[2][n+1];
			dp[0][1] = array[0][1];
			dp[1][1] = array[1][1];
			for (int j = 2; j < n+1; j++) {
				for (int i = 0; i < 2; i++) {
					dp[i][j] = Math.max(Math.max(dp[1-i][j-1], dp[1-i][j-2]), dp[i][j-2]) + array[i][j];
				}
			}
			
			sb.append(Math.max(dp[0][n], dp[1][n]) + "\n");
		}
		
		System.out.println(sb);
	}

}

package study1024;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SY_BOJ2096 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[][] array = new int[N][3];
		StringTokenizer st;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < 3; j++) {
				array[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int[][][] dp = new int[N][3][2];
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 2; j++) {
				dp[0][i][j] = array[0][i];
			}
		}
		
		for (int i = 1; i < N; i++) {
			for (int j = 0; j < 3; j++) {
				if (j == 0) {
					dp[i][j][0] = Math.max(dp[i-1][0][0], dp[i-1][1][0]) + array[i][j];
					dp[i][j][1] = Math.min(dp[i-1][0][1], dp[i-1][1][1]) + array[i][j];
				} else if (j == 1) {
					dp[i][j][0] = Math.max(Math.max(dp[i-1][0][0], dp[i-1][1][0]), dp[i-1][2][0]) + array[i][j];
					dp[i][j][1] = Math.min(Math.min(dp[i-1][0][1], dp[i-1][1][1]), dp[i-1][2][1]) + array[i][j];
				} else {
					dp[i][j][0] = Math.max(dp[i-1][1][0], dp[i-1][2][0]) + array[i][j];
					dp[i][j][1] = Math.min(dp[i-1][1][1], dp[i-1][2][1]) + array[i][j];
				}
			}
		}
		
		int max = Integer.MIN_VALUE;
		int min = Integer.MAX_VALUE;
		for (int i = 0; i < 3; i++) {
			if (dp[N-1][i][0] > max) max = dp[N-1][i][0];
			if (dp[N-1][i][1] < min) min = dp[N-1][i][1];
		}
		
		System.out.println(max + " " + min);
	}

}

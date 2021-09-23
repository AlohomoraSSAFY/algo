package study0923;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SY_BOJ17070 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[][] array = new int[N+1][N+1];
		StringTokenizer st;
		for (int i = 1; i < N+1; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 1; j < N+1; j++) {
				array[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int[][][] dp = new int[N+1][N+1][3];
		dp[1][2][0] = 1;
		for (int i = 1; i < N+1; i++) {
			for (int j = 1; j < N+1; j++) {
				if ((i == 1 && j == 1) || (i == 1 && j == 2)) continue;
				
				if (array[i][j] != 1) {
					dp[i][j][0] = dp[i][j-1][0] + dp[i][j-1][2];
					dp[i][j][1] = dp[i-1][j][1] + dp[i-1][j][2];
				}
				
				if (array[i][j] != 1 && array[i-1][j] != 1 && array[i][j-1] != 1) {
					dp[i][j][2] = dp[i-1][j-1][0] + dp[i-1][j-1][1] + dp[i-1][j-1][2];
				}
			}
		}
		
		System.out.println(dp[N][N][0] + dp[N][N][1] + dp[N][N][2]);
	}

}

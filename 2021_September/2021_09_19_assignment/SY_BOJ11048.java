package study0923;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SY_BOJ11048 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[][] miro = new int[N+1][M+1];
		for (int i = 1; i < N+1; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 1; j < M+1; j++) {
				miro[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int[][] dp = new int[N+1][M+1]; 
		for (int i = 1; i < N+1; i++) {
			for (int j = 1; j < M+1; j++) {
				dp[i][j] = Math.max(dp[i][j-1], Math.max(dp[i-1][j-1], dp[i-1][j])) + miro[i][j];
			}
		}
		
		System.out.println(dp[N][M]);
	}

}

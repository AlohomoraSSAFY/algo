package study0707;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SY_BOJ15724 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[][] array = new int[N+1][M+1];
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 1; j <= M; j++) {
				array[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int[][] dp = new int[N+1][M+1];
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= M; j++) {
				dp[i][j] = dp[i-1][j] + dp[i][j-1] - dp[i-1][j-1] + array[i][j];
			}
		}
		
		int K = Integer.parseInt(br.readLine());
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int x1 = Integer.parseInt(st.nextToken());
			int y1 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());
			
			System.out.println(dp[x2][y2] - dp[x1-1][y2] - dp[x2][y1-1] + dp[x1-1][y1-1]);
		}
	}

}

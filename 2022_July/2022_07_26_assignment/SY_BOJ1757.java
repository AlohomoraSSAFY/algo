package study0802;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SY_BOJ1757 {
	
	static int N, M;
	static int[] array;
	static int[][] dp;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		array = new int[N+1];
		for (int i = 1; i <= N; i++) {
			array[i] = Integer.parseInt(br.readLine());
		}
		
		dp = new int[N+1][M+1];
		for (int i = 1; i <= N; i++) {
			cal(i);
		}
		
		System.out.println(dp[N][0]);
	}
	
	private static void cal(int idx) {
		dp[idx][0] = dp[idx-1][0];
		
		for (int i = 1; i <= M; i++) {
			dp[idx][i] = dp[idx-1][i-1] + array[idx];
		}
		
		for (int i = 1; i <= M && idx > i; i++) {
			if (idx <= i) break;
			dp[idx][0] = Math.max(dp[idx][0], dp[idx-i][i]);
		}
	}

}

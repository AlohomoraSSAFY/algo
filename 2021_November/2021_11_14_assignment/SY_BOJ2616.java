package study1118;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SY_BOJ2616 {
	
	static int N;
	static int M;
	static int[] array;
	static int[] sumArray;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		array = new int[N+1];
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for (int i = 1; i < N+1; i++) {
			array[i] = Integer.parseInt(st.nextToken());
		}
		M = Integer.parseInt(br.readLine());
		
		sumArray = new int[N+1];
		sumArray[1] = array[1];
		for (int i = 2; i < N+1; i++) {
			sumArray[i] = sumArray[i-1] + array[i];
		}
		
		int[][] dp = new int[3][N+1];
		for (int i = M; i < N+1; i++) {
			dp[0][i] = Math.max(sumArray[i] - sumArray[i-M], dp[0][i-1]);
			if (i >= 2 * M) {
				dp[1][i] = Math.max(dp[0][i-M] + sumArray[i] - sumArray[i-M], dp[1][i-1]);
			}
			if (i >= 3 * M) {
				dp[2][i] = Math.max(dp[1][i-M] + sumArray[i] - sumArray[i-M], dp[2][i-1]);
			}
		}
		
		System.out.println(dp[2][N]);
	}

}

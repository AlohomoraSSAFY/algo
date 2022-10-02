package study1004;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SY_BOJ2629 {
	
	static int N;
	static int[] array;
	static boolean[][] dp;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		array = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < N; i++) {
			array[i] = Integer.parseInt(st.nextToken());
		}
		
		dp = new boolean[N+1][15001];
		recursion(0, 0);
		
		int T = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < T; i++) {
			int target = Integer.parseInt(st.nextToken());
			if (target > 15000) {
				System.out.print("N ");
				continue;
			}
			
			if (dp[N][target]) System.out.print("Y ");
			else System.out.print("N ");
		}
	}
	
	private static void recursion(int cnt, int num) {
		if (dp[cnt][num]) return;
		dp[cnt][num] = true;
		
		if (cnt == N) return;
		
		recursion(cnt + 1, num);
		recursion(cnt + 1, num + array[cnt]);
		recursion(cnt + 1, Math.abs(num - array[cnt]));
	}

}

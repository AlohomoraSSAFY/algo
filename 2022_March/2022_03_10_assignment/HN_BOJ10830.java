package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
/*
 * 80% 반례
2 1
1000 1000
1000 1000
0 0
0 0
*/
public class HN_BOJ10830 {
	static int N;
	static long B;
	static int[][] dp, answer;
	static int[][] memo;
	static boolean pass;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		B = Long.parseLong(st.nextToken());

		dp = new int[N][N];
		memo = new int[N][N];
		for (int n = 0; n < N; n++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < N; c++) {
				dp[n][c] = Integer.parseInt(st.nextToken()) % 1000;
			}
			memo[n][n] = 1;
		}

		pass = true;
		cal(B);
		int[][] answer = new int[N][N];
		if (!pass) {
			answer = solve(memo, dp);
		} else {
			answer = copy(answer, dp);
		}
		
		StringBuilder sb = new StringBuilder();
		for (int n = 0; n < N; n++) {
			for (int c = 0; c < N; c++) {
				sb.append(answer[n][c]).append(" ");
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}

	private static void cal(long b) {
		if (b == 1) {
			return;
		}

		if (b % 2 == 1) {
			pass = false;
			memo = solve(memo, dp);
		}
		dp = solve(dp, dp);
		cal(b / 2);
	}

	private static int[][] copy(int temp[][], int[][] dp) {
		for (int n = 0; n < N; n++) {
			temp[n] = dp[n].clone();
		}
		return temp;
	}

	private static int[][] solve(int[][] dp1, int[][] dp2) {
		int temp[][] = new int[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				for (int k = 0; k < N; k++) {
					temp[i][j] += dp1[i][k] * dp2[k][j];
				}
				temp[i][j] %= 1000;
			}
		}
		return temp;
	}

}

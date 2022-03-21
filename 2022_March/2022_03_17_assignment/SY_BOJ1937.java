package study0324;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SY_BOJ1937 {
	
	static int n;
	static int[][] array;
	static int[][] dp;
	static int[][] d = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		array = new int[n][n];
		dp = new int[n][n];
		
		StringTokenizer st;
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < n; j++) {
				array[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int result = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				result = Math.max(result, dfs(i, j));
			}
		}
		
		System.out.println(result);
	}
	
	private static int dfs(int x, int y) {
		if (dp[x][y] != 0) return dp[x][y];
		dp[x][y] = 1;
		
		for (int i = 0; i < 4; i++) {
			int nx = x + d[i][0];
			int ny = y + d[i][1];
			
			if (nx < 0 || nx >= n || ny < 0 || ny >= n) continue;
			if (array[nx][ny] > array[x][y]) {
				dp[x][y] = Math.max(dp[x][y], dfs(nx, ny) + 1);
			}
		}
		
		return dp[x][y];
	}

}

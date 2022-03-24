package date0317;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class BOJ1937 {

	static int n, m;
	static int map[][], dp[][];
	static int max;

	static int dx[] = { 0, 0, -1, 1 };
	static int dy[] = { 1, -1, 0, 0 };

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		map = new int[n][n];
		dp = new int[n][n];

		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		//

		max = 0;

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {

				max = Math.max(max, dfs(i, j));
				;

			}
		}
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
//				System.out.print(dp[i][j] + " ");
				max = Math.max(max, dp[i][j]);
			}
//			System.out.println();
		}

		System.out.println(max);

	}

	public static int dfs(int x, int y) {
		if(dp[x][y] != 0)
			return dp[x][y];
		
		dp[x][y] =1;
		
		for (int d = 0; d < 4; d++) {
			int nx = x + dx[d];
			int ny = y + dy[d];
			if (nx < n && ny < n && nx >= 0 && ny >= 0 && map[nx][ny] > map[x][y]) {
				dp[x][y] = Math.max(dp[x][y], dfs(nx,ny)+1);
				dfs(nx,ny);
			}
		}
	return dp[x][y];
	}
	

}

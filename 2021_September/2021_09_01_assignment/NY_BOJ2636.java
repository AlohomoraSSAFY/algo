import java.io.*;
import java.util.*;

public class BOJ2636 {
	static int n, m;
	static int[][] map;
	static int dx[] = { -1, 1, 0, 0 };
	static int dy[] = { 0, 0, -1, 1 };
	static boolean visited[][];
	static boolean flag;
	static int cheesecount;

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		map = new int[n][m];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		//

		int count = 0;

		while (true) {
			
			boolean end = true;
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < m; j++) {
					if (map[i][j] == 1) {
						end = false;
						break;
					}
				}
			}

			if (end) // 1이 안 남아있으면 나감
				break;

			cheesecount = 0;
			visited = new boolean[n][m];
			dfs(0, 0);
			count++;

		}

		System.out.println(count);
		System.out.println(cheesecount);

	}

	public static void dfs(int x, int y) {

		visited[x][y] = true;

		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];

			if (nx >= 0 && nx < n && ny >= 0 && ny < m && !visited[nx][ny]) {
				if (map[nx][ny] == 0) {
					dfs(nx, ny);
				} else {
					cheesecount++;
					map[nx][ny] = 0;
					visited[nx][ny] = true;
				}
			}
		}

	}
}

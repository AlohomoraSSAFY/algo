package date0602;

import java.io.BufferedReader;
import java.io.*;
import java.util.*;

public class NY_BOJ18430 {
	static int n, m;
	static int map[][];
	static boolean used[][];
	static int max;

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		map = new int[n][m];
		used = new boolean[n][m];

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		//

		dfs(0, 0, 0);
		System.out.println(max);

	}

	public static void dfs(int x, int y, int sum) {
		
		if (y == m) {
			y = 0;
			x++;
		}
		if (x == n) {
			max = Math.max(max, sum);
			return;
		}
		
		
		//네 가지 형태를 모두 확인
		
		if (isInRange(x, y - 1) && isInRange(x + 1, y) && !used[x][y] && !used[x][y - 1] && !used[x + 1][y]) {
			used[x][y] = used[x][y - 1] = used[x + 1][y] = true;
			dfs(x, y + 1, sum + 2 * map[x][y] + map[x][y - 1] + map[x + 1][y]);
			used[x][y] = used[x][y - 1] = used[x + 1][y] = false;
		}

		if (isInRange(x, y - 1) && isInRange(x - 1, y) && !used[x][y] && !used[x][y - 1] && !used[x - 1][y]) {
			used[x][y] = used[x][y - 1] = used[x - 1][y] = true;
			dfs(x, y + 1, sum + 2 * map[x][y] + map[x][y - 1] + map[x - 1][y]);
			used[x][y] = used[x][y - 1] = used[x - 1][y] = false;
		}

		if (isInRange(x - 1, y) && isInRange(x, y + 1) && !used[x][y] && !used[x - 1][y] && !used[x][y + 1]) {
			used[x][y] = used[x - 1][y] = used[x][y + 1] = true;
			dfs(x, y + 1, sum + 2 * map[x][y] + map[x - 1][y] + map[x][y + 1]);
			used[x][y] = used[x - 1][y] = used[x][y + 1] = false;
		}

		if (isInRange(x + 1, y) && isInRange(x, y + 1) && !used[x][y] && !used[x + 1][y] && !used[x][y + 1]) {
			used[x][y] = used[x + 1][y] = used[x][y + 1] = true;
			dfs(x, y + 1, sum + 2 * map[x][y] + map[x + 1][y] + map[x][y + 1]);
			used[x][y] = used[x + 1][y] = used[x][y + 1] = false;
		}

		dfs(x, y + 1, sum);

	}

	static boolean isInRange(int x, int y) {
		return x >= 0 && x < n && y >= 0 && y < m;
	}

}

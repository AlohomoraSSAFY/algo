package date0909;

import java.io.*;
import java.util.*;

class Node {
	int x;
	int y;
	int d;

	public Node(int x, int y, int d) {
		super();
		this.x = x;
		this.y = y;
		this.d = d;
	}
}

public class BOJ1189 {
	static char map[][];
	static boolean visited[][];
	static int result;
	static int dist[];
	static int r, c, k;
	static int[] dx = { 0, 0, -1, 1 };
	static int[] dy = { -1, 1, 0, 0 };

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());

		map = new char[r][c];
		visited = new boolean[r][c];
		dist = new int[r * c + 1];

		for (int i = 0; i < r; i++) {
			String str = br.readLine();
			for (int j = 0; j < c; j++) {
				map[i][j] = str.charAt(j);
			}
		}

		//

		// 한수 위치는 r-1, 0
		// 집은 0, c-1

		result = 0;
		visited[r - 1][0] = true;
		dfs(r - 1, 0, 1);
		System.out.println(result);
	}

	public static void dfs(int x, int y, int dist) {

		if (x == 0 && y == c - 1 && dist == k) {
			result++;
			return;
		}

		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];

			if (nx >= 0 && ny >= 0 && nx < r && ny < c && !visited[nx][ny] && map[nx][ny] == '.') {
				visited[nx][ny] = true;
				dfs(nx, ny, dist + 1);
				visited[nx][ny] = false;

			}
		}

	}

}

package date0707;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ5547 {
	static int w, h, map[][];
	static boolean[][] visited;
	static int[][] odd = { { -1, 0 }, { 0, -1 }, { 1, 0 }, { 1, 1 }, { 0, 1 }, { -1, 1 } };
	static int[][] even = { { -1, -1 }, { 0, -1 }, { 1, -1 }, { 1, 0 }, { 0, 1 }, { -1, 0 } };

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		h = Integer.parseInt(st.nextToken());
		w = Integer.parseInt(st.nextToken());
		map = new int[w+2][h+2];
		visited = new boolean[w+2][h+2];
		for (int i = 1; i <= w; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= h; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		bfs();
	}

	public static void bfs() {
		Queue<int[]> queue = new LinkedList<>();
		queue.offer(new int[] { 0, 0 });
		visited[0][0] = true;
		while (!queue.isEmpty()) {
			int[] temp = queue.poll();
			int nx = temp[0];
			int ny = temp[1];
			for (int i = 0; i < 6; i++) {
				int px;
				int py;
				if (nx % 2 == 0) {
					px = nx + even[i][0];
					py = ny + even[i][1];
				} else {
					px = nx + odd[i][0];
					py = ny + odd[i][1];
				}
				if (px >= 0 && px <= w + 1 && py >= 0 && py <= h + 1) {
					if (!visited[px][py]) {
						if (map[px][py] == 0) {
							visited[px][py] = true;
							queue.offer(new int[] { px, py });
						}
					}
				}
			}
		}
		int sum = 0;
		for (int i = 1; i <= w; i++) {
			for (int j = 1; j <= h; j++) {
				if (map[i][j] == 0)
					continue;
				for (int t = 0; t < 6; t++) {
					int nx;
					int ny;
					if (i % 2 == 0) {
						nx = i + even[t][0];
						ny = j + even[t][1];
					} else {
						nx = i + odd[t][0];
						ny = j + odd[t][1];
					}
					if (visited[nx][ny])
						sum++;
				}
			}
		}
		System.out.println(sum);
	}

}

package date1111THU;

import java.io.BufferedReader;
import java.io.*;
import java.util.*;

public class BOJ2638 {
	static int n, m;
	static int answer;
	static int map[][];
	static boolean visited[][];
	static int dx[] = { -1, 1, 0, 0 };
	static int dy[] = { 0, 0, -1, 1 };

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		map = new int[n][m];

		answer = 0;

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		while (true) {
			
			if (check()) 
				break;			

			answer++;

			visited = new boolean[n][m];
			bfs(0, 0);

			Queue<int[]> melt = new LinkedList<>();

			for (int i = 0; i < n; i++) {
				for (int j = 0; j < m; j++) {
					if (map[i][j] == 1) {
						int count = 0;
						for (int d = 0; d < 4; d++) {
							if (map[i + dx[d]][j + dy[d]] == -1)
								count++;
						}
						if (count >= 2) {
							melt.offer(new int[] { i, j });
						}
					}
				}
			}

			while (!melt.isEmpty()) {
				int temp[] = melt.poll();
				map[temp[0]][temp[1]] = -1;
			}

		}

		System.out.println(answer);
	}

	public static void bfs(int x, int y) { // 외부 공기를 -1 로 표시
		Queue<int[]> q = new LinkedList<>();
		map[x][y] = -1;
		visited[x][y] = true;

		q.offer(new int[] { x, y });

		while (!q.isEmpty()) {

			int temp[] = q.poll();

			for (int d = 0; d < 4; d++) {
				int nx = temp[0] + dx[d];
				int ny = temp[1] + dy[d];

				if (nx >= 0 && ny >= 0 && nx < n && ny < m && !visited[nx][ny] && map[nx][ny] != 1) {
					map[nx][ny] = -1;
					visited[nx][ny] = true;
					q.offer(new int[] { nx, ny });

				}
			}

		}
	}

	public static boolean check() { // 모든 치즈가 녹았는지 체크
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (map[i][j] == 1)
					return false;
			}
		}
		return true;
	}


}

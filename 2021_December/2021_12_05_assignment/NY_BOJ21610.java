package date1209THU;

import java.io.*;
import java.util.*;

public class BOJ21610 {
	static int n, m;
	static int map[][];

	static int dx[] = { 0, 0, -1, -1, -1, 0, 1, 1, 1 };
	static int dy[] = { 0, -1, -1, 0, 1, 1, 1, 0, -1 };

	static Queue<int[]> cloud = new LinkedList<>();

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		map = new int[n][n];

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		cloud.offer(new int[] { n - 1, 0 });
		cloud.offer(new int[] { n - 1, 1 });
		cloud.offer(new int[] { n - 2, 0 });
		cloud.offer(new int[] { n - 2, 1 });

		for (int i = 0; i < m; i++) { // 구름 한 번 이동
			boolean[][] visited = new boolean[n][n];

			st = new StringTokenizer(br.readLine());
			int d = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());

			for (int k = 0; k < cloud.size(); k++) {
				// 1. 구름이 d방향으로 s칸 이동
				int[] temp = cloud.poll();
				int x = (temp[0] + (dx[d] * s) + (n*25)) % n; 
				int y = (temp[1] + (dy[d] * s) + (n*25)) % n;    
				// 2. 해당 칸 물 양 1 증가
				map[x][y]++;
				visited[x][y] = true;

				cloud.offer(new int[] { x, y });
			}
			// 4. 물복사마법
			while (!cloud.isEmpty()) {

				int[] temp = cloud.poll();
				int x = temp[0];
				int y = temp[1];
				int count = 0;
				for (int l = 2; l <= 8; l += 2) {
					int nx = x + dx[l];
					int ny = y + dy[l];

					if (nx >= 0 && ny >= 0 && ny < n && nx < n && map[nx][ny] > 0)
						count++;
				}

				map[x][y] += count;
				// 3. 구름 삭제
			}
			
			makecloud(visited);
		}

		//

		System.out.println(sum());

	}

	public static void makecloud(boolean[][] visited) {
		// 5. 바구니에 저장된 물 양이 2 이상인 칸 2씩 물 제거, 구름 생김
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (map[i][j] >= 2 && !visited[i][j]) {
					map[i][j] -= 2;
					cloud.offer(new int[] { i, j });
				}
			}
		}

	}

	public static int sum() {
		int s = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				s += map[i][j];
			}
		}

		return s;
	}
}

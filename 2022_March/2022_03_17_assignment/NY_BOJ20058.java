package date0317;

import java.io.*;
import java.util.*;

import javax.swing.plaf.synth.SynthSplitPaneUI;

public class BOJ20058 {

	static int n, q, m;
	static int map[][];
	static int[] magic;

	static int dx[] = { 0, 1, 0, -1 }; // 우 하 좌 상
	static int dy[] = { 1, 0, -1, 0 };

	static boolean[][] visited;
	static int remain; // 남아있는 얼음의 합
	static int count; // 남아있는 얼음 중 가장 큰 덩어리가 차지하는 칸의 개수

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		q = Integer.parseInt(st.nextToken());

		m = (int) Math.pow(2, n);

		map = new int[m][m];

		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		st = new StringTokenizer(br.readLine());

		for (int i = 0; i < q; i++) {

			int l = Integer.parseInt(st.nextToken());

			for (int k = 0; k < m; k += Math.pow(2, l)) { // 부분 격자로 나누어서 회전
				for (int p = 0; p < m; p += Math.pow(2, l)) { // 부분 격자로 나누어서 회전
					rotate(k, p, (int) Math.pow(2, l));
				}
			}
			melting();
		}

		//

		remain = 0;
		count = 0;

		// 파이어스톰은 먼저 격자를 2L × 2L 크기의 부분 격자로 나눈다.
		// 그 후, 모든 부분 격자를 시계 방향으로 90도 회전시킨다.
		// 이후 얼음이 있는 칸 3개 또는 그 이상과 인접해있지 않은 칸은 얼음의 양이 1 줄어든다.

		// 남아있는 얼음의 합

		// 남아있는 얼음 중 가장 큰 덩어리가 차지하는 칸의 개수
		visited = new boolean[m][m];

		for (int i = 0; i < m; i++) {
			for (int j = 0; j < m; j++) {
				if (!visited[i][j] && map[i][j] != 0) {
					count = Math.max(count, bfs(i, j));
				}
			}
		}

		remain = getRemain();
		System.out.println(remain);
		System.out.println(count);
	}

	public static void rotate(int x, int y, int len) {
		int[][] newmap = new int[len][len];

		for (int i = 0; i < len; i++) {
			for (int j = 0; j < len; j++) {
				newmap[i][j] = map[y + len - 1 - j][x + i];
			}
		}

		for (int i = 0; i < len; i++) {
			for (int j = 0; j < len; j++) {
				map[i + y][j + x] = newmap[i][j];
			}
		}
	}

	public static int getRemain() {// 남아있는 얼음의 합을 구하기 위해
		int num = 0;
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < m; j++) {
				num += map[i][j];
			}
		}
		return num;
	}

	public static int bfs(int i, int j) { // 얼음 덩어리 칸 개수 세기 위한 bfs
		int count = 1;

		visited[i][j] = true;

		Queue<int[]> q = new LinkedList<>();
		q.add(new int[] { i, j });
		while (!q.isEmpty()) {
			int[] temp = q.poll();
			int x = temp[0];
			int y = temp[1];

			for (int d = 0; d < 4; d++) {
				int nx = x + dx[d];
				int ny = y + dy[d];
				if (nx >= 0 && ny >= 0 && nx < m && ny < m && !visited[nx][ny] && map[nx][ny]!=0) {
					visited[nx][ny] = true;
					count++;
					q.offer(new int[] { nx, ny });
				}
			}
		}

		return count;
	}

	public static int[][] copymap(int[][] map) {
		int result[][] = new int[m][m];
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < m; j++) {
				result[i][j] = map[i][j];
			}
		}
		return result;
	}

	public static void melting() {
		Queue<int[]> q = new LinkedList<>(); // 얼음 양 감소시킬 위치 담아둘 큐

		for (int i = 0; i < m; i++) {
			for (int j = 0; j < m; j++) {
				if (map[i][j] == 0)
					continue;
				int count = 0;

				for (int d = 0; d < 4; d++) {
					int nx = i + dx[d];
					int ny = j + dy[d];

					if (nx >= 0 && ny >= 0 && nx < m && ny < m && map[nx][ny] > 0) {
						count++;
					}
				}
				if (count < 3) { // 얼음이 있는 칸 3개 미만으로 인접하면 하나 줄어들음
					q.offer(new int[] { i, j });
				}
			}
		}

		// 얼음 양 감소
		while (!q.isEmpty()) {
			int[] temp = q.poll();
			map[temp[0]][temp[1]]--;
		}
	}

}

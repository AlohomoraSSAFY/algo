package date1021THU;

import java.io.BufferedReader;
import java.io.*;
import java.util.*;

public class BOJ17142 { // 연구소 3
	static int m, n;
	static int map[][];
	static int omap[][];
	static int selected[];
	static int dx[] = { 1, -1, 0, 0 };
	static int dy[] = { 0, 0, 1, -1 };
	static ArrayList<int[]> list = new ArrayList<>();
	static int min;

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		min = Integer.MAX_VALUE;
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		map = new int[n][n];
		omap = new int[n][n];
		selected = new int[m];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				int a = Integer.parseInt(st.nextToken());
				omap[i][j] = a;
				map[i][j] = a;
				if (a == 2) {
					list.add(new int[] { i, j, 2 });
				}
			}
		}

		//

		if (checkmap()) {
			System.out.println(0);
		} else {
			combination(0, 0);
			min = (min == Integer.MAX_VALUE ? -1 : min);
			System.out.println(min);
		}
	}

	public static void combination(int start, int count) {
		if (count == m) {
			Queue<int[]> q = new LinkedList<>();
			
			for (int i = 0; i < m; i++) {
				q.add(list.get(selected[i]));
			}
			
			int result = bfs(q);
			min = Math.min(min, result);
			
			return;
		}

		for (int i = start; i < list.size(); i++) {
			selected[count] = i;
			combination(i + 1, count + 1);
		}
	}

	public static int bfs(Queue<int[]> q) {
		int sec = 0;
		copymap();
		while (!q.isEmpty()) {
			int temp[] = q.poll();
			if (sec - 2 >= min)
				return min;
			int x = temp[0];
			int y = temp[1];
			sec = temp[2];

			for (int d = 0; d < 4; d++) {
				int nx = x + dx[d];
				int ny = y + dy[d];

				if (nx >= 0 && ny >= 0 && nx < n && ny < n) {
					if (map[nx][ny] == 0) {
						map[nx][ny] = sec + 1;
						q.add(new int[] { nx, ny, sec + 1 });
					}
					else if(map[nx][ny] == 2 && !checkmap()) {
						map[nx][ny] = sec + 1;
						q.add(new int[] { nx, ny, sec + 1 });
					}
				}
			}
		}
		
		if (checkmap())
			return sec - 2;
		else
			return Integer.MAX_VALUE;
	}

	public static void copymap() {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				map[i][j] = omap[i][j];
			}
		}
	}

	public static void printmap() {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				System.out.print(map[i][j]);
			}
			System.out.println();
		}
		System.out.println();
	}

	public static boolean checkmap() {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (map[i][j] == 0)
					return false;
			}
		}
		return true;
	}

}

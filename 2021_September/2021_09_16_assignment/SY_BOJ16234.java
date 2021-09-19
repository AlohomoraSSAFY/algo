package study0919;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class SY_BOJ16234 {
	
	static int N;
	static int L;
	static int R;
	static int[][] array;
	static boolean[][] visited;
	static int[][] d = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
	static boolean flag;
	static int day;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		array = new int[N][N];
		flag = true;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				array[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		while (flag) {
			day++;
			visited = new boolean[N][N];
			flag = false;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (!visited[i][j]) {
						bfs(i, j, 1, array[i][j]);
					}
				}
			}
		}
		
		System.out.println(day-1);
	}
	
	public static void bfs(int x, int y, int cnt, int sum) {
		Queue<int[]> q = new LinkedList<>();
		ArrayList<int[]> list = new ArrayList<>();
		visited[x][y] = true;
		list.add(new int[] {x, y});
		q.offer(new int[] {x, y});
		
		while (!q.isEmpty()) {
			int[] cur = q.poll();
			for (int i = 0; i < 4; i++) {
				int nx = cur[0] + d[i][0];
				int ny = cur[1] + d[i][1];
				
				if (nx >= 0 && nx < N && ny >= 0 && ny < N && !visited[nx][ny]
						&& Math.abs(array[nx][ny] - array[cur[0]][cur[1]]) >= L
						&& Math.abs(array[nx][ny] - array[cur[0]][cur[1]]) <= R) {
					flag = true;
					visited[nx][ny] = true;
					cnt += 1;
					sum += array[nx][ny];
					list.add(new int[] {nx, ny});
					q.offer(new int[] {nx, ny});
				}
			}
		}
		
		int avg = sum / cnt;
		for (int i = 0; i < list.size(); i++) {
			int tx = list.get(i)[0];
			int ty = list.get(i)[1];
			array[tx][ty] = avg;
		}
	}

}

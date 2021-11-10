package study1031;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class SY_BOJ2573 {
	
	static int N;
	static int M;
	static int[][] array;
	static int[][] d = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
	static Queue<int[]> q;
	static boolean flag;
	static boolean check;
	static int result;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		array = new int[N][M];
		q = new LinkedList<>();
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < M; j++) {
				array[i][j] = Integer.parseInt(st.nextToken());
				if (array[i][j] != 0) q.offer(new int[] {i, j});
			}
		}
		
		while (!flag) {
			bfs();
			isConnect();
		}
		
		if (check) {
			System.out.println(result);
		} else {
			System.out.println(0);
		}
	}
	
	public static void bfs() {
		int[][] temp = new int[N][M];
		result++;
		
		int size = q.size();
		for (int s = 0; s < size; s++) {
			int[] cur = q.poll();
			int x = cur[0];
			int y = cur[1];
			
			int cnt = 0;
			for (int i = 0; i < 4; i++) {
				int nx = x + d[i][0];
				int ny = y + d[i][1];
				
				if (nx < 0 || nx >= N || ny < 0 || ny >= M) continue;
				
				if (array[nx][ny] == 0) cnt++;
			}
			
			int after = array[x][y] - cnt;
			temp[x][y] = (after >= 0) ? after : 0;
			if (temp[x][y] > 0) q.offer(new int[] {x, y});
		}
		
		array = temp;
	}
	
	public static void isConnect() {
		int size = q.size();
		if (q.size() == 0) {
			flag = true;
			return;
		}
		
		Queue<int[]> queue = new LinkedList<>();
		boolean[][] visited = new boolean[N][M];
		queue.offer(new int[] {q.peek()[0], q.peek()[1]});
		visited[q.peek()[0]][q.peek()[1]] = true;
		int cnt = 1;
		
		while (!queue.isEmpty()) {
			int[] cur = queue.poll();
			int x = cur[0];
			int y = cur[1];
			
			for (int i = 0; i < 4; i++) {
				int nx = x + d[i][0];
				int ny = y + d[i][1];
				
				if (nx < 0 || nx >= N || ny < 0 || ny >= M || visited[nx][ny]) continue;
				
				if (array[nx][ny] != 0) {
					cnt++;
					visited[nx][ny] = true;
					queue.offer(new int[] {nx, ny});
				}
			}
		}
		
		if (size != cnt) {
			flag = true;
			check = true;
			return;
		}
	}

}

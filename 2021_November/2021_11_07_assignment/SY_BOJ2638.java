package study1111;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class SY_BOJ2638 {
	
	static int N;
	static int M;
	static int[][] array;
	static int[][] d = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
	static int cheeze;
	static int time;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		array = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < M; j++) {
				array[i][j] = Integer.parseInt(st.nextToken());
				if (array[i][j] == 1) cheeze++;
			}
		}
		
		while(true) {
			time++;
			bfs();
			
			if (cheeze == 0) break;
		}
		
		System.out.println(time);
	}
	
	public static void bfs() {
		Queue<int[]> q = new LinkedList<>();
		q.offer(new int[] {0, 0});
		int[][] visited = new int[N][M];
		visited[0][0] = 1;
		
		while (!q.isEmpty()) {
			int[] cur = q.poll();
			int x = cur[0];
			int y = cur[1];
			
			for (int i = 0; i < 4; i++) {
				int nx = x + d[i][0];
				int ny = y + d[i][1];
				
				if (nx < 0 || nx >= N || ny < 0 || ny >= M) continue;
				if (array[nx][ny] == 0 && visited[nx][ny] != 0) continue;
				if (array[nx][ny] != 0) {
					if (++visited[nx][ny] >= 2) {
						array[nx][ny] = 0;
						cheeze--;
					}
					continue;
				}
				
				visited[nx][ny] = 1;
				q.offer(new int[] {nx, ny});
			}
		}
	}

}

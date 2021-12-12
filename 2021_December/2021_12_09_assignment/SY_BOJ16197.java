package study1212;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class SY_BOJ16197 {

	static int N;
	static int M;
	static char[][] array;
	static int[][] coin;
	static int[][] d = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
	static int result;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		array = new char[N][M];
		coin = new int[2][2];
		int order = 0;
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < M; j++) {
				array[i][j] = str.charAt(j);
				if (array[i][j] == 'o') {
					coin[order][0] = i;
					coin[order][1] = j;
					order++;
				}
			}
		}
		
		bfs();
		System.out.println(result);
	}
	
	public static void bfs() {
		Queue<int[]> q = new LinkedList<>();
		q.offer(new int[] {coin[0][0], coin[0][1], coin[1][0], coin[1][1], 0});
		boolean[][][][] visited = new boolean[N][M][N][M];
		visited[coin[0][0]][coin[0][1]][coin[1][0]][coin[1][1]] = true;
		
		while (!q.isEmpty()) {
			int[] cur = q.poll();
			int x1 = cur[0];
			int y1 = cur[1];
			int x2 = cur[2];
			int y2 = cur[3];
			int cnt = cur[4];
			
			for (int i = 0; i < 4; i++) {
				int check = 0;
				int nx1 = x1 + d[i][0];
				int ny1 = y1 + d[i][1];
				int nx2 = x2 + d[i][0];
				int ny2 = y2 + d[i][1];
				
				if (nx1 < 0 || nx1 >= N || ny1 < 0 || ny1 >= M) check++;
				if (nx2 < 0 || nx2 >= N || ny2 < 0 || ny2 >= M) check++;
				
				if (check == 1) {
					result = cnt + 1;
					return;
				} else if (check == 2) continue;
				
				if (array[nx1][ny1] == '#') {
					nx1 -= d[i][0];
					ny1 -= d[i][1];
				}
				if (array[nx2][ny2] == '#') {
					nx2 -= d[i][0];
					ny2 -= d[i][1];
				}
				
				if (nx1 == nx2 && ny1 == ny2) continue;
				if (visited[nx1][ny1][nx2][ny2]) continue;
				
				visited[nx1][ny1][nx2][ny2] = true;
				if (cnt + 1 < 10) q.offer(new int[] {nx1, ny1, nx2, ny2, cnt + 1});
			}
		}
		
		result = -1;
	}

}

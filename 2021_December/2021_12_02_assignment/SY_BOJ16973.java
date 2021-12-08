package study1205;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class SY_BOJ16973 {
	
	static int N, M;
	static int[][] array;
	static int H, W;
	static int[] S;
	static int[] F;
	static int[][] d = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
	static int result;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		array = new int[N+1][M+1];
		for (int i = 1; i < N+1; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 1; j < M+1; j++) {
				array[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		st = new StringTokenizer(br.readLine(), " ");
		H = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());
		S = new int[2];
		F = new int[2];
		S[0] = Integer.parseInt(st.nextToken());
		S[1] = Integer.parseInt(st.nextToken());
		F[0] = Integer.parseInt(st.nextToken());
		F[1] = Integer.parseInt(st.nextToken());
		
		bfs();
		System.out.println(result);
	}
	
	public static void bfs() {
		Queue<int[]> q = new LinkedList<>();
		q.offer(new int[] {S[0], S[1], 0});
		boolean[][] visited = new boolean[N+1][M+1];
		visited[S[0]][S[1]] = true;
		
		while (!q.isEmpty()) {
			int[] cur = q.poll();
			int sx = cur[0];
			int sy = cur[1];
			int cnt = cur[2];
			
			if (sx == F[0] && sy == F[1]) {
				result = cnt;
				return;
			}
			
			for (int i = 0; i < 4; i++) {
				int nx = sx + d[i][0];
				int ny = sy + d[i][1];
				
				if (nx < 1 || (nx + H - 1) > N || ny < 1 || (ny + W - 1) > M || visited[nx][ny]) continue;
				
				boolean flag = false;
				if (i == 0) {
					for (int j = ny; j < ny + W; j++) {
						if (array[nx][j] == 1) {
							flag = true;
							break;
						}
					}
				} else if (i == 1) {
					for (int j = nx; j < nx + H; j++) {
						if (array[j][ny+W-1] == 1) {
							flag = true;
							break;
						}
					}
				} else if (i == 2) {
					for (int j = sy; j < sy + W; j++) {
						if (array[nx+H-1][j] == 1) {
							flag = true;
							break;
						}
					}
				} else {
					for (int j = sx; j < sx + H; j++) {
						if (array[j][ny] == 1) {
							flag = true;
							break;
						}
					}
				}
				
				if (flag) continue;
				visited[nx][ny] = true;
				q.offer(new int[] {nx, ny, cnt+1});
			}
		}
		
		result = -1;
	}

}

package study0714;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class SY_BOJ5547 {
	
	static int W, H;
	static int[][] array;
	static int[][] dx = {{0, 1, 0, -1, -1, -1}, {1, 1, 1, 0, -1, 0}};
	static int[] dy = {-1, 0, 1, 1, 0, -1};
	static int result;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		W = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		array = new int[H+2][W+2];
		for (int i = 1; i < H+1; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 1; j < W+1; j++) {
				array[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		bfs();
		System.out.println(result);
	}
	
	private static void bfs() {
		Queue<int[]> q = new LinkedList<>();
		q.offer(new int[] {0, 0});
		boolean[][] visited = new boolean[H+2][W+2];
		visited[0][0] = true;
		
		while (!q.isEmpty()) {
			int[] cur = q.poll();
			
			for (int i = 0; i < 6; i++) {
				int ny = cur[0] + dy[i];
				int nx = cur[1] + dx[cur[0] % 2][i];
				
				if (nx < 0 || nx > W+1 || ny < 0 || ny > H+1 || visited[ny][nx]) continue;
				if (array[ny][nx] == 0) {
					q.offer(new int[] {ny, nx});
					visited[ny][nx] = true;
				} else {
					result++;
				}
			}
		}
	}

}

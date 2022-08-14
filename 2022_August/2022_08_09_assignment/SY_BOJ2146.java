package study0816;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class SY_BOJ2146 {
	
	static int N, num, result;
	static int[][] array;
	static boolean[][] visited;
	static int[][] d = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		array = new int[N][N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				array[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		num = 1;
		visited = new boolean[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (array[i][j] == 1 && !visited[i][j]) {
					makeNum(i, j);
					num++;
				}
			}
		}
		
		result = Integer.MAX_VALUE;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (array[i][j] != 0) {
					visited = new boolean[N][N];
					bfs(i, j);
				}
			}
		}
		
		System.out.println(result);
	}
	
	private static void makeNum(int x, int y) {
		Queue<int[]> q = new LinkedList<>();
		q.offer(new int[] {x, y});
		visited[x][y] = true;
		array[x][y] = num;
		
		while (!q.isEmpty()) {
			int[] cur = q.poll();
			
			for (int i = 0; i < 4; i++) {
				int nx = cur[0] + d[i][0];
				int ny = cur[1] + d[i][1];
				
				if (nx < 0 || nx >= N || ny < 0 || ny >= N || visited[nx][ny]) continue;
				if (array[nx][ny] == 1) {
					q.offer(new int[] {nx, ny});
					visited[nx][ny] = true;
					array[nx][ny] = num;
				}
			}
		}
	}
	
	private static void bfs(int x, int y) {
		Queue<int[]> q = new LinkedList<>();
		q.offer(new int[] {x, y, 0});
		visited[x][y] = true;
		int num = array[x][y];
		
		while (!q.isEmpty()) {
			int[] cur = q.poll();
			
			for (int i = 0; i < 4; i++) {
				int nx = cur[0] + d[i][0];
				int ny = cur[1] + d[i][1];
				
				if (nx < 0 || nx >= N || ny < 0 || ny >= N || visited[nx][ny] || array[nx][ny] == num) continue;
				if (array[nx][ny] == 0) {
					q.offer(new int[] {nx, ny, cur[2] + 1});
					visited[nx][ny] = true;
				} else {
					result = Math.min(result, cur[2]);
					return;
				}
			}
		}
	}

}

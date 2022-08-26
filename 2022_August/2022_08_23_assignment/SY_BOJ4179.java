package study0830;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class SY_BOJ4179 {
	
	static int R, C;
	static char[][] array;
	static Queue<int[]> fireList;
	static int[][] d = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
	static int result;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		array = new char[R][C];
		fireList = new LinkedList<>();
		int[] person = new int[2];
		for (int i = 0; i < R; i++) {
			char[] cArray = br.readLine().toCharArray();
			for (int j = 0; j < C; j++) {
				array[i][j] = cArray[j];
				if (array[i][j] == 'J') {
					person[0] = i;
					person[1] = j;
					array[i][j] = '.';
				} else if (array[i][j] == 'F') {
					fireList.offer(new int[] {i, j});
				}
			}
		}
		
		bfs(person[0], person[1]);
		
		if (result == 0) System.out.println("IMPOSSIBLE");
		else System.out.println(result);
	}
	
	private static void bfs(int x, int y) {
		Queue<int[]> q = new LinkedList<>();
		q.offer(new int[] {x, y, 0});
		boolean[][] visited = new boolean[R][C];
		visited[x][y] = true;
		
		while (!q.isEmpty()) {
			spreadFire();
			
			int size = q.size();
			for (int t = 0; t < size; t++) {
				int[] cur = q.poll();
				
				for (int i = 0; i < 4; i++) {
					int nx = cur[0] + d[i][0];
					int ny = cur[1] + d[i][1];
					
					if (nx < 0 || nx >= R || ny < 0 || ny >= C) {
						result = cur[2] + 1;
						return;
					}
					
					if (visited[nx][ny] || array[nx][ny] == '#' || array[nx][ny] == 'F') continue;
					
					q.offer(new int[] {nx, ny, cur[2] + 1});
					visited[nx][ny] = true;
				}
			}
		}
	}
	
	private static void spreadFire() {
		int size = fireList.size();
		for (int t = 0; t < size; t++) {
			int[] cur = fireList.poll();
			
			for (int i = 0; i < 4; i++) {
				int nx = cur[0] + d[i][0];
				int ny = cur[1] + d[i][1];
				
				if (nx < 0 || nx >= R || ny < 0 || ny >= C) continue;
				if (array[nx][ny] == '#' || array[nx][ny] == 'F') continue;
				
				array[nx][ny] = 'F';
				fireList.offer(new int[] {nx, ny});
				fireList.offer(new int[] {cur[0], cur[1]});
			}
		}
	}

}

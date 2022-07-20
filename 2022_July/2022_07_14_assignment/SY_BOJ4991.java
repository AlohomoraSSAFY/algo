package study0721;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class SY_BOJ4991 {
	
	static int w, h;
	static int[][] array;
	static int[][] d = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
	static int[] start;
	static int total;
	static int result;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		while (true) {
			st = new StringTokenizer(br.readLine(), " ");
			w = Integer.parseInt(st.nextToken());
			h = Integer.parseInt(st.nextToken());
			if (w == 0 && h == 0) break;
			
			array = new int[h][w];
			start = new int[2];
			total = 0;
			result = -1;
			int idx = 1; 
			for (int i = 0; i < h; i++) {
				char[] cArray = br.readLine().toCharArray();
				for (int j = 0; j < w; j++) {
					if (cArray[j] == 'o') {
						start[0] = i;
						start[1] = j;
						array[i][j] = 0;
					} else if (cArray[j] == '*') {
						total++;
						array[i][j] = idx++;
					} else if (cArray[j] == '.') {
						array[i][j] = 0;
					} else if (cArray[j] == 'x') {
						array[i][j] = -1;
					}
				}
			}
			
			bfs();
			System.out.println(result);
		}
	}
	
	private static void bfs() {
		Queue<int[]> q = new LinkedList<>();
		q.offer(new int[] {start[0], start[1], 0, 0});
		boolean[][][] visited = new boolean[h][w][(int)Math.pow(2, total)];
		visited[start[0]][start[1]][0] = true;
		
		while (!q.isEmpty()) {
			int[] cur = q.poll();
			
			if (cur[2] == (int)Math.pow(2, total) - 1) {
				result = cur[3];
				return;
			}
			
			for (int i = 0; i < 4; i++) {
				int nx = cur[0] + d[i][0];
				int ny = cur[1] + d[i][1];
				
				if (nx < 0 || nx >= h || ny < 0 || ny >= w) continue;
				if (array[nx][ny] == -1 || visited[nx][ny][cur[2]]) continue;
				visited[nx][ny][cur[2]] = true;
				
				if (array[nx][ny] != 0) {
					int temp = cur[2] | 1 << (array[nx][ny] - 1);
					q.offer(new int[] {nx, ny, temp, cur[3] + 1});
				} else {
					q.offer(new int[] {nx, ny, cur[2], cur[3] + 1});
				}
			}
		}
	}

}

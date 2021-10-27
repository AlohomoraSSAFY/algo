package study1028;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class SY_BOJ1941 {
	
	static char[][] array;
	static int[][] selected;
	static int[][] d = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
	static int result;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		array = new char[5][5];
		selected = new int[7][2];
		for (int i = 0; i < 5; i++) {
			array[i] = br.readLine().toCharArray();
		}
		
		combination(0, 0, 0);
		System.out.println(result);
	}
	
	public static void combination(int cnt, int start, int sCnt) {
		if (cnt - sCnt > 3) return;
		
		if (cnt == 7) {
			bfs();
			return;
		}
		
		for (int i = start; i < 25; i++) {
			selected[cnt][0] = i / 5;
			selected[cnt][1] = i % 5;
			if (array[selected[cnt][0]][selected[cnt][1]] == 'S') {
				combination(cnt + 1, i + 1, sCnt + 1);
			} else {
				combination(cnt + 1, i + 1, sCnt);
			}
		}
	}
	
	public static void bfs() {
		int cnt = 1;
		Queue<int[]> q = new LinkedList<>();
		q.offer(new int[] {selected[0][0], selected[0][1]});
		boolean[][] visited = new boolean[5][5];
		visited[selected[0][0]][selected[0][1]] = true;
		
		while (!q.isEmpty()) {
			int[] cur = q.poll();
			int x = cur[0];
			int y = cur[1];
			
			for (int i = 0; i < 4; i++) {
				int nx = x + d[i][0];
				int ny = y + d[i][1];
				
				if (nx < 0 || nx >= 5 || ny < 0 || ny >= 5 || visited[nx][ny]) continue;
				
				for (int j = 1; j < 7; j++) {
					if (nx == selected[j][0] && ny == selected[j][1]) {
						cnt++;
						visited[nx][ny] = true;
						q.offer(new int[] {nx, ny});
						break;
					}
				}
			}
		}
		
		if (cnt == 7) result++;
	}

}

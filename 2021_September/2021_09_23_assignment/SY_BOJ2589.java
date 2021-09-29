package study0926;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class SY_BOJ2589 {

	static int R;
	static int C;
	static char[][] array;
	static List<int[]> list;
	static int[][] d = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
	static int result;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		array = new char[R][C];
		list = new ArrayList<>();
		result = Integer.MIN_VALUE;
		for (int i = 0; i < R; i++) {
			String[] str = br.readLine().split("");
			for (int j = 0; j < C; j++) {
				array[i][j] = str[j].charAt(0);
				if (array[i][j] == 'L') {
					list.add(new int[] {i, j});
				}
			}
		}
		
		for (int i = 0; i < list.size(); i++) {
			bfs(list.get(i)[0], list.get(i)[1]);
		}
		
		System.out.println(result);
	}
	
	public static void bfs(int x, int y) {
		Queue<int[]> q = new LinkedList<>();
		boolean[][] visited = new boolean[R][C];
		q.offer(new int[] {x, y, 0});
		visited[x][y] = true;
		
		while (!q.isEmpty()) {
			int[] cur = q.poll();
			int cx = cur[0];
			int cy = cur[1];
			int ct = cur[2];
			
			for (int i = 0; i < 4; i++) {
				int nx = cx + d[i][0];
				int ny = cy + d[i][1];
				if (nx >= 0 && nx < R && ny >= 0 && ny < C
						&& array[nx][ny] == 'L' && !visited[nx][ny]) {
					visited[nx][ny] = true;
					result = Math.max(result, ct + 1);
					q.offer(new int[] {nx, ny, ct + 1});
				}
			}
		}
	}

}

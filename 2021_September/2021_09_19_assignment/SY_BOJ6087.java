package study0923;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class SY_BOJ6087 {
	
	static int W;
	static int H;
	static char[][] array;
	static List<int[]> list;
	static int[][] d = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
	static int mirror = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		W = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		array = new char[H][W];
		list = new ArrayList<>();
		for (int i = 0; i < H; i++) {
			String[] str = br.readLine().split("");
			for (int j = 0; j < W; j++) {
				array[i][j] = str[j].charAt(0);
				if (array[i][j] == 'C') {
					list.add(new int[] {i, j});
				}
			}
		}
		
		bfs(list.get(0)[0], list.get(0)[1]);
		System.out.println(mirror - 1);
	}
	
	public static void bfs(int x, int y) {
		Queue<int[]> q = new LinkedList<>();
		int[][] visited = new int[H][W];
		q.offer(new int[] {x, y, -1, 1});
		visited[x][y] = 1;
		
		while (!q.isEmpty()) {
			int[] cur = q.poll();
			int cx = cur[0];
			int cy = cur[1];
			int dir = cur[2];
			int cnt = cur[3];
			
			if (cx == list.get(1)[0] && cy == list.get(1)[1]) {
				mirror = Math.min(mirror, cnt);
				continue;
			}
			
			for (int i = 0; i < 4; i++) {
				int nx = cx + d[i][0];
				int ny = cy + d[i][1];
				
				if (nx < 0 || nx >= H || ny < 0 || ny >= W || array[nx][ny] == '*') continue;
				
				int nc = cnt;
				if (dir != -1 && dir != i) nc++;
				
				if (visited[nx][ny] == 0) {
					visited[nx][ny] = nc;
					q.offer(new int[] {nx, ny, i, nc});
				} else {
					if (visited[nx][ny] >= nc) {
						visited[nx][ny] = nc;
						q.offer(new int[] {nx, ny, i, nc});
					}
				}
			}
		}
	}

}

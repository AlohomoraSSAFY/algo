package study0823;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class SY_BOJ2933 {
	
	static int R, C;
	static char[][] array;
	static boolean[][] visited;
	static int[][] d = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		array = new char[R][C];
		for (int i = 0; i < R; i++) {
			array[i] = br.readLine().toCharArray();
		}
		
		int N = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine(), " ");
		int turn = 0;
		for (int t = 0; t < N; t++) {
			int h = Integer.parseInt(st.nextToken());
			
			//막대를 던진 높이의 미네랄 제거
			if (turn++ % 2 == 0) {
				//왼쪽
				int k = 0;
				while (k < C) {
					if (array[R-h][k] == 'x') {
						array[R-h][k] = '.';
						break;
					}
					k++;
				}
			} else {
				//오른쪽
				int k = C-1;
				while (k >= 0) {
					if (array[R-h][k] == 'x') {
						array[R-h][k] = '.';
						break;
					}
					k--;
				}
			}
			
			//공중에 떠 있는 클러스터 확인 (바닥에 붙어있는 클러스터 방문 처리)
			visited = new boolean[R][C];
			for (int i = 0; i < C; i++) {
				if (!visited[R-1][i]) check(R-1, i);
			}
			
			//중력 적용
			gravity();
		}
		
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				System.out.print(array[i][j]);
			}
			System.out.println();
		}
		
	}
	
	private static void check(int x, int y) {
		Queue<int[]> q = new LinkedList<>();
		q.offer(new int[] {x, y});
		visited[x][y] = true;
		
		while (!q.isEmpty()) {
			int[] cur = q.poll();
			
			for (int i = 0; i < 4; i++) {
				int nx = cur[0] + d[i][0];
				int ny = cur[1] + d[i][1];
				
				if (nx < 0 || nx >= R || ny < 0 || ny >= C || visited[nx][ny]) continue;
				if (array[nx][ny] == 'x') {
					q.offer(new int[] {nx, ny});
					visited[nx][ny] = true;
				}
			}
		}
	}
	
	private static void gravity() {
		List<int[]> list = new ArrayList<>();
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (array[i][j] == 'x' && !visited[i][j]) {
					list.add(new int[] {i, j});
					array[i][j] = '.';
				}
			}
		}
		
		if (list.size() == 0) return;
		
		boolean flag = true;
		while (flag) {
			for (int[] cur : list) {
				int nx = cur[0] + 1;
				int ny = cur[1];
				
				if (nx >= R || array[nx][ny] == 'x') {
					flag = false;
					break;
				}
			}
			
			if (flag) {				
				for (int[] cur : list) {
					cur[0] += 1;
				}
			}
		}
		
		for (int[] cur : list) {
			array[cur[0]][cur[1]] = 'x';
		}
	}

}

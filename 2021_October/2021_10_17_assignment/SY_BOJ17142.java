package study1021;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class SY_BOJ17142 {
	
	static int N;
	static int M;
	static int[][] array;
	static List<int[]> list;
	static int[] selected;
	static int blankCnt;
	static int[][] d = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
	static int result;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		array = new int[N][N];
		list = new ArrayList<>();
		selected = new int[M];
		result = Integer.MAX_VALUE;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				array[i][j] = Integer.parseInt(st.nextToken());
				if (array[i][j] == 0) {
					blankCnt++;
				}
				if (array[i][j] == 2) {
					list.add(new int[] {i, j});
				}
			}
		}
		
		if (blankCnt == 0) {
			System.out.println(0);
			return;
		}
		
		combination(0, 0);
		
		if (result == Integer.MAX_VALUE) {
			System.out.println(-1);
		} else {
			System.out.println(result);
		}
	}
	
	public static void combination(int cnt, int start) {
		if (cnt == M) {
			bfs();
			return;
		}
		
		for (int i = start; i < list.size(); i++) {
			selected[cnt] = i;
			combination(cnt + 1, i + 1);
		}
	}
	
	public static void bfs() {
		int cnt = 0;
		Queue<int[]> q = new LinkedList<>();
		boolean[][] visited = new boolean[N][N];
		for (int i = 0; i < M; i++) {
			q.offer(new int[] {list.get(selected[i])[0], list.get(selected[i])[1], 0});
			visited[list.get(selected[i])[0]][list.get(selected[i])[1]] = true;
		}
		
		while (!q.isEmpty()) {
			int[] cur = q.poll();
			int x = cur[0];
			int y = cur[1];
			int time = cur[2];
			
			for (int i = 0; i < 4; i++) {
				int nx = x + d[i][0];
				int ny = y + d[i][1];
				
				if (nx < 0 || nx >= N || ny < 0 || ny >= N
						|| visited[nx][ny] || array[nx][ny] == 1) continue;
				
				if (array[nx][ny] == 0) {
					if (++cnt == blankCnt) {
						result = Math.min(result, time + 1);
						return;
					}
				}
				
				visited[nx][ny] = true;
				q.offer(new int[] {nx, ny, time + 1});
			}
		}
	}

}

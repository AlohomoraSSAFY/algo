package study0906;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class SY_BOJ3055 {
	
	static int R, C;
	static char[][] array;
	static int[] start;
	static List<int[]> waterList;
	static int[][] d = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
	static int time;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		array = new char[R][C];
		start = new int[2];
		waterList = new ArrayList<>();
		for (int i = 0; i < R; i++) {
			char[] cArray = br.readLine().toCharArray();
			for (int j = 0; j < C; j++) {
				array[i][j] = cArray[j];
				if (array[i][j] == 'S') {
					start[0] = i;
					start[1] = j;
					array[i][j] = '.';
				} else if (array[i][j] == '*') {
					waterList.add(new int[] {i, j});
				}
			}
		}
		
		bfs();
		
		if (time == 0) System.out.println("KAKTUS");
		else System.out.println(time);
	}
	
	private static void bfs() {
		Queue<int[]> q = new LinkedList<>();
		q.offer(new int[] {start[0], start[1], 0});
		boolean[][] visited = new boolean[R][C];
		visited[start[0]][start[1]] = true;
		
		while (!q.isEmpty()) {
			spreadWater();
			
			int size = q.size();
			for (int i = 0; i < size; i++) {
				int[] cur = q.poll();
				
				if (array[cur[0]][cur[1]] == 'D') {
					time = cur[2];
					return;
				}
				
				for (int j = 0; j < 4; j++) {
					int nx = cur[0] + d[j][0];
					int ny = cur[1] + d[j][1];
					
					if (nx < 0 || nx >= R || ny < 0 || ny >= C || visited[nx][ny]) continue;
					if (array[nx][ny] == 'X' || array[nx][ny] == '*') continue;
					
					q.offer(new int[] {nx, ny, cur[2] + 1});
					visited[nx][ny] = true;
				}
			}
		}
	}
	
	private static void spreadWater() {
		int size = waterList.size();
		for (int i = 0; i < size; i++) {
			int x = waterList.get(i)[0];
			int y = waterList.get(i)[1];
			
			for (int j = 0; j < 4; j++) {
				int nx = x + d[j][0];
				int ny = y + d[j][1];
				
				if (nx < 0 || nx >= R || ny < 0 || ny >= C) continue;
				if (array[nx][ny] == '.') {
					waterList.add(new int[] {nx, ny});
					array[nx][ny] = '*';
				}
			}
		}
	}

}

package study1202;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class SY_BOJ11559 {
	
	static char[][] array;
	static boolean[][] visited;
	static boolean flag;
	static int result;
	static int[][] d = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		array = new char[12][6];
		for (int i = 0; i < 12; i++) {
			array[i] = br.readLine().toCharArray();
		}
		
		while (!flag) {
			visited = new boolean[12][6];
			flag = true;
			for (int i = 0; i < 12; i++) {
				for (int j = 0; j < 6; j++) {
					if (array[i][j] != '.' && !visited[i][j]) {
						bfs(i, j);
					}
				}
			}
			
			for (int i = 0; i < 6; i++) {
				Queue<Character> q = new LinkedList<>();
				for (int j = 11; j >= 0; j--) {
					if (array[j][i] != '.' && array[j][i] != 'O') {
						q.offer(array[j][i]);
					}
				}
				for (int j = 11; j >= 0; j--) {
					if (!q.isEmpty()) {
						array[j][i] = q.poll();
					} else {
						array[j][i] = '.';
					}
				}
			}
			
			result++;
		}
		
		System.out.println(result - 1);
	}
	
	public static void bfs(int x, int y) {
		Queue<int[]> q = new LinkedList<>();
		q.offer(new int[] {x, y});
		visited[x][y] = true;
		List<int[]> list = new ArrayList<>();
		list.add(new int[] {x, y});
		int count = 1;
		
		while (!q.isEmpty()) {
			int[] cur = q.poll();
			int cx = cur[0];
			int cy = cur[1];
			
			for (int i = 0; i < 4; i++) {
				int nx = cx + d[i][0];
				int ny = cy + d[i][1];
				
				if (nx < 0 || nx >= 12 || ny < 0 || ny >= 6 || visited[nx][ny]) continue;
				
				if (array[cx][cy] == array[nx][ny]) {
					count++;
					visited[nx][ny] = true;;
					list.add(new int[] {nx, ny});
					q.offer(new int[] {nx, ny});
				}
			}
		}
		
		if (count >= 4) {
			flag = false;
			for (int i = 0; i < list.size(); i++) {
				int cx = list.get(i)[0];
				int cy = list.get(i)[1];
				array[cx][cy] = 'O';
			}
		}
	}

}

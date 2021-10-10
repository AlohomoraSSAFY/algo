package study1011;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class SY_BOJ2234 {
	
	static int n; //가로
	static int m; //세로
	static int[][] array;
	static int[][] visited;
	static int[][] d = {{0, -1}, {-1, 0}, {0, 1}, {1, 0}};
	static int count;
	static List<Integer> list;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		array = new int[m][n];
		visited = new int[m][n];
		list = new ArrayList<>();
		list.add(0);
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < n; j++) {
				array[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (visited[i][j] != 0) continue;
				count++;
				bfs(i, j);	
			}
		}
		
		sb.append(count + "\n");
		
		int max = 0;
		for (int i = 1; i < list.size(); i++) {
			int temp = list.get(i);
			max = Math.max(max, temp);
		}
		sb.append(max + "\n");
		
		int comMax = 0;
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (i+1 < m && visited[i][j] != visited[i+1][j]) {
					int temp = list.get(visited[i][j]) + list.get(visited[i+1][j]);
					comMax = Math.max(comMax, temp);
				}
				
				if (j+1 < n && visited[i][j] != visited[i][j+1]) {
					int temp = list.get(visited[i][j]) + list.get(visited[i][j+1]);
					comMax = Math.max(comMax, temp);
				}
			}
		}
		sb.append(comMax + "\n");
		
		System.out.println(sb);
	}
	
	public static void bfs(int x, int y) {
		Queue<int[]> q = new LinkedList<>();
		q.offer(new int[] {x, y});
		visited[x][y] = count;
		int area = 1;
		
		while (!q.isEmpty()) {
			int[] cur = q.poll();
			int cx = cur[0];
			int cy = cur[1];
			
			for (int i = 0; i < 4; i++) {
				int temp = array[cx][cy] & (1 << i);
				if (temp != 0) continue;
				
				int nx = cx + d[i][0];
				int ny = cy + d[i][1];
				if (visited[nx][ny] != 0) continue;
				
				visited[nx][ny] = count;
				area++;
				q.offer(new int[] {nx, ny});
			}
		}
		
		list.add(area);
	}

}

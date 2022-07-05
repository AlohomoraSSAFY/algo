package study0707;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;

public class SY_BOJ2665 {
	
	static int n;
	static int[][] array;
	static int[][] d = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
	static int result;
	
	static class Pos implements Comparable<Pos> {
		int x;
		int y;
		int cnt;
		
		public Pos(int x, int y, int cnt) {
			this.x = x;
			this.y = y;
			this.cnt = cnt;
		}
		
		@Override
		public int compareTo(Pos o) {
			return this.cnt - o.cnt;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		array = new int[n][n];
		for (int i = 0; i < n; i++) {
			char[] cArray = br.readLine().toCharArray();
			for (int j = 0; j < n; j++) {
				array[i][j] = cArray[j] - '0';
			}
		}
		
		bfs();
		System.out.println(result);
	}
	
	private static void bfs() {
		PriorityQueue<Pos> pq = new PriorityQueue<>();
		pq.offer(new Pos(0, 0, 0));
		int[][] visited = new int[n][n];
		for (int i = 0; i < n; i++) {
			Arrays.fill(visited[i], Integer.MAX_VALUE);
		}
		visited[0][0] = 0;
		
		while (!pq.isEmpty()) {
			Pos cur = pq.poll();
			
			if (cur.x == n-1 && cur.y == n-1) {
				result = cur.cnt;
				return;
			}
			
			for (int i = 0; i < 4; i++) {
				int nx = cur.x + d[i][0];
				int ny = cur.y + d[i][1];
				
				if (nx < 0 || nx >= n || ny < 0 || ny >= n) continue;
				
				if (array[nx][ny] == 0) {
					if (cur.cnt + 1 < visited[nx][ny]) {
						pq.offer(new Pos(nx, ny, cur.cnt + 1));
						visited[nx][ny] = cur.cnt + 1;
					}
				} else {
					if (cur.cnt < visited[nx][ny]) {
						pq.offer(new Pos(nx, ny, cur.cnt));
						visited[nx][ny] = cur.cnt;
					}
				}
			}
		}
	}

}

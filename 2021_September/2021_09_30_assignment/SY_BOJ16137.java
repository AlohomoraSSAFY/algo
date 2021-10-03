package study1003;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class SY_BOJ16137 {
	
	static int N;
	static int M;
	static int[][] array;
	static int[][] d = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
	static int result;
	
	static class Pos implements Comparable<Pos> {
		int x, y, v, t, c;
		
		public Pos(int x, int y, int v, int t, int c) {
			this.x = x;
			this.y = y;
			this.v = v;
			this.t = t;
			this.c = c;
		}

		@Override
		public int compareTo(Pos o) {
			return this.t - o.t;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		array = new int[N][N];
		result = Integer.MAX_VALUE;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				array[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		bfs();
		System.out.println(result);
	}
	
	public static void bfs() {
		PriorityQueue<Pos> pq = new PriorityQueue<>();
		pq.offer(new Pos(0, 0, array[0][0], 0, 0));
		boolean[][][] visited = new boolean[N][N][2];
		visited[0][0][0] = true;
		
		while (!pq.isEmpty()) {			
			Pos cur = pq.poll();
			int x = cur.x;
			int y = cur.y;
			int v = cur.v;
			int t = cur.t;
			int c = cur.c;
			
			if (x == N-1 && y == N-1) {
				result = Math.min(result, t);
			}
			
			for (int i = 0; i < 4; i++) {
				int nx = x + d[i][0];
				int ny = y + d[i][1];
				
				if (nx < 0 || nx >= N || ny < 0 || ny >= N || visited[nx][ny][c]) continue;
				if (v != 1 && array[nx][ny] != 1) continue;
				
				if (array[nx][ny] == 0) {
					boolean[] check = new boolean[4];
					
					for (int j = 0; j < 4; j++) {
						int tx = nx + d[j][0];
						int ty = ny + d[j][1];
						
						if (tx < 0 || tx >= N || ty < 0 || ty >= N) continue;
						if (array[tx][ty] == 0) check[j] = true;
					}
					
					if ((check[0] && check[1]) || (check[1] && check[2])
							|| (check[2] && check[3]) || (check[3] && check[0])) continue;
				}
				
				if (array[nx][ny] == 1) {
					visited[nx][ny][c] = true;
					pq.offer(new Pos(nx, ny, array[nx][ny], t + 1, c));
				} else if(array[nx][ny] == 0 && c == 0) {
					if ((t+1) % M == 0) {
						visited[nx][ny][c] = true;
						pq.offer(new Pos(nx, ny, array[nx][ny], t + 1, c + 1));
					} else {
						pq.offer(new Pos(x, y, array[x][y], t + 1, c));
					}
				} else if(array[nx][ny] > 1) {
					if ((t+1) % array[nx][ny] == 0) {
						visited[nx][ny][c] = true;
						pq.offer(new Pos(nx, ny, array[nx][ny], t + 1, c));
					} else {
						pq.offer(new Pos(x, y, array[x][y], t + 1, c));
					}
				}
			}
		}
	}
}

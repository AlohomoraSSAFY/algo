package study1209;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class SY_BOJ1261 {
	
	static int N;
	static int M;
	static int[][] miro;
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
		
		public int compareTo(Pos o) {
			return Integer.compare(this.cnt, o.cnt);
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		miro = new int[N][M];
		for (int i = 0; i < N; i++) {
			char[] cArray = br.readLine().toCharArray();
			for (int j = 0; j < M; j++) {
				miro[i][j] = cArray[j] - '0';
			}
		}
		
		bfs();
		System.out.println(result);
	}
	
	public static void bfs() {
		PriorityQueue<Pos> q = new PriorityQueue<>();
		q.offer(new Pos(0, 0, 0));
		int[][] visited = new int[N][M];
		for (int i = 0; i < N; i++) {
			Arrays.fill(visited[i], Integer.MAX_VALUE);
		}
		visited[0][0] = 0;
		
		while (!q.isEmpty()) {
			Pos cur = q.poll();
			int x = cur.x;
			int y = cur.y;
			int cnt = cur.cnt;
			
			if (x == N-1 && y == M-1) {
				result = visited[N-1][M-1];
				return;
			}
			
			for (int i = 0; i < 4; i++) {
				int nx = x + d[i][0];
				int ny = y + d[i][1];
				
				if (nx < 0 || nx >= N || ny < 0 || ny >= M) continue;
				if (miro[nx][ny] == 0) {
					if (cnt >= visited[nx][ny]) continue;
					visited[nx][ny] = cnt;
				} else {
					if (cnt + 1 >= visited[nx][ny]) continue;
					visited[nx][ny] = cnt+1;
				}
				
				q.offer(new Pos(nx, ny, visited[nx][ny]));
			}
		}
	}

}

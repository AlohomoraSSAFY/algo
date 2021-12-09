package date1209THU;

//BFS 우선순위큐 사용 - 통과 
import java.io.BufferedReader;
import java.io.*;
import java.util.*;

class Pos implements Comparable<Pos>{
	int x;
	int y;
	int c;
	
	public Pos(int x, int y, int c) {
		super();
		this.x = x;
		this.y = y;
		this.c = c;
	}

	@Override
	public int compareTo(Pos o) {
		// TODO Auto-generated method stub
		return this.c - o.c;
	}
	
	
}
public class BOJ1261_4 {
	static int n, m;
	static int map[][];
	static int dx[] = { 0, 0, -1, 1 };
	static int dy[] = { 1, -1, 0, 0 };
	static int answer;
	static int num;
	static int startnode, endnode;
	static int[][] graph;
	static boolean visited[][];

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		m = Integer.parseInt(st.nextToken());
		n = Integer.parseInt(st.nextToken());
		answer = 0;
		map = new int[n][m];
		visited = new boolean[n][m];

		for (int i = 0; i < n; i++) {
			String str = br.readLine();
			for (int j = 0; j < m; j++) {
				map[i][j] = str.charAt(j) - '0';
			}
		}
		
		bfs();
		System.out.println(answer);
	}

	public static void bfs() {
		PriorityQueue<Pos> pq = new PriorityQueue<>();

		pq.offer(new Pos(0,0,0)); // x,y,깬 벽 개수

		visited[0][0] = true;

		while (!pq.isEmpty()) {
			Pos node = pq.poll();

			int x = node.x;
			int y = node.y;
			int t = node.c;

			if(x == n-1 && y ==m-1) {
				answer = t;
				return;
			}
			for (int d = 0; d < 4; d++) {
				
				int nx = x + dx[d];
				int ny = y + dy[d];

				
				if (nx < n & ny < m && nx >= 0 && ny >= 0 && !visited[nx][ny]) {
					visited[nx][ny] = true;
					if (map[nx][ny] == 0) {
						pq.offer(new Pos ( nx, ny, t ));
					} else {
						pq.offer(new Pos( nx, ny, t + 1 ));
					}
				}
			}
		}
	}

}

package study0113;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class SY_BOJ22944 {
	
	static int N;
	static int H;
	static int D;
	static int K;
	static int[][] array;
	static int[] start;
	static int[] end;
	static int[][] d = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
	static int result;
	
	static class Pos {
		int x;
		int y;
		int cnt;
		int force;
		int umbrella;
		int durability;
		
		public Pos(int x, int y, int cnt, int force, int umbrella, int durability) {
			this.x = x;
			this.y = y;
			this.cnt = cnt;
			this.force = force;
			this.umbrella = umbrella;
			this.durability = durability;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		array = new int[N][N];
		start = new int[2];
		end = new int[2];
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < N; j++) {
				char c = str.charAt(j);
				
				if (c == 'S') {
					start[0] = i;
					start[1] = j;
					array[i][j] = 0;
				} else if (c == 'E') {
					end[0] = i;
					end[1] = j;
					array[i][j] = 0;
				} else if (c == 'U') {
					array[i][j] = ++K;
				} else {
					array[i][j] = 0;
				}
			}
		}
		
		bfs();
		System.out.println(result);
	}
	
	public static void bfs() {
		Queue<Pos> q = new LinkedList<>();
		q.offer(new Pos(start[0], start[1], 0, H, 0, 0));
		boolean[][][] visited = new boolean[N][N][(int)Math.pow(2, K)];
		visited[start[0]][start[1]][0] = true;
		
		while (!q.isEmpty()) {
			Pos cur = q.poll();
			int x = cur.x;
			int y = cur.y;
			int cnt = cur.cnt;
			int force = cur.force;
			int umbrella = cur.umbrella;
			int durability = cur.durability;
			
			for (int i = 0; i < 4; i++) {
				int nx = x + d[i][0];
				int ny = y + d[i][1];
				
				if (nx < 0 || nx >= N || ny < 0 || ny >= N) continue;
				if (visited[nx][ny][umbrella]) continue;
				visited[nx][ny][umbrella] = true;
				
				if (nx == end[0] && ny == end[1]) {
					result = cnt + 1;
					return;
				}
				
				int nForce = force;
				int nDurability = durability;
				int nUmbrella = umbrella;
				
				if (array[nx][ny] != 0) {
					nUmbrella = nUmbrella | 1 << (array[nx][ny] - 1);
					nDurability = D;
				}
				
				if (nDurability > 0) {
					nDurability--;
				} else {
					if (--nForce == 0) continue;
				}
				
				q.offer(new Pos(nx, ny, cnt + 1, nForce, nUmbrella, nDurability));
			}
		}
		
		result = -1;
	}

}

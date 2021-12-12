package date1211SUN;

import java.io.*;
import java.util.*;

public class BOJ16197 {
	static int n, m;
	static int dx[] = { 0, 0, -1, 1 };
	static int dy[] = { -1, 1, 0, 0 };
	static int count;
	static char map[][];
	static int answer;
	static int cx1, cx2, cy1, cy2;
	static boolean visited[][][];

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		map = new char[n][m];
		answer = -1;

		boolean next = false;
		for (int i = 0; i < n; i++) {
			String str = br.readLine();
			for (int j = 0; j < m; j++) {
				map[i][j] = str.charAt(j);
				if (map[i][j] == 'o') {
					if (!next) {
						cx1 = i;
						cy1 = j;
						next = true;
					} else {
						cx2 = i;
						cy2 = j;
					}
				}
			}
		}
		//
		bfs();
		if(answer>10)
			answer=-1;
		System.out.println(answer);
	}

	public static void bfs() {
		Queue<int[]> q = new LinkedList<>();
		q.offer(new int[] { cx1, cy1, cx2, cy2, 0 });

		while (!q.isEmpty()) {
			int[] temp = q.poll();
			int x1 = temp[0];
			int y1 = temp[1];
			int x2 = temp[2];
			int y2 = temp[3];
			int cnt = temp[4];

			if (cnt > 10)
				return;

			for (int d = 0; d < 4; d++) {
				int nx1 = x1 + dx[d];
				int nx2 = x2 + dx[d];
				int ny1 = y1 + dy[d];
				int ny2 = y2 + dy[d];
				
				if((!outofmap(nx1, ny1) && outofmap(nx2,ny2)) || (outofmap(nx1, ny1) && !outofmap(nx2,ny2))) { 
					answer = cnt+1;
					return;
				}
				
				else if(!outofmap(nx1, ny1) && !outofmap(nx2, ny2)) { 
					if(map[nx1][ny1] == '#' ) {
						nx1 = x1;
						ny1 = y1;
					}
					if(map[nx2][ny2] == '#' ) {
						nx2 = x2;
						ny2 = y2;
					}
					
					q.offer(new int[] {nx1, ny1, nx2, ny2, cnt+1});
				}
			}
		}
	}

	public static boolean outofmap(int x, int y) {

		if (x >= 0 && x < n && y < m && y >= 0)
			return false;
		else
			return true;
	}

}

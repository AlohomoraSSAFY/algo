package date0923THU;

import java.io.*;
import java.util.*;

class Pos {
	int x;
	int y;
	int d;
	int cnt;

	Pos(int x, int y, int d, int cnt) {
		this.x = x;
		this.y = y;
		this.d = d;
		this.cnt = cnt;

	}
}

public class BOJ6087 {
	static int w, h;
	static char map[][];
	static int min;
	static int visited[][];
	static int dx[] = { 0, 0, -1, 1 };
	static int dy[] = { 1, -1, 0, 0 };
	static int cx1, cx2, cy1, cy2;

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		w = Integer.parseInt(st.nextToken());
		h = Integer.parseInt(st.nextToken());

		map = new char[h][w];
		boolean check = false;
		for (int i = 0; i < h; i++) {
			String str = br.readLine();
			for (int j = 0; j < w; j++) {
				char c = str.charAt(j);
				map[i][j] = c;
				if (c == 'C') {
					if (!check) {
						check = true;
						cx1 = i;
						cy1 = j;
					} else {
						cx2 = i;
						cy2 = j;
					}
				}
			}
		}

		//

		min = Integer.MAX_VALUE;
		visited = new int[h][w];
		for (int i = 0; i < h; i++) {
			for (int j = 0; j < w; j++) {
				visited[i][j] = 10001;
			}
		}
		bfs(cx1, cy1);
		System.out.println(min);
	}

	public static void bfs(int x, int y) {

		visited[x][y] = 0;
		Queue<Pos> q = new LinkedList<>();
		q.offer(new Pos(x, y, -1, -1));
		int d = 0;
		while (!q.isEmpty()) {
			Pos temp = q.poll();
			int curx = temp.x;
			int cury = temp.y;
			int curd = temp.d;
			int curcnt = temp.cnt;

			if (curx == cx2 && cury == cy2) {
				min = Math.min(min, curcnt);
				continue;
			}

			for (int i = 0; i < 4; i++) {
				int nx = curx + dx[i];
				int ny = cury + dy[i];
				if (nx >= 0 && ny >= 0 && nx < h && ny < w && map[nx][ny] != '*') {

					if (curd != i && visited[nx][ny] >= curcnt + 1) {
						visited[nx][ny] = curcnt + 1;
						q.offer(new Pos(nx, ny, i, curcnt + 1));
					} else if (curd == i && visited[nx][ny] >= curcnt) {
						visited[nx][ny] = curcnt;
						q.offer(new Pos(nx, ny, i, curcnt));
					}
				}
			}

		}

	}

}

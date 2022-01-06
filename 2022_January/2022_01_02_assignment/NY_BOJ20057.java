package date0106THU;

import java.io.BufferedReader;
import java.io.*;
import java.util.*;

import javax.swing.plaf.synth.SynthSplitPaneUI;

public class BOJ20057 {

	static int n;
	static int answer;
	static int map[][];

	static int num[][];
	static int cx, cy; // 모래 이동시킬 칸
	static int dx[] = { 0, 1, 0, -1 };// 우하좌상
	static int dy[] = { 1, 0, -1, 0 };
	static int[][] sand = {};
	static boolean visited[][];

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		n = Integer.parseInt(br.readLine());

		answer = 0;
		map = new int[n][n];
		num = new int[n][n];
		visited = new boolean[n][n];

		cx = n / 2;
		cy = (n / 2) + 1;

		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		//

		Stack<int[]> order = new Stack<>();
		int tx = 0;
		int ty = 0;
		int dir = 0;
		visited[tx][ty] = true;
		order.add(new int[] { tx, ty, dir });

		int count = 0;
		while (true) {
			int nx = tx + dx[dir];
			int ny = ty + dy[dir];

			if (nx < 0 || ny < 0 || nx >= n || ny >= n || visited[nx][ny]) {
				dir = (dir + 1) % 4;
				continue;
			}

			order.add(new int[] { nx, ny, (dir + 2) % 4 });
			visited[nx][ny] = true;

			tx = nx;
			ty = ny;

			if (nx == n / 2 && ny == n / 2)
				break;

		}


		while (!order.isEmpty()) { // 토네이도 이동
			int temp[] = order.pop();
			int nx = temp[0];
			int ny = temp[1];
			int d = temp[2];

			if (nx + dx[d] >= 0 && ny + dy[d] >= 0 && nx + dx[d] < n && ny + dy[d] < n
					&& map[nx + dx[d]][ny + dy[d]] != 0)
				sandmove(nx, ny, d);
		}

		System.out.println(answer);

	}

	public static void sandmove(int ox, int oy, int d) {
		int x = ox + dx[d];
		int y = oy + dy[d];
		int amount = map[x][y];

		if (d == 1 || d == 3) { // 상하

			movecheck(x - dx[d], y - 1, (int) (amount * 0.01));
			movecheck(x - dx[d], y + 1, (int) (amount * 0.01));
			map[x][y] -= 2 * ((int) (amount * 0.01));

			movecheck(x, y - 1, (int) (amount * 0.07));
			movecheck(x, y + 1, (int) (amount * 0.07));
			map[x][y] -= 2 * ((int) (amount * 0.07));

			movecheck(x, y - 2, (int) (amount * 0.02));
			movecheck(x, y + 2, (int) (amount * 0.02));
			map[x][y] -= 2 * ((int) (amount * 0.02));

			movecheck(x + dx[d], y - 1, (int) (amount * 0.1));
			movecheck(x + dx[d], y + 1, (int) (amount * 0.1));
			map[x][y] -= 2 * ((int) (amount * 0.1));

			movecheck(x + 2 * dx[d], y, (int) (amount * 0.05));
			map[x][y] -= ((int) (amount * 0.05));

		} else { // 좌우

			movecheck(x - 1, y - dy[d], (int) (amount * 0.01));
			movecheck(x + 1, y - dy[d], (int) (amount * 0.01));
			map[x][y] -= 2 * ((int) (amount * 0.01));

			movecheck(x - 1, y, (int) (amount * 0.07));
			movecheck(x + 1, y, (int) (amount * 0.07));
			map[x][y] -= 2 * ((int) (amount * 0.07));

			movecheck(x - 2, y, (int) (amount * 0.02));
			movecheck(x + 2, y, (int) (amount * 0.02));
			map[x][y] -= 2 * ((int) (amount * 0.02));

			movecheck(x + 1, y + dy[d], (int) (amount * 0.1));
			movecheck(x - 1, y + dy[d], (int) (amount * 0.1));
			map[x][y] -= 2 * ((int) (amount * 0.1));

			movecheck(x, y + 2 * dy[d], (int) (amount * 0.05));
			map[x][y] -= ((int) (amount * 0.05));

		}
		if (x + dx[d] >= 0 && x + dx[d] < n && y + dy[d] < n && y + dy[d] >= 0) {
			map[x + (dx[d])][y + (dy[d])] += map[x][y]; // 알파가 적힌 칸
		} else
			answer += map[x][y];
		map[x][y] = 0; // y가 적힌 칸
	}

	public static void movecheck(int x, int y, int sand) { // 더해줄 모래의 양
		if (x < n && y < n && x >= 0 && y >= 0) { // 맵 안이면 모래 이동
			map[x][y] += sand;
		} else // 맵 바깥이면 정답에 더해줌
			answer += sand;
	}
}

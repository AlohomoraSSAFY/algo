package date1028THU;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
import java.util.Queue;

public class BOJ1941 { // 소문난 칠공주
	static char map[][];
	static int dx[] = { 0, 0, 1, -1 };
	static int dy[] = { 1, -1, 0, 0 };
	static int answer;
	static boolean[][] visited;

	static boolean[] isselected;

	static int[][] pos;
	static int[][] selected;

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		map = new char[5][5];
		answer = 0;
		isselected = new boolean[25];
		selected = new int[7][2];
		pos = new int[25][2];
		int idx = 0;
		for (int i = 0; i < 5; i++) {
			String str = br.readLine();
			for (int j = 0; j < 5; j++) {
				char c = str.charAt(j);
				map[i][j] = c;

				pos[idx][0] = i;
				pos[idx++][1] = j;
			}
		}

		//
		combination(0, 0);
		System.out.println(answer);
	}

	public static void combination(int start, int count) {
		if (count == 7) {
			int sum = 0;
			for (int i = 0; i < 7; i++) {
				int x = selected[i][0];
				int y = selected[i][1];
				if(map[x][y] =='S')
					sum++;
			}

			if (sum < 4) // S가 4개 미만이면 끝
				return;

			else { // 인접해있는지 체크
				visited = new boolean[5][5];
				if (bfs() == 7)
					answer++;
			}

			return;
		}

		for (int i = start; i < 25; i++) {
			if (!isselected[i]) {
				selected[count][0] = pos[i][0];
				selected[count][1] = pos[i][1];
				isselected[i] = true;
				combination(i + 1, count + 1);
				isselected[i] = false;
			}
		}

	}

	public static int bfs() {
		Queue<int[]> q = new LinkedList<>();
		int x = selected[0][0];
		int y = selected[0][1];
		q.offer(new int[] { x, y });
		int cnt = 1;
		visited[x][y] = true;

		while (!q.isEmpty()) {
			int temp[] = q.poll();
			int tx = temp[0];
			int ty = temp[1];

			for (int d = 0; d < 4; d++) {
				int nx = tx + dx[d];
				int ny = ty + dy[d];

				for (int i = 0; i < 7; i++) {
					if (selected[i][0] == nx && selected[i][1] == ny && !visited[nx][ny]) {
						visited[nx][ny] = true;
						cnt++;
						q.offer(new int[] { nx, ny });
					}
				}
			}
		}

		return cnt;
	}

}

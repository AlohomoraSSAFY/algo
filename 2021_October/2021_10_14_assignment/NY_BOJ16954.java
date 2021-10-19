package date1017SUN;

import java.io.*;
import java.util.*;

public class BOJ16954 {
	static char board[][][];

	static int[] dx = { -1, -1, -1, 0, 0, 1, 1, 1, 0 };
	static int[] dy = { -1, 0, 1, -1, 1, -1, 0, 1, 0 };

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		board = new char[9][8][8];
		for (int i = 0; i < 8; i++) {
			String str = br.readLine();
			for (int j = 0; j < 8; j++) {
				board[0][i][j] = str.charAt(j);
			}
		}

		for (int i = 1; i <= 8; i++) {
			movewall(i);
		}

		//

		if (bfs())
			System.out.println(1);
		else
			System.out.println(0);
	}

	public static boolean bfs() {
		Queue<int[]> q = new LinkedList<>();

		q.offer(new int[] { 7, 0, 0 }); // 욱제의 초기 위치

		while (!q.isEmpty()) {
			int[] temp = q.poll();

			int x = temp[0];
			int y = temp[1];
			int sec = temp[2];

			if ((x == 0 && y == 7) || sec > 7)
				return true;

			for (int d = 0; d < 9; d++) {

				int nx = x + dx[d];
				int ny = y + dy[d];

				if (nx < 8 & nx >= 0 && ny < 8 && ny >= 0 && board[sec][nx][ny] != '#'
						&& board[sec + 1][nx][ny] != '#') {
					q.offer(new int[] { nx, ny, sec + 1 });
				}
			}
		}
		return false;
	}

	public static void movewall(int sec) {
		for (int i = 7; i > 0; i--) {
			for (int j = 0; j < 8; j++) {
				board[sec][i][j] = board[sec - 1][i - 1][j];
			}
		}

		for (int i = 0; i < 8; i++) {
			board[sec][0][i] = '.';
		}
	}
}

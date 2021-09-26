package date0926SUN;

import java.io.BufferedReader;
import java.io.*;
import java.util.*;

class Pos {
	int x;
	int y;

	public Pos(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

public class BOJ18428 {

	static int n;
	static char map[][];
	static Pos[] selected;
	static int dx[] = { 0, 0, -1, 1 };
	static int dy[] = { 1, -1, 0, 0 };

	static ArrayList<Pos> teacher;
	static ArrayList<Pos> blank;

	static boolean flag;

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		map = new char[n][n];
		teacher = new ArrayList<>();
		blank = new ArrayList<>();
		selected = new Pos[3];
		flag = false;
		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				char c = st.nextToken().charAt(0);
				map[i][j] = c;

				if (c == 'T')
					teacher.add(new Pos(i, j));
				else if (c == 'X')
					blank.add(new Pos(i, j));
			}
		}

		combination(0, 0);

		String answer = (flag ? "YES" : "NO");

		System.out.println(answer);
	}

	public static void combination(int count, int start) {
		if (count == 3) {
			if (check()) {
				flag = true;
			}
			return;
		}
		for (int i = start; i < blank.size(); i++) {
			map[blank.get(i).x][blank.get(i).y] = 'O';
			combination(count + 1, i + 1);
			map[blank.get(i).x][blank.get(i).y] = 'X';
		}
	}

	public static boolean check() {
		for (Pos p : teacher) {

			for (int d = 0; d < 4; d++) {
				int nx = p.x;
				int ny = p.y;
				while (true) {
					nx += dx[d];
					ny += dy[d];

					if (nx >= 0 && ny >= 0 && nx < n && ny < n) {
						if (map[nx][ny] == 'S')
							return false;
						else if (map[nx][ny] == 'O')
							break;
					}

					else
						break;

				}
			}
		}
		return true;
	}
}

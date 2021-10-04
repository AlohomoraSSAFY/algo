package date1003SUN;

import java.io.BufferedReader;
import java.io.*;
import java.util.*;

public class BOJ14499 {
	static int n, m, x, y, k;
	static int dx[] = { 0, 0, 0, -1, 1 }; // 동 서 남 북
	static int dy[] = { 0, 1, -1, 0, 0 }; // 우 좌 하 상

	static int move[];
	static int map[][];

	static int dicew[];
	static int diceh[];
	static int wupi, wdowni, hupi, hdowni;
	static int upi, downi;

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		// x y 는 주사위 좌표
		x = Integer.parseInt(st.nextToken());
		y = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());

		move = new int[k];
		map = new int[n][m];

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		st = new StringTokenizer(br.readLine());

		for (int i = 0; i < k; i++) {
			move[i] = Integer.parseInt(st.nextToken());
		}

		dicew = new int[4];
		diceh = new int[4];

		//

		upi = 1;
		downi = 3;

		for (int i = 0; i < k; i++) {// 명령 하나
			int dir = move[i];

			int nx = x + dx[dir];
			int ny = y + dy[dir];

			if (nx >= 0 && ny >= 0 && nx < n && ny < m) { // 지도 범위 안이라 이동할 수 있으면
				// 주사위 굴리고 윗면 출력

				// 주사위 굴리기
				roll(dir);

				if (map[nx][ny] == 0) { // 주사위 이동한 칸에 쓰여있는 수가 0 이면
					// 주사위 바닥면에 쓰여있는 수가 칸에 복사됨
					map[nx][ny] = dicew[downi];

				} else { // 0이 아닌 경우에는
					// 칸에 쓰여있는 수가 주사위 바닥면으로 복사

					dicew[downi] = map[nx][ny];
					diceh[downi] = map[nx][ny];
					// 칸에 쓰여있는수는 0이됨
					map[nx][ny] = 0;

				}

				sb.append(upi + " " + downi + "\n");
				sb.append("dicew : " + Arrays.toString(dicew) + "\n");
				sb.append("diceh : " + Arrays.toString(diceh) + "\n");
				sb.append("\n");
				x = nx;
				y = ny;

				if (dir == 1 || dir == 2)
					sb.append(dicew[upi] + "\n");
				else
					sb.append(diceh[upi] + "\n");

			} else {
				System.out.println(nx + " " + ny);
			}

		} // 명령 하나 끝

		System.out.println(sb.toString());
	}

	public static void roll(int dir) {
		int nui = 0;
		int ndi = 0;
		if (dir == 1) { // 동쪽
			nui = upi - 1;
			nui = (nui < 0 ? 3 : nui);
			ndi = downi - 1;
			ndi = (ndi < 0 ? 3 : ndi);
			int temp = diceh[3];
			for (int i = 3; i > 0; i--) {
				diceh[i] = diceh[i - 1];
			}
			diceh[0] = temp;
			diceh[nui] = dicew[nui];
			diceh[ndi] = dicew[ndi];

		} else if (dir == 2) { // 서쪽

			nui = (upi + 1) % 4;
			ndi = (downi + 1) % 4;

			int temp = diceh[0];
			for (int i = 0; i < 3; i++) {
				diceh[i] = diceh[i + 1];
			}
			diceh[3] = temp;
			diceh[nui] = dicew[nui];
			diceh[ndi] = dicew[ndi];

		} else if (dir == 3) { // 북쪽
			nui = (upi + 1) % 4;
			ndi = (downi + 1) % 4;
			int temp = dicew[0];
			for (int i = 0; i < 3; i++) {
				dicew[i] = dicew[i + 1];
			}
			dicew[3] = temp;
			dicew[nui] = diceh[nui];
			dicew[ndi] = diceh[ndi];

		} else if (dir == 4) { // 남쪽
			nui = (upi - 1 < 0 ? 3 : upi - 1);
			ndi = (downi - 1 < 0 ? 3 : downi - 1);
			int temp = dicew[3];
			for (int i = 3; i > 0; i--) {
				dicew[i] = dicew[i - 1];
			}
			dicew[0] = temp;

			dicew[nui] = diceh[nui];
			dicew[ndi] = diceh[ndi];
		}

		upi = nui;
		downi = ndi;
	}
}

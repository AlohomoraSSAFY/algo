package date1017SUN;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ14719 {
	static int h, w;
	static boolean map[][];
	static int answer;

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		h = Integer.parseInt(st.nextToken());
		w = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		map = new boolean[h][w];
		answer = 0;

		for (int i = 0; i < w; i++) {
			int a = Integer.parseInt(st.nextToken());
			for (int j = h - 1; j >= h - a; j--) {
				map[j][i] = true;
			}
		}
		
		for (int i = 0; i < h; i++) {
			for (int j = 0; j < w; j++) {
				if (!map[i][j]) {
					search(i, j);
				}
			}
		}
		System.out.println(answer);
	}

	public static void search(int x, int y) {
		// 현재 칸 기준으로 양옆에 벽이 있으면 answer++;
		int ny = y;

		boolean right = false;
		boolean left = false;

		// 오른쪽
		while (ny < w - 1) {
			ny++;
			if (map[x][ny]) { //벽
				right = true;
				break;
			}
		}

		// 왼쪽
		while (ny > 0) {
			ny--;
			if (map[x][ny]) {
				left = true;
				break;
			}
		}

		if (left && right) //둘 다 벽
			answer++;
	}

}

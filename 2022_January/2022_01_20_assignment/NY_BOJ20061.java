package date0127THU;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.*;

public class BOJ20061 {

	static int n;
	static int blockcount, score;
	static int[][] bmap;
	static int[][] gmap;

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		n = Integer.parseInt(br.readLine());
		ArrayList<int[]> blocks = new ArrayList<>();
		gmap = new int[7][4];
		bmap = new int[4][7];

		score = 0;
		blockcount = 0;
		// 만났을 때 구분하기 쉽게 벽을 1로 해둠
		for (int i = 0; i < 4; i++) {
			gmap[6][i] = 1;
			bmap[i][6] = 1;
		}

		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int t = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());

			blocks.add(new int[] { t, x, y });

			go(t, x, y);

		}

		blockcount += blocksum(bmap);
		blockcount += blocksum(gmap);

		// 출력
		System.out.println(score);
		System.out.println(blockcount - 8);
	}

	public static void go(int t, int x, int y) {
		bluedrop(t, x, y);
		greendrop(t, x, y);
		greenpop();
		bluepop();
		greentopcheck();
		bluetopcheck();
	}

	public static void bluedrop(int t, int x, int y) {
		if (t == 1) { // 1x1 블록
			for (int i = 1; i < 6; i++) { // 열 한 칸씩 이동하며 다른 블록 만나거나 경계인지 확인
				if (bmap[x][i + 1] != 0) {
					bmap[x][i] = 1;
					break;
				}
			}
		} else if (t == 2) { // 1x2 블록
			for (int i = 0; i < 5; i++) { // 열 한 칸씩 이동하며 다른 블록 만나거나 경계인지 확인
				if (bmap[x][i + 2] != 0) {
					bmap[x][i] = 1;
					bmap[x][i + 1] = 1;
					break;
				}
			}
		} else { // 2x1 블록
			for (int i = 1; i < 6; i++) { // 열 한 칸씩 이동하며 다른 블록 만나거나 경계인지 확인
				if (bmap[x][i + 1] != 0 || bmap[x + 1][i + 1] != 0) {
					bmap[x][i] = 1;
					bmap[x + 1][i] = 1;
					break;
				}
			}
		}
	}

	public static void greendrop(int t, int x, int y) {
		if (t == 1) { // 1x1 블록
			for (int i = 1; i < 6; i++) { // 열 한 칸씩 이동하며 다른 블록 만나거나 경계인지 확인
				if (gmap[i + 1][y] != 0) {
					gmap[i][y] = 1;
					break;
				}
			}
		} else if (t == 2) { // 1x2 블록
			for (int i = 1; i < 6; i++) { // 열 한 칸씩 이동하며 다른 블록 만나거나 경계인지 확인
				if (gmap[i + 1][y] != 0 || gmap[i + 1][y + 1] != 0) {
					gmap[i][y] = 1;
					gmap[i][y + 1] = 1;
					break;
				}
			}
		} else { // 2x1 블록
			for (int i = 0; i < 5; i++) { // 열 한 칸씩 이동하며 다른 블록 만나거나 경계인지 확인
				if (gmap[i + 2][y] != 0) {
					gmap[i][y] = 1;
					gmap[i + 1][y] = 1;
					break;
				}
			}
		}
	}

	public static void greenpop() {
		for (int i = 0; i < 6; i++) {
			if (gmap[i][0] == 1 && gmap[i][1] == 1 && gmap[i][2] == 1 && gmap[i][3] == 1) { // 4개 다 1 이면 터뜨림
				gmap[i][0] = 0;
				gmap[i][1] = 0;
				gmap[i][2] = 0;
				gmap[i][3] = 0;
				greendown(i);
				i--;
				score++;
			}
		}
	}

	public static void bluepop() {
		for (int i = 0; i < 6; i++) {
			if (bmap[0][i] == 1 && bmap[1][i] == 1 && bmap[2][i] == 1 && bmap[3][i] == 1) { // 4개 다 1 이면 터뜨림
				bmap[0][i] = 0;
				bmap[1][i] = 0;
				bmap[2][i] = 0;
				bmap[3][i] = 0;
				bluedown(i);
				i--;
				score++;
			}
		}
	}

	public static void greentopcheck() {
		for (int i = 0; i < 2; i++) { // 상단 두 줄 체크
			for (int j = 0; j < 4; j++) {
				if (gmap[i][j] != 0) {
					greendown(5);
					break;
				}
			}
		}
	}

	public static void bluetopcheck() {
		for (int i = 0; i < 2; i++) { // 상단 두 줄 체크
			for (int j = 0; j < 4; j++) {
				if (bmap[j][i] != 0) {
					bluedown(5);
					break;
				}
			}
		}
	}

	public static void greendown(int row) {
		for (int i = row; i > 0; i--) { // 한 칸씩 내려줌
			for (int j = 0; j < 4; j++) {
				gmap[i][j] = gmap[i - 1][j];
			}
		}
		for (int j = 0; j < 4; j++) { // 제일 윗줄 초기화
			gmap[0][j] = 0;
		}
	}

	public static void bluedown(int col) {
		for (int i = col; i > 0; i--) { // 한 칸씩 내려줌
			for (int j = 0; j < 4; j++) {
				bmap[j][i] = bmap[j][i - 1];
			}
		}
		for (int j = 0; j < 4; j++) {
			bmap[j][0] = 0;
		}
	}

	public static void printmap(int[][] map) {
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[0].length; j++) {
				System.out.print(map[i][j]);
			}
			System.out.println();
		}
		System.out.println();
	}

	public static int blocksum(int[][] map) {
		int cnt = 0;
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[0].length; j++) {
				if (map[i][j] != 0)
					cnt++;
			}
		}
		return cnt;
	}
}

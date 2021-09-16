package date0912;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ14500 {

	static int n, m;
	static int max;
	static int map[][];
	static int dx[] = { 0, 0, 1, -1 };
	static int dy[] = { 1, -1, 0, 0 };
	static int tetrominoes[][][] = { { { 0, 1 }, { 0, 2 }, { 0, 3 } }, { { 1, 0 }, { 2, 0 }, { 3, 0 } }, // ㅣ 두종류
			{ { 1, 0 }, { 0, 1 }, { 0, 2 } }, { { 0, 1 }, { 0, 2 }, { 1, 2 } }, { { 1, 0 }, { 1, 1 }, { 1, 2 } },
			{ { 0, 1 }, { 1, 0 }, { 1, 1 } }, // ㅁ 한종류
			{ { 0, 1 }, { 0, 2 }, { -1, 2 } }, // ㄴ 네종류
			{ { 0, 1 }, { 1, 0 }, { 2, 0 } }, { { 0, 1 }, { 1, 1 }, { 2, 1 } }, { { 1, 0 }, { 2, 0 }, { 2, 1 } },
			{ { 1, 0 }, { 2, 0 }, { 2, -1 } }, // L 네종류
			{ { 0, 1 }, { -1, 1 }, { -1, 2 } }, { { 1, 0 }, { 1, 1 }, { 2, 1 } }, { { 0, 1 }, { 1, 1 }, { 1, 2 } },
			{ { 1, 0 }, { 1, -1 }, { 2, -1 } }, // ㄱㄴ네종류
			{ { 1, 0 }, { 1, 1 }, { 2, 0 } }, { { 1, 0 }, { 1, -1 }, { 2, 0 } }, { { 0, 1 }, { 0, 2 }, { -1, 1 } },
			{ { 0, 1 }, { 0, 2 }, { 1, 1 } } }; // ㅗ네종류

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		map = new int[n][m];
		max = 0;
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		//

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {

				for (int t = 0; t < tetrominoes.length; t++) {
					int tetromino[][] = new int[4][2];
					
					tetromino[0][0] = i;
					tetromino[0][1] = j;

					for (int tt = 0; tt < 3; tt++) {
					
						int x = i + tetrominoes[t][tt][0];
						int y = j + tetrominoes[t][tt][1];
						
						tetromino[tt + 1][0] = x;
						tetromino[tt + 1][1] = y;

					}
			
					max = Math.max(max, sum(tetromino));
				}
				
			}
		}
		System.out.println(max);

	}

	public static int sum(int[][] tetromino) {
		int sum = 0;
		for (int i = 0; i < 4; i++) {
			int x = tetromino[i][0];
			int y = tetromino[i][1];

			if (x >= 0 && x < n && y >= 0 && y < m) {
				sum += map[x][y];
			} else
				return 0;
		}
		return sum;
	}

}

package date0909;

import java.io.*;
import java.util.*;

public class BOJ14891 {
	static int k;
	static int gear[][];

	public static void turn(int num, int dir) {
		if (dir ==1 ) { // CW
			int a = gear[num][7];
			for (int i = 7; i > 0; i--) {
				gear[num][i] = gear[num][i - 1];
			}
			gear[num][0] = a;
		} else { // CCW
			int a = gear[num][0];
			for (int i = 0; i < 7; i++) {
				gear[num][i] = gear[num][i + 1];
			}
			gear[num][7] = a;
		}
	}

	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		gear = new int[5][8];

		for (int i = 1; i <= 4; i++) {
			String str = br.readLine();
			for (int j = 0; j < 8; j++) {
				gear[i][j] = str.charAt(j) - '0';
			}
		}

		k = Integer.parseInt(br.readLine());

		for (int i = 0; i < k; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int num = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int dd = 0;
			int[] flag = new int[5];

			if (d == 1)
				dd = -1;
			else
				dd = 1;

			flag[num] = d;

			if (num == 1) {
				if (gear[1][2] != gear[2][6]) {
					flag[2] = dd;
					if (gear[2][2] != gear[3][6]) {
						flag[3] = d;
						if (gear[3][2] != gear[4][6]) {
							flag[4] = dd;
						}
					}
				}
			}

			else if (num == 2) {
				if (gear[1][2] != gear[2][6]) {
					flag[1] = dd;
				}
				if (gear[2][2] != gear[3][6]) {
					flag[3] = dd;
					if (gear[3][2] != gear[4][6]) {
						flag[4] = d;
					}
				}
			}

			else if (num == 3) {
				if (gear[3][2] != gear[4][6]) {
					flag[4] = dd;
				}
				if (gear[2][2] != gear[3][6]) {
					flag[2] = dd;
					if (gear[2][6] != gear[1][2]) {
						flag[1] = d;
					}
				}
			}

			else {
				if (gear[3][2] != gear[4][6]) {
					flag[3] = dd;
					if (gear[2][2] != gear[3][6]) {
						flag[2] = d;
						if (gear[1][2] != gear[2][6]) {
							flag[1] = dd;
						}
					}
				}
			}

			for (int j = num; j <= num+3; j++) {
				int c =j%4+1;
				if (flag[c] != 0) {
					turn(c, flag[c]);
				}
			}

		}

		int result = 0;

		for (int i = 1; i <= 4; i++) {
			if (gear[i][0] == 1) {
				result += (1 << (i - 1));
			}
		}
		System.out.println(result);
	}

}

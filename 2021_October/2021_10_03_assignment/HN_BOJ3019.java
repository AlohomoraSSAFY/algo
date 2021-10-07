package com.baekjoon.problem20;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class HN_BOJ3019 {
	static int C, P, answer;
	static int[] height;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		C = Integer.parseInt(st.nextToken());
		P = Integer.parseInt(st.nextToken());

		height = new int[C];
		st = new StringTokenizer(br.readLine());
		for (int c = 0; c < C; c++) {
			height[c] = Integer.parseInt(st.nextToken());
		}

		switch (P) {
		case 1:
			// 체크해야하는 가로길이
			flat(1);
			flat(4);
			break;
		case 2:
			// 가로 길이와 홈 0, 1, 2
			flat(2);
			break;
		case 3:
			// reft, right, mid
			leftSlope(1, 1, 1);
			rightSlope(2, 1, 1);
			break;
		case 4:
			// 첫번째 변 길이, 두번째 변 길이, 첫째와 둘째 사이의 높이의 갭
			leftSlope(1, 2, 1);
			rightSlope(1, 1, 1);
			break;
		case 5:
			flat(3);
			leftSlope(1, 1, 1);
			rightSlope(1, 1, 1);
			midSlope();
			break;
		case 6:
			flat(3);
			flat(2);
			rightSlope(1, 2, 1);
			leftSlope(1, 1, 2);
			break;
		case 7:
			flat(3);
			flat(2);
			leftSlope(2, 1, 1);
			rightSlope(1, 1, 2);
			break;
		}

		System.out.println(answer);
	}

	private static void midSlope() {
		for (int c = 0; c < C - 2; c++) {
			if (height[c + 1] - height[c] == -1 && height[c + 2] - height[c + 1] == 1) {
				answer += 1;
			}
		}
	}

	private static void rightSlope(int f, int s, int g) {
		int ow = 1, sw = 1;
		boolean check = false;

		for (int c = 0; c < C - 1; c++) {
			if (check && sw >= s) {
				answer += 1;
				check = false;
			}

			if (height[c + 1] - height[c] == 0) {
				ow++;
				sw++;
			} else {
				if (height[c + 1] - height[c] == (1 * g) && ow >= f) {
					check = true;
					sw = 1;
				} else {
					check = false;
				}
				ow = 1;
			}
		}

		if (check && sw >= s) {
			answer += 1;
		}
	}

	private static void leftSlope(int f, int s, int g) {
		int ow = 1, sw = 1;
		boolean check = false;

		for (int c = 0; c < C - 1; c++) {
			if (check && sw >= s) {
				answer += 1;
				check = false;
			}

			if (height[c + 1] - height[c] == 0) {
				ow++;
				sw++;
			} else {
				if (height[c + 1] - height[c] == (-1 * g) && ow >= f) {
					check = true;
					sw = 1;
				} else {
					check = false;
				}
				ow = 1;
			}
		}

		if (check && sw >= s) {
			answer += 1;
		}
	}

	private static void flat(int w) {
		int ow = 1;
		for (int c = 0; c < C - 1; c++) {
			if (height[c + 1] - height[c] == 0) {
				ow++;
			} else {
				if (ow - w >= 0) {
					answer += (ow - w + 1);
				}
				ow = 1;
			}
		}

		if (ow - w >= 0) {
			answer += (ow - w + 1);
		}
	}

}

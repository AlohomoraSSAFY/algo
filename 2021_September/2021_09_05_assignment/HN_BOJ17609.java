package com.baekjoon.problem12;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class HN_BOJ17609 {
	static int T, start, end, remainL, result;
	static int r[][];
	static char[] input;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringBuilder sb = new StringBuilder();
		T = Integer.parseInt(br.readLine());
		for (int tc = 0; tc < T; tc++) {
			input = br.readLine().toCharArray();
			result = start = 0;
			remainL = input.length;
			end = remainL - 1;

			r = new int[2][2];
			result = check(start, end, remainL, 0);

			sb.append(result).append("\n");
		}
		System.out.println(sb);
	}

	static int check(int s, int e, int length, int depth) {
		boolean s1 = false;
		boolean s2 = false;
		
		if (depth >= 2) {
			return 2;
		}

		while (length > 1) {
			char a = input[s];
			char b = input[e];

			if (a == b) {
				s++;
				e--;
				length -= 2;
			} else {
				// step 1
				char c = input[s + 1];
				if (c == b) {
					s1 = true;
					r[depth][0] = check(s + 2, e - 1, length - 3, depth + 1);
				}
				// step 2
				c = input[e - 1];
				if (c == a) {
					s2 = true;
					r[depth][1] = check(s + 1, e - 2, length - 3, depth + 1);
				}

				if(s1 && s2) {
					return Math.min(r[depth][0], r[depth][1]);
				}else if(s1) {
					return r[depth][0];
				}else if(s2) {
					return r[depth][1];
				}
				// step 3 유사회문 아님
				return 2;
			}
		}

		if (depth == 1) {
			// depth 1일 때 check를 통과했으면 1
			return 1;
		}
		
		return 0;
	}
}

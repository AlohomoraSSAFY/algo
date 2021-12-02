package com.baekjoon.problem35;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class HN_BOJ6443 {
	static int N;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		for (int n = 0; n < N; n++) {
			char tmp[] = br.readLine().toCharArray();

			Arrays.sort(tmp);
			sb.append(String.valueOf(tmp)).append("\n");

			find(tmp);
		}

		System.out.println(sb);
	}

	private static void find(char[] tmp) {
		int len = tmp.length;
		int i, j;
		while (true) {
			i = len - 1;

			while (i > 0 && (tmp[i - 1] >= tmp[i])) {
				i--;
			}

			if (i == 0)
				break;

			i--;

			j = len - 1;
			while (j > 0 && (tmp[j] <= tmp[i])) {
				j--;
			}

			if (j == 0)
				break;

			char tmp2 = tmp[i];
			tmp[i] = tmp[j];
			tmp[j] = tmp2;

			int start = i + 1;
			int end = len-1;
			while (start < end) {
				tmp2 = tmp[end];
				tmp[end] = tmp[start];
				tmp[start] = tmp2;
				start++;
				end--;
			}

			sb.append(String.valueOf(tmp)).append("\n");
		}
	}

}

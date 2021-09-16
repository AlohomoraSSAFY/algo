package com.day1;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class HN_BOJ5052_2 {
	static int T, N;
	static char[][] numlist;
	static int[] length;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int tc = 0; tc < T; tc++) {
			N = Integer.parseInt(br.readLine());
			numlist = new char[N][];
			length = new int[N];
			for (int n = 0; n < N; n++) {
				numlist[n] = br.readLine().toCharArray();
				length[n] = numlist[n].length;
			}
			
			// System.out.println(Arrays.deepToString(numlist));
			boolean available = true;
			check : for (int i = 0; i < N; i++) {
				int len1 = length[i];
				for (int j = i + 1; j < N; j++) {
					int len2 = length[j];
					if(len1 == len2) {
						available = true;
						continue;
					}
					int minLen = Math.min(len1, len2);
					available = false;
					for (int l = 0; l < minLen; l++) {
						if (numlist[i][l] != numlist[j][l]) {
							available = true;
							break;
						}
					}
					
					if(!available) {
						break check;
					}
				}
			}
			
			sb.append(available ? "YES":"NO").append("\n");
		} // 테스트 케이스 종료
		System.out.println(sb);
	}

}

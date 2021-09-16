package com.baekjoon.problem13;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

public class HN_BOJ1038 {
	static int[] selected;
	static List<Long> numList = new LinkedList<>();
	public static void main(String[] args) throws Exception {
		// 자릿수에 따른 감소하는 수 전부 뽑기
		for (int m = 1; m <= 10 ; m++) {
			selected = new int[m + 1];
			selected[m] = -1;
			Permu(m, 0);
		}
		// 9876543210 까지
		// 10번째   1번째자리수
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int idx = Integer.parseInt(br.readLine());
		if(idx < numList.size())
			System.out.println(numList.get(idx));
		else 
			System.out.println(-1);
	}
	
	private static void Permu(int max, int cnt) {
		// max 3 cnt 3
		if(cnt == max) {
			long result = 0;
			for (int m = 0; m < max; m++) {
				// s[0] = 3 s[1] = 2 , s[2] = 1
				result += (long)selected[m]*(long)Math.pow(10, max - m - 1);
				// 3 * 10 ^(3- 0 -1) = 300
				// 2 * 10 ^(3 - 1- 1) = 20
				// 1 * 10 ^(3 - 2- 1) = 1
				// result == 321
			}
			numList.add(result);
			return;
		}
		
		for (int i = 0; i <= 9; i++) {
			selected[cnt] = i;
			// s[0] = 2 s[1] = 3 --> return 
			// s[0] = 9 s[1] = 0 ~ 8
			if(cnt != 0 && selected[cnt-1] <= selected[cnt])
				return;
			// 감소하는 수
			Permu(max, cnt+1);
		}
		
	}

}

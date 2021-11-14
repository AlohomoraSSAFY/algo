package com.baekjoon.problem32;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

public class HN_BOJ6588 {
	static int input;
	static List<Integer> odds = new LinkedList<>();
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// 1. 홀수 소수 구하기
		// => 2로 나눠지거나 다른 소수로 나눠지면 OUT
		boolean check;
		for (int o = 3; o < 1000; o++) {
			if (o % 2 == 0)
				continue;
			
			check = true;
			for (int i = 0; i < odds.size(); i++) {
				if(odds.get(i) > (int)Math.sqrt(o))
					break;
				
				if (o % odds.get(i) == 0) {
					check = false;
					break;
				}
			}
			
			if(!check)
				continue;
			
			odds.add(o);
		}

		int a, b;
		while (true) {
			input = Integer.parseInt(br.readLine());

			if (input == 0)
				break;

			// a 뽑기, N-A = B
			// 나머지 B가 홀수 소수인지에 대해 검증한다.
			boolean found = true;
			for (int o = 0; o < odds.size(); o++) {
				found = true;
				a = odds.get(o);
				b = input - a;

				int ref;
				for (int o2 = 0; o2 < odds.size(); o2++) {
					ref = odds.get(o2);
					
					if(b > ref) {
						if (b % ref == 0) {
							found = false;
							break;
						}
					}else if(b == ref) {
						break;
					}else {
						found = false;
						break;
					}
				}

				if (found) {
					sb.append(input).append(" = ").append(a).append(" + ").append(b).append("\n");
					break;
				}
			}

			if (!found) {
				sb.append("Goldbach's conjecture is wrong.").append("\n");
			}
		}

		System.out.println(sb);
	}

}

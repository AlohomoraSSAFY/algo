package com.baekjoon.problem15;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class HN_BOJ2156 {
	static int N;
	static long answer;
	static long[] data;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		data = new long[N + 1];
		for (int i = 1; i <= N; i++) {
			data[i] = Integer.parseInt(br.readLine());
		}

		answer = 0;
		drink(N);
		System.out.println(answer);
	}

	private static void drink(int n) {
		long includeMe[] = new long[N + 1];
		
		if(n == 1) {
			answer = data[1];
		}else if(n == 2) {
			answer = data[1] + data[2];
		}else {
			long a = data[1] + data[2], b = data[1] + data[3], c = data[2] + data[3];
			includeMe[1] = data[1];
			includeMe[2] = data[1] + data[2];
			includeMe[3] = Math.max(b, c);
			
			// (tmp0/ a) = 연속으로 연결된 값이 아무것도 없음 (나를 붙일 수 있음)
			// (tmp1/ b) = 숫자가 1번 연속으로 연결됨 (나를 붙일 수 있음)
			// (tmp2/ c) = 숫자가 2번 연속으로 연결됨 (나를 붙일 수 없음)
			for (int i = 4; i <= N; i++) {
				long tmp1 = a + data[i]; 
				long tmp2 = b + data[i]; 
				includeMe[i] = Math.max(tmp1, tmp2);
				long tmp0 = Math.max(includeMe[i-2], includeMe[i-1]);
				
				a = tmp0; 
				b = tmp1; 
				c = tmp2; 
			}
			answer = Math.max(a, Math.max(b, c));
		}
	}

}

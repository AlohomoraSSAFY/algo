package com.baekjoon.problem24;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class HN_BOJ14719 {
	static int H, W, answer;
	static int input[];
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		H = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());

		input = new int[W];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < W; i++) {
			input[i] = Integer.parseInt(st.nextToken());
		}

		answer = 0;

		gogo(input);

		System.out.println(answer);
	}

	private static void gogo(int[] data) {
		Queue<Integer> q = new LinkedList<>();
		int len = data.length;
		
		// 3 4 5 2 2 6 q에 6만들어가 있는 사이즈 1
		for (int i = 0; i < len; i++) {
			if (q.isEmpty()) {
				q.add(data[i]);
			} else {
				int ref = q.peek();
				if (ref <= data[i]) {
					int size = q.size();
					if (size == 1) {
						// 빗물이 고일 수 없음
						q.poll();
					} else {
						// 고인 빗물 반영
						// 5-5 5-2 5-2 => 6
						for (int s = 0; s < size; s++) {
							answer += (ref - q.poll());
						}
					}
				}
				q.add(data[i]);
			}
		} // for
		
		// 6 2 2 4 => 4
		// 4 2 2 6 => 고인 빗물을 다시 계산
		int size = q.size();
		if (size != 1) {
			int d[] = new int[size];
			for (int i = size - 1; i >= 0; i--) {
				d[i] = q.poll();
			}
			gogo(d);
		}
	}
}

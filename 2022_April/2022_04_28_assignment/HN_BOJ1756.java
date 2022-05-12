package com.baekjoon.problem58;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class HN_BOJ1756 {
	static int D, N, answer;
	static Long[] oven;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		D = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());

		oven = new Long[D + 1];
		oven[0] = 0L;
		Long max = Long.MAX_VALUE;

		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= D; i++) {
			max = Long.min(max, Long.parseLong(st.nextToken()));
			oven[i] = max;
		}

		Queue<Long> q = new LinkedList<>();
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			q.add(Long.parseLong(st.nextToken()));
		}

		int end = D;
		boolean pass = false;
		while (end > 0 && q.size() > 0) {
			Long cur = q.poll();
			pass = false;

			while (end > 0) {
				if (oven[end--] >= cur) {
					pass = true;
					break;
				}
			}
		}

		answer = end + 1;
		// 도우가 남아있거나, 마지막 도우가 들어가지 않은 경우...
		if (q.size() > 0 || !pass)
			answer = 0;

		System.out.println(answer);
	}

}

package com.baekjoon.problem28;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class HN_BOJ1700 {
	static int N, K, answer;
	static int[] input;
	static int[] cnt;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		input = new int[K];
		st = new StringTokenizer(br.readLine());

		cnt = new int[K + 1];
		for (int k = 0; k < K; k++) {
			input[k] = Integer.parseInt(st.nextToken());
			cnt[input[k]]++;
		}

		answer = 0;
		Set<Integer> set1 = new TreeSet<>(); 
		Set<Integer> set2 = new TreeSet<>();
		int sp = 0, ep = 0, n = 0;
		while (sp < K) {
			while (sp < K && (set1.size() < N || set1.contains(input[sp]))) {
				set1.add(input[sp]);
				cnt[input[sp]]--;
				sp++;
			}

			if (sp == K)
				break;

			// N, 새로운 애를 만나서 뽑아야함
			ep = sp;
			set2.clear();
			while (ep < K && set2.size() < N) {
				set2.add(input[ep]);
				ep++;
			}

			int min = Integer.MAX_VALUE;
			int idx = -1;
			for (Integer i : set1) {
				if (min > cnt[i] && !set2.contains(i)) {
					min = cnt[i];
					idx = i;
				}
			}

			if (idx != -1) {
				set1.remove(idx);
				answer++;
			} else {
				for (Integer i : set1) {
					cnt[i]--;
				}
				sp += N;
			}
		}

		System.out.println(answer);
	}

}

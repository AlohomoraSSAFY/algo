package com.baekjoon.problem41;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class HN_BOJ1068 {
	static boolean notLeaf[];
	static int N, rootIdx, answer;
	static int[] tree, child;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		notLeaf = new boolean[N];
		tree = new int[N];
		child = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int n = 0; n < N; n++) {
			int temp = Integer.parseInt(st.nextToken());
			tree[n] = temp;
			if (temp == -1) {
				rootIdx = n;
			} else {
				notLeaf[temp] = true;
			}
		}

		Queue<Integer> q = new LinkedList<>();
		for (int n = 0; n < N; n++) {
			if (!notLeaf[n]) {
				answer++;
				q.add(n);
			}
		}

		while (!q.isEmpty()) {
			int idx = q.poll();
			child[idx] = 1;
			while (tree[idx] != -1) {
				child[tree[idx]] += 1;
				idx = tree[idx];
			}
		}

		int d = Integer.parseInt(br.readLine());
		if (tree[d] != -1 && child[tree[d]] == 1) {
			answer++;
		}
		System.out.println(answer - child[d]);
	}

}

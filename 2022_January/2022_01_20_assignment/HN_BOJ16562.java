package com.baekjoon.problem48;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class HN_BOJ16562 {
	static int N, M, K, cnt;
	static int parents[];
	static LinkedList<Integer> list[];
	static boolean visited[];

	static class Friend {
		int no, cost;

		public Friend(int no, int cost) {
			this.no = no;
			this.cost = cost;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		parents = new int[N + 1];
		list = new LinkedList[N + 1];
		visited = new boolean[N + 1];
		for (int i = 0; i <= N; i++) {
			parents[i] = i;
			list[i] = new LinkedList<>();
		}
		PriorityQueue<Friend> pq = new PriorityQueue<>((f1, f2) -> Integer.compare(f1.cost, f2.cost));
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			pq.add(new Friend(i, Integer.parseInt(st.nextToken())));
		}

		for (int m = 0; m < M; m++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			list[from].add(to);
			list[to].add(from);
		}

		boolean result = true;
		int costSum = 0;
		cnt = 0;
		while (!pq.isEmpty()) {
			Friend f = pq.poll();
			if (union(0, f.no)) {
				costSum += f.cost;
				connection(f.no);
				cnt++;
				if (costSum > K) {
					result = false;
					break;
				}
				if (cnt == N) {
					break;
				}
			}
		} // while
		if (result) {
			System.out.println(costSum);
		} else {
			System.out.println("Oh no");
		}
	}

	private static void connection(int no) {
		Queue<Integer> q = new LinkedList<>();
		visited[no] = true;
		q.add(no);

		while (!q.isEmpty()) {
			int cur = q.poll();

			for (int next : list[cur]) {
				if (!visited[next]) {
					parents[next] = 0;
					visited[next] = true;
					cnt++;
					q.add(next);
				}
			}
		}
	}

	private static boolean union(int a, int b) {
		int pa = find(a);
		int pb = find(b);

		if (pa != pb) {
			parents[pb] = pa;
			return true;
		}
		return false;
	}

	private static int find(int a) {
		if (parents[a] == a) {
			return a;
		}
		return parents[a] = find(parents[a]);
	}

}

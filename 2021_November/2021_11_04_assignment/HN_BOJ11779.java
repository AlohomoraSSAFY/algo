package com.baekjoon.problem30;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;
// 30% 시간초과
public class HN_BOJ11779 {
	static int N, M, start, goal;
	static long minCost[];
	static boolean visited[];
	static class Way implements Comparable<Way> {
		int from, to;
		long cost;

		public Way(int from, int to, long cost) {
			this.from = from;
			this.to = to;
			this.cost = cost;
		}

		@Override
		public int compareTo(Way o) {
			return Long.compare(this.cost, o.cost);
		}

	}

	static Queue<Way> Ways[];
	static StringBuilder sb = new StringBuilder();
	static int parent[];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());

		Ways = new LinkedList[N + 1];
		minCost = new long[N + 1];
		visited = new boolean[N + 1];
		parent = new int[N + 1];
		for (int n = 0; n <= N; n++) {
			Ways[n] = new LinkedList<>();
		}

		StringTokenizer st;
		int from, to, value;
		for (int m = 0; m < M; m++) {
			st = new StringTokenizer(br.readLine());
			from = Integer.parseInt(st.nextToken());
			to = Integer.parseInt(st.nextToken());
			value = Integer.parseInt(st.nextToken());

			Ways[from].add(new Way(from, to, value));
		}
		st = new StringTokenizer(br.readLine());
		start = Integer.parseInt(st.nextToken());
		goal = Integer.parseInt(st.nextToken());
		
		dikjstra();

		sb.append(minCost[goal]).append("\n");

		Stack<Integer> stack = new Stack();
		stack.add(goal);
		int now = goal;
		int next = parent[now];
		while (next != -1) {
			stack.add(next);
			now = next;
			next = parent[now];
		}
		int size = stack.size();
		sb.append(size).append("\n");
		for (int r = 0; r < size; r++) {
			sb.append(stack.pop()).append(" ");
		}
		System.out.println(sb);
	}

	private static void dikjstra() {
		Arrays.fill(minCost, Long.MAX_VALUE);
		PriorityQueue<Way> pq = new PriorityQueue<>();
		minCost[start] = 0;
		pq.add(new Way(-1, start, 0));
		int idx;
		Way w = null;
		
		while (true) {
			idx = -1;
			w = pq.poll();
			if (visited[w.to]) {
				continue;
			}
			idx = w.to;

			visited[idx] = true;
			parent[idx] = w.from;
			if (idx == goal) {
				return;
			}

			while(!Ways[idx].isEmpty()) {
				w = Ways[idx].poll();
				if(visited[w.to])
					continue;
				
				if (minCost[w.to] > minCost[idx] + w.cost) {
					minCost[w.to] = minCost[idx] + w.cost;
					pq.add(new Way(w.from, w.to, minCost[w.to]));
				}				
			}
		}
	}

}

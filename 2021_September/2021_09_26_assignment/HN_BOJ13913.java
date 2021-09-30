package com.baekjoon.problem18;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class HN_BOJ13913 {
	static int N, K;
	static boolean visited[] = new boolean[100001];

	static class Point {
		int x;
		List<Integer> l;

		public Point(int x, List<Integer> l) {
			this.x = x;
			this.l = l;
		}
	}

	static Queue<Point> q = new LinkedList<>();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		// 가장 빠른 시간, BFS
		// 어떻게 이동하는지 공백으로 구분 출력하기
		if (N < K) {
			List<Integer> tmp = new LinkedList<>();
			tmp.add(N);

			q.add(new Point(N, tmp));
			visited[N] = true;

			int level = -1;
			move: while (!q.isEmpty()) {
				int size = q.size();
				level++;

				for (int s = 0; s < size; s++) {
					Point p = q.poll();

					if (p.x == K) {
						tmp = p.l;
						break move;
					}

					int nx = p.x * 2;
					if (nx <= 100000 && !visited[nx]) {
						visited[nx] = true;
						
						tmp = copyList(p.l);
						tmp.add(nx);
						q.add(new Point(nx, tmp));
					}
					
					nx = p.x + 1;
					if (nx <= 100000 && !visited[nx]) {
						visited[nx] = true;

						tmp = copyList(p.l);
						tmp.add(nx);
						q.add(new Point(nx, tmp));
					}

					nx = p.x - 1;
					if (nx >= 0 && !visited[nx]) {
						visited[nx] = true;

						tmp = copyList(p.l);
						tmp.add(nx);
						q.add(new Point(nx, tmp));
					}

				}
			}

			System.out.println(level);
			StringBuilder sb = new StringBuilder();
			for (Integer i : tmp) {
				sb.append(i).append(" ");
			}
			System.out.println(sb);
		}else {
			System.out.println(N-K);
			for (int i = N; i >= K; i--) {
				System.out.print(i+" ");
			}
		}
	}

	private static List<Integer> copyList(List<Integer> l) {
		List<Integer> clist = new LinkedList<>();
		for (Integer i : l) {
			clist.add(i);
		}

		return clist;
	}
}

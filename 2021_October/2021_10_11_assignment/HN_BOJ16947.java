package com.baekjoon.problem23;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.TreeSet;

public class HN_BOJ16947 {
	static List<Integer> el[];
	static int[] distance, cnt;
	static int N;
	static boolean visited[];
	static Queue<Integer> q = new LinkedList<>();
	static Set<Integer> circle = new TreeSet<>();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		el = new LinkedList[N + 1];
		cnt = new int[N + 1];
		distance = new int[N + 1];
		
		for (int i = 0; i <= N; i++) {
			el[i] = new LinkedList<>();
		}

		StringTokenizer st;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());

			el[from].add(to);
			el[to].add(from);
			cnt[from]++;
			cnt[to]++;
		}
		
		// System.out.println(Arrays.toString(cnt));
		// cnt가 3 이상인 지점을 기준으로 탐색, 순환선 탐색
		for (int i = 1; i <= N; i++) {
			if (cnt[i] >= 3) {
				q.add(i);
			}
		}

		visited = new boolean[N + 1];
		while (!q.isEmpty()) {
			Integer start = q.poll();

			Set<Integer> line = new HashSet<>();
			dfs(start, start, 0, line);
		}
		
		// 거리 갱신
		visited = new boolean[N + 1];
		for (Integer c : circle) {
			if(!visited[c]) {
				bfs(c);
			}
		}

		StringBuilder sb = new StringBuilder();
		for (int i = 1; i <= N; i++) {
			sb.append(distance[i]).append(" ");
		}

		System.out.println(sb);
	}

	private static void bfs(Integer start) {
		visited[start] = true;
		distance[start] = 0;
		q = new LinkedList<>();
		q.add(start);
		
		while(!q.isEmpty()) {
			Integer now = q.poll();
			
			for (int i = 0; i < el[now].size(); i++) {
				int next = el[now].get(i);
				
				if(!visited[next]) {
					visited[next] = true;
					q.add(next);
					if(circle.contains(next)) {
						distance[next] = 0;
					}else {
						distance[next] = distance[now] + 1;
					}
				}
			}
		}
		
	}

	private static void dfs(Integer start, Integer now, int depth, Set<Integer> line) {
		visited[now] = true;
		line.add(now);

		for (int i = 0; i < el[now].size(); i++) {
			int next = el[now].get(i);

			// depth 2개 이상일 떄 나를 만나는 것!...
			if (depth >= 2 && next == start) {
				// 순환선 찾음
				for (Integer l : line) {
					circle.add(l);
				}
			} else if (!visited[next]) {
				dfs(start, next, depth + 1, line);
			}
		}
		
		line.remove(now);
		return;
	}

}

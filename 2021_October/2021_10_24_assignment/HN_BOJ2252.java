package com.baekjoon.problem27;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/* 반례
8 8 
1 2 
2 3
3 7 
3 8 
2 6 
1 4
4 5 
6 3
* */
// 접근을 잘못함.. 부모를 저장해야함
public class HN_BOJ2252_2 {
	static int N, M;
	static int in[];
	static List<Integer> V = new LinkedList<>();
	static Queue<Integer> slist = new LinkedList<>();
	static ArrayList<Integer> parents[];
	static StringBuilder sb;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		parents = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++) {
			parents[i] = new ArrayList<>();
			V.add(i);
		}

		in = new int[N + 1];
		int c, p;
		for (int m = 0; m < M; m++) {
			st = new StringTokenizer(br.readLine());
			c = Integer.parseInt(st.nextToken());
			p = Integer.parseInt(st.nextToken());
			parents[c].add(p);
			in[p]++;
		}

		sb = new StringBuilder();
		while (!V.isEmpty()) {
			for (Integer v : V) {
				if (in[v] != 0) {
					continue;
				}
				// 진입 차수가 0인 정점을 선택
				slist.add(v);
			}
			bfs();
		}

		System.out.println(sb);
	}

	// 위상 정렬
	private static void bfs() {
		while (!slist.isEmpty()) {
			Integer s = slist.poll();
			sb.append(s).append(" ");
			V.remove(s);

			for (Integer p : parents[s]) {
				in[p]--;
				if (in[p] == 0) {
					slist.add(p);
				}
			}
		}
	}

}

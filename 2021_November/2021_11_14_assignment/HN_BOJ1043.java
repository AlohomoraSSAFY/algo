package com.baekjoon.problem33;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;
import java.util.StringTokenizer;

public class HN_BOJ1043 {
	static int N, M, pnum, answer;
	static boolean visited[];
	static LinkedList<Set> plist = new LinkedList<>();
	static Set<Integer> tset = new HashSet<>();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		pnum = Integer.parseInt(st.nextToken());

		for (int p = 0; p < pnum; p++) {
			tset.add(Integer.parseInt(st.nextToken()));
		}

		for (int m = 0; m < M; m++) {
			st = new StringTokenizer(br.readLine());
			pnum = Integer.parseInt(st.nextToken());

			Set hs = new HashSet<Integer>();
			for (int p = 0; p < pnum; p++) {
				hs.add(Integer.parseInt(st.nextToken()));
			}
			plist.add(hs);
		}

		answer = 0;
		boolean end = false, contain = false;
		visited = new boolean[M];
		while (!end) {
			end = true;
			for (int m = 0; m < M; m++) {
				if (!visited[m]) {
					Set<Integer> hs = plist.get(m);
					contain = false;
					for (Integer t : tset) {
						if (hs.contains(t)) {
							end = false;
							contain = true;
							break;
						}
					}

					if (contain) {
						answer++;
						visited[m] = true;
						for (Integer h : hs) {
							tset.add(h);
						}
					}

				} // for
			} // for
		} // while
		
		System.out.println(M-answer);
	}

}

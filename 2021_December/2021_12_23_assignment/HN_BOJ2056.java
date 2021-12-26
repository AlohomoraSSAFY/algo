package com.baekjoon.problem42;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class HN_BOJ2056 {
	static int N, answer;
	static int parents[], remainTime[];
	static List<Integer> list[];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		parents = new int[N + 1];
		remainTime = new int[N + 1];
		list = new LinkedList[N + 1];
		for (int n = 1; n <= N; n++) {
			list[n] = new LinkedList<>();
		}

		StringTokenizer st;
		int pNum;
		for (int n = 1; n <= N; n++) {
			st = new StringTokenizer(br.readLine());
			remainTime[n] = Integer.parseInt(st.nextToken());
			parents[n] = Integer.parseInt(st.nextToken());
			for (int p = 0; p < parents[n]; p++) {
				pNum = Integer.parseInt(st.nextToken());
				list[pNum].add(n);
			}
		}

		PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
			public int compare(int[] i1, int[] i2) {
				return Integer.compare(i1[1], i2[1]);
			}
		});

		int cnt = 1;
		int curTime = 0;
		boolean find = true;
		answer = 0;
		while (cnt <= N || !pq.isEmpty()) {
			while (!pq.isEmpty() && curTime >= pq.peek()[1]) {
				int temp[] = pq.poll();
				Iterator<Integer> it = list[temp[0]].iterator();
				int size = list[temp[0]].size();
				while (size-- > 0) {
					parents[it.next()]--;
				}
				answer = temp[1];
				find = true;
			}
			
			if (find) {
				for (int n = 1; n <= N; n++) {
					if (parents[n] == 0) {
						parents[n] = -1;
						pq.add(new int[] { n, curTime + remainTime[n] });
						cnt++;
					}
				}
				find = false;
			}

			curTime++;
		}

		System.out.println(answer);
	}

}

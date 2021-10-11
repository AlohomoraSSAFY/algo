package com.baekjoon.problem19;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class HN_BOJ13164 {
	static class Point implements Comparable<Point>{
		int height;

		public Point(int height) {
			this.height = height;
		}
		
		public int compareTo(Point p) {
			return Integer.compare(p.height, this.height);
		}
	}
	static PriorityQueue<Point> pq = new PriorityQueue<>();
	static int N, K, answer;
	static int[] child;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		child = new int[N];
		st = new StringTokenizer(br.readLine());
		answer = 0;
		for (int i = 0; i < N; i++) {
			child[i] = Integer.parseInt(st.nextToken());
		}
		
		// 아이들의 키 간격을 pq에 저장하고, 비용을 증가시킨다.
		for (int i = 0; i < N-1; i++) {
			int tmp = child[i+1] - child[i];
			pq.add(new Point(tmp));
			answer += tmp;
		}
		
		// 갭이 큰 곳부터 칸막이를 끼운다... 끼웠으니 조로 분할되고 그 비용은 발생하지 않는다.
		for (int i = 0; i < K - 1; i++) {
			Point p = pq.poll();
			answer -= p.height;
		}
		
		System.out.println(answer);
	}

}

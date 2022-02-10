package com.baekjoon.problem50;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
/*
반례
5
1
2
1
2
2
*/
public class HN_BOJ1655 {
	static int N;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		PriorityQueue<Integer> maxQ = new PriorityQueue<>((i1, i2) -> Integer.compare(i2, i1));
		PriorityQueue<Integer> minQ = new PriorityQueue<>((i1, i2) -> Integer.compare(i1, i2));
		N = Integer.parseInt(br.readLine());

		int middle = Integer.MAX_VALUE;
		int maxSize = 0, minSize = 0;
		for (int n = 0; n < N; n++) {
			int temp = Integer.parseInt(br.readLine());
			if (temp > middle) {
				minQ.add(temp);
				minSize++;
			} else {
				maxQ.add(temp);
				maxSize++;
			}

			if (Math.abs(minSize - maxSize) == 2) {
				if (minSize > maxSize) {
					maxQ.add(minQ.poll());
					maxSize++;
					minSize--;
				} else {
					minQ.add(maxQ.poll());
					maxSize--;
					minSize++;
				}
			}
			middle = minSize > maxSize ? minQ.peek() : maxQ.peek();
			sb.append(middle).append("\n");
		}
		System.out.println(sb);
	}

}

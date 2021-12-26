package com.baekjoon.problem42;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class HN_BOJ17825 {
	static class Edge {
		int to, cost;

		public Edge(int to, int cost) {
			this.to = to;
			this.cost = cost;
		}
	}

	static String input = "0 1 2\r\n" + "1 2 4\r\n" + "2 3 6\r\n" + "3 4 8\r\n" + "4 5 10\r\n" + "5 6 12\r\n"
			+ "6 7 14\r\n" + "7 8 16\r\n" + "8 9 18\r\n" + "9 10 20\r\n" + "10 11 22\r\n" + "11 12 24\r\n"
			+ "12 13 26\r\n" + "13 14 28\r\n" + "14 15 30\r\n" + "15 16 32\r\n" + "16 17 34\r\n" + "17 18 36\r\n"
			+ "18 19 38\r\n" + "19 20 40\r\n" + "20 21 0\r\n" + "5 22 13\r\n" + "22 23 16\r\n" + "23 24 19\r\n"
			+ "24 25 25\r\n" + "10 26 22\r\n" + "26 27 24\r\n" + "27 25 25\r\n" + "15 28 28\r\n" + "28 29 27\r\n"
			+ "29 30 26\r\n" + "30 25 25\r\n" + "25 31 30\r\n" + "31 32 35\r\n" + "32 20 40\r\n";
	static int answer;
	static int pan[], move[];
	static final int max = 10, panCnt = 4;
	static List<Edge> elist[];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new StringReader(input));
		elist = new LinkedList[33];
		for (int i = 0; i < 33; i++) {
			elist[i] = new LinkedList<>();
		}
		StringTokenizer st;
		String temp = br.readLine();
		int from, to, cost;
		while (temp != null) {
			st = new StringTokenizer(temp);
			from = Integer.parseInt(st.nextToken());
			to = Integer.parseInt(st.nextToken());
			cost = Integer.parseInt(st.nextToken());
			elist[from].add(new Edge(to, cost));
			temp = br.readLine();
		}
		move = new int[max];
		pan = new int[panCnt];
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < max; i++) {
			move[i] = Integer.parseInt(st.nextToken());
		}

		answer = 0;
		game(0, 0);
		System.out.println(answer);

	}

	private static void game(int round, int sum) {
		if (round == max) {
			answer = Math.max(answer, sum);
			return;
		}

		for (int i = 0; i < panCnt; i++) {
			int cur = pan[i];
			int size = elist[cur].size();
			if (size == 0)
				continue;

			Edge e;
			if (size == 2) {
				e = elist[cur].get(1);
			} else {
				e = elist[cur].get(0);
			}
			
			int cnt = move[round];
			int next = e.to, cost = e.cost;
			boolean end = false;
			while (cnt > 0) {
				next = e.to;
				cost = e.cost;
				if (elist[next].size() == 0) {
					end = true;
					break;
				}
				e = elist[next].get(0);
				cnt--;
			}

			if (end) {
				pan[i] = next;
				game(round + 1, sum);
				pan[i] = cur;
			} else {
				if (check(next)) {
					pan[i] = next;
					game(round + 1, sum + cost);
					pan[i] = cur;
				}
			}
		}
	}

	private static boolean check(int next) {
		for (int i = 0; i < panCnt; i++) {
			if (pan[i] == next)
				return false;
		}
		return true;
	}

}

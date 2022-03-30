package com.baekjoon.problem55;

import java.util.*;

public class HN_후보키 {
	static int R, C, result;
	static boolean selected[];

	static class Tuple implements Comparable<Tuple> {
		String key;
		int length;

		public Tuple(String key, int length) {
			this.key = key;
			this.length = length;
		}

		public int compareTo(Tuple o) {
			if (this.length == o.length) {
				return this.key.compareTo(o.key);
			}
			return Integer.compare(this.length, o.length);
		}
	}

	static PriorityQueue<Tuple> pq;

	public int solution(String[][] relation) {
		R = relation.length;
		C = relation[0].length;

		selected = new boolean[C];
		pq = new PriorityQueue<>();

		find(0, relation, "", 0);

		Set<String> set = new TreeSet<>();
		while (!pq.isEmpty()) {
			Tuple t = pq.poll();

			boolean pass = true;
			for (String key : set) {
				int cnt = 0;
				for (int k = 0; k < key.length(); k++) {
					if (t.key.contains(key.substring(k, k + 1))) {
						cnt++;
					}
				}
				if (cnt == key.length()) {
					pass = false;
					break;
				}
			}

			if (pass)
				set.add(t.key);
		}
		result = set.size();
		return result;
	}

	public void find(int cnt, String[][] relation, String key, int get) {
		if (cnt >= C) {
			if (get == 0)
				return;

			Set<String> set = new TreeSet<>();
			for (int i = 0; i < R; i++) {
				String str = "";
				for (int c = 0; c < C; c++) {
					if (selected[c])
						str += (relation[i][c] + "/");
				}
				set.add(str);
			}

			if (set.size() == R) {
				pq.add(new Tuple(key, key.length()));
			}
			return;
		}

		selected[cnt] = true;
		find(cnt + 1, relation, key + cnt, get + 1);

		selected[cnt] = false;
		find(cnt + 1, relation, key, get);
		return;
	}
}

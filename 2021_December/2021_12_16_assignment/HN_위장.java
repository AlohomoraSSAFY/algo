package com.baekjoon.problem40;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HN_위장 {
	static Map<String, Integer> map;
	static List<String> slist;
	static int max;
	static int answer;

	public int solution(String[][] clothes) {
		map = new HashMap<>();
		slist = new ArrayList<>();
		int len = clothes.length;
		for (int l = 0; l < len; l++) {
			if (map.containsKey(clothes[l][1])) {
				int temp = map.get(clothes[l][1]);
				map.put(clothes[l][1], temp + 1);
			} else {
				slist.add(clothes[l][1]);
				map.put(clothes[l][1], 1);
			}
		}
		max = map.size();
		answer = 0;
		combi(0, 1);

		return answer - 1;
	}

	public void combi(int cnt, int sum) {
		if (cnt == max) {
			answer += sum;
			return;
		}

		int len = map.get(slist.get(cnt));
		combi(cnt + 1, sum);
		combi(cnt + 1, sum * len);

	}
}

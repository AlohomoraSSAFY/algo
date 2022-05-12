package com.baekjoon.problem000;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class HN_BOJ1339 {
	static int N, T, answer;
	static boolean selected[];
	static Map<String, Integer> hmap;
	static List<Integer> list;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		selected = new boolean[10];
		hmap = new HashMap<>();
		list = new LinkedList<>();

		for (int n = 0; n < N; n++) {
			String s = br.readLine();
			for (int l = 0; l < s.length(); l++) {
				String ts = s.substring(l, l + 1);
				int value = hmap.getOrDefault(ts, 0);
				value += (int) Math.pow(10, s.length() - l - 1);
				hmap.put(ts, value);
			}
		}

		for (String key : hmap.keySet()) {
			list.add(hmap.get(key));
		}
		list.sort(Collections.reverseOrder());

		answer = 0;
		int mux = 9;
		for (int i = 0; i < list.size(); i++) {
			answer += list.get(i) * mux--;
		}

		System.out.println(answer);
	}

}

package com.baekjoon.problem46;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.TreeMap;

public class HN_BOJ4358 {
	static Map<String, Double> tmap = new TreeMap<>();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String name;
		int cnt = 0;
		while ((name = br.readLine()) != null && name.length() != 0) {
			double temp = tmap.getOrDefault(name, 0.0);
			tmap.put(name, temp + 1);
			cnt++;
		}

		// 50.0의 경우 50.0000으로 표시가 안돼서 틀림
		// double value = Math.round((temp / cnt) * 1000000) / 10000.0;
		// System.out.println(key + " " + value);
		for (String key : tmap.keySet()) {
			double temp = tmap.get(key);
			double value = temp * 100 / cnt;
			System.out.println(key + " " + String.format("%.4f", value));
		}
	}

}

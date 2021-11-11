package com.baekjoon.problem29;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class HN_BOJ14725 {
	static int N, K;
	static String word;
	static TreeMap<String, TreeMap> root;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		root = new TreeMap<>();
		for (int n = 0; n < N; n++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			K = Integer.parseInt(st.nextToken());
			TreeMap<String, TreeMap> tm = root;
			for (int k = 0; k < K; k++) {
				word = st.nextToken();
				if (!tm.containsKey(word)) {
					tm.put(word, new TreeMap<>());
				}
				tm = tm.get(word);
			} // for
		} // for

		print(root, 0);
		System.out.println(sb);
	}

	private static void print(TreeMap<String, TreeMap> root, int depth) {
		for (String word : root.keySet()) {
			for (int d = 0; d < depth; d++) {
				sb.append("--");
			}
			sb.append(word).append("\n");
			if (root.get(word).size() != 0) {
				print(root.get(word), depth + 1);
			}
		}
	}

}

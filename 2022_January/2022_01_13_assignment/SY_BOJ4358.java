package study0120;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.TreeMap;

public class SY_BOJ4358 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		TreeMap<String, Integer> treeMap = new TreeMap<>();
		String tree = "";
		int cnt = 0;
		while (true) {
			tree = br.readLine();
			if (tree == null || tree.equals("")) break;
			
			treeMap.put(tree, treeMap.getOrDefault(tree, 0) + 1);
			cnt++;
		}
		
		StringBuilder sb = new StringBuilder();
		for (String key : treeMap.keySet()) {
			sb.append(key + " " + String.format("%.4f", (double)treeMap.get(key) * 100 / cnt) + "\n");
		}
		System.out.println(sb);
	}

}

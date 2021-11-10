package study1104;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class SY_BOJ14725 {
	
	static TrieNode root;
	static StringBuilder sb = new StringBuilder();
	
	static class TrieNode {
		Map<String, TrieNode> children = new TreeMap<>();
	}
	
	static void insert(String[] key) {
		int len = key.length;
		TrieNode cur = root;
		for (int i = 0; i < len; i++) {
			String str = key[i];
			if (!cur.children.containsKey(str)) {
				cur.children.put(str, new TrieNode());
			}
			cur = cur.children.get(str);
		}
	}
	
	static void search(TrieNode cur, int cnt) {
		for (String str : cur.children.keySet()) {
			for (int i = 0; i < cnt; i++) {
				sb.append("--");
			}
			sb.append(str + "\n");
			
			if (cur.children.size() != 0) {
				search(cur.children.get(str), cnt + 1);
			}
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		root = new TrieNode();
		
		StringTokenizer st;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int K = Integer.parseInt(st.nextToken());
			
			String[] temp = new String[K];
			for (int j = 0; j < K; j++) {
				temp[j] = st.nextToken();
			}
			
			insert(temp);
		}
		
		search(root, 0);
		System.out.println(sb);
	}

}

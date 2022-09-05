package study0906;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class SY_BOJ20920 {
	
	static class Word implements Comparable<Word> {
		String str;
		int cnt;
		
		public Word(String str, int cnt) {
			this.str = str;
			this.cnt = cnt;
		}
		
		@Override
		public int compareTo(Word o) {
			if (o.cnt == this.cnt) {
				if (o.str.length() == this.str.length()) {
					return this.str.compareTo(o.str);
				}
				return o.str.length() - this.str.length();
			}
			return o.cnt - this.cnt;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		Map<String, Integer> map = new HashMap<>();
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			if (str.length() < M) continue;
			
			map.put(str, map.getOrDefault(str, 0) + 1);
		}
		
		PriorityQueue<Word> pq = new PriorityQueue<>();
		for (String str : map.keySet()) {
			pq.offer(new Word(str, map.get(str)));
		}
		
		StringBuilder sb = new StringBuilder();
		while (!pq.isEmpty()) {
			sb.append(pq.poll().str + "\n");
		}
		
		System.out.println(sb);
	}

}

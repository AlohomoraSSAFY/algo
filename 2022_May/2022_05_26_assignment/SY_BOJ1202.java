package study0602;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class SY_BOJ1202 {
	
	static class Jewel implements Comparable<Jewel> {
		int m;
		int v;
		
		public Jewel(int m, int v) {
			this.m = m;
			this.v = v;
		}
		
		@Override
		public int compareTo(Jewel o) {
			return this.m - o.m;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		PriorityQueue<Jewel> pq = new PriorityQueue<>();
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int m = Integer.parseInt(st.nextToken());
			int k = Integer.parseInt(st.nextToken());
			
			pq.offer(new Jewel(m, k));
		}
		
		int[] bag = new int[K];
		for (int i = 0; i < K; i++) {
			bag[i] = Integer.parseInt(br.readLine());
		}
		
		Arrays.sort(bag);
		
		PriorityQueue<Integer> temp = new PriorityQueue<>((a, b) -> b - a);
		long result = 0;
		for (int i = 0; i < K; i++) {
			while (!pq.isEmpty() && pq.peek().m <= bag[i]) {
				temp.offer(pq.poll().v);
			}
			
			if (!temp.isEmpty()) {
				result += temp.poll();
			}
		}
		
		System.out.println(result);
	}

}

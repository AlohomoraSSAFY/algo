package study0331;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class SY_BOJ2109 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return o2[0] - o1[0];
			}
		});
		
		int max = 0;
		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int p = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			pq.offer(new int[] {p, d});
			max = Math.max(max, d);
		}
		
		int[] array = new int[max+1];
		while (!pq.isEmpty()) {
			int[] cur = pq.poll();
			int idx = cur[1];
			for (int i = idx; i > 0; i--) {
				if (array[i] != 0) continue;
				
				array[i] = cur[0];
				break;
			}
		}
		
		int result = 0;
		for (int i = 1; i < max+1; i++) {
			result += array[i];
		}
		System.out.println(result);
	}

}

package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class HN_BOJ2109 {
	static int N;
	static int DP[];
	static final int LIMIT = 10000;

	static class Work {
		int pay, day;

		public Work(int pay, int day) {
			this.pay = pay;
			this.day = day;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		PriorityQueue<Work> pq = new PriorityQueue<>((w1, w2) -> {
			if (w1.pay == w2.pay) {
				return Integer.compare(w2.day, w1.day);
			}
			return Integer.compare(w2.pay, w1.pay);
		});
		StringTokenizer st;
		for (int n = 0; n < N; n++) {
			st = new StringTokenizer(br.readLine());
			pq.add(new Work(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
		}
		DP = new int[LIMIT + 1];
		while (!pq.isEmpty()) {
			Work w = pq.poll();
			for (int i = w.day; i >= 1; i--) {
				if (DP[i] < w.pay) {
					DP[i] = w.pay;
					break;
				}
			}
		}

		int answer = Arrays.stream(DP).sum();
		System.out.println(answer);
	}

}

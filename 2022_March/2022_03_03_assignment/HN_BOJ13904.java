package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class HN_BOJ13904 {
	static final int limit = 1001;
	static int N, answer;
	static int board[];

	static class Homework {
		int deadline, score;

		public Homework(int deadline, int score) {
			this.deadline = deadline;
			this.score = score;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		board = new int[limit];

		PriorityQueue<Homework> pq = new PriorityQueue<>((h1, h2) -> {
			if (h1.score == h2.score) {
				return Integer.compare(h2.deadline, h1.deadline);
			}
			return Integer.compare(h2.score, h1.score);
		});
		
		StringTokenizer st;
		int deadline, score;
		for (int n = 0; n < N; n++) {
			st = new StringTokenizer(br.readLine());
			deadline = Integer.parseInt(st.nextToken());
			score = Integer.parseInt(st.nextToken());
			pq.add(new Homework(deadline, score));
		}

		while (!pq.isEmpty()) {
			Homework h = pq.poll();

			for (int i = h.deadline; i > 0; i--) {
				if (h.score > board[i]) {
					board[i] = h.score;
					break;
				}
			}
		}

		answer = 0;
		for (int i = 1; i < limit; i++) {
			answer += board[i];
		}

		System.out.println(answer);
	}

}

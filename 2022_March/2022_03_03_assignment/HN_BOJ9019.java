package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class HN_BOJ9019 {
	static final int limit = 10000;
	static int T, A, B;
	static boolean visited[];

	static class Register {
		int value;
		int length;
		String word;

		public Register(int value, int length, String word) {
			this.value = value;
			this.length = length;
			this.word = word;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		T = Integer.parseInt(br.readLine());

		for (int t = 0; t < T; t++) {
			st = new StringTokenizer(br.readLine());
			A = Integer.parseInt(st.nextToken());
			B = Integer.parseInt(st.nextToken());
			visited = new boolean[limit];
			sb.append(solve(A, B)).append("\n");
		}
		System.out.println(sb);
	}

	private static String solve(int a, int b) {
		String result = "";
		Queue<Register> q = new LinkedList<>();
		q.add(new Register(a, 0, ""));
		visited[a] = true;

		int temp, value;
		while (!q.isEmpty()) {
			Register r = q.poll();

			if (r.value == b) {
				result = r.word;
				break;
			}

			// D
			temp = r.value * 2;
			value = temp >= limit ? temp % limit : temp;
			if (!visited[value]) {
				visited[value] = true;

				q.add(new Register(value, r.length + 1, r.word + "D"));
			}

			// S
			temp = r.value - 1;
			value = temp < 0 ? limit - 1 : temp;
			if (!visited[value]) {
				visited[value] = true;

				q.add(new Register(value, r.length + 1, r.word + "S"));
			}

			// L
			value = ((r.value % 1000) * 10) + r.value / 1000;
			if (!visited[value]) {
				visited[value] = true;

				q.add(new Register(value, r.length + 1, r.word + "L"));
			}

			// R
			value = ((r.value % 10) * 1000) + r.value / 10;
			if (!visited[value]) {
				visited[value] = true;

				q.add(new Register(value, r.length + 1, r.word + "R"));
			}

		}

		return result;
	}
}

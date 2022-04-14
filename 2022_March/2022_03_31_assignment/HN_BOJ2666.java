package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class HN_BOJ2666 {
	static int N, M, answer;
	static int[] input;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		int d1 = Integer.parseInt(st.nextToken());
		int d2 = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(br.readLine());
		input = new int[M];
		for (int m = 0; m < M; m++) {
			input[m] = Integer.parseInt(br.readLine());
		}
		answer = Integer.MAX_VALUE;
		openDoor(d1, d2, 0, 0);
		System.out.println(answer);
	}

	private static void openDoor(int d1, int d2, int cnt, int sum) {
		if (sum >= answer)
			return;
		if (cnt >= M) {
			answer = Math.min(answer, sum);
			return;
		}
		openDoor(input[cnt], d2, cnt + 1, sum + Math.abs(d1 - input[cnt]));
		openDoor(d1, input[cnt], cnt + 1, sum + Math.abs(d2 - input[cnt]));
	}
}

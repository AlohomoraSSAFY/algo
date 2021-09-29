package study0926;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SY_BOJ14501 {
	
	static int N;
	static int[] T;
	static int[] P;
	static int result = Integer.MIN_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		T = new int[N+1];
		P = new int[N+1];
		StringTokenizer st;
		for (int i = 1; i < N+1; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			T[i] = Integer.parseInt(st.nextToken());
			P[i] = Integer.parseInt(st.nextToken());
		}
		
		subset(1, 0);
		
		System.out.println(result);
	}
	
	public static void subset(int cnt, int sum) {
		if (cnt > N) {
			result = Math.max(result, sum);
			return;
		}
		
		if (cnt + T[cnt] - 1 <= N) subset(cnt + T[cnt], sum + P[cnt]);
		subset(cnt + 1, sum);
	}

}

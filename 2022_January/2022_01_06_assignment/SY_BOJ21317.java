package study0113;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SY_BOJ21317 {
	
	static int N, K;
	static int[][] array;
	static int result = Integer.MAX_VALUE;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		array = new int[N-1][2];
		
		StringTokenizer st;
		for (int i = 0; i < N-1; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			array[i][0] = Integer.parseInt(st.nextToken());
			array[i][1] = Integer.parseInt(st.nextToken());
		}
		
		K = Integer.parseInt(br.readLine());
		
		recursion(0, 0, false);
		System.out.println(result);
	}
	
	public static void recursion(int loc, int sum, boolean check) {
		if (loc == N - 1) {
			result = Math.min(result, sum);
			return;
		}
		
		recursion(loc + 1, sum + array[loc][0], check);
		
		if (loc + 2 <= N - 1) {
			recursion(loc + 2, sum + array[loc][1], check);
		}
		
		if (loc + 3 <= N - 1 && !check) {
			recursion(loc + 3, sum + K, true);
		}
	}

}

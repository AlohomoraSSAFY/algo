package study0526;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SY_BOJ10836 {
	
	static int M, N;
	static int[][] array;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		array = new int[M][M];
		for (int i = 0; i < M; i++) {
			Arrays.fill(array[i], 1);
		}
		
		for (int t = 0; t < N; t++) {
			st = new StringTokenizer(br.readLine(), " ");
			int zero = Integer.parseInt(st.nextToken());
			int one = Integer.parseInt(st.nextToken());
			int two = Integer.parseInt(st.nextToken());
			
			for (int i = M-1-zero; i >= 0; i--) {
				if (one > 0) {
					array[i][0]++;
					one--;
				} else {
					array[i][0] += 2;
					two--;
				}
			}
			
			int idx = 0;
			if (zero > M) idx = zero - M;
			for (int i = 1+idx; i < M; i++) {
				if (one > 0) {
					array[0][i]++;
					one--;
				} else {
					array[0][i] += 2;
					two--;
				}
			}
		}
		
		for (int i = 1; i < M; i++) {
			for (int j = 1; j < M; j++) {
				array[i][j] = array[i-1][j];
			}
		}
		
		for (int i = 0; i < M; i++) {
			for (int j = 0; j < M; j++) {
				sb.append(array[i][j] + " ");
			}
			sb.append("\n");
		}
		
		System.out.println(sb);
	}

}

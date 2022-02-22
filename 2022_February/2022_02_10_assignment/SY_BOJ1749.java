package study0224;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SY_BOJ1749 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[][] array = new int[N+1][M+1];
		int[][] prefixSum = new int[N+1][M+1];
		for (int i = 1; i < N+1; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 1; j < M+1; j++) {
				array[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for (int i = 1; i < N+1; i++) {
			for (int j = 1; j < M+1; j++) {
				prefixSum[i][j] = prefixSum[i-1][j] + prefixSum[i][j-1] + array[i][j] - prefixSum[i-1][j-1];
			}
		}
		
		int result = Integer.MIN_VALUE;
		for (int i = 1; i < N+1; i++) {
			for (int j = 1; j < M+1; j++) {
				for (int k = i; k < N+1; k++) {
					for (int l = j; l < M+1; l++) {
						result = Math.max(result, prefixSum[k][l] - prefixSum[i-1][l] - prefixSum[k][j-1] + prefixSum[i-1][j-1]);
					}
				}
			}
		}
		System.out.println(result);
	}

}

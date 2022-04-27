package study0428;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SY_BOJ17779 {

	static int N;
	static int[][] array;
	static int total;
	static int result;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		array = new int[N+1][N+1];
		result = Integer.MAX_VALUE;
		for (int i = 1; i < N+1; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int j = 1; j < N+1; j++) {
				array[i][j] = Integer.parseInt(st.nextToken());
				total += array[i][j];
			}
		}
		
		for (int x = 1; x < N+1; x++) {
			for (int y = 1; y < N+1; y++) {
				for (int d1 = 1; d1 < N+1; d1++) {
					for (int d2 = 1; d2 < N+1; d2++) {
						if (x + d1 + d2 <= N && 1 <= y - d1 && y + d2 <= N) {
							result = Math.min(result, cal(x, y, d1, d2));
						}
					}
				}
			}
		}
		
		System.out.println(result);
	}
	
	private static int cal(int x, int y, int d1, int d2) {
		boolean[][] check = new boolean[N+1][N+1];
		
		for (int i = 0; i <= d1; i++) {
			check[x + i][y - i] = true;
			check[x + d2 + i][y + d2 - i] = true;
		}
		
		for (int i = 0; i <= d2; i++) {
			check[x + i][y + i] = true;
			check[x + d1 + i][y - d1 + i] = true;
		}
		
		int[] sum = new int[6];
		
		//1번 선거구
		for (int i = 1; i < x + d1; i++) {
			for (int j = 1; j <= y; j++) {
				if (check[i][j]) break;
				sum[1] += array[i][j];
			}
		}
		
		//2번 선거구
		for (int i = 1; i <= x + d2; i++) {
			for (int j = N; j > y; j--) {
				if (check[i][j]) break;
				sum[2] += array[i][j];
			}
		}
		
		//3번 선거구
		for (int i = x + d1; i <= N; i++) {
			for (int j = 1; j < y - d1 + d2; j++) {
				if (check[i][j]) break;
				sum[3] += array[i][j];
			}
		}
		
		//4번 선거구
		for (int i = x + d2 + 1; i <= N; i++) {
			for (int j = N; j >= y - d1 + d2; j--) {
				if (check[i][j]) break;
				sum[4] += array[i][j];
			}
		}
		
		//5번 선거구
		sum[5] = total;
		for (int i = 1; i < 5; i++) {
			sum[5] -= sum[i];
		}
		
		Arrays.sort(sum);
		return sum[5] - sum[1];
	}

}

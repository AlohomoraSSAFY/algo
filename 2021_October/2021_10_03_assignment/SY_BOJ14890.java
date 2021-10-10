package study1007;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SY_BOJ14890 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int L = Integer.parseInt(st.nextToken());
		int[][] array = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				array[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int result = 0;
		boolean[][] check = new boolean[N][N];
		for (int i = 0; i < N; i++) {
			boolean flag = true;
			for (int j = 1; j < N; j++) {
				if (array[i][j] == array[i][j-1]) {
					continue;
				} else if (array[i][j] + 1 == array[i][j-1]) {
					int temp = array[i][j];
					for (int k = 0; k < L; k++) {
						if (j + k >= N || check[i][j+k] || array[i][j+k] != temp) {
							flag = false;
							break;
						} else {
							check[i][j+k] = true;
						}
					}
				} else if (array[i][j] == array[i][j-1] + 1) {
					int temp = array[i][j] - 1;
					for (int k = 1; k <= L; k++) {
						if (j - k < 0 || check[i][j-k] || array[i][j-k] != temp) {
							flag = false;
							break;
						} else {
							check[i][j-k] = true;
						}
					}
				} else {
					flag = false;
					break;
				}
			}
			
			if (flag) {
				result++;
			} else {
				for (int j = 0; j < N; j++) {
					check[i][j] = false;
				}
			}
		}
		
		boolean[][] check2 = new boolean[N][N];
		for (int j = 0; j < N; j++) {
			boolean flag = true;
			for (int i = 1; i < N; i++) {
				if (array[i][j] == array[i-1][j]) {
					continue;
				} else if (array[i][j] + 1 == array[i-1][j]) {					
					int temp = array[i][j];
					for (int k = 0; k < L; k++) {
						if (i + k >= N || check2[i+k][j] || array[i+k][j] != temp) {
							flag = false;
							break;
						} else {
							check2[i+k][j] = true;
						}
					}
				} else if (array[i][j] == array[i-1][j] + 1) {
					int temp = array[i][j] - 1;
					for (int k = 1; k <= L; k++) {
						if (i - k < 0 || check2[i-k][j] || array[i-k][j] != temp) {
							flag = false;
							break;
						} else {
							check2[i-k][j] = true;
						}
					}
				} else {
					flag = false;
					break;
				}
			}
			
			if (flag) {
				result++;
			} else {
				for (int i = 0; i < N; i++) {
					check2[i][j] = false;
				}
			}
		}
				
		System.out.println(result);
	}

}

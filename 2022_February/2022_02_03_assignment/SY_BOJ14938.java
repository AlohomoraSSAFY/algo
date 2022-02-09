package study0210;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SY_BOJ14938 {
	
	static final int INF = 1000000000;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int r = Integer.parseInt(st.nextToken());
		
		int[] itemCnt = new int[n+1];
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 1; i < n+1; i++) {
			itemCnt[i] = Integer.parseInt(st.nextToken());
		}
		
		int[][] array = new int[n+1][n+1];
		for (int i = 1; i < n+1; i++) {
			for (int j = 1; j < n+1; j++) {
				if (i == j) array[i][j] = 0;
				else array[i][j] = INF;
			}
		}
		
		for (int i = 0; i < r; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int l = Integer.parseInt(st.nextToken());
			array[a][b] = l;
			array[b][a] = l;
		}
		
		for (int k = 1; k < n+1; k++) {
			for (int i = 1; i < n+1; i++) {
				for (int j = 1; j < n+1; j++) {
					if (array[i][j] > array[i][k] + array[k][j]) {
						array[i][j] = array[i][k] + array[k][j];
					}
				}
			}
		}
		
		int result = 0;
		for (int i = 1; i < n+1; i++) {
			int cnt = 0;
			for (int j = 1; j < n+1; j++) {
				if (array[i][j] <= m) {
					cnt += itemCnt[j];
				}
			}
			result = Math.max(result, cnt);
		}
		System.out.println(result);
	}

}

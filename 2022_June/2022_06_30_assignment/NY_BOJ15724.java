package date0630;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class NY_BOJ15724 {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());

		int[][] prefsum = new int[n][m];
		int[][] arr = new int[n][m];

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		prefsum[0][0] = arr[0][0];
		for (int i = 1; i < n; i++) {
			prefsum[i][0] = prefsum[i - 1][0] + arr[i][0];
		}
		for (int i = 1; i < m; i++) {
			prefsum[0][i] = prefsum[0][i - 1] + arr[0][i];
		}

		for (int i = 1; i < n; i++) {
			for (int j = 1; j < m; j++) {
				prefsum[i][j] = prefsum[i - 1][j] + prefsum[i][j - 1] + arr[i][j] - prefsum[i - 1][j - 1];
			}
		}

		int k = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < k; i++) {
			st = new StringTokenizer(br.readLine());
			int[] xy = new int[4];
			for (int j = 0; j < 4; j++) {
				xy[j] = Integer.parseInt(st.nextToken()) - 1;
			}

			int sum = prefsum[xy[2]][xy[3]];
			if (xy[0] != 0) {
				sum-=prefsum[xy[0]-1][xy[3]];

			}
			if (xy[1] != 0) {
				sum-=prefsum[xy[2]][xy[1]-1];
			}
			if (xy[1] != 0 && xy[0]!=0) {
				sum+=prefsum[xy[0]-1][xy[1]-1];
			}
			sb.append(sum + "\n");
		}
		System.out.println(sb.toString());

	}

}

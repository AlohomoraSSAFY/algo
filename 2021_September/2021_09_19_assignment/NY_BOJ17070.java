package date0923THU;

import java.io.BufferedReader;
import java.io.*;
import java.util.*;

public class BOJ17070 {
	static int n;
	static int map[][];
	static int dp[][];
	static int dpw[][];
	static int dph[][];
	static int dpd[][];

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());

		map = new int[n][n];
		dp = new int[n][n];

		dpw = new int[n][n];
		dph = new int[n][n];
		dpd = new int[n][n];

		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		//
		dp[0][1] = 1;
		dpw[0][1] = 1;

		
		for (int i = 0; i < n; i++) {
			for (int j = 2; j < n; j++) {
				// �밢��
				if (map[i][j] == 1)
					continue;
				if (i - 1 >= 0 && j - 1 >= 0 && map[i][j - 1] == 0 && map[i - 1][j] == 0 && map[i - 1][j - 1] == 0) {
					dpd[i][j] = dp[i - 1][j - 1];
				}
				// ����
				if( j - 1 >= 0 && map[i][j - 1] == 0 ) {
					dpw[i][j] = dpd[i][j-1] + dpw[i][j-1];
				}
				// ����
				if( i - 1 >= 0  && map[i-1][j] == 0 ) {
					dph[i][j] = dpd[i-1][j] + dph[i-1][j];
				}
				
				dp[i][j] = dpw[i][j]+dph[i][j] + dpd[i][j];
			}
		}
		
		System.out.println(dp[n-1][n-1]);
		
	}

}

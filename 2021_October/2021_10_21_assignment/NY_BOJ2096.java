package date1024SUN;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ2096 {
	static int n;
	static int arr[][];
	static int dpmin[][];
	static int dpmax[][];
	static int min, max;

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		min = Integer.MAX_VALUE;
		max = 0;
		n = Integer.parseInt(br.readLine());
		arr = new int[n][3];
		dpmax = new int[n][3];
		dpmin = new int[n][3];

		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 3; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		for (int j = 0; j < 3; j++) {
			dpmin[0][j] = arr[0][j];
			dpmax[0][j] = arr[0][j];
		}

		for (int i = 1; i < n; i++) {

			dpmax[i][0] = Math.max(dpmax[i - 1][0], dpmax[i - 1][1]) + arr[i][0];

			dpmin[i][0] = Math.min(dpmin[i - 1][0], dpmin[i - 1][1]) + arr[i][0];

			dpmax[i][1] = Math.max(dpmax[i - 1][0] + arr[i][1], dpmax[i - 1][1] + arr[i][1]);
			dpmax[i][1] = Math.max(dpmax[i][1], dpmax[i - 1][2] + arr[i][1]);

			dpmin[i][1] = Math.min(dpmin[i - 1][0] + arr[i][1], dpmin[i - 1][1] + arr[i][1]);
			dpmin[i][1] = Math.min(dpmin[i][1], dpmin[i - 1][2] + arr[i][1]);

			dpmax[i][2] = Math.max(dpmax[i - 1][2], dpmax[i - 1][1]) + arr[i][2];

			dpmin[i][2] = Math.min(dpmin[i - 1][2], dpmin[i - 1][1]) + arr[i][2];

		}
		
		min = Math.min(dpmin[n - 1][0], dpmin[n - 1][1]);
		min = Math.min(min, dpmin[n - 1][2]);

		max = Math.max(dpmax[n - 1][0], dpmax[n - 1][1]);
		max = Math.max(max, dpmax[n - 1][2]);
		
		System.out.println(max + " " + min);
	}

}

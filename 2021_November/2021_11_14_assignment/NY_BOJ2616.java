package date1118THU;

import java.io.*;
import java.util.*;

public class BOJ2616 { //소형기관차

	static int n; 
	static int m;
	static int arr[];
	static int prefixsum[];
	static int answer;
	static int dp[][];

	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		prefixsum = new int[n+1];
		dp = new int[4][n+1];
		arr = new int[n];
		for(int i=0;i<n;i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		m = Integer.parseInt(br.readLine());
		prefixsum[1] =arr[0];
		
		for(int i=2;i<=n;i++) {
			prefixsum[i] += prefixsum[i-1] +arr[i-1];
		}
		
		for(int i=1;i<=3;i++) { 
			for(int j=m;j<=n;j++) {
				dp[i][j] = Math.max(dp[i][j-1], prefixsum[j] - prefixsum[j-m] + dp[i-1][j-m]);
			}
		}
		
		System.out.println(dp[3][n]);
		
	}

}

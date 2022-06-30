package net.acmicpc.jun;

import java.io.*;
import java.util.*;


public class BOJ3980 {
	
	static int TC;
	static int[][] s;
	static int answer;
	
	private static void dfs(boolean[] check, int n, int sum) {
		if(n == 11) {
			answer = Math.max(answer, sum);
			return;
		}
		
		for(int i = 0; i < 11; i++) {
			if(s[n][i] == 0) continue;
			if(check[i]) continue;
			check[i] = true;
			dfs(check, n+1, sum+s[n][i]);
			check[i] = false;
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		TC = Integer.parseInt(br.readLine());
		for(int cs = 1; cs < TC+1; cs++) {
			s = new int[11][11];
			for(int line = 0; line < 11; line++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for(int j = 0; j < 11; j++) {
					s[line][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			answer = 0;
			boolean[] check = new boolean[11];
			dfs(check, 0, 0);
			bw.write(answer+"\n");
			
		}
		
		bw.close();
		br.close();	
		
	}

}

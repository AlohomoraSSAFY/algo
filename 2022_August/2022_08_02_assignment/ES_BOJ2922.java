package net.acmicpc.august.week1;

import java.io.*;
import java.util.*;


public class BOJ2922 {
	
	static String input;
	
	public static long dfs(int index, long con, long vow, long ans ) {
		if(con >2 || vow >2 ) {
			return 0L;
		}
		if(index == input.length() ) {
			return ans;
		}
		
		long sum = 0L;
		if(input.charAt(index) == '_') {
			sum += dfs(index + 1, con+1, 0, ans)*20;
			sum += dfs(index + 1, con+1, 0, 1);
			sum += dfs(index + 1, 0, vow+1, ans)*5;
		} else {
			if(input.charAt(index) =='A' || input.charAt(index) =='E' || input.charAt(index) =='I' || input.charAt(index) =='O' || input.charAt(index) =='U' ) {
				sum += dfs(index+1, 0, vow+1, ans);
			} else {
				if(input.charAt(index)=='L') {
					sum += dfs(index+1, con+1, 0, 1);
				} else {
					sum += dfs(index+1, con+1, 0, ans);
				}
			}
		}
		
		return sum;
	}
	
	
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		input = br.readLine();
		
		bw.write(dfs(0, 0, 0, 0)+"\n");
		
		
		bw.close();
		br.close();	
		
	}

}

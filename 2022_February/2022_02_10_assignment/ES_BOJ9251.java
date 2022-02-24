package net.acmicpc.fet.week4;

import java.io.*;
import java.util.*;


public class BOJ9251 {
	
	static String A, B;
	static int[][] comp;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
		A = br.readLine();
		B = br.readLine();
		
		int a = A.length();
		int b= B.length();
		comp = new int[b+1][a+1];
		
		for(int i = 1; i < b+1; i++) {
			char c = B.charAt(i-1);
			for(int j = 1; j < a+1; j++) {
				int val = Math.max(comp[i-1][j], comp[i][j-1]);
				int tmp = comp[i-1][j-1];
				if(c == A.charAt(j-1)) { // 같다면
					tmp++;
				}
				comp[i][j] = Math.max(val, tmp);
			}
		}
		
		bw.write(comp[b][a]+"\n");		
		
		bw.close();
		br.close();	
		
	}

}

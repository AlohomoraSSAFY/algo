package net.acmicpc.august.week1;

import java.io.*;
import java.util.*;


public class BOJ1188 {
	
	static int N, M, answer;
	
	private static int gcd(int a, int b) {
		if(b==0) {
			return a;
		}
		return gcd(b, a %b);
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		answer = M - gcd(N, M);
		
		bw.write(answer + "\n");
		
		bw.close();
		br.close();	
		
	}

}

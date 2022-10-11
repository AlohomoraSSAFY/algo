package com.acmipc.oct.week1;

import java.io.*;
import java.util.*;


public class BOJ2138 {
	
	static int N;
	static String one, two;
	static boolean[] a, b, tmp;
	static int first, second;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		N = Integer.parseInt(br.readLine());
		a = new boolean[N+1]; b = new boolean[N+1];
		
		one = br.readLine();
		for(int i = 0; i < N; i++) {
			a[i] = one.charAt(i) =='1'; // 1이면 true
		}
		
		two = br.readLine();
		for(int i = 0; i < N; i++) {
			b[i] = two.charAt(i) =='1'; // 1이면 true
		}
				
		
		// 1번 스위치 off
		first = 0; tmp = a.clone();
		for(int i = 1; i < N; i++) {
			if(tmp[i-1] == b[i-1]) { // 같으면 그대로
				
			} else { // 다르면 반전
				tmp[i-1] = !tmp[i-1];
				tmp[i] = !tmp[i];
				tmp[i+1] = !tmp[i+1];
				first++;
			}
		}
		
		if(tmp[N-1] != b[N-1]) {
			first = 2*N;
		}
		
		// 1번 스위치 on
		second = 1; tmp = a.clone();
		tmp[0] = !a[0]; tmp[1] = !a[1]; // 반전
		for(int i = 1; i < N; i++) {
			if(tmp[i-1] == b[i-1]) { // 같으면 그대로
				
			} else { // 다르면 반전
				tmp[i-1] = !tmp[i-1];
				tmp[i] = !tmp[i];
				tmp[i+1] = !tmp[i+1];
				second++;
			}
		}
		
		if(tmp[N-1] != b[N-1]) {
			second = 2*N;
		}
		
		
		if(one.equals(two)) {
			bw.write("0\n");
		} else {
			if(first == 2*N && second == 2*N) {
				bw.write("-1\n");
			} else {
				bw.write(Math.min(first, second)+"\n");				
			}

		}
		
		bw.close();
		br.close();	
		
	}

}

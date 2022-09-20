package net.acmicpc.august.week4;

import java.io.*;
import java.util.*;


public class BOJ2607 {
	
	static int N, K;
	static String word;
	static int arr[];
	static int ans;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		N = Integer.parseInt(br.readLine());
		word = br.readLine();
		arr = new int[30];
		
		for(int i = 0; i < word.length(); i++) {
			char c = word.charAt(i);
			arr[c-'A']++;
			
		}
		
		ans = 0;
		for(int i = 0; i < N-1; i++) {
			String input = br.readLine();
			int[] brr = arr.clone();
			int same = 0;
			if(Math.abs(word.length() - input.length()) > 1) continue;
			for(int j = 0; j < input.length(); j++) {
				char c = input.charAt(j);
				if(brr[c-'A'] > 0) {
					same++;
					brr[c-'A']--;
				}
			}
			
			if(word.length() - input.length() == 0) {
				if(same == word.length() || same == word.length()-1) {
					ans++;
				}
			} else if(word.length() - input.length() == 1) {
				if(same == word.length() -1) {
					ans++;
				}
			} else if(word.length() - input.length() == -1) {
				if(same == word.length()) {
					ans++;
				}
			}
			
		}
		
		bw.write(ans+"\n");
		bw.close();
		br.close();	
		
	}

}

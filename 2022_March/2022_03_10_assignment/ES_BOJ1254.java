package net.acmicpc.march.week3;

import java.io.*;
import java.util.*;


public class BOJ1254 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		String input = br.readLine();
		StringBuilder sb= new StringBuilder(input);
		if(input.equals(sb.reverse().toString())) {
			bw.write(input.length()+"\n");
		}else {
			for(int i = 1; i < input.length(); i++) {
				sb = new StringBuilder();
				sb.append(input);
				sb.append(new StringBuilder(input.substring(0, i)).reverse());
			
				if(sb.toString().equals(sb.reverse().toString())) {
					bw.write(sb.length()+"\n");
					break;
				}
			}
			
		}
		
		
		bw.close();
		br.close();	
		
	}

}

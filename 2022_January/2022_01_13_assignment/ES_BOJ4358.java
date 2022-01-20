package net.acmicpc.jan.week3;

import java.io.*;
import java.util.*;


public class BOJ4358 {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		TreeMap<String, Long> trees = new TreeMap<>();
		String input;
		float size = 0;
		while( (input = br.readLine() )!= null && !input.isEmpty()) {
			
			if(trees.containsKey(input)) {
				trees.put(input, trees.get(input) + 1L); 
			}else {
				trees.put(input, 1L);
			}
			size++;
		}
		for(String str : trees.keySet()) {
			bw.write(String.format("%s %.4f\n", str, (float)trees.get(str) / size * 100.0));
		}
		
		bw.close();
		br.close();	
		
	}

}

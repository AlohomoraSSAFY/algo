package net.acmicpc.april.week2;

import java.io.*;
import java.util.*;

public class BOJ4889 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int idx = 1;
		while(true) {
			Stack<String> stack = new Stack<>();
			String[] input = br.readLine().split("");
			if(input.length > 0 && input[0].equals("-")) {
				break;
			}
			
			for(String c : input) {
				if(stack.isEmpty()) {
					stack.push(c);
				}else {
					if(c.equals("}") && stack.peek().equals("{")) {
						stack.pop();
					}else {
						stack.push(c);
					}
				}
			}
			
			int sum = 0;
			while(!stack.isEmpty()) {
				if(stack.pop().equals("{")) {
					sum++;
				}
				if(stack.pop().equals("}")) {
					sum++;
				}
			}
			
			bw.write(String.format("%d. %d\n", idx++, sum));
		}
		
		bw.close();
		br.close();	
		
	}

}

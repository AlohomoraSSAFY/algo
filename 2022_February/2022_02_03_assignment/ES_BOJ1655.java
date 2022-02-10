package net.acmicpc.feb.week2;

import java.io.*;
import java.util.*;


public class BOJ1655 {
	
	static int N;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		PriorityQueue<Integer> apq = new PriorityQueue<>(Collections.reverseOrder());
		PriorityQueue<Integer> dpq = new PriorityQueue<>();
		N = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		int val = Integer.parseInt(br.readLine());
		sb.append(val+"\n");
		apq.add(val);
		for(int i = 0; i < N-1; i++) {
			val = Integer.parseInt(br.readLine());
			if(apq.size() == dpq.size()) {
				int a = apq.peek();
				int b = dpq.peek();
				if( a > val) {
					apq.add(val);
					sb.append(a+"\n");
				} else if(b < val) {
					dpq.add(val);
					sb.append(b+"\n");
				} else {
					sb.append(val+"\n");
					apq.add(val);
				}
			} else if (apq.size() > dpq.size()) {
				int a = apq.peek();
				if(dpq.size() == 0) {
					if (a < val) {
						sb.append(a+"\n");
						dpq.add(val);						
					} else {
						sb.append(val+"\n");
						dpq.add(apq.poll());
						apq.add(val);
					}
				}else {
					int b = dpq.peek();
					if( a > val) {
						dpq.add(apq.poll());
						apq.add(val);
						sb.append(apq.peek()+"\n");
					} else if(b < val) {
						dpq.add(val);
						sb.append(a+"\n");
					} else {
						sb.append(a+"\n");
						dpq.add(val);
					}					
				}
			} else { // apq < dpq
				int a = apq.peek();
				int b = dpq.peek();
				if( a > val) {
					apq.add(val);
					sb.append(a+"\n");
				} else if(b < val) {
					dpq.add(val);
					apq.add(dpq.poll());
					sb.append(b+"\n");
				} else {
					sb.append(val+"\n");
					apq.add(val);
				}
				
			} 
		}
		
		bw.write(sb.toString());
		bw.close();
		br.close();	
		
	}

}

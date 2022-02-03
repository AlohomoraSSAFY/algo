package net.acmicpc.feb.week1;

import java.io.*;
import java.util.*;


public class BOJ11000 {
	
	static int N;
	static int[][] time;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		N = Integer.parseInt(br.readLine());
		time = new int[N][2];
		
		for(int i = 0; i < N; i++) {
			String[] tmp = br.readLine().split(" ");
			time[i][0] = Integer.parseInt(tmp[0]);
			time[i][1] = Integer.parseInt(tmp[1]);
		}
		
		Arrays.sort(time, new Comparator<int[]>() {
			@Override
			public int compare(int[] a, int[] b) {
				if(a[0]-b[0] == 0) {
					return a[1] - b[1];
				} else {
					return a[0] - b[0];
				}
			}
		} );
		
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		pq.add(time[0][1]);
		
		for(int i = 1; i < N; i++) {
			if(pq.peek() <= time[i][0]) {
				pq.poll();
			}
			
			pq.add(time[i][1]);
		}
		
		
		bw.write(pq.size()+"\n");
		bw.close();
		br.close();	
		
	}

}

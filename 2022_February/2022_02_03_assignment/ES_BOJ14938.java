package net.acmicpc.feb.week2;

import java.io.*;
import java.util.*;


public class BOJ14938 {
	
	static int N, M, R;
	static int[] item;
	static int[][] dist;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		item = new int[N+1];
		for(int i = 1; i < N+1; i++) {
			item[i] = Integer.parseInt(st.nextToken());
		}

		dist = new int[N+1][N+1];
		for(int i =1; i < N+1; i++) {
			Arrays.fill(dist[i], Integer.MAX_VALUE);
		}
		
		for(int i = 1; i < R+1; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			int t = Integer.parseInt(st.nextToken());
			
			dist[s][e] = Math.min(dist[s][e], t);
			dist[e][s] = Math.min(dist[e][s], t);
		}
		
		for(int k = 1; k < N+1; k++) {
			for(int s = 1; s < N+1; s++) {
				for(int e = 1; e < N+1; e++) {
					if(s==e) {
						dist[s][e] = 0;
						continue;
					}
					if(dist[s][k]==Integer.MAX_VALUE || dist[k][e] == Integer.MAX_VALUE) continue;
					if(dist[s][e] > dist[s][k] + dist[k][e]) {
						dist[s][e] = dist[s][k] + dist[k][e];
					}
				}
			}
		}
		
		int ans = 0;
		for(int i = 1; i < N+1; i++) {
			int sum = 0;
			for(int j = 1; j < N+1; j++) {
				if(dist[i][j] <= M) {
					sum += item[j];
				}
			}
			ans = Math.max(ans,  sum);
		}
		bw.write(ans+"\n");
		
//		for(int i = 1; i < N+1; i++) {
//			for(int j = 1; j < N+1; j++) {
//				System.out.printf("%5d ", dist[i][j]);
//			}
//			System.out.println();
//		}
		
		bw.close();
		br.close();	
		
	}

}

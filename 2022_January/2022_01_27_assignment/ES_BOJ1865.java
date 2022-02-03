package net.acmicpc.feb.week1;

import java.io.*;
import java.util.*;


public class BOJ1865 {
	
	static int TC, N, M, W;
//	static ArrayList<Integer>[][] con;
	static int[][] time;
	static int[][] wormholes;
	static int[][] dist;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		TC = Integer.parseInt(br.readLine());
		for(int cs = 1; cs < TC +1; cs++) {
			StringTokenizer st = new StringTokenizer(br.readLine());			
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			
			time = new int[N+1][N+1];
			wormholes = new int[N+1][N+1];
			dist = new int[N+1][N+1];
			
			for(int i =1; i < N+1; i++) {
				Arrays.fill(time[i], Integer.MAX_VALUE);
				Arrays.fill(wormholes[i], -1);
				Arrays.fill(dist[i], Integer.MAX_VALUE);
			}
			
			for(int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int s = Integer.parseInt(st.nextToken());
				int e = Integer.parseInt(st.nextToken());
				int t = Integer.parseInt(st.nextToken());
				time[s][e] = Math.min(time[s][e], t);
				time[e][s] = Math.min(time[e][s], t);
				
				dist[s][e] = Math.min(dist[s][e], t);
				dist[e][s] = Math.min(dist[e][s], t);
			}
			
			for(int j = 0; j < W; j++) {
				st = new StringTokenizer(br.readLine());
				int s = Integer.parseInt(st.nextToken());
				int e = Integer.parseInt(st.nextToken());
				int t = Integer.parseInt(st.nextToken());
				
				wormholes[s][e] = Math.max(wormholes[s][e], t);
				dist[s][e] = Math.min(dist[s][e], -t);
//				dist[s][e] = Math.min(dist[s][e], -t);
			}

			boolean flag = false;
			LOOP:
			for(int k = 1; k < N+1; k++) {
				for(int s = 1; s < N+1; s++) {
					for(int e = 1; e < N+1; e++) {
//						System.out.println(dist[s][e]);
//						System.out.println(dist[s][k]);
//						System.out.println(dist[k][e]);
						if(dist[s][k]==Integer.MAX_VALUE || dist[k][e] == Integer.MAX_VALUE) continue;
						if(dist[s][e] > dist[s][k] + dist[k][e]) {
							dist[s][e] = dist[s][k] + dist[k][e];
						}
						if(s==e && dist[s][e] < 0) {
//							System.out.println(s+" "+e);
							flag = true;
							break LOOP;
						}
					}
				}
			}
			
			
			bw.write((flag ? "YES\n" : "NO\n"));
			
		}
		
		bw.close();
		br.close();	
		
	}

}

package net.acmicpc.feb.week1;

import java.io.*;
import java.util.*;


public class BOJ14226 {
	
	static int S;
	static int ans = Integer.MAX_VALUE;
	static int[][] dp;
	
	private static void bfs() {
		Queue<int[]> queue = new LinkedList<>(); // 화면, 클립보드, 시간
		queue.offer(new int[] {1, 0, 0});
		dp[1][0] = 0;
		
		while(!queue.isEmpty()) {
			int[] cur = queue.poll();
			int screen = cur[0]; int clip = cur[1]; int time = cur[2]; 
//			System.out.println("화면: "+screen + " 클립보드 "+ clip+" 시간 "+time+" dp값 "+dp[screen]);
			
			if(screen == S) {
				ans = time;
				return;
			} else { // 작거나 크거나
				// 1번 복사
				if(dp[screen][screen] > time +1) {
					dp[screen][screen] = time+1;
					queue.offer(new int[] {screen, screen, time + 1}); 					
				}
				
				// 2번 붙여넣기
				if(clip > 0 && screen+clip <= 2*S && dp[screen + clip][clip] > time+1) {
					dp[screen + clip][clip] = time+1;
					queue.offer(new int[] {screen + clip, clip, time + 1}); 
				}
				
				// 3번 삭제
				if(screen > 0 && dp[screen-1][clip] > time+1) {
					dp[screen-1][clip] = time+1;
					queue.offer(new int[] {screen-1, clip, time+1});
				}
				
			}
			
			
			
		}
		
		
	}
	
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
		S = Integer.parseInt(br.readLine());
		dp = new int[2*S +2][2*S+2];
		
		for(int i = 0 ; i < 2*S+2; i++) {
			Arrays.fill(dp[i], Integer.MAX_VALUE);			
		}
		
		bfs();
		
		bw.write(ans+"\n");
		bw.close();
		br.close();	
		
	}

}

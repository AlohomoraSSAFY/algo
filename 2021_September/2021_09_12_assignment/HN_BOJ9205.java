package com.baekjoon.problem13;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class HN_BOJ9205 {
	static int T, N;

	static class coordinate {
		int x, y;

		public coordinate(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	static List<coordinate> clists = new LinkedList<>();
	static coordinate home, pestival;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int tc = 0; tc < T; tc++) {
			N = Integer.parseInt(br.readLine());
			StringTokenizer st = new StringTokenizer(br.readLine());
			home = new coordinate(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			for (int n = 0; n < N; n++) {
				st = new StringTokenizer(br.readLine());
				int tmpx = Integer.parseInt(st.nextToken());
				int tmpy = Integer.parseInt(st.nextToken());
				
				clists.add(new coordinate(tmpx, tmpy));
			}
			st = new StringTokenizer(br.readLine());
			pestival = new coordinate(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));

		
			boolean happy = false;
			boolean[] visited = new boolean[clists.size()];
			Queue<coordinate> q = new LinkedList<>();
			q.add(new coordinate(home.x, home.y));
			
			goFestival : while(!q.isEmpty()) {
				int size = q.size();
				for (int s = 0; s < size; s++) {
					coordinate c = q.poll();
					
					int distance1 = getDistance(c.x, c.y, pestival.x, pestival.y);
					if(distance1 <= 1000) {
						happy = true;
						break goFestival;
					}
					
					for (int cl = 0; cl< clists.size(); cl++) {
						coordinate tempc = clists.get(cl);
						int distance2 = getDistance(c.x, c.y, tempc.x, tempc.y);
						if(!visited[cl] && distance2 <= 1000) {
							q.add(tempc);
							visited[cl] = true;
						}
					}
				}
			}
			
			sb.append(happy ? "happy":"sad").append("\n");
			
			clists.clear();
		}
		System.out.println(sb);
	}

	private static int getDistance(int x, int y, int x2, int y2) {
		int distance = Math.abs(x - x2) + Math.abs(y - y2);
		return distance;
	}

}

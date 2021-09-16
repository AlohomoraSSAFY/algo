package com.baekjoon.problem13;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class HN_BOJ4386 {
	static class Star{
		Double x, y;

		public Star(Double x, Double y) {
			this.x = x;
			this.y = y;
		}
	}
	static boolean visited[];
	static int N;
	static double answer;
	static double[] MinDistance;
	static List<Star> slist = new LinkedList<>();
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		answer = 0;
		StringTokenizer st;
		for (int n = 0; n < N; n++) {
			st = new StringTokenizer(br.readLine());
			
			slist.add(new Star(Double.parseDouble(st.nextToken()), Double.parseDouble(st.nextToken())));
		}
		visited = new boolean[N];
		MinDistance = new double[N];
		Arrays.fill(MinDistance, Double.MAX_VALUE);
		Prim();
		
		System.out.printf("%.2f",answer);
	}
	private static void Prim() {
		int idx = 0;
		int cnt = 0;
		
		MinDistance[idx] = 0;
		
		while(true) {
			double tmpMin = Double.MAX_VALUE;
			for (int i = 0; i < MinDistance.length; i++) {
				if(!visited[i] && MinDistance[i] < tmpMin) {
					idx = i;
					tmpMin = MinDistance[i];
				}
			}
			
			visited[idx] = true;
			double cx = slist.get(idx).x;
			double cy = slist.get(idx).y;
			answer += MinDistance[idx];
			if(++cnt == N)
				break;
			for (int i = 0; i < MinDistance.length; i++) {
				if(!visited[i]) {
					double nx = slist.get(i).x;
					double ny = slist.get(i).y;
					tmpMin = getDistance(cx, cy, nx, ny);
					if(MinDistance[i] > tmpMin) {
						MinDistance[i] = tmpMin;
					}
				}
			}
		}
	}
	private static double getDistance(double cx, double cy, double nx, double ny) {
		double result = Math.sqrt(Math.pow(Math.abs(nx-cx), 2) + Math.pow(Math.abs(ny-cy), 2));
		return result;
	}

}

/*
		Collections.sort(slist, new Comparator(){
			@Override
			public int compare(Object o1, Object o2) {
				Star s1 = (Star)o1;
				Star s2 = (Star)o2;
				int result = 0;
				if(s1.x.compareTo(s2.x) == 0) {
					result = s1.y - s2.y > 0 ? -1 : 1;
				}else {
					result = s1.x - s2.x > 0 ? -1 : 1;
				}
				return result;
			}
		});
 */
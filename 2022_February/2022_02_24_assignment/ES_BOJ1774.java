package net.acmicpc.march.week1;

import java.io.*;
import java.util.*;


public class BOJ1774 {
	
	static int N, M;
	static ArrayList<double[]> pos;
	static int[] parent;
	
	private static int find(int x) {
		if(parent[x]== x) {
			return x;
		}
		return (parent[x] = find(parent[x]));		
	}
	
	private static boolean union(int a, int b) {
		int pa = find(a);
		int pb = find(b);
		if(pa == pb) {
			return false;
		} else if(pa < pb) {
			parent[pb] = pa;
		} else {
			parent[pa] = pb;
		}
		return true;
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		pos = new ArrayList<>();
		parent = new int[N];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			double x = Double.parseDouble(st.nextToken());
			double y = Double.parseDouble(st.nextToken());
			pos.add(new double[] {x, y});
			parent[i] = i;
		}
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken()) -1;
			int b = Integer.parseInt(st.nextToken()) -1;
			union(a, b);
		}
		
		ArrayList<double[]> list = new ArrayList<>(); 
		for(int i = 0 ; i < N; i++) {
			if(parent[i] == i) { // 연결 안된 경우
				double ax = pos.get(i)[0];
				double ay = pos.get(i)[1];
				for(int j = 0; j < N; j++) {
					double bx = pos.get(j)[0];
					double by = pos.get(j)[1];
					double dist = Math.sqrt(Math.pow(ax - bx, 2) + Math.pow(ay - by, 2));
					list.add(new double[] {i, j, dist});
//					System.out.println(String.format("(%f, %f)와 (%f, %f)의 거리는 %f", ax, ay, bx, by, dist));
				}
			}
		}
		
		// 오름차순 정렬
		Collections.sort(list, new Comparator<double[]>() {
			@Override
			public int compare(double[] a, double[] b) {
				return Double.compare(a[2], b[2]);
			}
		});
		
		double ans = 0.00;
		for(int i = 0 ; i < list.size(); i++) {
			double[] data = list.get(i);
//			System.out.println(data[0]+" "+data[1]+" "+data[2]);
			if(union((int)data[0], (int)data[1])) { // 연결 X
//				System.out.println("더한 것 "+data[0]+" "+data[1]+" "+data[2]);
				ans += data[2];
			}
		}
		
		bw.write(String.format("%.2f\n", Math.round(ans*100)/100.0));
		bw.close();
		br.close();	
	}

}                                                            
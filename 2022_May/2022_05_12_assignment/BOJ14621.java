package net.acmicpc.may.week4;

import java.io.*;
import java.util.*;


public class BOJ14621 {
	
	static int N, M;
	static int[] univ;
	static int[] parent;
	
	private static int find(int a) {
		if(a == parent[a]) {
			return a;
		}
		
		return (parent[a] = find(parent[a]));
	}
	
	private static void union(int a, int b) {
		int pa = find(a);
		int pb = find(b);
		if(pa < pb) {
			parent[pb] = pa;
		}else if(pa > pb) {
			parent[pa] = pb;
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		univ = new int[N+1];
		for(int i = 1; i < N+1; i++) {
			String tmp = st.nextToken();
			if(tmp.equals("M")) {
				univ[i] = 0;
			}else {
				univ[i] = 1;
			}
		}
		
		List<int[]> list = new ArrayList<>();
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			list.add(new int[] {u, v, d});
		}
		
		parent = new int[N+1];
		for(int i = 1; i < N+1; i++) {
			parent[i] = i;
		}
		
		Collections.sort(list, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[2] - o2[2];
			}
			
		});
		
		int cnt = 0;
		int answer = 0;
		for(int i = 0; i < M; i++) {
			int[] cur = list.get(i);
//			System.out.printf("%d %d %d\n", cur[0], cur[1], cur[2]);
			if(find(cur[0]) != find(cur[1])) {
				if(univ[cur[0]] != univ[cur[1]]) {
					cnt++;
					answer += cur[2];
					union(cur[0], cur[1]);
				}
			}

		}
//		System.out.println(cnt+" "+answer);
		if(cnt == N-1) {
			bw.write(answer +"\n");
		}else {
			bw.write("-1\n");
		}
		bw.close();
		br.close();	
		
	}

}




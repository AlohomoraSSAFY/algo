package study1118;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class SY_BOJ1043 {
	
	static int N;
	static int M;
	static int trueN;
	static int[] trueArray;
	static int[] parent;
	static List<Integer>[] party;
	static boolean[] check;
	static int result;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine(), " ");
		trueN = Integer.parseInt(st.nextToken());
		trueArray = new int[trueN];
		for (int i = 0; i < trueN; i++) {
			trueArray[i] = Integer.parseInt(st.nextToken());
		}
		
		make();
		
		party = new ArrayList[M];
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int partyN = Integer.parseInt(st.nextToken());
			party[i] = new ArrayList<>();
			for (int j = 0; j < partyN; j++) {
				party[i].add(Integer.parseInt(st.nextToken()));
				if (j >= 1) union(party[i].get(j-1), party[i].get(j));
			}
		}
		
		for (int i = 1; i < N+1; i++) {
			find(i);
		}
		
		check = new boolean[N+1];
		for (int i = 0; i < trueN; i++) {
			int t = trueArray[i];
			for (int j = 1; j < N+1; j++) {
				if (parent[j] == parent[t]) check[j] = true;
			}
		}
		
		for (int i = 0; i < M; i++) {
			boolean flag = true;
			for (int j = 0; j < party[i].size(); j++) {
				if (check[party[i].get(j)]) {
					flag = false;
					break;
				}
			}
			if (flag) result++;
		}
		
		System.out.println(result);
	}
	
	static void make() {
		parent = new int[N+1];
		for (int i = 1; i < N+1; i++) {
			parent[i] = i;
		}
	}
	
	static int find(int a) {
		if (parent[a] == a) return a;
		return parent[a] = find(parent[a]);
	}
	
	static boolean union(int a, int b) {
		int pa = find(a);
		int pb = find(b);
		
		if (pa == pb) return false;
		
		parent[pb] = pa;
		return true;
	}

}

package study0909;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SY_BOJ1976 {
	
	static int N;
	static int M;
	static int[][] connect;
	static int[] parent;
	
	public static int find(int a) {
		if (parent[a] == a) return a;
			
		return parent[a] = find(parent[a]);
	}
	
	public static boolean union(int a, int b) {
		int fa = find(a);
		int fb = find(b);
		
		if (fa == fb) {
			return false;
		}
		
		parent[fb] = fa;
		return true;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		parent = new int[N+1];
		for (int i = 1; i < N+1; i++) {
			parent[i] = i;
		}
		
		StringTokenizer st;
		for (int i = 1; i < N+1; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 1; j < N+1; j++) {
				int k = Integer.parseInt(st.nextToken());
				if (k == 1) union(i, j);
			}
		}
		
		st = new StringTokenizer(br.readLine(), " ");
		int[] result = new int[M];
		for (int i = 0; i < M; i++) {
			int t = Integer.parseInt(st.nextToken());
			result[i] = find(t);
		}
		
		boolean flag = true;
		for (int i = 1; i < M; i++) {
			if (result[i-1] != result[i]) {
				flag = false;
				break;
			}
		}
		
		if (flag) System.out.println("YES");
		else System.out.println("NO");
	}

}

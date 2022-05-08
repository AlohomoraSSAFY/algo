package study0512;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

public class SY_BOJ16724 {
	
	static int N, M;
	static char[][] array;
	static int[][] d = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	static int[] parent;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		array = new char[N][M];
		parent = new int[N * M];
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < M; j++) {
				array[i][j] = str.charAt(j);
				parent[i * M + j] = i * M + j;
			}
		}
		
		Map<Character, Integer> map = new HashMap<>();
		map.put('U', 0);
		map.put('D', 1);
		map.put('L', 2);
		map.put('R', 3);
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				int num = map.get(array[i][j]);
				int nx = i + d[num][0];
				int ny = j + d[num][1];
				union(i * M + j, nx * M + ny);
			}
		}
		
		Set<Integer> set = new HashSet<>();
		for (int i = 0; i < N * M; i++) {
			set.add(find(i));
		}
		
		System.out.println(set.size());
	}
	
	private static int find(int a) {
		if (a == parent[a]) return a;
		return parent[a] = find(parent[a]);
	}
	
	private static boolean union(int a, int b) {
		int pa = find(a);
		int pb = find(b);
		
		if (pa == pb) return false;
		
		if (pa < pb) {
			parent[pb] = pa;
		} else {
			parent[pa] = pb;
		}
		
		return true;
	}

}

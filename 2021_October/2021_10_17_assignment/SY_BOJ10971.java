package study1021;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SY_BOJ10971 {
	
	static int N;
	static int[][] array;
	static int[] selected;
	static boolean[] visited;
	static int result;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		array = new int[N][N];
		selected = new int[N];
		visited = new boolean[N];
		result = Integer.MAX_VALUE;
		StringTokenizer st;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				array[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for (int i = 0; i < N; i++) {
			visited[i] = true;
			permutation(1, i, i, 0);
			visited[i] = false;
		}
		
		System.out.println(result);
	}
	
	public static void permutation(int cnt, int firstIdx, int preIdx, int sum) {
		if (cnt == N) {
			if (array[preIdx][firstIdx] == 0) return;
			result = Math.min(result, sum + array[preIdx][firstIdx]);
			return;
		}
		
		for (int i = 0; i < N; i++) {
			if (visited[i] || array[preIdx][i] == 0) continue;
			visited[i] = true;
			permutation(cnt + 1, firstIdx, i, sum + array[preIdx][i]);
			visited[i] = false;
		}
	}

}

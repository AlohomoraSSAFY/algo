package study0210;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SY_BOJ2668 {
	
	static int N;
	static int[] array;
	static boolean[] visited;
	static List<Integer> result;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		N = Integer.parseInt(br.readLine());
		array = new int[N+1];
		visited = new boolean[N+1];
		result = new ArrayList<>();
		for (int i = 1; i < N+1; i++) {
			array[i] = Integer.parseInt(br.readLine());
		}
		
		for (int i = 1; i < N+1; i++) {
			visited[i] = true;
			dfs(array[i], i);
			visited[i] = false;
		}
		
		Collections.sort(result);
		
		sb.append(result.size() + "\n");
		for (int i = 0; i < result.size(); i++) {
			sb.append(result.get(i) + "\n");
		}
		System.out.println(sb);
	}
	
	private static void dfs(int num, int target) {
		if (num == target) {
			result.add(target);
			return;
		}
		
		if (!visited[num]) {
			visited[num] = true;
			dfs(array[num], target);
			visited[num] = false;
		}
	}

}

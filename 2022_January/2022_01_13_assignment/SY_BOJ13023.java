package study0120;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class SY_BOJ13023 {
	
	static int N;
	static int M;
	static List<Integer>[] list;
	static boolean[] visited;
	static int result;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		list = new ArrayList[N];
		visited = new boolean[N];
		for (int i = 0; i < N; i++) {
			list[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			list[a].add(b);
			list[b].add(a);
		}
		
		for (int i = 0; i < N; i++) {
			visited[i] = true;
			recursion(i, 1);
			visited[i] = false;
		}
		
		System.out.println(result);
	}
	
	public static void recursion(int index, int cnt) {
		if (cnt == 5) {
			result = 1;
			return;
		}
		
		for (int i = 0; i < list[index].size(); i++) {
			int temp = list[index].get(i);
			if (visited[temp]) continue;
			
			visited[temp] = true;
			recursion(temp, cnt + 1);
			visited[temp] = false;
			
			if (result == 1) break;
		}
	}

}

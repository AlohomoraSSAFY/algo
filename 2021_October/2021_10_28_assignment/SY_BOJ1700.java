package study1031;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SY_BOJ1700 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int[] array = new int[K];
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < K; i++) {
			array[i] = Integer.parseInt(st.nextToken());
		}
		
		int[] plug= new int[N];
		int t;
		for (t = 0; t < K; t++) {
			for (int i = 0; i < N; i++) {
				if (plug[i] == array[t]) break;
				
				if (plug[i] == 0) {
					plug[i] = array[t];
					break;
				}
			}
			
			if (plug[N-1] != 0) break;
		}
		
		int result = 0;
		for (int i = t; i < K; i++) {
			boolean check = false;
			for (int j = 0; j < N; j++) {
				if (plug[j] == array[i]) {
					check = true;
					break;
				}
			}
			
			if (check) continue;
			
			int cnt = 0;
			boolean[] visited = new boolean[N];
			for (int j = i; j < K; j++) {
				for (int k = 0; k < N; k++) {
					if (array[j] == plug[k]) {
						if (visited[k]) continue;
						
						cnt++;
						visited[k] = true;
						break;
					}
				}
				
				if (cnt == N-1) break;
			}
			
			for (int j = 0; j < K; j++) {
				if (!visited[j]) {
					plug[j] = array[i];
					result++;
					break;
				}
			}
		}
		
		System.out.println(result);
	}

}

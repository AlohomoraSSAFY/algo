package study0623;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SY_BOJ3584 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for (int tc = 0; tc < T; tc++) {
			int N = Integer.parseInt(br.readLine());
			int[] parent = new int[N+1];
			
			StringTokenizer st;
			for (int i = 0; i < N-1; i++) {
				st = new StringTokenizer(br.readLine());
				int A = Integer.parseInt(st.nextToken());
				int B = Integer.parseInt(st.nextToken());
				parent[B] = A;
			}
			
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			boolean[] check = new boolean[N+1];

			while (a != 0) {
				check[a] = true;
				a = parent[a];
			}
			
			while (!check[b]) {
				b = parent[b];
			}
			
			sb.append(b + "\n");
		}
		
		System.out.println(sb);
	}

}

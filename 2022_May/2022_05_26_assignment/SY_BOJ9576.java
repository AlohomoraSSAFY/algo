package study0602;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class SY_BOJ9576 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for (int i = 0; i < T; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			
			int[][] array = new int[M][2];
			for (int j = 0; j < M; j++) {
				st = new StringTokenizer(br.readLine(), " ");
				array[j][0] = Integer.parseInt(st.nextToken());
				array[j][1] = Integer.parseInt(st.nextToken());
			}

			Arrays.sort(array, new Comparator<int[]>() {
				@Override
				public int compare(int[] o1, int[] o2) {
					if (o1[1] == o2[1]) {
						return o1[0] - o2[0];
					}
					return o1[1] - o2[1];
				}
			});
			
			boolean[] visited = new boolean[N+1];
			int result = 0;
			for (int j = 0; j < M; j++) {
				int a = array[j][0];
				int b = array[j][1];
				
				for (int k = a; k <= b; k++) {
					if (!visited[k]) {
						visited[k] = true;
						result++;
						break;
					}
				}
			}
			
			sb.append(result + "\n");
		}
		
		System.out.println(sb);
	}

}

package study0726;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SY_BOJ1027 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] array = new int[N+1];
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for (int i = 1; i < N+1; i++) {
			array[i] = Integer.parseInt(st.nextToken());
		}
		
		int max = 0;
		for (int i = 1; i < N+1; i++) {
			int cnt = 0;
			for (int j = 1; j < N+1; j++) {
				if (i == j) continue;
				
				boolean flag = true;
				for (int k = Math.min(i, j) + 1; k < Math.max(i, j); k++) {
					double temp = ((double)(array[j] - array[i]) / (j - i)) * (k - i) + array[i];
					if (array[k] >= temp) {
						flag = false;
						break;
					}
				}
				
				if (flag) cnt++;
			}
			
			max = Math.max(max, cnt);
		}
		
		System.out.println(max);
	}

}

package study0512;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SY_BOJ1756 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int D = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		int[] array = new int[D];
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < D; i++) {
			array[i] = Integer.parseInt(st.nextToken());
		}
		
		for (int i = 1; i < D; i++) {
			if (array[i] >= array[i-1]) {
				array[i] = array[i-1];
			}
		}	
		
		st = new StringTokenizer(br.readLine(), " ");
		int depth = D - 1;
		int idx = 0;
		for (; idx < N; idx++) {
			int num = Integer.parseInt(st.nextToken());
			boolean flag = false;
			while (depth >= 0) {
				if (num <= array[depth]) {
					flag = true;
					depth--;
					break;
				} else {
					depth--;
				}
			}
			
			if (!flag) break;
		}
		
		if (idx < N) {
			System.out.println(0);
		} else {
			System.out.println(depth + 2);
		}
	}

}

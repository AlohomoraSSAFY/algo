package study0830;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class SY_BOJ19941 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		char[] array = br.readLine().toCharArray();
		List<Integer> list = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			if (array[i] == 'P') list.add(i);
		}
		
		int result = 0;
		for (int i = 0; i < list.size(); i++) {
			int idx = list.get(i);
			int left = idx - K;
			int right = idx + K;
			if (left < 0) left = 0;
			if (right >= N) right = N - 1;
			
			for (int j = left; j <= right; j++) {
				if (array[j] == 'H') {
					result++;
					array[j] = 'P';
					break;
				}
			}
		}
		
		System.out.println(result);
	}

}

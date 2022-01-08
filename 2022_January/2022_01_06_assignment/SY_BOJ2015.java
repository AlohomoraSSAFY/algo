package study0113;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class SY_BOJ2015 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		long K = Long.parseLong(st.nextToken());
		long[] array = new long[N];
		
		st = new StringTokenizer(br.readLine(), " ");
		array[0] = Long.parseLong(st.nextToken());
		for (int i = 1; i < N; i++) {
			array[i] = array[i-1] + Long.parseLong(st.nextToken());
		}
		
		long result = 0;
		HashMap<Long, Long> map = new HashMap<>();
		for (int i = 0; i < N; i++) {
			if (array[i] == K) result++;
			if (map.containsKey(array[i] - K)) result += map.get(array[i] - K);
			
			if (map.containsKey(array[i])) {
				map.put(array[i], map.get(array[i]) + 1);
			} else {
				map.put(array[i], (long)1);
			}
		}
		
		System.out.println(result);
	}

}

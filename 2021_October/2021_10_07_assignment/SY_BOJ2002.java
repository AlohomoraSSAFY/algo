package study1011;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class SY_BOJ2002 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		Map<String, Integer> map = new HashMap<>();
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			map.put(str, i);
		}
		
		int[] array = new int[N];
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			array[i] = map.get(str);
		}
		
		int cnt = 0;
		for (int i = 0; i < N-1; i++) {
			for (int j = i+1; j < N; j++) {
				if (array[i] > array[j]) {
					cnt++;
					break;
				}
			}
		}
		
		System.out.println(cnt);
	}

}

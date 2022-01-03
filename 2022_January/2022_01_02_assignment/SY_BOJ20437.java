package study0106;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class SY_BOJ20437 {
	
	static int[] array;
	static int min;
	static int max;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int tc = 0; tc < T; tc++) {
			array = new int[26];
			min = Integer.MAX_VALUE;
			max = Integer.MIN_VALUE;
			
			String W = br.readLine();
			for (int i = 0; i < W.length(); i++) {
				array[W.charAt(i) - 'a'] += 1;
			}
			
			int K = Integer.parseInt(br.readLine());
			
			char temp = 'a';
			int count = 0;
			int result = 0;
			for (int i = 0; i < W.length(); i++) {
				array[W.charAt(i) - 'a'] -= 1;
				if (array[W.charAt(i) - 'a'] < K - 1) continue;
				
				temp = W.charAt(i);
				count = 0;
				result = 0;
				for (int j = i; j < W.length(); j++) {
					result++;
					if (temp == W.charAt(j)) count++;
					if (count == K) {
						min = Math.min(min, result);
						max = Math.max(max, result);
						break;
					}
				}
			}
			
			if (min == Integer.MAX_VALUE) {
				sb.append("-1\n");
			} else {
				sb.append(min + " " + max + "\n");
			}
		}
		
		System.out.println(sb);
	}

}

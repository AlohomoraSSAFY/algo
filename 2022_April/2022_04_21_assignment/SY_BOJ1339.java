package study0428;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class SY_BOJ1339 {
	
	static int[] array;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		array = new int[26];
		int N = Integer.parseInt(br.readLine());
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			int num = (int)Math.pow(10, str.length() - 1);
			for (int j = 0; j < str.length(); j++) {
				array[str.charAt(j) - 'A'] += num;
				num /= 10;
			}
		}
		
		Arrays.sort(array);
		
		int result = 0;
		int idx = 9;
		for (int i = 25; i > 16; i--) {
			result += (array[i] * idx--);
		}
		
		System.out.println(result);
	}

}

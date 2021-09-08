package study0909;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SY_BOJ17609 {
	
	static char[] array;
	static int result;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for (int tc = 0; tc < T; tc++) {
			array = br.readLine().toCharArray();
			result = 2;
			check(0, array.length - 1, true);
			sb.append(result + "\n");
		}
		System.out.println(sb);
	}

	public static void check(int left, int right, boolean flag) {
		if (left >= right) {
			if (flag) {
				result = 0;
			} else {
				result = 1;
			}
			
			return;
		}
		
		if (array[left] == array[right]) {
			check(left + 1, right - 1, flag);
		} else {
			boolean b = false;
			if (flag && array[left + 1] == array[right]) {
				b = true;
				check(left + 1, right, false);
			}
			
			if (flag && array[left] == array[right - 1]) {
				b = true;
				check(left, right - 1, false);
			}
			
			if (!b) {
				return;
			}
		}
	}
}

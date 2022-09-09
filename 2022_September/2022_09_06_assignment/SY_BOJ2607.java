package study0920;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class SY_BOJ2607 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] alphabet = new int[26];
		String str = br.readLine();
		for (int i = 0; i < str.length(); i++) {
			char c = str.charAt(i);
			alphabet[c - 'A']++;
		}
		
		int result = 0;
		for (int i = 0; i < N-1; i++) {
			int[] array = new int[26];
			str = br.readLine();
			for (int j = 0; j < str.length(); j++) {
				char c = str.charAt(j);
				array[c - 'A']++;
			}
			
			boolean flag = true;
			boolean check1 = false;
			boolean check2 = false;
			for (int j = 0; j < 26; j++) {
				if (Math.abs(alphabet[j] - array[j]) > 1
						|| (check1 && alphabet[j] - array[j] == 1)
						|| (check2 && array[j] - alphabet[j] == 1)) {
					flag = false;
					break;
				}
				
				if (alphabet[j] - array[j] == 1) check1 = true;
				if (array[j] - alphabet[j] == 1) check2 = true;
			}
			
			if (flag) result++;
		}
		
		System.out.println(result);
	}

}

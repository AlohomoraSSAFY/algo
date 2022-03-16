package study0317;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class SY_BOJ1254 {
	
	static char[] cArray;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		cArray = br.readLine().toCharArray();
		
		boolean flag = false;
		int idx = 0;
		for (int i = 0; i < cArray.length - 1; i++) {
			if (isPalindrome(i)) {
				flag = true;
				idx = i;
				break;
			}
		}
		
		if (flag) {
			System.out.println(cArray.length + idx);
		} else {
			System.out.println(cArray.length * 2 - 1);
		}
	}
	
	private static boolean isPalindrome(int idx) {
		int left = idx;
		int right = cArray.length - 1;
		while (left < right) {
			if (cArray[left] != cArray[right]) {
				return false;
			}
			
			left++;
			right--;
		}
		
		return true;
	}

}

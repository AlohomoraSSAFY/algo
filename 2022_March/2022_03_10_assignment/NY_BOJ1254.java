package date0310;

import java.io.BufferedReader;
import java.io.*;
import java.util.*;

public class BOJ1254 {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();
		int len = str.length();
		for (int i = 0; i < len; i++) {
			if (isPalindrome(str.substring(i, len))) {
				int answer =str.length()+i;
				System.out.println(answer);
				break;
			} 
		}
	}

	public static boolean isPalindrome(String str) {

		int len = str.length();
		for (int i = 0; i < len / 2; i++) {
			if (str.charAt(i) != str.charAt(len - i - 1)) {
				return false;
			}
		}
		return true;
	}

}

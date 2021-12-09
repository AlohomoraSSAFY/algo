package date1205SUN;

import java.util.*;
import java.io.*;
import java.lang.reflect.Array;

public class BOJ2661_2 {

	static int n;
	static int[] selected;
	static int[] visited;
	static String str;

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		selected = new int[n];
		visited = new int[4];
		str ="";
		permutation(str, 0, 0);

	}

	public static void permutation(String str, int count, int num) {
		if (count == n) {
			System.out.println(str);

			System.exit(0);

			return;
		}

		for (int i = 1; i <= 3; i++) {
			if (i != num) {
				String temp=str+ Integer.toString(i);
				if(isgood(temp)) {
					permutation(temp, count + 1, i);
				}
			}
		}
	}

	public static boolean isgood(String str) {
		
		for(int i=1;i<=str.length()/2;i++) { //i개씩 길이 체크
			for(int j=0;j<str.length()-(i*2)+1;j++) { //j번째 체크
				if(str.substring(j, j+i).equals(str.substring(j+i,j+(2*i)))){
					return false;
				}
			}	
		}
		return true;
	}

}

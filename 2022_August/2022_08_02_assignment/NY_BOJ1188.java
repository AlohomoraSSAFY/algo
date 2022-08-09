package date0802;

import java.io.*;
import java.util.*;

public class BOJ1188 {

	public static void main(String[] args)  throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		System.out.println(m-gcd(n,m));
		
	}
	public static int gcd(int a, int b) {
		if(b==0) 
			return a;
		return gcd(b,a%b);
	}
	

}

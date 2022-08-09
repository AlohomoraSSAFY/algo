package study0809;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SY_BOJ1188 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		System.out.println(M - gcd(N, M));
	}
	
	private static int gcd(int a, int b) {
		if (b == 0) return a;
		
		return gcd(b, a % b);
	}

}

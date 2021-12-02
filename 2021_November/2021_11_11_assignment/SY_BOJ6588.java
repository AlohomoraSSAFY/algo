package study1114;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class SY_BOJ6588 {
	
	static boolean[] isPrime;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		isPrime = new boolean[1000001];
		for (int i = 2; i < 1000001; i++) {
			boolean flag = true;
			for (int j = 2; j < i; j++) {
				if (i % j == 0) {
					flag = false;
					break;
				}
				if (j * j >= i) break;
			}
			if (flag) isPrime[i] = true;
		}
		
		while (true) {
			int t = Integer.parseInt(br.readLine());
			if (t == 0) break;
			
			boolean flag = false;
			for (int i = 3; i < t/2+1; i++) {
				int a = i;
				int b = t - i;
				
				if (isPrime[a] && isPrime[b]) {
					sb.append(t + " = " + a + " + " + b + "\n");
					flag = true;
					break;
				}
			}
			
			if (!flag) sb.append("Goldbach's conjecture is wrong.\n");
		}
		
		System.out.println(sb);
	}

}

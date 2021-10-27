package study1024;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SY_BOJ3020 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int H = Integer.parseInt(st.nextToken());
		
		int[] up = new int[H+1];
		int[] down = new int[H+1];
		
		for (int i = 0; i < N; i++) {
			int temp = Integer.parseInt(br.readLine());
			
			if (i % 2 == 0) up[temp] += 1;
			else down[H-temp+1] += 1;
		}
		
		for (int i = H-1; i >= 1; i--) {
			up[i] += up[i+1];
		}
		for (int i = 2; i <= H; i++) {
			down[i] += down[i-1];
		}
		
		int min = N;
		int cnt = 0;
		for (int i = 1; i <= H; i++) {
			int temp = up[i] + down[i];
			
			if (temp < min) {
				min = temp;
				cnt = 1;
			} else if (temp == min) {
				cnt++;
			}
		}
		
		System.out.println(min + " " + cnt);
	}

}

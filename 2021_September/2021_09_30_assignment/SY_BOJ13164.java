package study1003;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SY_BOJ13164 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int[] array = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			array[i] = Integer.parseInt(st.nextToken());
		}
		
		if (K == 1) {
			System.out.println(array[N-1] - array[0]);
		} else if (K == N) {
			System.out.println(0);
		} else {
			int[] temp = new int[N-1];
			for (int i = 0; i < N-1; i++) {
				temp[i] = array[i+1] - array[i];
			}
			Arrays.sort(temp);
			
			int result = 0;
			for (int i = 0; i < N-K; i++) {
				result += temp[i];
			}
			System.out.println(result);
		}
	}

}

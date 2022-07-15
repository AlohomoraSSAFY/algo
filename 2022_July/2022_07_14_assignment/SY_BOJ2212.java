package study0721;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SY_BOJ2212 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int K = Integer.parseInt(br.readLine());
		
		int[] array = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			array[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(array);
		
		int[] diff = new int[N-1];
		for (int i = 0; i < N-1; i++) {
			diff[i] = array[i+1] - array[i];
		}
		
		Arrays.sort(diff);
		
		int result = 0;
		for (int i = 0; i < N-K; i++) {
			result += diff[i];
		}
		
		System.out.println(result);
	}

}

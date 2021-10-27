package study1028;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SY_BOJ21758 {
	
	static int N;
	static int[] array;
	static int[] sum;
	static int result;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		array = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < N; i++) {
			array[i] = Integer.parseInt(st.nextToken());
		}
		
		sum = new int[N];
		sum[0] = array[0];
		for (int i = 1; i < N; i++) {
			sum[i] = sum[i-1] + array[i];
		}
		
		for (int i = 1; i < N-1; i++) {
			int temp = (sum[N-2] - array[i]) + (sum[i-1]);
			result = Math.max(result, temp);
		}
		
		for (int i = 1; i < N-1; i++) {
			int temp = (sum[N-1] - sum[0] - array[i]) + (sum[N-1] - sum[i]);
			result = Math.max(result, temp);
		}
		
		for (int i = 1; i < N-1; i++) {
			int temp = (sum[i] - sum[0]) + (sum[N-2] - sum[i-1]);
			result = Math.max(result, temp); 
		}
		
		System.out.println(result);
	}

}

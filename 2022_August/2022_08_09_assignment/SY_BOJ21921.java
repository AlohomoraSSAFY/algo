package study0816;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SY_BOJ21921 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int X = Integer.parseInt(st.nextToken());
		int[] array = new int[N];
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < N; i++) {
			array[i] = Integer.parseInt(st.nextToken());
		}
		
		int temp = 0;
		for (int i = 0; i < X; i++) {
			temp += array[i];
		}
		
		int max = temp;
		int cnt = 1;
		for (int i = X; i < N; i++) {
			temp = temp - array[i-X] + array[i];
			if (temp > max) {
				max = temp;
				cnt = 1;
			} else if (temp == max) {
				cnt++;
			}
		}
		
		if (max == 0) {
			System.out.println("SAD");
		} else {
			System.out.println(max);
			System.out.println(cnt);
		}
	}

}

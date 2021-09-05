package study0905;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SY_BOJ14569 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(br.readLine());
		long[] subject = new long[N];
		StringTokenizer st;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int k = Integer.parseInt(st.nextToken());
			for (int j = 0; j < k; j++) {
				subject[i] |= ((long)1 << Integer.parseInt(st.nextToken()));
			}
		}
		
		int M = Integer.parseInt(br.readLine());
		long[] student = new long[M];
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int p = Integer.parseInt(st.nextToken());
			for (int j = 0; j < p; j++) {
				student[i] |= ((long)1 << Integer.parseInt(st.nextToken()));
			}
		}
		
		for (int i = 0; i < M; i++) {
			int count = 0;
			for (int j = 0; j < N; j++) {
				if ((student[i] & subject[j]) == subject[j]) count++;
			}
			sb.append(count + "\n");
		}
		System.out.println(sb);
	}

}

package date1003SUN;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ13164 {
	static int n, k;
	static int answer;
	static int kid[];
	static int cost[];

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		kid = new int[n];
		cost = new int[n-1];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			kid[i] = Integer.parseInt(st.nextToken());
		}

		for (int i = 0; i < n - 1; i++) {
			cost[i] = kid[i + 1] - kid[i];
		}

		Arrays.sort(cost);
		for (int i = 0; i < n-k; i++) {
			answer += cost[i];
		}
		System.out.println(answer);
	}

}

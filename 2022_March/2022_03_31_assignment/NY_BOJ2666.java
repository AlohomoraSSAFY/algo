package date0331;

import java.io.*;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ2666 {
	static int n, m;
	static int arr[];
	static int answer = 0;

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());

		StringTokenizer st = new StringTokenizer(br.readLine());

		int a = Integer.parseInt(st.nextToken());
		int b = Integer.parseInt(st.nextToken());

		answer = Integer.MAX_VALUE;
		m = Integer.parseInt(br.readLine());
		Queue<Integer> list = new LinkedList<>();
		for (int i = 0; i < m; i++) {
			int c = Integer.parseInt(br.readLine());
			list.add(c);
		}
		dfs(list, a, b, 0);
		System.out.println(answer);

	}

	public static void dfs(Queue<Integer> list, int a, int b, int count) {
		
		if (list.isEmpty()) {
			answer = Math.min(count, answer);
			return;
		}

		Queue<Integer> nq = new LinkedList<>();
		for(Integer i : list)
			nq.add(i);
		
		int temp = nq.poll();
		dfs(nq, a, temp, count + Math.abs((int) (temp - b)));
		dfs(nq, temp, b, count + Math.abs((int) (temp - a)));

		return;

	}

}

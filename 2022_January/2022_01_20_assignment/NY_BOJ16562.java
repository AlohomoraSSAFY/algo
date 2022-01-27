package date0127THU;

import java.io.BufferedReader;
import java.io.*;
import java.util.*;

public class BOJ16562 {

	static int n, m, k;
	static int cost[];
	static int costsum;
	static int parent[];

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());

		parent = new int[n + 1];
		for (int i = 1; i <= n; i++) {
			parent[i] = i;
		}

		boolean[] check = new boolean[n + 1];
		st = new StringTokenizer(br.readLine());
		cost = new int[n + 1];
		costsum = 0;
		for (int i = 1; i <= n; i++) {
			cost[i] = Integer.parseInt(st.nextToken());
		}

		ArrayList<Integer> list[] = new ArrayList[n + 1];

		for (int i = 0; i <= n; i++) {
			list[i] = new ArrayList<>();
		}

		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			union(a, b);
		}
		
		int count = 0;
		for (int i = 1; i < n + 1; i++) {
			list[parent[i]].add(i);
		}
		
		boolean fail = false;
		
		for (int i = 1; i <= n; i++) {
			if (list[i].size() == 0)
				continue;
			int min = Integer.MAX_VALUE;
			for (int d : list[i]) {
				check[d] = true;
				min = Math.min(min, cost[d]);
			}
			costsum += min;
			if (costsum > k) {
				fail = true;
				break;
			}
		}

		if (fail)
			System.out.println("Oh no");
		else
			System.out.println(costsum);

	}

	public static void union(int a, int b) {

		a = find(a);
		b = find(b);
		if (a < b) {
			parent[b] = find(a);
			setParent(b, find(a));
		} else {
			parent[a] = find(b);
			setParent(a, find(b));
		}
	}

	public static void setParent(int p, int num) {
		for (int i = 1; i <= n; i++) {
			if (parent[i] == p)
				parent[i] = num;
		}
	}

	public static int find(int a) {
		if (parent[a] == a)
			return a;
		return find(parent[a]);
	}

}

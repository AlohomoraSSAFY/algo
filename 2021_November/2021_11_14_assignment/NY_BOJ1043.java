package date1118THU;

import java.util.*;
import java.io.*;

public class BOJ1043 { //거짓말
	static int n, m;
	static ArrayList<Integer> truth;
	static int answer;
	static int parent[];

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		answer = 0;
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		parent = new int[n + 1];
		for (int i = 0; i <= n; i++) {
			parent[i] = i;
		}

		ArrayList<int[]> list = new ArrayList<>();
		truth = new ArrayList<>();

		st = new StringTokenizer(br.readLine());

		int tnum = Integer.parseInt(st.nextToken());

		for (int j =0; j < tnum; j++) {
			int a = Integer.parseInt(st.nextToken());
			truth.add(a);
		}

		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int num = Integer.parseInt(st.nextToken());
			if(num !=0 ) {
			int arr[] = new int[num];
			
			int curr = Integer.parseInt(st.nextToken());
			arr[0]=curr;
			for (int j = 1; j < num; j++) {
				arr[j] = Integer.parseInt(st.nextToken());
				union(curr, arr[j]);
			}
			list.add(arr);
			}else
				answer++;
		}

		for (int[] arr : list) {
			boolean check = false;
			for (int i : arr) {
				for(int t : truth) {
					if(find(i) == find(t)) {
						check = true;
						break;
					}
				}
			if(check)
				break;
			}
			
			if (!check) {
				answer++;
			}
		}
		System.out.println(answer);
	}

	static void union(int a, int b) {
		a = find(a);
		b = find(b);

		if( a < b)
			parent[b] = a;
		else
			parent[a] = b;
	}

	static int find(int a) {
		if (parent[a] == a)
			return a;
		return find(parent[a]);
	}

}

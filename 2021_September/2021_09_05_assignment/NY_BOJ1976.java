package date0909;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.StringTokenizer;

public class BOJ1976 {
	static int n, m;
	static int route[];
	static int[] parent;

	public static int find(int a) {
		if (parent[a] == a)
			return a;
		else
			return parent[a] = find(parent[a]);
	}

	public static void union(int a, int b) {
		a = find(a);
		b = find(b);
		if (a != b) {
			if (a > b)
				parent[a] = b;
			else
				parent[b] = a;
		}

	}

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		n = Integer.parseInt(br.readLine());
		m = Integer.parseInt(br.readLine());

		parent = new int[n];
		
		for( int i=0;i<n;i++) {
			parent[i]=i;
		}

		route = new int[n];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				int a = Integer.parseInt(st.nextToken());
				if(a==1) {
					union(i,j);
				}
			}
		}
	
		st = new StringTokenizer(br.readLine());
		
		HashSet<Integer> hs = new HashSet<>();
		
		for(int i =0;i<m;i++) {
			int a = Integer.parseInt(st.nextToken())-1;
			hs.add(parent[a]);
		}
		
		if (hs.size() != 1)
			System.out.println("NO");
		else
			System.out.println("YES");
		
		
	}

}

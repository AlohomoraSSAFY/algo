package date1202THU;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
import java.util.*;

public class BOJ11725 {
	static 	ArrayList<Integer>[] list;
	static int parent[];
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		boolean visited[] = new boolean[n+1];
		list = new ArrayList[n+1];
		for(int i=0;i<=n;i++) {
			list[i] = new ArrayList<Integer>();
		}
		for (int i = 0; i < n - 1; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			list[a].add(b);
			list[b].add(a);
		}
		parent = new int[n + 1];
		parent[1] = 1;

		
		bfs();
		for (int i = 2; i <= n; i++) {
			sb.append(parent[i] + "\n");
		}
		System.out.println(sb.toString());

	}
	public static void bfs() {
		Queue<Integer> q = new LinkedList<Integer>();
		
		q.offer(1);
		
		while(!q.isEmpty()) {
			int temp = q.poll();
			for(int i : list[temp]) {
				if(parent[i] == 0) {
					parent[i] = temp;
					q.offer(i);
				}
			}
		}
	}

}

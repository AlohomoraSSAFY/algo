package date1107SUN;

import java.io.*;
import java.util.*;

public class BOJ11779 { //최소 비용 구하기
	static int n, m;
	static int graph[][];

	static int distance[];
	static int prev[];
	static boolean visited[];
	static int from, to;

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		m = Integer.parseInt(br.readLine());
		graph = new int[n + 1][n + 1];
		distance = new int[n + 1];
		visited = new boolean[n + 1];
		prev = new int[n+1];

		Stack<Integer> path = new Stack<>();

		for (int i = 0; i <= n; i++) {
			for (int j = 0; j <= n; j++) {
				graph[i][j] = Integer.MAX_VALUE;
			}
		}
		
		for (int i = 0; i <= n; i++) {
			distance[i] = Integer.MAX_VALUE;
		}

		for (int i = 0; i < m; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			if (graph[a][b] > c)
				graph[a][b] = c;

		}

		StringTokenizer st = new StringTokenizer(br.readLine());

		from = Integer.parseInt(st.nextToken());
		to = Integer.parseInt(st.nextToken());

		distance[from] = 0;

		
		for (int i = 0; i < n-1 ; i++) {
			int minidx = -1;
			int min = Integer.MAX_VALUE;

			for (int j = 1; j <= n; j++) {
				if (!visited[j] && distance[j] < min) {
					min = distance[j];
					minidx = j;
				}
			}
			
			visited[minidx] = true;
			
			if (minidx == to)
				break;

			for (int j = 1; j <= n; j++) {
				if (!visited[j] && graph[minidx][j] != Integer.MAX_VALUE && min + graph[minidx][j] < distance[j]) {
					distance[j] = min + graph[minidx][j];
					prev[j] = minidx;
				}
			}
		}
		
		System.out.println(distance[to]);
		int check = to;
		path.add(to);
		while(true) {
			int temp = prev[check];
			path.add(temp);
			if(temp == from)
				break;
			else
				check = temp;
		}
		System.out.println(path.size());
		while(!path.isEmpty())
			System.out.print(path.pop()+" ");

	}

}

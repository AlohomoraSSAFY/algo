package date1211SUN;

import java.io.*;
import java.util.*;

class Node implements Comparable<Node> {
	int from;
	int to;
	int dist;

	public Node(int from, int to, int dist) {
		super();
		this.from = from;
		this.to = to;
		this.dist = dist;
	}

	@Override
	public int compareTo(Node o) {
		return this.dist - o.dist;
	}
}

public class BOJ1647 {
	static int n;
	static int m;
	static int parents[];
	static int answer;

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		answer = 0;
		PriorityQueue<Node> pq = new PriorityQueue<>();

		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			pq.offer(new Node(a, b, c));
		}
		//

		parents = new int[n + 1];
		for (int i = 1; i <= n; i++) {
			parents[i] = i;
		}

		//

		int d = 0;
		int count = 0;
		for(int i=0;i<m;i++) {
			Node temp = pq.poll();
			int start = temp.from;
			int end = temp.to;
			int a = find(start);
			int b = find(end);
			if (a != b) {
				union(a, b);
				answer += temp.dist;
				d = temp.dist;
				count++;
			}
			if(count>=n)
				break;
		}
		answer -= d;
		System.out.println(answer);
	}

	public static void union(int a, int b) {
		a = find(a);
		b = find(b);

		if (a < b) {
			parents[b] = a;
		} else if (a > b) {
			parents[a] = b;
		}
		return;

	}

	public static int find(int a) {
		if (parents[a] == a)
			return a;
		return parents[a] = find(parents[a]);

	}

}

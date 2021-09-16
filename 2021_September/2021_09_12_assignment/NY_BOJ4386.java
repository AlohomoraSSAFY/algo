package date0916THU;

import java.io.*;
import java.util.*;

class Ed {
	int from;
	int to;
	double dist;

	public Ed(int from, int to, double dist) {
		super();
		this.from = from;
		this.to = to;
		this.dist = dist;
	}

}

public class BOJ4386 {
	static int n;
	static double stars[][];
	static boolean visited[][];
	static int parent[];
	static double answer;

	private static void union(int a, int b) {
		a = find(a);
		b = find(b);
		if (a > b) {
			parent[a] = b;
		} else {
			parent[b] = a;
		}
	}

	private static int find(int x) {
		if (parent[x] == x)
			return x;
		else
			return find(parent[x]);
	}

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		ArrayList<Ed> list = new ArrayList<>();
		visited = new boolean[n][n];
		stars = new double[n][2];
		answer = 0;
		parent = new int[n];
		for (int i = 0; i < n; i++) {
			parent[i] = i;
		}
		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			stars[i][0] = Float.parseFloat(st.nextToken());
			stars[i][1] = Float.parseFloat(st.nextToken());
		}

		for (int i = 0; i < n; i++) {
			for (int j = i+1; j < n; j++) {
				double dist = Math.sqrt(((stars[i][0] - stars[j][0]) * (stars[i][0] - stars[j][0]))
						+ ((stars[i][1] - stars[j][1]) * (stars[i][1] - stars[j][1])));
				list.add(new Ed(i, j, dist));
			}
		}

		Collections.sort(list, new Comparator<Ed>() {

			@Override
			public int compare(Ed o1, Ed o2) {
				// TODO Auto-generated method stub
				if (o1.dist < o2.dist)
					return -1;
				else if (o1.dist > o2.dist)
					return 1;
				else
					return 0;
			}
		});

		for( int i=0;i<list.size();i++) {
			if( find(list.get(i).from)!= find(list.get(i).to)) {
				answer += list.get(i).dist;
				union(list.get(i).from,list.get(i).to);			
				}
		}
		System.out.println(String.format("%.2f", answer));
	}

}

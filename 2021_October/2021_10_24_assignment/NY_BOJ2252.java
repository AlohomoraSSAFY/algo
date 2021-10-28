package date1028THU;

import java.io.BufferedReader;
import java.io.*;
import java.util.*;

import javax.swing.plaf.synth.SynthSplitPaneUI;

public class BOJ2252 { // 줄세우기(위상정렬)
	static int n;
	static int m;

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		ArrayList<ArrayList<Integer>> list = new ArrayList<>();
		for (int i = 0; i <= n; i++) {
			list.add(new ArrayList<>());
		}
		
		int[] indegree = new int[n + 1];

		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			list.get(a).add(b);
			indegree[b]++;
		}

		Queue<Integer> q = new LinkedList<>();
		
		for (int i = 1; i <= n; i++) {
			if (indegree[i] == 0)
				q.add(i);
		}

		while (!q.isEmpty()) {
			int cur = q.poll();
			sb.append(cur + " ");

			for (int i : list.get(cur)) {
				indegree[i]--;
				if (indegree[i] == 0)
					q.add(i);
			}

			indegree[cur] = -1;

		}

		System.out.println(sb.toString());
	}

}

package date1226SUN;

import java.io.*;
import java.util.*;

public class BOJ2056 {
	static int n;
	static int time[];

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		time = new int[n + 1];
		int[] finish = new int[n + 1];

		ArrayList<ArrayList<Integer>> list = new ArrayList<>();
		int answer = 0;
		int[] indegree = new int[n + 1];
		for (int i = 0; i <= n; i++) {
			list.add(new ArrayList<>());
		}

		for (int i = 1; i <= n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			time[i] = Integer.parseInt(st.nextToken());
			int num = Integer.parseInt(st.nextToken());
			indegree[i] = num;
			for (int j = 0; j < num; j++) {
				int a = Integer.parseInt(st.nextToken());

				list.get(a).add(i);
			}
		}

		Queue<Integer> q = new LinkedList<>();

		for (int i = 1; i <= n; i++) {
			if (indegree[i] == 0)
				q.offer(i);
		}

		while (!q.isEmpty()) {

			int cur = q.poll();

			finish[cur] += time[cur]; // 이 일이 완료되는데 걸리는 시간 + 이전일들 모두 처리하는데 걸리는 시간

			indegree[cur] = -1;

			for (int i : list.get(cur)) {
				indegree[i]--;
				finish[i] = Math.max(finish[cur], finish[i]);
				if (indegree[i] == 0)
					q.offer(i);
			}

		}
		for (int i : finish) {
			answer = Math.max(i, answer);
		}
		System.out.println(answer);

	}

}

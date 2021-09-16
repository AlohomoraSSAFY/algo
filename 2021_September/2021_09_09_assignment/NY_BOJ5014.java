package date0912;

import java.io.*;
import java.util.*;

public class BJ5014 {

	static int f, s, g, u, d;
	static int count[];
	static boolean visited[];

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		f = Integer.parseInt(st.nextToken()); // 건물 총 층 수
		s = Integer.parseInt(st.nextToken()); // 현재 위치
		g = Integer.parseInt(st.nextToken()); // 목적지
		u = Integer.parseInt(st.nextToken()); // 위로 u층
		d = Integer.parseInt(st.nextToken()); // 아래로 d층
		
		visited = new boolean[f + 1];
		count = new int[f + 1];

		Arrays.fill(count, -1);
		bfs();

		if (count[g] == -1)
			System.out.println("use the stairs");
		else
			System.out.println(count[g]);
	}

	public static void bfs() {
		Queue<Integer> q = new LinkedList<>();
		q.add(s);
		visited[s] = true;
		count[s] =0;
		
		while (!q.isEmpty()) {

			int temp = q.poll();

			if (temp == g)
				return;

			int uu = temp + u;
			if (uu <= f && uu > 0 && !visited[uu]) {
				visited[uu] = true;
				q.add(uu);
				count[uu] = count[temp] + 1;
			}
			int dd = temp - d;
			if (dd <= f && dd > 0 && !visited[dd]) {
				visited[dd] = true;
				q.add(dd);
				count[dd] = count[temp] + 1;
			}

		}

	}

}

package study0310;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class SY_BOJ9019 {
	
	static int A, B;
	static StringBuilder sb;
	
	static class Register {
		int num;
		String cmd;
		
		public Register(int num, String cmd) {
			this.num = num;
			this.cmd = cmd;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		StringTokenizer st;
		for (int tc = 0; tc < T; tc++) {
			st = new StringTokenizer(br.readLine(), " ");
			A = Integer.parseInt(st.nextToken());
			B = Integer.parseInt(st.nextToken());
			
			bfs();
		}
		
		System.out.println(sb);
	}
	
	private static void bfs() {
		Queue<Register> q = new LinkedList<>();
		q.offer(new Register(A, ""));
		boolean[] visited = new boolean[10001];
		visited[A] = true;
		
		while (!q.isEmpty()) {
			Register cur = q.poll();
			
			if (cur.num == B) {
				sb.append(cur.cmd + "\n");
				return;
			}
			
			int num1 = (cur.num * 2) % 10000;
			if (!visited[num1]) {
				visited[num1] = true;
				q.offer(new Register(num1, cur.cmd + "D"));
			}
			
			int num2 = cur.num - 1;
			if (num2 == -1) num2 = 9999;
			if (!visited[num2]) {
				visited[num2] = true;
				q.offer(new Register(num2, cur.cmd + "S"));
			}
			
			int num3 = (cur.num % 1000) * 10 + (cur.num / 1000);
			if (!visited[num3]) {
				visited[num3] = true;
				q.offer(new Register(num3, cur.cmd + "L"));
			}
			
			int num4 = (cur.num % 10) * 1000 + (cur.num / 10);
			if (!visited[num4]) {
				visited[num4] = true;
				q.offer(new Register(num4, cur.cmd + "R"));
			}
		}
	}

}

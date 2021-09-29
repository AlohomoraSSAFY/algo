package study0930;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class SY_BOJ13913 {
	
	static int N;
	static int K;
	static int[] visited;
	static int result;
	
	static class Pos {
		int p;
		int time;
		
		public Pos(int p, int time) {
			this.p = p;
			this.time = time;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		visited = new int[100001];
		
		bfs();
		
		int temp = K;
		Stack<Integer> stack = new Stack<>();
		while (true) {
			stack.add(temp);
			temp = visited[temp];
			if (temp == -1) break;
		}
		
		sb.append(result + "\n");
		int size = stack.size();
		for (int i = 0; i < size; i++) {
			sb.append(stack.pop() + " ");
		}
		
		System.out.println(sb);
	}
	
	public static void bfs() {
		Queue<Pos> q = new LinkedList<>();
		q.offer(new Pos(N, 0));
		Arrays.fill(visited, -2);
		visited[N] = -1;
		
		while (!q.isEmpty()) {
			Pos pos = q.poll();
			int p = pos.p;
			int t = pos.time;
			
			if (p == K) {
				result = t;
				break;
			}
			
			if (p - 1 >= 0 && visited[p - 1] == -2) {
				visited[p - 1] = p;
				q.offer(new Pos(p - 1, t + 1));
			}
			
			if (p + 1 <= 100000 && visited[p + 1] == -2) {
				visited[p + 1] = p;
				q.offer(new Pos(p + 1, t + 1));
			}
			
			if (2 * p <= 100000 && visited[2 * p] == -2) {
				visited[2 * p] = p;
				q.offer(new Pos(2 * p, t + 1));
			}
		}
	}

}

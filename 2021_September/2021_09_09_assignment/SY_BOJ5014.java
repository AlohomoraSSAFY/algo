package study0912;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class SY_BOJ5014 {
	
	static int F;
	static int S;
	static int G;
	static int U;
	static int D;
	static int cnt;
	static int[] visit;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		F = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());
		G = Integer.parseInt(st.nextToken());
		U = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		visit = new int[F + 1];
		bfs(S);
		
		if (visit[G] == 0) {
			System.out.println("use the stairs");
		} else {
			System.out.println(cnt);
		}
	}
	
	public static void bfs(int s) {
		Queue<Integer> q = new LinkedList<>();
		q.offer(s);
		visit[s] = 1;
		
		while(!q.isEmpty()) {
			int k = q.poll();
			
			if (k == G) {
				cnt = visit[k] - 1;
				break;
			}
			
			if (k + U <= F && visit[k + U] == 0) {
				visit[k + U] = visit[k] + 1;
				q.offer(k + U);
			}
			
			if (k - D >= 1 && visit[k - D] == 0) {
				visit[k - D] = visit[k] + 1;
				q.offer(k - D);
			}
		}
	}

}

package study0916;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class SY_BOJ9205 {
	
	static int n;
	static int[] home;
	static List<int[]> con;
	static int[] fes;
	static boolean result;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int t = Integer.parseInt(br.readLine());
		for (int tc = 0; tc < t; tc++) {
			n = Integer.parseInt(br.readLine());
			home = new int[2];
			con = new ArrayList<int[]>();
			fes = new int[2];
			result = false;
			
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			home[0] = Integer.parseInt(st.nextToken());
			home[1] = Integer.parseInt(st.nextToken());
			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				int[] temp = new int[2];
				temp[0] = Integer.parseInt(st.nextToken());
				temp[1] = Integer.parseInt(st.nextToken());
				con.add(temp);
			}
			st = new StringTokenizer(br.readLine(), " ");
			fes[0] = Integer.parseInt(st.nextToken());
			fes[1] = Integer.parseInt(st.nextToken());
			
			bfs(home[0], home[1]);
			
			if (result) {
				sb.append("happy\n");
			} else {
				sb.append("sad\n");
			}
		}
		System.out.println(sb);
	}
	
	public static void bfs(int x, int y) {
		Queue<int[]> q = new LinkedList<>();
		boolean[] visited = new boolean[n];
		q.offer(new int[] {x, y});
		
		while(!q.isEmpty()) {
			int[] temp = q.poll();
			int nx = temp[0];
			int ny = temp[1];
			
			if (Math.abs(fes[0] - nx) + Math.abs(fes[1] - ny) <= 20 * 50) {
				result = true;
				break;
			}
			
			for (int i = 0; i < n; i++) {
				//남은 맥주로 갈 수 있는 거리인가?
				int res = Math.abs(con.get(i)[0] - nx) + Math.abs(con.get(i)[1] - ny);
				if (res <= 20 * 50 && visited[i] == false) {
					//방문처리
					visited[i] = true;
					q.offer(new int[] {con.get(i)[0], con.get(i)[1]});
				}
			}
		}
	}

}

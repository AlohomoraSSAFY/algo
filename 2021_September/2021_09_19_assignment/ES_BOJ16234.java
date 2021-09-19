package net.acmicpc.bfs;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;
import java.util.*;
public class BOJ16234 {
	
	
	
	static int N, L, R;
	static int[][] A;
	static boolean[][] visited;
	static int[] dy = {-1, 1, 0, 0};
	static int[] dx = {0, 0, -1, 1};
	static int cnt = 0;
	static List<int[]> country;
	
	private static boolean bfs(int sy, int sx) {
//		System.out.println("START "+sy+" "+ sx);
		Queue<Integer> queue = new LinkedList<>();
		queue.offer(sy);
		queue.offer(sx);		
		country = new ArrayList<>();
		visited[sy][sx] = true;
		country.add(new int[] {sy, sx});
		
		int sum = A[sy][sx];
		while(!queue.isEmpty()) {
			int y = queue.poll();
			int x = queue.poll();
			int prev = A[y][x];
			for(int i = 0 ; i < 4; i++) {
				int ny = y + dy[i];
				int nx = x + dx[i];

//				System.out.println("FOR "+ny+" "+ nx+" ");				
				if(ny < 0 || nx < 0 || ny > N -1 || nx > N -1) continue;
//				System.out.print("A°ªÀº "+prev+" ");
				int diff = Math.abs( (prev - A[ny][nx]) );
//				System.out.println(diff);
				if(diff >= L && diff <= R && visited[ny][nx]==false) {
					visited[ny][nx] = true;
					queue.offer(ny);
					queue.offer(nx);
					country.add(new int[]{ny, nx});
					sum += A[ny][nx];
				}
			}
		}
		
//		System.out.println("test");
		
		if(country.size() > 1) {
			for(int i = 0 ; i < country.size(); i++) {
				int[] data = country.get(i);
//				System.out.println(data[0]+" "+data[1]);
				A[data[0]][data[1]] = sum/country.size();
//				System.out.println("new "+sum+" "+ country.size()+" " + A[data[0]][data[1]]);
			}
			return true;
		}
		
		return false;
		
	}
	
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("./res/16234_5.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		
		A = new int[N][N];		
		for(int i = 0; i < N; i++) {
			String[] str = br.readLine().split(" ");
			for(int j = 0 ; j < N ; j++) {
				A[i][j] = Integer.parseInt(str[j]);
			
			}
		}
		
		while(true) {
			
			visited = new boolean[N][N];
			boolean flag = false;
			for(int i = 0 ; i < N; i++) {
				for(int j = 0 ; j < N; j++) {
					if(visited[i][j]==false) {
						boolean tmp = bfs(i, j);
						if(tmp == true) {
							flag = true;
						}
					}
				}
			}
			
			if( !flag ) {
				break;
			}
			
			cnt++;
			
		}
		
		bw.write(cnt+"\n");
		bw.close();
		br.close();	
		
	}
}

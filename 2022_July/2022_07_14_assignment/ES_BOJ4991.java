package algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main4991 {
	
	static int w, h;
	static char[][] map;
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	static List<int[]> point;
	static int[][] dist;
	static int[] selected;
	static int answer;
	
	private static void bfs(int idx) {
		int[] s = point.get(idx);
		boolean[][] visited = new boolean[h][w];
		
		Queue<int[]> q = new LinkedList<int[]>();
		q.add(new int[] {s[0], s[1]});
		visited[s[0]][s[1]] = true;
		int[][] level = new int[h][w];
		
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			int r = cur[0]; int c = cur[1];
			for(int d = 0; d < 4; d++) {
				int nr = r + dr[d];
				int nc = c + dc[d];
				if( nr < 0 || nc <0 || nr > h-1 || nc > w-1 ) continue;
				if(!visited[nr][nc]) {
					if(map[nr][nc] == 'x') { // º®
						continue;
					} else if(map[nr][nc] == '.') { // ºóÄ­
						level[nr][nc] = level[r][c]+1;
						visited[nr][nc] = true;
						q.add(new int[] {nr, nc});
					} else if(map[nr][nc]=='o') { // ·Îº¿
						level[nr][nc] = level[r][c]+1;
						visited[nr][nc] = true;
						q.add(new int[] {nr, nc});
					} else { // ¸ÕÁö
						level[nr][nc] = level[r][c]+1;
						visited[nr][nc] = true;
						q.add(new int[] {nr, nc});
					}
				}
			}
		}
		
//		System.out.println("levle");
//		printMap(level);
		for(int i = 0 ; i < point.size(); i++) {
			if(i == idx) continue;
			int[] comp = point.get(i);
			dist[idx][i] = level[comp[0]][comp[1]];
		}
	}
	
	static boolean flag;
	
	private static void dfs(int cnt, boolean[] checked) {
		if(cnt == point.size()) {
			int sum = 0;
			for(int i = 0; i < cnt-1; i++) {
				int val = dist[selected[i]][selected[i+1]];
				if(val == 0) {
					sum = Integer.MAX_VALUE;
					break;
				} 
				sum += (val);
			}
			answer = Math.min(answer, sum);
		}
		
		for(int i = 0; i < point.size()-1; i++) {
			if(!checked[i]) {
				checked[i] = true;
				selected[cnt] = i;
				dfs(cnt+1, checked);
				checked[i] = false;
			}
		}
	}
	
	private static void printMap(int[][] tmp) {
		System.out.println();
		System.out.println("+++++++++++++++++++");
		for(int i = 0; i < tmp.length; i++) {
			for(int j = 0; j < tmp[i].length; j++) {
				System.out.printf("%3d ", tmp[i][j]);
			}
			System.out.println();
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		while(true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			w = Integer.parseInt(st.nextToken());
			h = Integer.parseInt(st.nextToken());

			if(w==0 && h ==0) {
				break;
			}
			
			map = new char[h][w];
			point = new ArrayList<>();
			int sr = -1, sc = -1; int sn = -1;
			char idx = '0';
			for(int i = 0; i < h; i++) {
				map[i] = br.readLine().toCharArray();
				for(int j = 0; j < w; j++) {
					if(map[i][j] == '*') {
//						map[i][j] = (idx++);
						point.add(new int[] {i, j});
					} else if(map[i][j]=='o') {
						sr = i; sc = j;
					}
				}
			}
			
			point.add(new int[] {sr, sc});
			
			int size = point.size();
			dist = new int[size][size];
			
			
			for(int i = 0; i < size; i++) {
				bfs(i);
			}
//			printMap(dist);
			answer = Integer.MAX_VALUE;
			selected = new int[size];
			boolean[] checked = new boolean[size];
			selected[0] = size-1;
			checked[size-1] = true;
			dfs(1, checked);
			
			if(point.size() != 0 && answer == Integer.MAX_VALUE) {
				bw.write("-1\n");
			} else {
				bw.write(answer+"\n");				
			}
			
			
		}
		
		
		bw.close();
		br.close();
	}

}

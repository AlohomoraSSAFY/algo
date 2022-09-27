package net.acmicpc.august.week4;

import java.io.*;
import java.util.*;


public class BOJ9328 {
	
	static int TC, h, w;
	static char[][] map;
	static boolean[][] visited;
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	static int ans, keys;
	static Queue<int[]> q;
	static Set<Integer> key;
	static List<int[]>[] door;
	
	private static void check(int r, int c) {
		if(map[r][c] == '.') {
			q.add(new int[] {r, c});
			visited[r][c] = true;
		}
		else if('A' <= map[r][c] && map[r][c] <= 'Z') { // 문
			door[map[r][c]-'A'].add(new int[] {r, c});
		} else if('a' <= map[r][c] && map[r][c] <= 'z') {// 열쇠
			q.add(new int[] {r, c});
			key.add(map[r][c]-'a');
			visited[r][c] = true;
		} else { // 문서
			q.add(new int[] {r, c});
			visited[r][c] = true;
			ans++;
		}
	}
	
	private static void bfs() {
		q = new LinkedList<>();
		
		// 외곽
		for(int i = 0; i < w-1; i++) { // -
			if(map[0][i] == '*') continue;
			check(0, i);
		}
		
		for(int i = 0; i < h-1; i++) { // |
			if(map[i][w-1] == '*') continue;
			check(i, w-1);
		}
		
		for(int i = 1; i < w; i++) { // -
			if(map[h-1][i] == '*') continue;
			check(h-1, i);
		}
		
		for(int i = 1; i < h; i++) { // |
			if(map[i][0] == '*') continue;
			check(i, 0);
		}
		
		// key에 대한 문 추가
		for(int c : key) {
			if(!door[c].isEmpty()) {
				for(int[] e : door[c]) {
					q.add(e);
					visited[e[0]][e[1]] = true;
				}
			}
		}
		

		// 큐 탐색
		while(!q.isEmpty()){
			int[] cur = q.poll(); int r = cur[0]; int c = cur[1];
			for(int d = 0; d < 4; d++) {
				int nr = r + dr[d]; int nc = c + dc[d];
				if(nr < 0 || nc < 0 || nr > h-1 || nc > w-1 || map[nr][nc] == '*' || visited[nr][nc]) continue;
				if(map[nr][nc] == '.') {
					q.add(new int[] {nr, nc});
					visited[nr][nc] = true;
				}
				else if('A' <= map[nr][nc] && map[nr][nc] <= 'Z') { // 문
					if(key.contains(map[nr][nc] - 'A')) { // 열쇠가 있는 경우
						visited[nr][nc] = true;
						q.add(new int[] {nr, nc});
					} else { // 없는 경우
						door[map[nr][nc]-'A'].add(new int[] {nr, nc});
					}
				} else if('a' <= map[nr][nc] && map[nr][nc] <= 'z') {
					int k = map[nr][nc]-'a';
					key.add(k);
					if(!door[k].isEmpty()) {
						for(int[] e : door[k]) {
							q.add(e);
							visited[e[0]][e[1]] = true;
						}
					}
					visited[nr][nc] = true;
					q.add(new int[] {nr, nc});
				} else {
					visited[nr][nc] = true;
					ans++;
					q.add(new int[] {nr, nc});
				}

				
			}
			
		}

	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		TC = Integer.parseInt(br.readLine());
		for(int cs = 1; cs < TC+1; cs++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			h = Integer.parseInt(st.nextToken());
			w = Integer.parseInt(st.nextToken());
			map = new char[h][w];
			visited = new boolean[h][w];
			ans = 0; keys = 0;
			door = new ArrayList[30];
			for(int i = 0; i < 30; i++) {
				door[i] = new ArrayList<int[]>();
			}
			
			for(int i = 0; i < h; i++) {
				map[i] = br.readLine().toCharArray();
			}
			
			String line = br.readLine();
			key = new HashSet<>();
			if(!line.equals("0")) {
				for(int i = 0; i < line.length(); i++) {
					key.add(line.charAt(i)-'a');
				}
			}
			
			bfs();
			bw.write(ans+"\n");
			
		}
		
		
		bw.close();
		br.close();	
		
	}

}
package study0421;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class SY_BOJ2174 {
	
	static HashMap<String, Integer> map;
	static int[][] d = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
	
	static class Robot {
		int x;
		int y;
		int dir;
		
		public Robot(int x, int y, int dir) {
			this.x = x;
			this.y = y;
			this.dir = dir;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		map = new HashMap<>();
		map.put("N", 0);
		map.put("E", 1);
		map.put("S", 2);
		map.put("W", 3);
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int A = Integer.parseInt(st.nextToken());
		int B = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[][] array = new int[B+1][A+1];
		Robot[] robot = new Robot[N+1];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int y = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());
			int dir = map.get(st.nextToken());
			array[x][y] = i + 1;
			robot[i+1] = new Robot(x, y, dir);
		}
		
		boolean flag = true;
		String result = "OK";
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int idx = Integer.parseInt(st.nextToken());
			String cmd = st.nextToken();
			int r = Integer.parseInt(st.nextToken());
			
			if (!flag) continue;
			int x = robot[idx].x;
			int y = robot[idx].y;
			int dir = robot[idx].dir;
			
			if (cmd.equals("L")) {
				robot[idx] = new Robot(x, y, (dir - r + 100) % 4);
			} else if (cmd.equals("R")) {
				robot[idx] = new Robot(x, y, ((dir + r) % 4));
			} else {
				int nx = x;
				int ny = y;
				
				while(r-- > 0) {
					array[nx][ny] = 0;
					nx = nx + d[dir][0];
					ny = ny + d[dir][1];
					
					if (nx < 1 || nx > B || ny < 1 || ny > A) {
						flag = false;
						result = "Robot " + idx + " crashes into the wall";
						break;
					} else if (array[nx][ny] > 0) {
						flag = false;
						result = "Robot " + idx + " crashes into robot " + array[nx][ny];
						break;
					} else {
						array[nx][ny] = idx;
						robot[idx].x = nx;
						robot[idx].y = ny;
					}
				}
			}
		}
		
		System.out.println(result);
	}

}

package study1017;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class SY_BOJ16954 {
	
	static char[][] array;
	static List<int[]> list;
	static int[][] d = {{-1, 0}, {-1, 1}, {0, 1}, {1, 1}, {1, 0}, {1, -1}, {0, -1}, {-1, -1}, {0, 0}};
	static int result;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		array = new char[8][8];
		list = new ArrayList<>();
		
		StringTokenizer st;
		for (int i = 0; i < 8; i++) {
			char[] cArray = br.readLine().toCharArray();
			for (int j = 0; j < 8; j++) {
				array[i][j] = cArray[j];
				if (array[i][j] == '#') {
					list.add(new int[] {i, j});
				}
			}
		}
		
		bfs();
		System.out.println(result);
	}
	
	public static void bfs() {
		Queue<int[]> q = new LinkedList<>();
		q.offer(new int[] {7, 0});
		
		while (!q.isEmpty()) {
			int size = q.size();
			for (int i = 0; i < size; i++) {
				int[] cur = q.poll();
				int x = cur[0];
				int y = cur[1];
				
				for (int j = 0; j < 9; j++) {
					int nx = x + d[j][0];
					int ny = y + d[j][1];
					
					if (nx < 0 || nx >= 8 || ny < 0 || ny >= 8) continue;
					
					boolean flag = true;
					for (int k = 0; k < list.size(); k++) {
						int wx = list.get(k)[0];
						int wy = list.get(k)[1];
						
						if ((nx == wx && ny == wy) || (nx >= 1 && nx - 1 == wx && ny == wy)) {
							flag = false;
							break;
						}
					}
					
					if (!flag) continue;
					
					if (nx == 0 && ny == 7) {
						result = 1;
						return;
					}
					
					q.offer(new int[] {nx, ny});
				}
			}
			
			moveWall();
		}
	}
	
	public static void moveWall() {
		for (int i = 0; i < list.size(); i++) {
			int x = list.get(i)[0];
			if (x == 7) {
				list.remove(i);
				i--;
			} else {
				list.get(i)[0] = x + 1;
			}
		}
	}

}

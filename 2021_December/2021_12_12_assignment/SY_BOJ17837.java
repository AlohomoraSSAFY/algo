package study1216;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class SY_BOJ17837 {
	
	static int N;
	static int K;
	static int[][] array;
	static int[][] dir = {{0, 0}, {0, 1}, {0, -1}, {-1, 0}, {1, 0}};
	static List<Integer>[][] board;
	static List<int[]> horses;
	static boolean flag;
	static int result;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		array = new int[N+1][N+1];
		for (int i = 1; i < N+1; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 1; j < N+1; j++) {
				array[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		board = new ArrayList[N+1][N+1];
		for (int i = 1; i < N+1; i++) {
			for (int j = 1; j < N+1; j++) {
				board[i][j] = new ArrayList<>();
			}
		}
		
		horses = new ArrayList<>();
		horses.add(new int[] {0, 0, 0});
		for (int i = 1; i <= K; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			board[r][c].add(i);
			horses.add(new int[] {r, c, d});
		}
		
		while (!flag) {
			move();
			if(++result > 1000) {
				result = -1;
				break;
			}
		}
		
		System.out.println(result);
	}
	
	public static void move() {
		for (int i = 1; i <= K; i++) {
			int x = horses.get(i)[0];
			int y = horses.get(i)[1];
			int d = horses.get(i)[2];
			
			int nx = x + dir[d][0];
			int ny = y + dir[d][1];
			
			if (nx < 1 || nx > N || ny < 1 || ny > N || array[nx][ny] == 2) {
				//체스판을 벗어나는 경우 or 파란색
				if (d == 1) {
					d = 2;
				} else if (d == 2) {
					d = 1;
				} else if (d == 3) {
					d = 4;
				} else {
					d = 3;
				}
				
				nx = x + dir[d][0];
				ny = y + dir[d][1];
				
				horses.set(i, new int[] {x, y, d});
			}
			
			if (nx < 1 || nx > N || ny < 1 || ny > N || array[nx][ny] == 2) {
				//체스판을 벗어나는 경우 or 파란색
				continue;
			} else if (array[nx][ny] == 0) {
				//흰색
				boolean check = false;
				List<Integer> temp = new ArrayList<>();
				Queue<Integer> tempMove = new LinkedList<>();
				for (int j = 0; j < board[x][y].size(); j++) {
					if (board[x][y].get(j) == i) check = true;
					if (check) {
						tempMove.offer(board[x][y].get(j));
					} else {
						temp.add(board[x][y].get(j));
					}
				}
				board[x][y] = temp;
				
				int size = tempMove.size();
				for (int j = 0; j < size; j++) {
					int cur = tempMove.poll();
					board[nx][ny].add(cur);
					horses.set(cur, new int[] {nx, ny, horses.get(cur)[2]});
				}
			} else {
				//빨간색
				boolean check = false;
				List<Integer> temp = new ArrayList<>();
				Stack<Integer> tempMove = new Stack<>();
				for (int j = 0; j < board[x][y].size(); j++) {
					if (board[x][y].get(j) == i) check = true;
					if (check) {
						tempMove.push(board[x][y].get(j));
					} else {
						temp.add(board[x][y].get(j));
					}
				}
				board[x][y] = temp;
				
				int size = tempMove.size();
				for (int j = 0; j < size; j++) {
					int cur = tempMove.pop();
					board[nx][ny].add(cur);
					horses.set(cur, new int[] {nx, ny, horses.get(cur)[2]});
				}
			}
			
			if (board[nx][ny].size() >= 4) {
				flag = true;
				break;
			}
		}
	}

}

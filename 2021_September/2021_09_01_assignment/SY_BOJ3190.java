package study0905;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class SY_BOJ3190 {
	
	static int N;
	static List<Pos> apple;
	static List<Pos> snake;
	static List<Info> change;
	static int[][] d = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
	static int direction = 1;
	static int time;
	
	static class Pos {
		int x;
		int y;
		
		public Pos(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	static class Info {
		int n;
		char c;
		
		public Info(int n, char c) {
			this.n = n;
			this.c = c;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		apple = new LinkedList<>();
		snake = new LinkedList<>();
		change = new LinkedList<>();
		StringTokenizer st;
		
		int K = Integer.parseInt(br.readLine());
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			Pos aPos = new Pos(x, y);
			apple.add(aPos);
		}
		
		int L = Integer.parseInt(br.readLine());
		for (int i = 0; i < L; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int n = Integer.parseInt(st.nextToken());
			char c = st.nextToken().charAt(0);
			Info info = new Info(n, c);
			change.add(info);
		}
		
		Pos sPos = new Pos(1, 1);
		snake.add(sPos);
		game(1, 1);
		System.out.println(time);
	}
	
	public static void game(int x, int y) {
		time += 1;
		int nx = x + d[direction][0];
		int ny = y + d[direction][1];
		
		// 벽에 부딪히면 끝
		if (nx < 1 || nx > N || ny < 1 || ny > N) return;
		
		// 자기 몸과 부딪히면 끝
		for (int i = 0; i < snake.size(); i++) {
			int sx = snake.get(i).x;
			int sy = snake.get(i).y;
			if (nx == sx && ny == sy) return;
		}
		
		// 뱀의 위치에 포함
		Pos npos = new Pos(nx, ny);
		snake.add(npos);
		
		// 사과가 있으면 사과를 먹고, 없으면 꼬리 부분 빼주기
		boolean check = false;
		for (int i = 0; i < apple.size(); i++) {
			int ax = apple.get(i).x;
			int ay = apple.get(i).y;
			if (nx == ax && ny == ay) {
				check = true;
				apple.remove(i);
				break;
			}
		}
		if (!check) {
			snake.remove(0);
		}
		
		// 시간에 따른 방향 전환 있는지 확인
		if (!change.isEmpty() && time == change.get(0).n) {
			if (change.get(0).c == 'L') {
				direction -= 1;
			} else {
				direction += 1;
			}
			if (direction < 0) {
				direction += 4;
			} else if (direction >= 4) {
				direction -= 4;
			}
			change.remove(0);
		}
		
		// 재귀
		game(nx, ny);
	}
}

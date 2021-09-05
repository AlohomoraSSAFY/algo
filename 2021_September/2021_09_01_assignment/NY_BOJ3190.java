import java.io.*;
import java.util.*;

class Snake {
	int x;
	int y;

	public Snake(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}
}

public class BOJ3190 {
	static int n, k, l;
	static int map[][];
	
	static int count;
	
	static int dx[] = { 0, 1, 0, -1 };
	static int dy[] = { 1, 0, -1, 0 };
	
	static boolean gameover;
	static int x, y, d;
	static int sec;
	static char direction;
	
	static Queue<Snake> q = new LinkedList<>();

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		n = Integer.parseInt(br.readLine());
		k = Integer.parseInt(br.readLine());

		map = new int[n + 1][n + 1];

		map[1][1] = 1; // 뱀은 1

		for (int i = 0; i < k; i++) { // 사과 위치 입력
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			map[a][b] = 2; // 사과가 2
		}

		l = Integer.parseInt(br.readLine());

		gameover = false;
		count = 0;

		x = 1;
		y = 1;

		d = 0; // 처음 시작은 오른쪽을 향함
		q.offer(new Snake(1, 1)); // 초기위치

		for (int i = 0; i < l; i++) { // 뱀의 방향 변환 정보 입력
			st = new StringTokenizer(br.readLine());
			sec = Integer.parseInt(st.nextToken());
			direction = st.nextToken().charAt(0);

			go(sec, direction);
			if (gameover)
				break;
		}
		
		if (!gameover)
			go(Integer.MAX_VALUE, direction);

		//

		System.out.println(count);
	}

	public static void go(int end, char dir) {

		while (count < end) {

			count++;

			int nx = x + dx[d];
			int ny = y + dy[d];

			q.offer(new Snake(nx, ny)); // 머리 늘려서 다음칸에 위치시킴

			if (nx >= 1 && ny >= 1 && nx <= n && ny <= n) { // 다음칸이 맵 안이고
				if (map[nx][ny] == 0) { // 이동할 칸이 빈칸이라면

					Snake cur = q.poll(); // 큐에서 하나 뽑아서 ->꼬리
					map[cur.x][cur.y] = 0; // 꼬리있던칸 비움

					map[nx][ny] = 1;

				} else if (map[nx][ny] == 1) { // 자기 자신이랑 부딪히면 게임오버
					gameover = true;
					return;
				} else if (map[nx][ny] == 2) { // 이동할 칸에 사과가 있다면
					map[nx][ny] = 1; // 머리 움직이고 꼬리는 그대로
				}
				
				//위치업데이트
				x = nx;
				y = ny;

			} else { // 다음으로 갈 곳이 맵 밖이면 벽에 부딪혀서 게임 오버
				gameover = true;
				return;
			}
		}

		// end초에서 마지막에 방향 바꿈
		if (dir == 'D') // 오른쪽으로 90도
			d = (d + 1) % 4;
		else // 왼쪽으로 90도
			d = (d + 3) % 4;
	}

}

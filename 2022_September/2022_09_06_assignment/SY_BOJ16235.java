package study0920;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class SY_BOJ16235 {
	
	static int N, M, K;
	static int[][] array;
	static int[][] add;
	static PriorityQueue<Integer>[][] list;
	static int[][] d = {{-1, 0}, {-1, 1}, {0, 1}, {1, 1}, {1, 0}, {1, -1}, {0, -1}, {-1, -1}};

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		array = new int[N+1][N+1];
		for (int i = 1; i <= N; i++) {
			Arrays.fill(array[i], 5);
		}
		
		add = new int[N+1][N+1];
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 1; j <= N; j++) {
				add[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		list = new PriorityQueue[N+1][N+1];
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				list[i][j] = new PriorityQueue<>();
			}
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int z = Integer.parseInt(st.nextToken());
			list[x][y].add(z);
		}
		
		for (int i = 0; i < K; i++) {
			//봄 & 여름
			springAndSummer();
			
			//가을
			fall();
			
			//겨울
			winter();
		}
		
		int result = 0;
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				result += list[i][j].size();
			}
		}
		
		System.out.println(result);
	}
	
	private static void springAndSummer() {
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				PriorityQueue<Integer> pq = new PriorityQueue<>();
				int sum = 0;
				while (!list[i][j].isEmpty()) {
					int age = list[i][j].poll();
					if (age <= array[i][j]) {
						pq.offer(age + 1);
						array[i][j] -= age;
					} else {
						sum += (age / 2);
					}
				}
				
				list[i][j] = pq;
				array[i][j] += sum;
			}
		}
	}
	
	private static void fall() {
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				int cnt = 0;
				for (Integer age : list[i][j]) {
					if (age % 5 == 0) cnt++;
				}
				
				for (int k = 0; k < 8; k++) {
					int nx = i + d[k][0];
					int ny = j + d[k][1];
					
					if (nx < 1 || nx > N || ny < 1 || ny > N) continue;
					
					for (int l = 0; l < cnt; l++) {
						list[nx][ny].add(1);
					}
				}
			}
		}
	}
	
	private static void winter() {
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				array[i][j] += add[i][j];
			}
		}
	}

}

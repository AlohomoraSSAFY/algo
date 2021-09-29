package study0926;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class SY_BOJ18428 {
	
	static int N;
	static char[][] array;
	static List<int[]> list;
	static List<int[]> tList;
	static int[][] d = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
	static boolean result;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		array = new char[N][N];
		list = new ArrayList<>();
		tList = new ArrayList<>();
		result = false;
		StringTokenizer st;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				array[i][j] = st.nextToken().charAt(0);
				if (array[i][j] == 'X') {
					list.add(new int[] {i, j});
				}
				if (array[i][j] == 'T') {
					tList.add(new int[] {i, j});
				}
			}
		}
		
		recursion(0, 0);
		
		if (result) {
			System.out.println("YES");
		} else {
			System.out.println("NO");
		}
	}
	
	public static void recursion(int cnt, int start) {
		if (cnt == 3) {
			if (check()) result = true;
			return;
		}
		
		for (int i = start; i < list.size(); i++) {
			//O 적용
			array[list.get(i)[0]][list.get(i)[1]] = 'O';
			
			//재귀
			recursion(cnt + 1, i + 1);
			
			//O 적용 취소
			array[list.get(i)[0]][list.get(i)[1]] = 'X';
			
			if (result) break;
		}
	}
	
	public static boolean check() {
		boolean flag = true;
		here: for (int i = 0; i < tList.size(); i++) {
			int x = tList.get(i)[0];
			int y = tList.get(i)[1];
			
			for (int j = 0; j < 4; j++) {
				int nx = x;
				int ny = y;
				while (true) {
					nx += d[j][0];
					ny += d[j][1];
					
					if (nx < 0 || nx >= N || ny < 0 || ny >= N) break;
					if (array[nx][ny] == 'O' || array[nx][ny] == 'T') break;
					if (array[nx][ny] == 'S') {
						flag = false;
						break here;
					}
				}
			}
		}
		
		return flag;
	}

}

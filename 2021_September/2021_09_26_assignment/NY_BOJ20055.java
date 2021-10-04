package date0930THU;

import java.io.*;
import java.util.*;

public class BOJ20055 {
	static int n, k;
	static int belt[];
	static boolean robot[];
	static int count;
	static int answer;

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());

		belt = new int[n * 2];
		robot = new boolean[n];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < 2 * n; i++) {
			belt[i] = Integer.parseInt(st.nextToken());

		}

		answer = 0;
		while (true) {
			answer++;

			// 벨트 회전, 로봇 회전
			int temp = belt[2 * n - 1];
			for (int i = 2 * n - 1; i > 0; i--) {
				belt[i] = belt[i - 1];
			}
			belt[0] = temp;

			for (int i = n - 1; i > 0; i--) {
				robot[i] = robot[i - 1];
			}
			robot[0] = false;
			robot[n - 1] = false;

			// 로봇 이동
			for (int i = n-1; i >0; i--) {
				if (robot[i-1] && !robot[i] && belt[i] > 0) { // 로봇이 i번째 위치에 있고 이동하려는 칸(i+1)번째 위치가 비어있고 내구도가 0초과
					robot[i-1] = false;
					robot[i] = true;
					belt[i]--;
				}
			}
			robot[n - 1] = false;

			// 0위치에 로봇 올리기
			if (belt[0] > 0) {
				robot[0] = true;
				belt[0]--;
			}

			//k확인
			int num = 0;
			for (int i = 0; i < 2 * n; i++) {
				if (belt[i] == 0)
					num++;
			}
			if (num >= k)
				break;

		}

		System.out.println(answer);

	}

}

package date1007THU;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ3019 {
	static int c, p;
	static int arr[];
	static int answer;
	static int block[][][] = { {}, { { 0 }, { 0, 0, 0, 0 } }, // 1
			{ { 0, 0 } }, // 2
			{ { 0, 0, 1 }, { 1, 0 } }, // 3
			{ { 1, 0, 0 }, { 0, 1 } }, // 4
			{ { 0, 0, 0 }, { 1, 0 }, { 0, 1 }, { 1, 0, 1 } }, // 5
			{ { 0, 0, 0 }, { 0, 0 }, { 0, 1, 1 }, { 2, 0 } }, // 6
			{ { 0, 0, 0 }, { 0, 0 }, { 1, 1, 0 }, { 0, 2 } } // 7
	};

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		c = Integer.parseInt(st.nextToken());
		p = Integer.parseInt(st.nextToken());
		arr = new int[c];
		answer = 0;
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < c; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		for (int i = 0; i < block[p].length; i++) { // p번 블럭에서 나올 수 있는 모양만큼 반복
			for (int k = 0; k <= c - block[p][i].length; k++) { // 열 처음부터 모양 나오는지 확인
				boolean flag = true;
				for (int j = 0; j < block[p][i].length - 1; j++) { // 그 모양의 길이만큼 반복
						if ((arr[k + j] - block[p][i][j]) != (arr[k + j+1] - block[p][i][j + 1])) {
							flag = false;
							break;
						}
				}
				if (flag) {
					answer++;
				}
			}
		}
		System.out.println(answer);
	}
}

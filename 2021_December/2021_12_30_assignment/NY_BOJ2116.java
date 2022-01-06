package date0102SUN;

import java.util.*;
import java.io.*;

public class BOJ2116 {
	static int n;
	static int dice[][];
	static int answer;

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		n = Integer.parseInt(br.readLine());

		dice = new int[n][6];
		answer = 0;

		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			// 마주보는 면 : 0-5, 1-3, 2-4
			// -> 0-3 1-4 2-5
			dice[i][0] = Integer.parseInt(st.nextToken());
			dice[i][1] = Integer.parseInt(st.nextToken());
			dice[i][2] = Integer.parseInt(st.nextToken());
			dice[i][4] = Integer.parseInt(st.nextToken());
			dice[i][5] = Integer.parseInt(st.nextToken());
			dice[i][3] = Integer.parseInt(st.nextToken());
		}

		//

		for (int i = 0; i < 6; i++) {
			int sum = getsum(i); // i번쨰 인덱스를 윗면으로 하는 주사위 쌓아서 옆면의 최댓값
			answer = Math.max(sum, answer);
		}
		
		System.out.println(answer);

	}

	public static int getsum(int c) {
		int sum = 0;
		int idx = c; 
		
		sum+=getmax(0,idx); //첫번째주사위
		
		for (int i = 1; i < n; i++) { //주사위 쌓음
			for (int j = 0; j < 6; j++) { //쌓을 주사위의 모든면 확인해서 만나는 부분의 반대편 인덱스 값을 저장
				if(dice[i][j] == dice[i-1][idx]) {
					sum+=getmax(i,j);
					idx = (j+3)%6;
					break;
				}
			}
		}

		return sum;

	}

	public static int getmax(int num, int cur) { // 옆면중에 가장 큰 값 구함
		int max = 0;
		for (int i = 0; i < 6; i++) {
			if ((i % 3) != (cur % 3)) //인덱스로 접근
				max = Math.max(max, dice[num][i]);
		}
		return max;
	}

}

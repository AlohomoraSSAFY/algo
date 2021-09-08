package study0909;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class SY_BOJ14891 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		LinkedList<LinkedList<Integer>> array = new LinkedList<>();
		StringTokenizer st;
		for (int i = 0; i < 4; i++) {
			LinkedList<Integer> list = new LinkedList<>();
			String[] str = br.readLine().split("");
			for (int j = 0; j < 8; j++) {
				list.add(Integer.parseInt(str[j]));
			}
			array.add(list);
		}
		
		int K = Integer.parseInt(br.readLine());
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int n = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			
			int[] r = new int[4];
			r[n-1] = d;
			for (int j = n-2; j >= 0; j--) {
				if (array.get(j).get(2) == array.get(j+1).get(6)) {
					continue;
				} else {
					r[j] = r[j+1] * (-1);
				}
			}
			for (int j = n; j <= 3; j++) {
				if (array.get(j-1).get(2) == array.get(j).get(6)) {
					continue;
				} else {
					r[j] = r[j-1] * (-1);
				}
			}
			
			for (int j = 0; j < 4; j++) {
				if (r[j] == 1) { //오른쪽 이동
					int temp = array.get(j).remove(7);
					array.get(j).add(0, temp);
				} else if (r[j] == -1) { //왼쪽 이동
					int temp = array.get(j).remove(0);
					array.get(j).add(temp);
				}
			}
		}
		
		int result = 0;
		for (int i = 0; i < 4; i++) {
			if (array.get(i).get(0) == 0) {
				continue;
			} else {
				switch (i) {
				case 0:
					result += 1;
					break;
				case 1:
					result += 2;
					break;
				case 2:
					result += 4;
					break;
				default:
					result += 8;
					break;
				}
			}
		}
		
		System.out.println(result);
	}

}

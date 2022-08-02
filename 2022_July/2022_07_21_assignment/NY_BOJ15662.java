package date0721;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ15662 {
	public static ArrayList<String> gear;

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		gear = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			String str = br.readLine();
			gear.add(str);
		}

		int k = Integer.parseInt(br.readLine());

		for (int i = 0; i < k; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken()) - 1;
			int b = Integer.parseInt(st.nextToken());
			left(a, -b);
			right(a, -b);
			rotate(a,b);
//			for(int j=0;j<n;j++) {
//				System.out.println(gear.get(j));
//			}			
//			System.out.println("===========");

		}

		// 12시방향이 S극인 톱니바퀴의 개수를 출력한다.
		int answer = 0;
		for (int i = 0; i < n; i++) {
			String temp = gear.get(i);
			if (temp.charAt(0) == '1')
				answer++;
		}
		System.out.println(answer);
	}

	public static void left(int idx, int d) {
//		System.out.println("left" + " "+idx+" "+d);
//		for(int j=0;j<gear.size();j++) {
//			System.out.println(gear.get(j));
//		}
//		System.out.println();
		if (idx == 0)
			return;

		if (gear.get(idx).charAt(6) != gear.get(idx - 1).charAt(2)) {

			left(idx - 1, -d);
			rotate(idx - 1, d);
		}

	}

	public static void right(int idx, int d) {
//		System.out.println("right" + " "+idx+" "+d);
//		for(int j=0;j<gear.size();j++) {
//			System.out.println(gear.get(j));
//		}
//		System.out.println();
//		
		if (idx == gear.size() - 1)
			return;

		if (gear.get(idx).charAt(2) != gear.get(idx + 1).charAt(6)) {
			right(idx + 1, -d);
			rotate(idx + 1, d);

		}
	}

	public static void rotate(int idx, int d) {
		String str = gear.get(idx);
		String temp = "";
		if (d == 1) {
			temp += str.charAt(7);
			temp += str.substring(0, 7);
		} else {
			temp += str.substring(1, 8);
			temp += str.charAt(0);
		}
		gear.set(idx, temp);

	}
}

package date0324;

import java.util.*;
import java.io.*;

public class BOJ19942 {

	static int n;
	static int m[];
	static int ingr[][];
	static int dp[][];
	static int answer;
	static int minsize;
	static int min;
	static boolean selected[];
	static ArrayList<String> list;

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		list = new ArrayList<>();
		m = new int[4];
		answer = -1;
		min = Integer.MAX_VALUE;
		minsize = Integer.MAX_VALUE;
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < 4; i++) {
			m[i] = Integer.parseInt(st.nextToken());
		}
		ingr = new int[n][5];

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 5; j++) {
				ingr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		dp = new int[n][n];
		//

		selected = new boolean[n];
		subset(n - 1);
		Collections.sort(list);
		if (min != Integer.MAX_VALUE) {
			System.out.println(min);
			System.out.print(list.get(0));
		} else {
			System.out.println(-1);

		}

	}

	public static void subset(int count) {
		if (count < 0) {
			int cost = getsumcost(selected);
			// System.out.println(cost);
			if (cost > min)
				return;
			if (check(selected)) {
				if(cost<min)
					list= new ArrayList<>();
				min = cost;
				setlist(selected);
			}

			return;
		}

		selected[count] = false;

		subset(count - 1);

		selected[count] = true;
		// if (sum < min)
		subset(count - 1);
	}

	public static int getsumcost(boolean[] selected) {

		int s = 0;
		for (int i = 0; i < n; i++) {
			if (selected[i])
				s += ingr[i][4];
		}
		return s;
	}

	public static boolean check(boolean selected[]) {
		for (int i = 0; i < 4; i++) {
			int sum = 0;
			for (int j = 0; j < n; j++) {
				if (!selected[j])
					continue;
				sum += ingr[j][i];
			}
			if (sum < m[i])
				return false;
		}

		return true;
	}

	public static int getsize(boolean selected[]) {
		int count = 0;
		for (int i = 0; i < n; i++) {
			if (selected[i])
				count++;
		}
		return count;
	}

	public static void setlist(boolean[] selected) {
		String str = "";
		for (int i = 0; i < n; i++) {
			if (selected[i])
				str += (i + 1) + " ";
		}
		list.add(str);
	}

}

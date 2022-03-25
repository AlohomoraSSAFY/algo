package study0331;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

public class SY_BOJ19942 {
	
	static int N;
	static int mp, mf, ms, mv;
	static int[][] array;
	static boolean[] isSelected;
	static int cost;
	static List<String> list;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		mp = Integer.parseInt(st.nextToken());
		mf = Integer.parseInt(st.nextToken());
		ms = Integer.parseInt(st.nextToken());
		mv = Integer.parseInt(st.nextToken());
		
		array = new int[N+1][5];
		for (int i = 1; i < N+1; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < 5; j++) {
				array[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		 isSelected = new boolean[N+1];
		 cost = Integer.MAX_VALUE;
		 list = new ArrayList<>();
		 recursion(1, 0, 0, 0, 0, 0);
		 
		 if (list.size() == 0) {
			 System.out.println(-1);
		 } else {
			 Collections.sort(list, new Comparator<String>() {
				 @Override
				 public int compare(String o1, String o2) {
					 return o1.compareTo(o2);
				 }
			 });
			 
			 System.out.println(cost);
			 System.out.println(list.get(0));
		 }
	}
	
	private static void recursion(int cnt, int p, int f, int s, int v, int c) {
		if (c > cost) return;
		
		if (cnt == N+1) {
			if (p >= mp && f >= mf && s >= ms && v >= mv) {
				String str = "";
				for (int i = 1; i < N+1; i++) {
					if (isSelected[i]) {
						str += (i + " ");
					}
				}
				
				if (c < cost) {
					list = new ArrayList<>();
					cost = c;
				}
				list.add(str);
			}
			
			return;
		}
		
		isSelected[cnt] = true;
		recursion(cnt + 1, p + array[cnt][0], f + array[cnt][1], s + array[cnt][2], v + array[cnt][3], c + array[cnt][4]);
		
		isSelected[cnt] = false;
		recursion(cnt + 1, p, f, s, v, c);
	}

}

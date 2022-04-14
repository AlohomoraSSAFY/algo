package net.acmicpc.march.week5;

import java.io.*;
import java.util.*;

public class BOJ17140 {
	
	static int r, c, k;
	
	public static int[][] Column(int[][] A) {
		ArrayList<ArrayList<Integer>> list = new ArrayList<>();
		int max = 0;
		for(int j = 0; j < A[0].length; j++) {
			ArrayList<Integer> line = new ArrayList<>();
			HashMap<Integer, Integer> data = new HashMap<>();
			for(int i = 0; i < A.length; i++) {
				if(A[i][j]!=0 && data.putIfAbsent(A[i][j], 1)!=null) {
					int cnt = data.get(A[i][j]);
					data.put(A[i][j], cnt+1);
				}
			}
			List<Integer> keySetList = new ArrayList<>(data.keySet());
			// 오름차순
			Collections.sort(keySetList, new Comparator<Integer>() {
				@Override
				public int compare(Integer o1, Integer o2) {
					if(data.get(o1) - data.get(o2) > 0) {
						return 1;
					}else if(data.get(o1) - data.get(o2) ==0) {
						if(o1 > o2) {
							return 1;
						}else {
							return -1;
						}
					}else {
						return -1;
					}
				}
			});
			for(Integer e : keySetList) {
				line.add(e);
				line.add(data.get(e));
			}
			list.add(line);
			max = Math.max(max, line.size());
		}
		
		if(max > 100) max = 100;
		
		int[][] next = new int[max][A[0].length];
		for(int j = 0; j < A[0].length; j++) {
			ArrayList<Integer> line = list.get(j);
			int len = -1;
			if(line.size() > 100) len = 100;
			else len = line.size();
			for(int i = 0 ; i < len; i++) {
				next[i][j] = line.get(i);
			}
			for(int i = len; i < max; i++) {
				next[i][j] = 0;
			}
		}
		return next;
	}
	
	public static int[][] Row(int[][] A) {
		ArrayList<ArrayList<Integer>> list = new ArrayList<>();
		int max = 0;
		for(int i = 0; i < A.length; i++) {
			ArrayList<Integer> line = new ArrayList<>();
			HashMap<Integer, Integer> data = new HashMap<>();
			for(int j = 0; j < A[0].length; j++) {
				if(A[i][j]!=0 && data.putIfAbsent(A[i][j], 1) != null) {
					int cnt = data.get(A[i][j]);
					data.put(A[i][j], cnt+1);
				}
			}
			
			List<Integer> keySetList = new ArrayList<>(data.keySet());
			// 오름차순
			Collections.sort(keySetList, new Comparator<Integer>() {
				@Override
				public int compare(Integer o1, Integer o2) {
					if(data.get(o1) - data.get(o2) > 0) {
						return 1;
					}else if(data.get(o1) - data.get(o2) ==0) {
						if(o1 > o2) {
							return 1;
						}else {
							return -1;
						}
					}else {
						return -1;
					}
				}
			});
			for(Integer e : keySetList) {
				line.add(e);
				line.add(data.get(e));
			}

			list.add(line);
			max = Math.max(max, line.size());
		}

		if(max > 100) max = 100;
		int[][] next = new int[A.length][max];
		for(int i = 0; i < A.length; i++) {
			
			ArrayList<Integer> line = list.get(i);
			int len = -1;
			if(line.size() > 100) len = 100;
			else len = line.size();
			for(int j = 0 ; j < len; j++) {
				next[i][j] = line.get(j);
			}
			for(int j = len; j < max; j++) {
				next[i][j] = 0;
			}
		}
		return next;
	}
	
	public static void printMap(int[][] A) {
		System.out.println("--------- ");
		for(int i = 0 ; i < A.length; i++) {
			for(int j = 0 ; j < A[0].length; j++) {
				System.out.printf("%5d", A[i][j]);
			}
			System.out.println();
		}
		System.out.println("-----------");
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		r = Integer.parseInt(st.nextToken())-1;
		c = Integer.parseInt(st.nextToken())-1;
		k = Integer.parseInt(st.nextToken());
		
		int[][] A = new int[3][3];
		for(int i = 0 ; i < 3; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j =0; j < 3; j++) {
				A[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		int[][] cur = A;
		int time = 0;
//		System.out.println(r+" "+c);
		if((cur.length > r && cur[0].length > c && cur[r][c] == k)) {
			
		}else {
//			printMap(cur);
			while(true) {
				int[][] ret;
				time++;
				if(cur.length >= cur[0].length) { // R 연산
					ret = Row(cur); 
				}else { // C 연산
					ret = Column(cur);
				}
				
//				printMap(cur);
				if( (ret.length > r && ret[0].length > c && ret[r][c] == k) || time == 101) {
					break;
				}
				cur = ret;
//				printMap(cur);
			}
		}
		
		bw.write((time==101)? "-1\n" : time+"\n");
		bw.close();
		br.close();	
		
	}

}
package com.acmicpc.samsung;

import java.io.*;
import java.util.*;


public class BOJ20061 {
	
	static int N; // 행이 x 열이 y
	static int score = 0;
	
	static ArrayList<Integer>[] rows = new ArrayList[4];
	static ArrayList<Integer>[] cols = new ArrayList[4];
	
	public static boolean checkCol(int c) {
		for(int i = 0; i < 4; i++) {
			int tmp = cols[i].get(c);
			if(tmp != 0) continue;
			else return false;
		}
		score++;
		for(int i = 0; i < 4; i++) {
			cols[i].remove(c);
			cols[i].add(0, 0);
		}
		return true;
	}
	public static boolean checkRow(int r) {
		for(int i = 0; i < 4; i++) {
			int tmp = rows[i].get(r);
			if(tmp != 0) continue;
			else return false;
		}
		score++;
		for(int i = 0; i < 4; i++) {
			rows[i].remove(r);
			rows[i].add(0, 0);
		}
		return true;
	}
	
	public static void moveRow(int cnt) {
		for(int i = 0; i < 4; i++) {
			for(int k = 0; k < cnt; k++) {
				rows[i].remove(5);
				rows[i].add(0, 0);
			}
		}
	}
	
	public static void moveCol(int cnt) {
		for(int i = 0; i < 4; i++) {
			for(int k = 0; k < cnt; k++) {
				cols[i].remove(5);
				cols[i].add(0, 0);
			}
		}
	}
	
	public static void one(int x, int y ) {
		// 초록보드에서 열이 y인 아이들 선택
		boolean flag = false;
		for(int i = 2; i < 6; i++) {
			int tmp = rows[y].get(i);
			if(tmp == 0) continue;
			else { // i-1까지 내려갈 수 있음
				flag = true;
				rows[y].set(i-1, 1);
				if(i==2) {
					moveRow(1);
					break;
				}
//				rows[y].set(i-1, 1);
				checkRow(i-1);
				break;
			}
		}
		
		if(!flag) {
			rows[y].set(5, 1);
			checkRow(5);
		}
		
		// 파란보드 탐색
		flag = false;
		for(int i = 2; i < 6; i++) {
			int tmp = cols[x].get(i);
			if(tmp == 0) continue;
			else { // i-1까지 내려갈 수 있음
				flag = true;
				if(i==2) {
					cols[x].set(i-1, 1);
					moveCol(1);
					break;
				}
				cols[x].set(i-1, 1);
				checkCol(i-1);
				break;
			}
		}
		if(!flag) {
			cols[x].set(5, 1);
			checkCol(5);
		}
		
	}
	
	public static void two(int x, int y, int x2, int y2) {
		boolean flag = false;
		// 초록보드에서 열이 y인 아이들 선택
		for(int i = 2; i < 6; i++) {
			int tmp = rows[y].get(i);
			int tmp2 = rows[y2].get(i);
			if(tmp == 0 && tmp2==0 ) continue;
			else { // i-1까지 내려갈 수 있음
				flag = true;
				rows[y].set(i-1, 1);
				rows[y2].set(i-1, 1);
				if(i==2) {
					moveRow(1);
					break;
				}
				checkRow(i-1);
				break;
			}
		}
		
		if(!flag) {
			rows[y].set(5, 1);
			rows[y2].set(5, 1);
			checkRow(5);
		}
		
		flag = false;
		// 파란보드 탐색
		for(int i = 2; i < 6; i++) {
			int tmp = cols[x].get(i);
			if(tmp == 0) continue;
			else { // i-1까지 내려갈 수 있음
				flag = true;
				cols[x].set(i-1, 1);
				cols[x].set(i-2, 1);

				if(i==2) {
					moveCol(2);
					break;
				}else if(i==3) {
					boolean ret = checkCol(i-1); 
					if(ret) {// 없어졌다면
						checkCol(i-1);
					}else { // 있다면
						moveCol(1);
					}					
					break;
				}
				
				boolean ret = checkCol(i-1); 
				if(ret) {// 없어졌다면
					checkCol(i-1);
				}else { // 있다면
					checkCol(i-2);
				}
				break;
			}
		}
		
		if(!flag) {
			cols[x].set(5, 1);
			cols[x].set(4, 1);
			boolean ret = checkCol(5);
			if(ret) {// 없어졌다면
				checkCol(5);
			}else { // 있다면
				checkCol(4);
			}
		}
	}
	
	public static void three(int x, int y, int x2, int y2) {
		boolean flag = false;
		// 초록보드에서 열이 y인 아이들 선택
		for(int i = 2; i < 6; i++) {
			int tmp = rows[y].get(i);
			if(tmp ==0) continue;
			else {
				flag = true;
				rows[y].set(i-1, 1);
				rows[y].set(i-2, 1);

				if(i==2) {
					moveRow(2);
					break;
				}else if(i==3) {
					boolean ret = checkRow(i-1); 
					if(ret) {// 없어졌다면
						checkRow(i-1);
					}else { // 있다면
						moveRow(1);
					}
					break;
				}
				boolean ret = checkRow(i-1); 
				if(ret) {// 없어졌다면
					checkRow(i-1);
				}else { // 있다면
					checkRow(i-2);
				}
				break;
			}
		}
		
		if(!flag) {
			rows[y].set(5, 1);
			rows[y].set(4, 1);
			boolean ret = checkRow(5); 
			if(ret) {// 없어졌다면
				checkRow(5);
			}else { // 있다면
				checkRow(4);
			}
		}
		
		flag = false;
		// 파란보드 탐색
		for(int i = 2; i < 6; i++) {
			int tmp = cols[x].get(i);
			int tmp2 = cols[x2].get(i);
			if(tmp == 0 && tmp2==0 ) continue;
			else { // i-1까지 내려갈 수 있음
				flag = true;
				cols[x].set(i-1, 1);
				cols[x2].set(i-1, 1);
				if(i==2) {
					moveCol(1);
					break;
				}
				checkCol(i-1);
				break;
			}
		}
		if(!flag) {
			cols[x].set(5, 1);
			cols[x2].set(5, 1);
			checkCol(5);
		}
	}
	
	public static void printMap() {
		System.out.println("--------행 검색---------");
		for(int i = 0 ; i < 4; i++) {
			for(int j = 0; j < 6; j++) {
				System.out.print(rows[i].get(j)+" ");
			}
			System.out.println();
		}
		System.out.println("--------열 검색----------");
		for(int i = 0 ; i < 4; i++) {
			for(int j = 0; j < 6; j++) {
				System.out.print(cols[i].get(j)+" ");
			}
			System.out.println();
		}
	}
	
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		for(int i = 0 ; i < 4; i++) {
			rows[i] = new ArrayList<>(Arrays.asList(0, 0, 0, 0, 0, 0));
			cols[i] = new ArrayList<>(Arrays.asList(0, 0, 0, 0, 0, 0));
		}
		
		N= Integer.parseInt(br.readLine());
		int[][] cmd = new int[N][3];
		for(int i = 0 ; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int t = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			cmd[i][0] = t;
			cmd[i][1] = x;
			cmd[i][2] = y;
//			if(t==1) {
////				System.out.println("1 X 1블럭");
//				one(x, y);
//			}else if(t==2) {
////				System.out.println("1 X 2블럭");
//				two(x,y, x, y+1);
//			}else {
////				System.out.println("2 X 1블럭");
//				three(x,y, x+1, y);
//			}
////			printMap();
		}
		
		for(int i = 0; i < N; i++) {
			int t = cmd[i][0];
			int x = cmd[i][1];
			int y = cmd[i][2];
			if(t==1) {
//				System.out.println("1 X 1블럭");
				one(x, y);
			}else if(t==2) {
//				System.out.println("1 X 2블럭");
				two(x,y, x, y+1);
			}else {
//				System.out.println("2 X 1블럭");
				three(x,y, x+1, y);
			}
//			printMap();
		}
		
		
		int left = 0;
		for(int i = 0 ; i < 4; i++) {
			for(int j = 0; j < 6; j++) {
				if(rows[i].get(j)==1) {
					left++;
				}
			}
		}

		for(int i = 0 ; i < 4; i++) {
			for(int j = 0; j < 6; j++) {
				if(cols[i].get(j)==1) {
					left++;
				}
			}
		}
		
		
		
		bw.write(score+"\n");
		bw.write(left+"\n");
		bw.close();
		br.close();	
		
	}

}

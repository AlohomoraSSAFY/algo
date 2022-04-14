package net.acmicpc.march.week5;

import java.io.*;
import java.util.*;


public class BOJ19942 {
	
	static int N;
	static int[] mlist;
	
	static class FOOD{
		int idx;
		int p, f, s, v, c;
		public FOOD(int idx, int p, int f, int s, int v, int c) {
			super();
			this.idx = idx;
			this.p = p;
			this.f = f;
			this.s = s;
			this.v = v;
			this.c = c;
		}
		
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		N = Integer.parseInt(br.readLine());
		mlist = new int[4];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < 4; i++) {
			mlist[i] = Integer.parseInt(st.nextToken());
		}
		
		ArrayList<FOOD> list = new ArrayList<>();
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int p = Integer.parseInt(st.nextToken());
			int f = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			list.add(new FOOD(i, p, f, s, v, c));
		}
		
		HashMap<Integer, ArrayList<String>> pos = new HashMap<>(); int minc = Integer.MAX_VALUE;
		for(int i = 0; i < (1 << N); i++) {
			int sp = 0, sf=0, ss=0, sv=0, sc=0;
			for(int j = 0; j < N; j++) {
				if( (i & 1<<j) == 1<< j) {
					sp += list.get(j).p;
					sf += list.get(j).f;
					ss += list.get(j).s;
					sv += list.get(j).v;
					sc += list.get(j).c;
				}
			}
			
			if(sp >= mlist[0] && sf >= mlist[1] && ss >= mlist[2] && sv >= mlist[3]) {
				minc = Math.min(minc, sc);
				StringBuilder sb = new StringBuilder();
				for(int j = 0; j < N; j++) {
					if((i & 1<<j)== 1<<j) {
						sb.append((j+1) +" ");
					}
				}
				
				if(pos.containsKey(sc)) {
					pos.get(sc).add(sb.toString());
				}else {
					ArrayList<String> tmp = new ArrayList<>();
					tmp.add(sb.toString());
					pos.put(sc, tmp);
				}
				
			}
		}
		
		
		if(pos.size()==0) {
			bw.write(-1+"\n");
		}else {

			ArrayList<String> min = pos.get(minc);
			Collections.sort(min);
			bw.write(minc+"\n");
			bw.write(min.get(0));
		}		
		
//		System.out.println(1<<N);
		bw.close();
		br.close();
		
	}

}

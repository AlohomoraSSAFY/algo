package net.acmicpc.march.week2;

import java.io.*;
import java.util.*;


public class BOJ13904 {
	
	static int N;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		N = Integer.parseInt(br.readLine());
//		PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
//			@Override
//			public int compare(int[] a, int[] b) {
//				if(b[1] > a[1]) {
//					return b[1] - a[1];
//				}else if(b[1] < a[1]) {
//					return b[1] - a[1];
//				}else { // 점수가 같으면
//					return b[0] - a[0];
//				}
//			}
//		});
		
		ArrayList<int[]> pq = new ArrayList<>();
		

		int max = 0;
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int d = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			pq.add(new int[] {d, s});
			max = Math.max(max, d);
		}
		
		Collections.sort(pq, new Comparator<int[]>() {
			@Override
			public int compare(int[] a, int[] b) {
				if(b[1] > a[1]) {
					return b[1] - a[1];
				}else if(b[1] < a[1]) {
					return b[1] - a[1];
				}else { // 점수가 같으면
					return b[0] - a[0];
				}
			}
		});
		
//		for(Iterator<int[]> iter = pq.iterator(); iter.hasNext(); ) {
//			int[] cur = iter.next();
//			System.out.println(cur[0]+ " "+cur[1]);
//		}

//		while(!pq.isEmpty()) {
//			int[] cur = pq.poll();
//			System.out.println(cur[0]+ " "+cur[1]);
//		}
		
		int ans = 0;
		for(int i = max; i > 0; i--) {
			for(Iterator<int[]> iter = pq.iterator(); iter.hasNext(); ) {
				int[] cur = iter.next();
				if(cur[0] >= i) {
					ans+= cur[1];
					pq.remove(cur);
					break;
				}
			}
		}
		
		bw.write(ans+"\n");
		bw.close();
		br.close();	
		
	}

}

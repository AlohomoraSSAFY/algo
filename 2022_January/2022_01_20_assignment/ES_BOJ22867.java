package net.acmicpc.jan.week4;

import java.io.*;
import java.util.*;


public class BOJ22867_3 {
	
	static int N;
	static int[][] bus;
//	static TreeMap<Integer, Integer> map = new TreeMap<>();
	static HashMap<Integer, Integer> map = new HashMap<>();
	static int min = Integer.MAX_VALUE;
	static int max = 0;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		N = Integer.parseInt(br.readLine());
		bus = new int[N][2];
		for(int i = 0 ; i < N; i++) {
			String[] input = br.readLine().replace("\n", "").split(" ");
			for(int j = 0; j < 2; j++) {
				String[] line = input[j].split(":");
				int H = Integer.parseInt(line[0]);
				int M = Integer.parseInt(line[1]);
				String[] tmp = line[2].split("\\.");
				int S = Integer.parseInt(tmp[0]);
				int s = Integer.parseInt(tmp[1]);
				int total = H * 3600 * 1000 + M * 60 * 1000 + S * 1000 + s;
			
				if(j == 0) {
					map.put(total, map.getOrDefault(total, 0)+1);
					min = Math.min(min, total);
				} else {
					map.put(total, map.getOrDefault(total, 0)-1);
					max = Math.max(max, total);
				}
				
			}
		}
		
		int ans = 0; int total = 0;
		Object[] key = map.keySet().toArray();
		Arrays.sort(key);
		for(Object val : key) {
			total += map.get(val);
			System.out.println(val+" "+map.get(val));
			ans = Math.max(ans, total);
		}

		bw.write(ans+"\n");
		bw.close();
		br.close();	
		
	}

}

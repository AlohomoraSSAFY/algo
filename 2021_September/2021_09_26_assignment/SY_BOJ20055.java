package study0930;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class SY_BOJ20055 {
	
	static class Belt {
		int num;
		boolean robot;
		
		public Belt(int num, boolean robot) {
			this.num = num;
			this.robot = robot;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		List<Belt> list = new ArrayList<>();
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < N*2; i++) {
			list.add(new Belt(Integer.parseInt(st.nextToken()), false));
		}
		
		int level = 0;
		while (true) {
			level++;
			
			list.add(0, list.remove(list.size()-1));
			list.set(list.size()/2, new Belt(list.get(list.size()/2).num, false));
			
			for (int i = list.size()/2-1; i >= 0; i--) {
				if (list.get(i).robot) {
					if (i == list.size()/2-1) {
						list.get(i).robot = false;
					} else {
						if (list.get(i+1).num > 0 && list.get(i+1).robot == false) {
							list.get(i).robot = false;
							list.get(i+1).num -= 1;
							list.get(i+1).robot = true;
						}
					}
				}
			}
			
			if (list.get(0).num > 0) {
				list.get(0).robot = true;
				list.get(0).num -= 1;
			}
			
			int cnt = 0;
			for (int i = 0; i < list.size(); i++) {
				if (list.get(i).num == 0) cnt++;
			}
			
			if (cnt >= K) break;
		}
		
		System.out.println(level);
	}

}

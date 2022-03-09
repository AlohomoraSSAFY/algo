package study0310;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class SY_BOJ13904 {
	
	static class Assignment implements Comparable<Assignment> {
		int d;
		int w;
		
		public Assignment(int d, int w) {
			this.d = d;
			this.w = w;
		}
		
		@Override
		public int compareTo(Assignment o) {
			return Integer.compare(o.w, this.w);
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int max = 0;
		List<Assignment> list = new ArrayList<>();
		StringTokenizer st;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int d = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			max = Math.max(max, d);
			list.add(new Assignment(d, w));
		}
		
		Collections.sort(list);
		
		int result = 0;
		for (int i = max; i > 0; i--) {
			int idx = -1;
			for (int j = 0; j < list.size(); j++) {
				if (list.get(j).d >= i) {
					idx = j;
					result += list.get(j).w;
					break;
				}
			}
			
			if (idx != -1) list.remove(idx);
		}
		
		System.out.println(result);
	}

}

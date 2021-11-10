package study1104;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SY_BOJ2304 {
	
	static Bar[] bArray;
	
	static class Bar implements Comparable<Bar> {
		int x;
		int y;
		
		public Bar(int x, int y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public int compareTo(Bar o) {
			return this.x - o.x;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		bArray = new Bar[N];
		int max = 0;
		StringTokenizer st;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			if (y > max) max = y;
			bArray[i] = new Bar(x, y);
		}
		
		Arrays.sort(bArray);
		
		int idx = -1;
		for (int i = 0; i < N; i++) {
			if (bArray[i].y == max) {
				idx = i;
				break;
			}
		}
		
		int sum = 0;
		int c = 0;
		int before = bArray[0].x;
		for (int i = 0; i <= idx; i++) {
			if (bArray[i].y < c) continue;
			
			if (i != 0) {
				sum += (bArray[i].x - before - 1) * c;
			}
			
			sum += bArray[i].y;
			c = bArray[i].y;
			before = bArray[i].x;
		}
		
		c = 0;
		before = bArray[N-1].x;
		for (int i = N-1; i >= idx; i--) {
			if (bArray[i].y < c) continue;
			
			if (i != N-1) {
				sum += (before - bArray[i].x - 1) * c;
			}
			
			sum += bArray[i].y;
			c = bArray[i].y;
			before = bArray[i].x;
		}
		
		sum -= max;
		System.out.println(sum);
	}

}

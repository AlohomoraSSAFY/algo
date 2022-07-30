package study0802;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class SY_BOJ2650 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		List<int[]> list = new ArrayList<>();
		for (int i = 0; i < N/2; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			
			int[] arr1 = convert(a, b);
			int[] arr2 = convert(c, d);
			list.add(new int[]{arr1[0], arr1[1], arr2[0], arr2[1]});
		}
		
		int[] crossed = new int[N/2];
		int result = 0;
		for (int i = 0; i < N/2-1; i++) {
			for (int j = i+1; j < N/2; j++) {
				if (isCross(list.get(i), list.get(j))) {
					result++;
					crossed[i]++;
					crossed[j]++;
				}
			}
		}
		
		int max = 0;
		for (int i = 0; i < N/2; i++) {
			max = Math.max(max, crossed[i]);
		}
		
		System.out.println(result);
		System.out.println(max);
	}
	
	private static int[] convert(int a, int b) {
		int[] array = new int[2];
		if (a == 1) {
			array[0] = b;
			array[1] = 0;
		} else if (a == 2) {
			array[0] = b;
			array[1] = 51;
		} else if (a == 3) {
			array[0] = 0;
			array[1] = b;
		} else {
			array[0] = 51;
			array[1] = b;
		}
		
		return array;
	}
	
	private static boolean isCross(int[] line1, int[] line2) {
		int ax = line1[0];
		int ay = line1[1];
		int bx = line1[2];
		int by = line1[3];
		int cx = line2[0];
		int cy = line2[1];
		int dx = line2[2];
		int dy = line2[3];
		int res1 = ccw(ax, ay, bx, by, cx, cy) * ccw(ax, ay, bx, by, dx, dy);
		int res2 = ccw(cx, cy, dx, dy, ax, ay) * ccw(cx, cy, dx, dy, bx, by);
		
		if (res1 == 0 && res2 == 0) {
			if (ax == bx) { //수직 방향
				int a = Math.min(ay, by);
				int b = Math.max(ay, by);
				int c = Math.min(cy, dy);
				int d = Math.max(cy, dy);
				if (b < c || d < a || (a < c && c < d && d < b) || (c < a && a < b && b < d)) return false;
			} else { //수평 방향
				int a = Math.min(ax, bx);
				int b = Math.max(ax, bx);
				int c = Math.min(cx, dx);
				int d = Math.max(cx, dx);
				if (b < c || d < a || (a < c && c < d && d < b) || (c < a && a < b && b < d)) return false;
			}
		}
		
		return res1 <= 0 && res2 <= 0;
	}
	
	private static int ccw(int ux, int uy, int vx, int vy, int tx, int ty) {
		return ux * vy + vx * ty + tx * uy - vx * uy - tx * vy - ux * ty;
	}

}

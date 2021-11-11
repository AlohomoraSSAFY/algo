package date1104THU;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ2304 { //창고 다각형
	static int n;
	static int arr[];
	static int answer;

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		arr = new int[1001];

		int max = 0;
		int maxidx = 0;
		int size = 0;

		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int l = Integer.parseInt(st.nextToken());
			int h = Integer.parseInt(st.nextToken());
			arr[l] = h;
			size = Math.max(size, l);
			if (max < h) {
				max = h;
				maxidx = l;
			}
		}
		
		answer = max;
		
		int left = 0;
		for (int i = 0; i < maxidx; i++) { // 제일 높은 기둥 왼쪽
			if (left < arr[i])
				left = arr[i];

			answer += left;
		}
		
		int right = 0;
		for (int i = size; i > maxidx; i--) { //오른쪽
			if (right < arr[i])
				right = arr[i];
			answer += right;
		}

		System.out.println(answer);

	}

}

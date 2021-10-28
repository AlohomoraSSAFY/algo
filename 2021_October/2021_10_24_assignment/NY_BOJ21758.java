package date1028THU;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ21758 { // 꿀 따기
	static int n;
	static int arr[];
	static int a[], z[];
	static int max;

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		arr = new int[n];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		max = 0;
		a = new int[n];
		z = new int[n];
		a[0] = arr[0];
		for (int i = 1; i < n; i++) {
			a[i] = a[i - 1] + arr[i];
		}
		//
		z[n - 1] = arr[n - 1];
		for (int i = n - 2; i >= 0; i--) {
			z[i] = z[i + 1] + arr[i];
		}

		// 인덱스 0이 첫번째 벌
		for (int i = 1; i < n; i++) { // 인덱스 i번째가 두 번째 벌
			for (int j = 1; j < n; j++) { // 인덱스 j가 집
				if (i == j)
					continue;
				int h = a[j] - a[0]; // 첫번째 벌
				if (i < j) { // 두 번째 벌 위치 체크
					h += a[j] - a[i] -arr[i];
				} else {
					h += z[j] - z[i];
				}
				max = Math.max(h, max);
			}
		}

		// 마지막 인덱스가 첫번째 벌
		for (int i = 0; i < n - 1; i++) { // 두번째벌
			for (int j = 0; j < n - 1; j++) { // 벌집 위치
				if (i == j)
					continue;
				int h = z[j] - z[n - 1];
				if (i > j) {
					h += z[j] - z[i] -arr[i];
				} else {
					h+= a[j] - a[i];
				}
				max = Math.max(h, max);
			}
		}
		
		System.out.println(max);

	}

}

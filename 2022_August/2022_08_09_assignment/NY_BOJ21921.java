package date0809;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class NY_BOJ21921 {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int x = Integer.parseInt(st.nextToken());

		int arr[] = new int[n];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		int sum = 0;
		for (int i = 0; i < x; i++) {
			sum += arr[i];
		}

		int answer = sum;
		int cnt = 1;
		for (int i = x; i < n; i++) {
			sum += arr[i];
			sum -= arr[i - x];
			if (answer < sum) {
				answer = sum;
				cnt = 1;
			} else if (answer == sum) {
				cnt++;
			}
		}
		if(answer ==0)
			System.out.println("SAD");
		else {
			System.out.println(answer + "\n"+cnt);
		}
	}

}

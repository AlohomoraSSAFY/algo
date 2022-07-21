package date0714;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.*;

public class NY_BOJ2212 {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int k = Integer.parseInt(br.readLine());
		if(k>=n) {
			System.out.println(0);
			return;
		}
		int arr[] = new int[n];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		for(int i=0;i<n;i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(arr);
		
		int diff[] = new int[n-1];
		for(int i=0;i<n-1;i++) {
			diff[i] = arr[i+1] - arr[i];
		}
		
		Arrays.sort(diff);

		int sum =0;
		for(int i=0;i<n-k;i++) {
			sum+=diff[i];
		}
		System.out.println(sum);
		
	}

}

package date1014THU;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ2467 {

	static int n;
	static int arr[];

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		arr = new int[n];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		//

		int start = 0;
		int end = n - 1;
		int sidx = 0;
		int eidx = n - 1;

		int min = Integer.MAX_VALUE;
		
		while (start < end) {
			
			int diff = Math.abs(arr[start] + arr[end]);
			
			if ( diff < min) {
				sidx = start;
				eidx = end;
				min = diff;
			}

			if(arr[start] + arr[end] == 0)
				break;
			else if(arr[start] + arr[end] < 0)
				start++;
			else
				end--;
		}
		
		
		System.out.println(arr[sidx] + " " + arr[eidx]);
	}

}

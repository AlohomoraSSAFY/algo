package study0324;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

public class SY_BOJ1713 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int S = Integer.parseInt(br.readLine());
		
		List<int[]> list = new ArrayList<>();
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < S; i++) {
			int no = Integer.parseInt(st.nextToken());
			boolean flag = false;
			for (int j = 0; j < list.size(); j++) {
				if (no == list.get(j)[0]) {
					flag = true;
					list.get(j)[1] += 1;
					break;
				}
			}
			
			if (!flag) {
				if (list.size() >= N) {
					Collections.sort(list, new Comparator<int[]>() {
						@Override
						public int compare(int[] o1, int[] o2) {
							if (o1[1] == o2[1]) {
								return o1[2] - o2[2];
							}
							return o1[1] - o2[1];
						}
					});
					
					list.remove(0);
				}
				
				list.add(new int[] {no, 1, i});
			}
		}
		
		Collections.sort(list, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[0] - o2[0];
			}
		});
		
		for (int i = 0; i < list.size(); i++) {
			System.out.print(list.get(i)[0] + " ");
		}
	}

}

package date0310;

import java.io.*;
import java.util.*;

class HW implements Comparable<HW> {
	int d;
	int w;

	public HW(int d, int w) {
		this.d = d;
		this.w = w;
	}

	@Override
	public int compareTo(HW o) {
		if (this.d == o.d)
			return o.w - this.w;
		return o.d - this.d;
	}

}

public class BOJ13904 {

	static int n;

	public static void main(String[] args) throws Exception {

		// TODO Auto-generated method stub

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		int max = 0;
		ArrayList<HW> list = new ArrayList<>();

		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int d = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			max = Math.max(d, max);
			list.add(new HW(d, w));
		}

		Collections.sort(list, new Comparator<HW>() {

			@Override
			public int compare(HW o1, HW o2) {
				// TODO Auto-generated method stub
				if (o2.d == o1.d)
					return o2.w - o1.w;
				return o2.d - o1.d;
			}
		});
		
		int sum = 0;
		int date = max;
		while (date > 0) {
			int maxtemp = 0;
			int maxidx = -1;
			for (int i = 0; i < list.size(); i++) {
				if (maxtemp < list.get(i).w && date <= list.get(i).d) {
					maxidx = i;
					maxtemp = list.get(i).w;
				}
			}
			sum += maxtemp;
			if (maxidx != -1)
				list.remove(maxidx);
			date--;
		}
		System.out.println(sum);

	}

}

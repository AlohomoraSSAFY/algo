package date0120THU;

import java.util.*;
import java.io.*;

public class BOJ4358 {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		HashMap<String, Integer> hm = new HashMap<>();

		ArrayList<String> list = new ArrayList<>();
		int cnt = 0;

		while (true) {
			String str = br.readLine();
			if (str == null || str.equals(""))
				break;
			cnt++;
			if (hm.containsKey(str))
				hm.put(str, hm.get(str) + 1);
			else {
				hm.put(str, 1);
				list.add(str);
			}
		}
		
		Collections.sort(list);
		for(String str : list) {
			float ratio = hm.get(str) / (float)cnt;
			sb.append(str+" ");
			sb.append(String.format("%.4f\n", ratio*100));
		}
			
		System.out.println(sb.toString());
	}

}

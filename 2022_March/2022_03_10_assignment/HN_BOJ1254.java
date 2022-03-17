package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class HN_BOJ1254 {
	static char[] input;
	static int answer, p1, p2;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		input = br.readLine().toCharArray();
		List<Character> output = new LinkedList<>();
		for (Character character : input) {
			output.add(character);
		}

		int size = 0, idx = 0;
		while (true) {
			size = output.size();
			p1 = 0;
			p2 = size;
			boolean pass = true;
			for (int i = 0; i < size / 2; i++) {
				if (output.get(p1) != output.get(p2 - 1)) {
					pass = false;
					break;
				}
				p1++;
				p2--;
			}

			if (pass) {
				answer = output.size();
				break;
			} else {
				output.add(size - idx, output.get(idx++));
			}
		}
		System.out.println(answer);
	}

}

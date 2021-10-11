package TwoPointer;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
// 77376 KB, 392 ms
// k       1  2  3  4  5  6  7  8  9  10  11  12 << 2*10^9
// f(k)    1  2  2  3  3  4  4  4  5   5   5   6 >> 1*10^6
public class HN_BOJ2038_GolombProgression {
	// 2, 2 >> f(k) = 2 
	// 4, 3 >> f(3) = 2
	// 6, 4 >> f(4) = 3
	// 9, 5 
	static Map<Integer, Integer> map = new HashMap<>();
	static int result = 0;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int k = Integer.parseInt(br.readLine());
		
		int firstPointer, secondPointer, val;
		map.put(1, 1);
		map.put(2, 2);
		
		val = 1;
		firstPointer = secondPointer = 2;
		while(firstPointer <= k) {
			if(map.containsKey(secondPointer)) {
				val = map.get(secondPointer);
			}
			firstPointer +=val;
			map.put(firstPointer, ++secondPointer);
		}
		
		while(true) {
			if(map.containsKey(k)) {
				result = map.get(k);
				break;
			}
			k--;
		}
		
		System.out.println(result);
	}

}

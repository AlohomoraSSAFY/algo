package study0609;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.HashMap;
import java.util.TreeMap;

public class SY_BOJ2608 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str1 = br.readLine();
		String str2 = br.readLine();
		
		int num1 = makeNum(str1);
		int num2 = makeNum(str2);
		System.out.println(num1 + num2);
		
		String str = makeString(num1 + num2);
		System.out.println(str);
	}
	
	private static int makeNum(String str) {
		HashMap<String, Integer> map = new HashMap<>();
		map.put("I", 1);
		map.put("IV", 4);
		map.put("V", 5);
		map.put("IX", 9);
		map.put("X", 10);
		map.put("XL", 40);
		map.put("L", 50);
		map.put("XC", 90);
		map.put("C", 100);
		map.put("CD", 400);
		map.put("D", 500);
		map.put("CM", 900);
		map.put("M", 1000);
		
		int num = 0;
		for (int i = 0; i < str.length(); i++) {
			char c = str.charAt(i);
			String temp = "" + c;
			if ((c == 'I' || c == 'X' || c == 'C') && i < str.length() - 1) {
				String s = temp + str.charAt(i+1);
				if (map.containsKey(s)) {
					temp = s;
					i++;
				}
			}
			
			num += map.get(temp);
		}
		
		return num;
	}
	
	private static String makeString(int num) {
		TreeMap<Integer, String> map = new TreeMap<>(Collections.reverseOrder());
		map.put(1, "I");
		map.put(4, "IV");
		map.put(5, "V");
		map.put(9, "IX");
		map.put(10, "X");
		map.put(40, "XL");
		map.put(50, "L");
		map.put(90, "XC");
		map.put(100, "C");
		map.put(400, "CD");
		map.put(500, "D");
		map.put(900, "CM");
		map.put(1000, "M");
		
		String str = "";
		while (num > 0) {
			for (Integer i : map.keySet()) {
				int temp = num / i;
				if (temp > 0) {
					for (int j = 0; j < temp; j++) {
						str += map.get(i);
					}
					
					num -= (temp * i);
					break;
				}
			}
		}
		
		return str;
	}

}

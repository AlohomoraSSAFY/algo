import java.io.*;
import java.util.*;

public class Main {
	static HashMap<String, Integer> hm; 
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub==
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String str1= br.readLine();
		String str2= br.readLine();
		
		hm = new HashMap<String, Integer>();
		
		hm.put("IV",4);
		hm.put("IX",9);
		hm.put("XL",40);
		hm.put("XC",90);
		hm.put("CD",400);
		hm.put("CM",900);
		
		hm.put("I",1);
		hm.put("V",5);
		hm.put("X",10);
		hm.put("L",50);
		hm.put("C",100);
		hm.put("D",500);
		hm.put("M",1000);
		
		int n1 = todecimal(str1);
		int n2 = todecimal(str2);
		int num = n1+n2;
		String rome = torome(num);
		System.out.println(num);
		System.out.println(rome);
	}

	public static int todecimal(String s) {
		int answer =0;
		String str = s;
		for(int i=0;i<str.length()-1;i++) {
			String st = str.charAt(i)+""+str.charAt(i+1);
			if(hm.containsKey(st)) {
				answer+=hm.get(st);
				str = str.replace(st, "");
				i-=1;
			}
		}
		for(int i=0;i<str.length();i++) {
			answer+=hm.get(Character.toString(str.charAt(i)));
		}
		
		return answer;
	}
	
	public static String torome(int num) {
		String answer="";
		boolean[] check = new boolean[1000];
		while(num>0) {
			if(num>=1000) {
				num-=1000;
				answer+="M";
			}else if (num>= 900 && !check[900]) {
				check[900] = true;
				num-=900;
				answer+="CM";
			}else if(num>=500) {
				num-=500;
				answer+="D";
			}else if (num>= 400 && !check[400]) {
				check[400] = true;
				num-=400;
				answer+="CD";
			}else if(num>=100) {
				num-=100;
				answer+="C";
			}else if(num>=90 && !check[90]) {
				check[90] = true;
				num-=90;
				answer+="XC";
			}else if(num>=50) {
				num-=50;
				answer+="L";
			}else if(num>=40 && !check[40]) {
				check[40] = true;
				num-=40;
				answer+="XL";
			}else if(num>=10) {
				num-=10;
				answer+="X";
			}else if(num>=9 && !check[9]) {
				check[9] = true;
				num-=9;
				answer+="IX";
			}else if(num>=5) {
				num-=5;
				answer+="V";
			}else if(num>=4 && !check[4]) {
				check[4] = true;
				num-=4;
				answer+="IV";
			}else if(num>=1) {
				num-=1;
				answer+="I";
			}
		}
		
		return answer;
	}
	
}
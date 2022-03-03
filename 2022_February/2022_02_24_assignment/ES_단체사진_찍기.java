package net.acmicpc.march.week1;

import java.util.*;

public class ES_단체사진_찍기 {
	static String data = "ACFJMNRT";
	static String[] input; 
	static boolean visited[];
	static int[] selected;
	static int answer;
	
	private static boolean check() {
	    
	    StringBuilder sb = new StringBuilder();
	    for(int i = 0 ; i < 8; i++){
	        sb.append(data.charAt(selected[i]));
	    }
	    String friends = sb.toString();
	    
	    for (String s : input) {
	        int friend1 = friends.indexOf(s.charAt(0));
	        int friend2 = friends.indexOf(s.charAt(2));
	
	        char op = s.charAt(3);
	        int d = s.charAt(4) - '0' + 1;
	
	        int distance = Math.abs(friend1 - friend2);
	        if (op == '=') {
	            if (distance != d)
	                return false;
	        } else if (op == '>' ) {
	            if(distance <= d)
	                return false;
	        } else if (distance >= d)
	            return false;
	    }
	
	    return true;
	}
	
	private static void perm(int cnt) {
	    if (cnt == 8) {
	        if (check())
	            answer++;
	        return;
	    }
	
	    for (int i = 0; i < 8; i++) {
	        if (visited[i])
	            continue;
	        visited[i] = true;
	        selected[cnt] = i;
	        perm(cnt + 1);
	        visited[i] = false;
	    }
	}
	
	static int solution(int n, String[] data) {
	    input = data;
	    answer = 0;
	    
	    selected = new int[8];
	    visited = new boolean[8];
	    perm(0);
	
	    return answer;
	}


}
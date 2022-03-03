package net.acmicpc.march.week1;


import java.util.*;

public class ES_괄호_변환 {
    
    private static boolean check(String p){
      String[] input = p.split("");
      int count = 0;
      for(String s : input){
          if(s.equals("(")) count++;
          else count--;

          if(count < 0) return false;
      }
      return true;
    }

    private static int divide(String p){
	    String[] input = p.split("");
	    int count = 0;
	    int index = 0;
	    for(int i = 0; i < input.length; i++){
	        if(input[i].equals("(")) count++;
	        else count--;
	
	        if(count == 0){
	            index = i + 1;
	            break;
	        }
	    }
	    return index;
	}
    
    public String solution(String p) {
        if (p.equals("")) return p;
        
        int pos = divide(p);
        String u = p.substring(0, pos);
        String v = p.substring(pos);
        
        if(check(u)){
            return u + solution(v);
        }
        
        StringBuilder sb = new StringBuilder();
        sb.append("("); 
        sb.append(solution(v));
        sb.append(")");
        
        for (int i = 1; i < u.length() - 1; i++) {
            if (u.charAt(i) == '(') {
                sb.append(')');
            } else {
                sb.append('(');
            }
        }
        
        return sb.toString();
    }

    
}
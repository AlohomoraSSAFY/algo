package study0303;

import java.util.*;

public class SY_괄호_변환 {
    
    int pos;
    
    public String solution(String p) {
        if (p.length() == 0) return p;
        
        boolean flag = check(p);
        String u = p.substring(0, pos + 1);
        String v = p.substring(pos + 1);
        
        if (flag) {
            return u + solution(v);
        }
        
        String answer = "";
        answer += ("(" + solution(v) + ")");
        for (int i = 1; i < u.length() - 1; i++) {
            if (u.charAt(i) == '(') {
                answer += ")";
            } else {
                answer += "(";
            }
        }
        return answer;
    }
    
    private boolean check(String p) {
        Stack<Character> stack = new Stack<>();
        int left = 0;
        int right = 0;
        boolean flag = true;
        
        for (int i = 0; i < p.length(); i++) {
            char temp = p.charAt(i);
            if (temp == '(') {
                left++;
                stack.push(temp);
            } else {
                right++;
                if (stack.isEmpty() || stack.peek() == ')') {
                    flag = false;
                } else {
                    stack.pop();
                }
            }
            
            if (left == right) {
                pos = i;
                return flag;
            }
        }
        
        return true;
    }
}

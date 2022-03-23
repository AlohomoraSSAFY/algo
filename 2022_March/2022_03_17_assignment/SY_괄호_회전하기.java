package study0324;

import java.util.*;

public class SY_괄호_회전하기 {
    public int solution(String s) {
        int answer = 0;
        for (int i = 0; i < s.length(); i++) {
            String str = s.substring(i) + s.substring(0, i);
            if (isCorrect(str)) answer++;
        }
        return answer;
    }
    
    private boolean isCorrect(String str) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c == '[' || c == '(' || c == '{') {
                stack.push(c);
            } else if (c == ']') {
                if (!stack.isEmpty() && stack.peek() == '[') {
                    stack.pop();
                } else {
                    return false;
                }
            } else if (c == ')') {
                if (!stack.isEmpty() && stack.peek() == '(') {
                    stack.pop();
                } else {
                    return false;
                }
            } else {
                if (!stack.isEmpty() && stack.peek() == '{') {
                    stack.pop();
                } else {
                    return false;
                } 
            }
        }
        
        if (stack.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }
}

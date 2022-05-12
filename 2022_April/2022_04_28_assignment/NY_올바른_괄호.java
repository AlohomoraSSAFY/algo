import java.util.*;

class Solution {
    boolean solution(String s) {
        boolean answer = true;

        Stack<Character> stack  = new Stack<>();

        for(int i=0;i<s.length();i++){
            char c= s.charAt(i);
            if(c == '('){
                stack.push('(');
            }else {
                if(!stack.isEmpty() && stack.peek() == '('){
                   stack.pop();
                }else
                    return false;
                    
            }
        }
        
        if(stack.isEmpty())
        return true;
        else
            return false;
    }
}
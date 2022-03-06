package study0310;

import java.util.*;

public class SY_수식_최대화 {
    
    List<Long> numList;
    List<Character> opList;
    char[] opArray = {'*', '+', '-'};
    char[] selected;
    boolean[] visited;
    long answer;
    
    public long solution(String expression) {
        numList = new ArrayList<>();
        opList = new ArrayList<>();
        selected = new char[3];
        visited = new boolean[3];
        
        String num = "";
        for(int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);
            if (c - '0' >= 0 && c - '0' <= 9) {
                num += String.valueOf(c);
            } else {
                numList.add(Long.parseLong(num));
                num = "";
                opList.add(c);
            }
        }
        numList.add(Long.parseLong(num));
        
        permutation(0);
        return answer;
    }
    
    private void permutation(int cnt) {
        if (cnt == 3) {
            List<Long> nList = new ArrayList<>(numList);
            List<Character> oList = new ArrayList<>(opList);
            
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < oList.size(); j++) {
                    if (oList.get(j) == selected[i]) {
                        nList.set(j, operation(nList.get(j), nList.get(j+1), oList.get(j)));
                        nList.remove(j+1);
                        oList.remove(j);
                        j--;
                    }
                }
            }
            
            answer = Math.max(answer, Math.abs(nList.get(0)));
            return;
        }
        
        for (int i = 0; i < 3; i++) {
            if (visited[i]) continue;
            
            visited[i] = true;
            selected[cnt] = opArray[i];
            permutation(cnt + 1);
            visited[i] = false;
        }
    }
    
    private Long operation(long num1, long num2, char op) {
        long result = 0;
        switch(op) {
                case '+':
                    result = num1 + num2;
                    break;
                case '-':
                    result = num1 - num2;
                    break;
                case '*':
                    result = num1 * num2;
                    break;
        }
        
        return result;
    }
}

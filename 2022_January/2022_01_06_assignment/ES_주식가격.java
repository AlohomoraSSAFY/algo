package net.acmicpc.jan.week2;

import java.util.*;

class ES_주식가격 {
    
    public int[] solution(int[] prices) {
        int[] answer = new int[prices.length];
        
        Stack<Integer> stack = new Stack<>();
        stack.add(0);
        
        for(int i = 1 ; i < prices.length; i++){
            int p = prices[i];
            while(!stack.isEmpty()){
                int top = stack.peek();
                if(prices[top] <= p){
                    break;
                }else{
                    answer[top] = i - top; // 1 2 3 || 2 3 // top이 2 i가 현재 인덱스니 3
                    // 1 2 3 || 1 3 -> idx = 3 top = 2;
                    // idx = 3 top 2 -> answer에 2가 
                    // 
                    stack.pop();
                }
            }
            stack.add(i);
        }
        
        while(!stack.isEmpty()){
            answer[stack.peek()] = prices.length - 1 - stack.peek();
            stack.pop();
        }
        
        return answer;
    }
}
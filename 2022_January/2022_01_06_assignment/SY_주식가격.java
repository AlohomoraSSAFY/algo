package study0113;

import java.util.*;

public class SY_주식가격 {
    
    class Node {
        int index;
        int price;
        int time;
        
        public Node(int index, int price, int time) {
            this.index = index;
            this.price = price;
            this.time = time;
        }
    }
    
    public int[] solution(int[] prices) {
        int[] answer = new int[prices.length];
        Queue<Node> q = new LinkedList<>();
        
        for (int i = 0; i < prices.length; i++) {            
            int size = q.size();
            for (int j = 0; j < size; j++) {
                Node cur = q.poll();
                if (cur.price > prices[i]) {
                    answer[cur.index] = cur.time + 1;
                } else {
                    q.offer(new Node(cur.index, cur.price, cur.time + 1));
                }
            }
            
            q.offer(new Node(i, prices[i], 0));
        }
        
        int size = q.size();
        for (int i = 0; i < size; i++) {
            Node cur = q.poll();
            answer[cur.index] = cur.time;
        }
        
        return answer;
    }
}

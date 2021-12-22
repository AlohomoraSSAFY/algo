package study1223;

import java.util.*;

public class SY_단어_변환 {
    
    static String begin;
    static String target;
    static String[] words;
    
    public class Node {
        String word;
        int cnt;
        
        public Node(String word, int cnt) {
            this.word = word;
            this.cnt = cnt;
        }
    }
    
    public int solution(String begin, String target, String[] words) {
        this.begin = begin;
        this.target = target;
        this.words = words;
        
        return bfs();
    }
    
    public int bfs() {
        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(begin, 0));
        boolean[] visited = new boolean[words.length];
        
        while (!q.isEmpty()) {
            Node cur = q.poll();
            String word = cur.word;
            int cnt = cur.cnt;
            
            if (word.equals(target)) return cnt;
            
            for (int i = 0; i < words.length; i++) {
                if (visited[i]) continue;
                
                int count = 0;
                for (int j = 0; j < words[i].length(); j++) {
                    if (word.charAt(j) == words[i].charAt(j)) count++;
                }
                
                if (count >= word.length() - 1) {
                    visited[i] = true;
                    q.offer(new Node(words[i], cnt + 1));
                }
            }
        }
        
        return 0;
    }
}

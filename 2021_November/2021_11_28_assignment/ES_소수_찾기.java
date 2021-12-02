import java.util.*;

class Solution {
    
    static String str="";
    static int N, answer = 0;
    static char[] selected;
    static HashSet<Integer> set = new HashSet<>();
    static boolean[] prime = new boolean[10000000];
    
    private static void dfs(int idx, int goal, boolean[] visited){
        if(idx==goal){
            StringBuilder sb = new StringBuilder();
            for(int i = 0 ; i < goal; i++){
                sb.append(selected[i]);
            }
            String s = sb.toString();
            System.out.println(s);
            if(s.length() > 0){
                int tmp = Integer.parseInt(sb.toString());
                if(!prime[tmp]){
                    set.add(tmp);
                }
            }
            return;
        }
        
        for(int i = 0 ; i < N; i++ ){
            if(!visited[i]){
                visited[i] = true;
                selected[idx] = str.charAt(i);
                dfs(idx+1, goal, visited);
                visited[i] = false;
            }
        }
        
    }
    
    
    public int solution(String numbers) {
        N = numbers.length();
        // System.out.println(N);
        str = numbers;
        // System.out.println(str);
        
        prime[0] = true;
        prime[1] = true;
        prime[2] = false; // 소수가 false
        for(int i = 2; i < 10000000; i++){
            if(!prime[i]){
                for(int j = 2; i*j < 10000000; j++){
                    prime[i*j] = true;
                }
            }    
        }
    
        
        for(int i = 1; i < N+1; i++){
            selected = new char[i];
            boolean[] visited = new boolean[N];
            dfs(0, i, visited);
        }
        
        return set.size();
    }
}
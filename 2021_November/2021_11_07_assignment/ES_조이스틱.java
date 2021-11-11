import java.util.*;

class Solution {
    static int[] move;
    static ArrayList<Integer> list;
    static int n, len;
    static int min = Integer.MAX_VALUE;
    
    public static void next(int cnt, int idx, int sum, boolean[] checked){
        if(cnt==n){
            min = Math.min(min, sum);
            return;
        }
        
        for(int e : list){
            if(!checked[e]){ // 방문 안했으면
                checked[e] = true;
                int diff = Math.abs(idx - e);
                int m = Math.min(diff, len - diff);
                next(cnt+1, e, sum+m, checked);
                checked[e] = false;
            }
        }
        
    }
    
    public int solution(String name) {
        int answer = 0;
        // AAAHAHA -> 5번, <- 4번
        // ABAAAAAAAAACA
        move = new int[name.length()];
        n = 0; len = name.length();
        list = new ArrayList<>();
        int total = 0;
        for(int i = 0; i < name.length(); i++){
            char c = name.charAt(i);
            if(c=='A'){
                
            }else{
                int a = c - 'A';
                int z = 'Z' - c +1;
                move[i] = Math.min(a,z);
                total+=move[i];
                list.add(i); n++;
            }
        }
        
        boolean[] checked = new boolean[len];
        next(0, 0, 0, checked);
       
        
        answer = total + min;
        return answer;
    }
}
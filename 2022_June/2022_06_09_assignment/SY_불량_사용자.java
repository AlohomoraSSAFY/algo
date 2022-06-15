package study0616;

import java.util.*;

public class SY_불량_사용자 {
    
    String[] user_id;
    String[] banned_id;
    boolean[] visited;
    Set<String> result;
    
    public int solution(String[] user_id, String[] banned_id) {
        this.user_id = user_id;
        this.banned_id = banned_id;
        visited = new boolean[user_id.length];
        result = new HashSet<>();
        
        recursion(0);
        return result.size();
    }
    
    private void recursion(int banned_idx) {
        if (banned_idx == banned_id.length) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < user_id.length; i++) {
                if (visited[i]) {
                    sb.append(i);
                }
            }
            
            result.add(sb.toString());
            return;
        }
        
        for (int i = 0; i < user_id.length; i++) {
            if (visited[i]) continue;
            if (isCheck(i, banned_idx)) {
                visited[i] = true;
                recursion(banned_idx + 1);
                visited[i] = false;
            }
        }
    }
    
    private boolean isCheck(int user_idx, int banned_idx) {
        String user = user_id[user_idx];
        String banned = banned_id[banned_idx];
        if (user.length() != banned.length()) return false;
        
        for (int i = 0; i < user.length(); i++) {
            if (banned.charAt(i) == '*') continue;
            if (user.charAt(i) != banned.charAt(i)) return false;
        }
        
        return true;
    }
}

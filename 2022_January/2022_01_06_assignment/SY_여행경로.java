package study0113;

import java.util.*;

public class SY_여행경로 {
    String[][] tickets;
    boolean[] used;
    boolean flag;
    List<String> result;
    
    public String[] solution(String[][] tickets) {
        this.tickets = tickets;
        used = new boolean[tickets.length];
        Arrays.sort(tickets, new Comparator<String[]>() {
            @Override
            public int compare(String[] o1, String[] o2) {
                return o1[1].compareTo(o2[1]);
            }
        });
        
        result = new ArrayList<>();
        result.add("ICN");
        dfs("ICN", 0);
        
        String[] answer = new String[result.size()];
        for (int i = 0; i < result.size(); i++) {
            answer[i] = result.get(i);
        }
        return answer;
    }
    
    public void dfs(String start, int cnt) {
        if (cnt == tickets.length) {
            flag = true;
            return;
        }
        
        for (int i = 0; i < tickets.length; i++) {
            if (used[i]) continue;
            if (!tickets[i][0].equals(start)) continue;
            
            used[i] = true;
            result.add(tickets[i][1]);
            dfs(tickets[i][1], cnt + 1);
            used[i] = false;
            
            if (flag) break;
            result.remove(result.size() - 1);
        }
    }
}

package date0113THU;

import java.util.*;

public class 프로그래머스_여행경로 {

    static String[] answer;
    static boolean check;
    
    public String[] solution(String[][] tickets) {
        answer = new String[tickets.length+1];
        Arrays.sort(tickets, new Comparator<String[]>() {
            public int compare(String[] o1, String[] o2){
              	if (o1[1].charAt(0) == o2[1].charAt(0) )
                    return o1[1].charAt(1) - o2[1].charAt(1);
                else
                    return o1[1].charAt(0) - o2[1].charAt(0);
            }
        });
        
        String[] path = new String[tickets.length + 1];
        path[0] = "ICN";
        boolean visited[] = new boolean[tickets.length];
        dfs(tickets, path, 1, visited, "ICN");
        return answer;
    }
    
    public static void dfs(String[][] tickets, String[] path, int count, boolean visited[], String cur){
    	
        if(count == tickets.length+1){
            if(!check)
            answer = path.clone();
            check= true;
            return;
        }
        
        for(int i=0;i<tickets.length;i++){
            if(!visited[i] && cur.equals(tickets[i][0])){
                visited[i] = true;
                path[count] = tickets[i][1];
                dfs(tickets, path, count+1, visited, tickets[i][1]);
                visited[i] = false;
            }        
        }
        
        
    }
}

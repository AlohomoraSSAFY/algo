package net.acmicpc.jan.week2;

import java.util.*;

class ES_여행경로 {
    static String[][] ticket;
    static boolean[] used;
    static List<String> list = new ArrayList<String>();
    static String[] answer = {};
    
     private void dfs(String start, int cnt) {
        if(answer.length > 0) return; 

        if(cnt == used.length) { 
            answer = new String[list.size()]; 
            for(int i = 0; i < list.size(); i++) {
                answer[i] = list.get(i); 
            }
            return;
        }

        for(int i = 0 ; i < ticket.length; i++) {
            if(!used[i] && ticket[i][0].equals(start)) { 
                used[i] = true; 
                list.add(ticket[i][1]);
                dfs(ticket[i][1], cnt +1); 
                used[i] = false;
                list.remove(list.size()-1);
            }
        }
    }

    public String[] solution(String[][] tickets) {
        
    	used = new boolean[tickets.length]; 
        ticket = tickets;

        Arrays.sort(ticket, (o1, o2) -> {
           if(o1[0].equals(o2[0])) {
               return o1[1].compareTo(o2[1]);
           } else {
               return o1[0].compareTo(o2[0]);
           }
        });

        list.add("ICN");
        dfs("ICN", 0);
        
        return answer;
    }

   
}
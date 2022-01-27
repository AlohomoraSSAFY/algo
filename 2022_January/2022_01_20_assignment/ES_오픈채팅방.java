package net.acmicpc.jan.week4;

import java.util.*;

public class ES_오픈채팅방 {
    public String[] solution(String[] record) {
        
        HashMap<String, String> nicknames = new HashMap<>();
        ArrayList<String[]> list = new ArrayList<>();
        
        for(int i = 0 ; i < record.length; i++){
            String[] tmp = record[i].split(" ");
            String cmd = tmp[0];
            String id = tmp[1];
            if(cmd.equals("Leave")) {
                list.add(new String[]{cmd, id});
            }else if(cmd.equals("Change")){
                String nick = tmp[2];
                nicknames.put(id, nick);
            }else {
                String nick = tmp[2];
                nicknames.put(id, nick);
                list.add(new String[]{cmd, id});
            }
        }
        
        ArrayList<String> answers = new ArrayList<>();
        for(Iterator<String[]> iter = list.iterator(); iter.hasNext(); ){
            String[] cur = iter.next();
            String nick = nicknames.get(cur[1]);
            if(cur[0].equals("Enter")){
                answers.add(nick+"님이 들어왔습니다.");
            }else{
                answers.add(nick+"님이 나갔습니다.");
            }
        }
        
        // String[] answer = {};
        String[] answer = answers.stream().toArray(String[]::new);
        
        return answer;
    }
}
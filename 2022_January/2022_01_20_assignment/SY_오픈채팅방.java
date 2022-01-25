package study0127;

import java.util.*;

public class SY_오픈채팅방 {
    public String[] solution(String[] record) {
        Map<String, String> map = new HashMap<>();
        List<String[]> list = new ArrayList<>();
        for (int i = 0; i < record.length; i++) {
            String[] sArray = record[i].split(" ");
            if (!sArray[0].equals("Change")) {
                list.add(new String[]{sArray[0], sArray[1]});
            }
            if (!sArray[0].equals("Leave")) {
                map.put(sArray[1], sArray[2]);
            }
        }
        
        String[] answer = new String[list.size()];
        for (int i = 0; i < list.size(); i++) {
            String[] temp = list.get(i);
            answer[i] = (map.get(temp[1]) + "님이 ");
            if (temp[0].equals("Enter")) {
                answer[i] += "들어왔습니다.";
            } else {
                answer[i] += "나갔습니다.";
            }
        }
        return answer;
    }
}

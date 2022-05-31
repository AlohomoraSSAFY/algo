package study0602;

import java.util.*;

public class SY_주차_요금_계산 {
    
    public int[] solution(int[] fees, String[] records) {
        Map<String, Integer> map = new HashMap<>();
        Map<String, Integer> timeMap = new HashMap<>();
        for (int i = 0; i < records.length; i++) {
            String[] sArray = records[i].split(" ");
            String[] timeArray = sArray[0].split(":");
            int time = Integer.parseInt(timeArray[0]) * 60 + Integer.parseInt(timeArray[1]);
            
            if (sArray[2].equals("IN")) {
                map.put(sArray[1], time);
            } else {
                int temp = time - map.get(sArray[1]);
                timeMap.put(sArray[1], timeMap.getOrDefault(sArray[1], 0) + temp);
                map.remove(sArray[1]);
            }
        }
        
        for (String s : map.keySet()) {
            int temp = 1439 - map.get(s);
            timeMap.put(s, timeMap.getOrDefault(s, 0) + temp);        
        }
        
        TreeMap<String, Integer> resultMap = new TreeMap<>();
        for (String str : timeMap.keySet()) {
            int time = timeMap.get(str);
            int fee = 0;
            if (time <= fees[0]) {
                fee = fees[1];
            } else {
                time -= fees[0];
                fee = fees[1];
                fee += (time / fees[2]) * fees[3];
                if (time % fees[2] != 0) {
                    fee += fees[3];
                }
            }
            
            resultMap.put(str, fee);
        }
        
        int[] answer = new int[resultMap.size()];
        int idx = 0;
        for (String str : resultMap.keySet()) {
            answer[idx++] = resultMap.get(str);
        }
        return answer;
    }
}

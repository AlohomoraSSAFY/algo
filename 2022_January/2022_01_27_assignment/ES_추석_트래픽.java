package net.acmicpc.feb.week1;

import java.util.*;

public class ES_추석_트래픽 {
    public int solution(String[] lines) {
        int answer = 0;

        int[] cnt = new int[lines.length];

        for(int i = 0; i<lines.length;i++){
            lines[i] = lines[i].substring(11).replace(":","").replace("s","");

            int sec = Integer.parseInt(lines[i].substring(0,2)) * 3600 +
                        Integer.parseInt(lines[i].substring(2,4))* 60+
                        Integer.parseInt(lines[i].substring(4,6));
            lines[i] = String.valueOf(sec) + lines[i].substring(6);
        }

        for(int i = 0; i< lines.length;i++){
            String[] split = lines[i].split(" ");
            Double complete = Double.parseDouble(split[0]); //완료시간
            double space = complete + 1;
            for(int j = i; j < lines.length; j++){
                split = lines[j].split(" ");

                double start = Double.parseDouble(split[0]) - Double.parseDouble(split[1]) + 0.001;
                if(start < space){
                    cnt[i]++;
                }
            }
        }
        
        Arrays.sort(cnt);
        return cnt[cnt.length-1];
    }
}
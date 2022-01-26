package study0127;

import java.util.*;

public class SY_다단계_칫솔_판매 {
    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        Map<String, Integer> map = new HashMap<>();
        map.put("center", enroll.length);
        for (int i = 0; i < enroll.length; i++) {
            map.put(enroll[i], i);
        }
        
        int[] parent = new int[enroll.length];
        for (int i = 0; i < referral.length; i++) {
            if (referral[i].equals("-")) {
                parent[i] = map.get("center");
            } else {
                parent[i] = map.get(referral[i]);
            }
        }
        
        int[] answer = new int[enroll.length];
        for (int i = 0; i < seller.length; i++) {
            int profit = amount[i] * 100;
            int person = map.get(seller[i]);
            while (true) {
                if ((profit * 10 / 100) < 1) {
                    answer[person] += profit;
                } else {
                    answer[person] += (profit - (profit * 10 / 100));
                }
                
                if (parent[person] == enroll.length || (profit * 10 / 100) < 1) break;
                profit = (profit * 10 / 100);
                person = parent[person];
            }
        }
        return answer;
    }
}

package net.acmicpc.jan.week4;

import java.util.*;

public class ES_다단계_칫솔_판매 {
    
    static HashMap<String, String> con = new HashMap<>();
    static HashMap<String, Integer> sum = new HashMap<>();
    
    private static void dfs(String cur, int money) {
        if(cur.equals("-")){
            sum.put(cur, sum.getOrDefault(cur, 0) + money);
            return;
        }
        int fee = (int) (money * 0.1);
        int left = money - fee;
        sum.put(cur, sum.getOrDefault(cur, 0) + left);
        // System.out.println(cur+" "+sum.get(cur));
        if(fee==0) return;
        else dfs(con.get(cur), fee);
        
    }

    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        
        // sum = new int[enroll.length +1]
        
        for(int i = 0 ; i < enroll.length; i++){
            String me= enroll[i];
            String pa = referral[i];
            con.put(me, pa);
        }
        
        for(int i = 0; i < seller.length; i++){
            String name = seller[i];
            int money = amount[i]*100;
            int fee = (int) (money * 0.1);
            int left = money - fee;
            sum.put(name, sum.getOrDefault(name, 0) + left);
            // System.out.println(name+" "+sum.get(name));
            dfs(con.get(name), fee);
        }
        
        int[] answer = new int[enroll.length];
        for(int i = 0 ; i < enroll.length; i++){
            answer[i] = sum.getOrDefault(enroll[i], 0);
            // System.out.println(sum.get(enroll[i]));
        }
        
        return answer;
    }
}
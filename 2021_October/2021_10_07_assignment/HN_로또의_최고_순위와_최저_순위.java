package com.baekjoon.problem21;

public class HN_로또의_최고_순위와_최저_순위 {
    static int min, max;
    static final int N = 45;
    public int[] solution(int[] lottos, int[] win_nums) {
        
        boolean[] selected = new boolean[N+1];
        int cnt = 0;
        for(int i = 0; i < lottos.length; i++){
            if(lottos[i] != 0){
                selected[lottos[i]] = true;
                cnt++;
            }
        }
        
        min = Integer.MAX_VALUE;
        max = Integer.MIN_VALUE;
        
        Combi(0, 1, win_nums, lottos.length - cnt, selected);
        
        int[] answer = {min, max};
        
        return answer;
    }
    
    void Combi(int position, int start, int[] win_nums, int finish, boolean[] selected){
        if(position == finish){
            int cnt = 0;
            for(int i = 0; i < win_nums.length; i++){
                if(selected[win_nums[i]]){
                    cnt++;
                }
            }
            int rank = 0;
            switch(cnt) {
                case 6 :
                    rank = 1;
                    break;
                case 5 :
                    rank = 2;
                    break;
                case 4 :
                    rank = 3;
                    break;
                case 3 :
                    rank = 4;
                    break;
                case 2 :
                    rank = 5;
                    break;
                default :
                    rank = 6;
                    break;
            }
            
            min = Math.min(min, rank);
            max = Math.max(max, rank);
            return;
        }
        
        for(int i = start; i<= N; i++){
            if(selected[i]) continue;
            selected[i] = true;
            
            Combi(position + 1, i+1, win_nums, finish, selected);
            
            selected[i] = false;
        }
    }
}

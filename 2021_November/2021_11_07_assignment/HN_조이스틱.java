package com.baekjoon.problem31;

import java.util.*;

class Solution {
    static char word[];
    static boolean checked[];
    static int total, answer, len;
    public int solution(String name) {
        total = 0;
        word = name.toCharArray();
        len = word.length;
        checked = new boolean[len];
        for(int l = 0; l < len; l++){
            if(word[l] != 'A')
                total++;
        }
        
        // 현재 위치, 카운트 수, 처음 진행방향(1, -1), 방향 전환 유무, 이동횟수
        
        if(total != 0 ){
            answer = Integer.MAX_VALUE;
            recur(0, 0, 1, false, 0);
            recur(0, 0, -1, false, 0);
        }else {
            answer = 0;
        }
        
        return answer;
    }
    
    public void recur(int now, int cnt, int dir, boolean change, int move){
        if(cnt == total){
            answer = Math.min(answer, move - 1);
            return;
        }
        
        if(move > answer)
            return;
        
        if(word[now] != 'A'){
            boolean init = false;
            int cur = -1;
            if(!checked[now]){
                move += Math.min(word[now] - 'A', 'Z' - word[now] + 1);
                checked[now] = true;
                cnt += 1;
                init = true;
                cur = now;
            }
            
            if(dir == 1){
                now = now + 1 >= len ? 0 : now + 1;
            }else {
                now = now - 1 < 0 ? len - 1 : now - 1;
            }
            recur(now, cnt, dir, change, move + 1);
            
            if(init) {
                checked[cur] = false;
                move -= Math.min(word[cur] - 'A', 'Z' - word[cur] + 1);
                cnt -= 1;
            }
        }else { // if(word[now] == 'A')
            if(!change){
                int cdir = dir*(-1);
                int cnow = 0;
                if(cdir == 1){
                    cnow = now + 1 >= len ? 0 : now + 1;
                }else {
                    cnow = now - 1 < 0 ? len - 1 : now - 1;
                }
                // 주의 "ABAAAAAABB"
                recur(cnow, cnt, cdir, true, move - 1);
            }
            
            if(dir == 1){
                now = now + 1 >= len ? 0 : now + 1;
            }else {
                now = now - 1 < 0 ? len - 1 : now - 1;
            }
            recur(now, cnt, dir, change, move + 1);
        }
    }
}
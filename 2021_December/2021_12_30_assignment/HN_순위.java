package com.baekjoon.problem43;

import java.util.LinkedList;
import java.util.List;

public class HN_순위 {
	static int[] victory, defeat;
    static boolean visited[];
    static List<Integer> list[];
    public int solution(int n, int[][] results) {
        // 배열의 길이
        victory = new int[n+1];
        defeat = new int[n+1];
        list = new LinkedList[n+1];
        for(int i = 0; i <= n; i++){
            list[i] = new LinkedList<>();
        }
        
        int len = results.length;
        for(int i = 0; i < len; i++){
            list[results[i][0]].add(results[i][1]);
        }
        
        for(int i = 1; i <= n; i++){
            visited = new boolean[n+1];
            dfs(i, i);
        }
        
        int answer = 0;
        
        for(int i = 1; i <= n; i++){
            if(victory[i] + defeat[i] == n-1){
                answer++;
            }
        }
        
        return answer;
    }
    
    public void dfs(int cur, int me){
        visited[cur] = true;
        for(Integer i : list[cur]){
            if(!visited[i]){
                victory[me]++;
                defeat[i]++;
                dfs(i, me);    
            }
        }
    }
}

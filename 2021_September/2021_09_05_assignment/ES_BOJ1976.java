package com.ssafy.algostudy.ds;

import java.io.*;
import java.util.*;

public class Main1976 {
    static int N, M;
    static int[][] link;
    static int[] parent;

    private static int findSet(int cur){
        if(parent[cur] == cur){
            return cur;
        }

        return (parent[cur] = findSet(parent[cur]));
    }

    private static void union(int a, int b){
        int pa = findSet(a);
        int pb = findSet(b);
        if(pa < pb){
            parent[pb] = pa;
        }else{
            parent[pa] = pb;
        }
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("./res/1976.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        link = new int[N][N];
        for(int i = 0 ; i < N; i++){
            String[] line = br.readLine().split(" ");
            for(int j = 0 ; j < N; j++){
                link[i][j] = Integer.parseInt(line[j]);
            }
        }

        parent = new int[N];
        for(int i = 0 ; i < N ; i++){
            parent[i] = i;
        }
        
        for(int i = 0 ; i < N ; i++){
            for(int j = 0 ; j < N ; j++){
                if(link[i][j]==1){
                    union(i, j);
            }
        }

        boolean flag = true;
        if( M > 0 ){
            String[] str = br.readLine().split(" ");
            int p = findSet(Integer.parseInt(str[0])-1);
            for(int i = 1 ; i < M ; i++){
                int cur = Integer.parseInt(str[i]) - 1;
                if(cur < 0 || cur > N - 1){
                    flag = false;
                    break;
                }
                if( findSet(cur) != p ){
                    flag = false;
                    break;
                }
            }
        }else {
            flag = false;
        }
        String ans = (flag) ? "YES" : "NO";
        bw.write(ans+"\n");
        bw.close();
        br.close();
    }
}

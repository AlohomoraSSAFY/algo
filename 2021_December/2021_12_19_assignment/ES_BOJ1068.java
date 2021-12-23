package com.ssafy.december.week4;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.StringTokenizer;

public class BOJ1068 {

    static int N;
    static int[] parent;
    static ArrayList<Integer>[] children;
    static int root = -1;
    static int del = -1;
    static HashSet<Integer> leaves = new HashSet<>();

    private static void dfs(int cur){
        if(children[cur].size()==0){
            leaves.add(cur);
            return;
        }

        for (int child : children[cur]){
            if(child != del){
                dfs(child);
            }
        }
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        parent = new int[N];
        children = new ArrayList[N+1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++){
            parent[i] = Integer.parseInt(st.nextToken());
            children[i] = new ArrayList<>();
        }

        del = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++){
            int val = parent[i];
            if(val==-1){
                root = i;
            }else {
                if(i == del) continue;
                children[val].add(i);
            }
        }

        if(del != root){
            children[del] = new ArrayList<>();
            dfs(root);
            bw.write(leaves.size()+"\n");
        }else{
            bw.write("0\n");
        }

        br.close();
        bw.close();
    }
}

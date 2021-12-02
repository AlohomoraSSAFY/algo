package com.ssafy.december.week1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main11725 {

    static int N;
    static ArrayList<Integer>[] conn;
    static int[] parent;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        conn = new ArrayList[N+1];
        for(int i = 0 ; i < N+1; i++){
            conn[i] = new ArrayList<>();
        }
        parent = new int[N+1];
        for(int i = 1 ; i < N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            conn[a].add(b);
            conn[b].add(a);
        }

        Queue<Integer> queue = new LinkedList<>();
        queue.offer(1);
        parent[1] = 1;
        while (!queue.isEmpty()){
            int cur = queue.poll();
            for(int e : conn[cur]){
                if(e == 1) continue;
                if(parent[e] != 0) continue;
                parent[e] = cur;
                queue.offer(e);
            }
        }

        for(int i = 2; i < N+1; i++){
            bw.write(parent[i]+"\n");
        }

        br.close();
        bw.close();
    }
}

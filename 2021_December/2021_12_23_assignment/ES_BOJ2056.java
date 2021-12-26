package com.ssafy.december.week4;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ2056 {

    static int N;
    static int[] indegree;
    static ArrayList<Integer>[] job;
    static int[] T;
    static int[] total;

    private static void topo(){
        Queue<Integer> queue = new LinkedList<>();

        total = new int[N+1];
        for(int i = 1; i < N+1; i++){
            total[i] = T[i];
            if(indegree[i] == 0){
                queue.offer(i);
            }
        }

        while(!queue.isEmpty()){
            int cur = queue.poll();
            for(int n : job[cur]){
                indegree[n]--;
                total[n] = Math.max(total[n], total[cur]+T[n]);

                if(indegree[n] == 0){
                    queue.offer(n);
                }
            }
        }
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        indegree = new int[N+1];
        T = new int[N+1];
        job = new ArrayList[N+1];

        for(int i = 1; i < N+1; i++){
            job[i] = new ArrayList<>();
        }

        for(int i = 1 ; i < N+1; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            T[i] = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            for(int j = 0 ; j < M; j++){
                int tmp = Integer.parseInt(st.nextToken());
                job[tmp].add(i);
                indegree[i]++;
            }
        }

        topo();

        int time = 0;
        for(int i = 1; i < N+1; i++){
            time = Math.max(time, total[i]);
        }

        bw.write(time+"\n");
        br.close();
        bw.close();
    }
}

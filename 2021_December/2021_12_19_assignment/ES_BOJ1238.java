package com.ssafy.december.week4;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ1238 {

    static int N, X, M;
    static ArrayList<ArrayList<EDGE>> T;
    static ArrayList<ArrayList<EDGE>> reverseT;

    static class EDGE implements Comparable<EDGE>{
        int e, time;

        public EDGE(int e, int time) {
            this.e = e;
            this.time = time;
        }

        @Override
        public int compareTo(EDGE o) {
            return time - o.time;
        }
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());

        T = new ArrayList<ArrayList<EDGE>>();
        for(int i = 0; i < N+1; i++){
            ArrayList<EDGE> list = new ArrayList<>();
            T.add(list);
        }

        reverseT = new ArrayList<ArrayList<EDGE>>();
        for(int i = 0; i < N+1; i++){
            ArrayList<EDGE> list = new ArrayList<>();
            reverseT.add(list);
        }

        for (int i = 0 ; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());
            T.get(s).add(new EDGE(e, t));
            reverseT.get(e).add(new EDGE(s, t));
        }

        int[] dist = new int[N+1];
        for(int i = 1; i < N+1; i++){
            dist[i] = Integer.MAX_VALUE;
        }
        dist[X] = 0;
        boolean[] visited = new boolean[N+1];

        PriorityQueue<EDGE> pq = new PriorityQueue<>();
        pq.add(new EDGE(X, 0));
        while(!pq.isEmpty()){
            EDGE cur = pq.poll();
            int end = cur.e;

            visited[end] = true;
            for(int i = 0 ; i < T.get(end).size(); i++){
                EDGE next = T.get(end).get(i);
                if(!visited[next.e]){
                    if(dist[end] + next.time < dist[next.e]){
                        dist[next.e] = dist[end] + next.time;
                        pq.add(new EDGE(next.e, dist[next.e]));
                    }
                }
            }

        }

        int[] rdist = new int[N+1];
        for(int i = 1; i < N+1; i++){
            rdist[i] = Integer.MAX_VALUE;
        }
        rdist[X] = 0;
        boolean[] rvisited = new boolean[N+1];

        PriorityQueue<EDGE> rpq = new PriorityQueue<>();
        rpq.add(new EDGE(X, 0));
        while(!rpq.isEmpty()){
            EDGE cur = rpq.poll();
            int end = cur.e;

            rvisited[end] = true;
            for(int i = 0 ; i < reverseT.get(end).size(); i++){
                EDGE next = reverseT.get(end).get(i);
                if(!rvisited[next.e]){
                    if(rdist[end] + next.time < rdist[next.e]){
                        rdist[next.e] = rdist[end] + next.time;
                        rpq.add(new EDGE(next.e, rdist[next.e]));
                    }
                }
            }

        }

        int ans = -1;
        for (int i = 1; i < N+1; i++) {
            ans = Math.max(ans, dist[i] + rdist[i]);
        }

        bw.write(ans+"\n");
        br.close();
        bw.close();
    }
}

package com.ssafy.algostudy;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// 네트워크 연결
// 최소 스패닝 트리
public class Main1922 {

    static int N, M;
    static int[] parent;
    static PriorityQueue<Edge> pq = new PriorityQueue<>();

    static private class Edge implements Comparable<Edge>{
        int s, e, weight;

        public Edge(int s, int e, int weight) {
            this.s = s;
            this.e = e;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            return this.weight - o.weight;
        }
    }

    public static int findSet(int a){
        if(parent[a] == a ) return a;
        else{
            return (parent[a] = findSet(parent[a]));
        }
    }

    public static boolean union(int a, int b){
        int pa = findSet(a);
        int pb = findSet(b);
        if(pa==pb){
            return false;
        }else{
            if(pa < pb) parent[pb] = pa;
            else parent[pa] = pb;
            return true;
        }
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        parent = new int[N+1];
        for(int i = 1; i < N+1; i++){
            parent[i] = i;
        }

        StringTokenizer st;
        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            pq.add(new Edge(a, b, c));
        }

        int answer = 0;
        int con = 1;
        while (!pq.isEmpty()){
            Edge e = pq.poll();
            if(union(e.s, e.e)){
                answer+= e.weight;
            }
        }

        bw.write(answer+"\n");
        br.close();
        bw.close();
    }
}

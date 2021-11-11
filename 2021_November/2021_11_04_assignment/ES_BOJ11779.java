package com.ssafy.algostudy.graph;

import java.io.*;
import java.util.*;

// 최소 비용 구하기 2
public class Main11779 {

    static int N, M;
    static long[][] cities;
    static int A, B;
    static long[] distance;
    static int[] parent;

    static class Edge implements Comparable<Edge> {
        int v; long weight;

        public Edge(int v, long weight) {
            this.v = v;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            // TODO Auto-generated method stub
            return Long.compare(this.weight, o.weight);
        }

        @Override
        public String toString() {
            return weight + "";
        }

    }

    public static void dikjsta(){

        distance = new long[N];
        Arrays.fill(distance, Integer.MAX_VALUE);
        parent = new int[N];
        Arrays.fill(parent,-1);

        PriorityQueue<Edge> pq = new PriorityQueue<>();
        boolean[] visited = new boolean[N];

        distance[A] = 0;
        pq.offer(new Edge(A,0));

        while(!pq.isEmpty()){
            Edge cur = pq.poll();
            if(visited[cur.v]) continue;
            else visited[cur.v] = true;

            for(int i = 0; i < N; i++){
                if(cities[cur.v][i] != Integer.MAX_VALUE && distance[i] > distance[cur.v] + cities[cur.v][i] ){
                    distance[i] = distance[cur.v] + cities[cur.v][i];
                    parent[i] = cur.v;
                    pq.offer(new Edge(i, distance[i]));
                }
            }
        }

//        for(int i = 0; i < N; i++){
//            System.out.printf("%3d", distance[i]);
//        }
//        System.out.println();
//        for(int i = 0; i < N; i++){
//            System.out.printf("%3d ", parent[i]);
//        }


    }

    public static void main(String[] args) throws Exception{
//        System.setIn(new FileInputStream("./res/11779.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        cities = new long[N][N];
        for(int i = 0; i < N; i++){
            Arrays.fill(cities[i], Integer.MAX_VALUE);
        }
        StringTokenizer st;
        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken())-1;
            int e = Integer.parseInt(st.nextToken())-1;
            long w = Long.parseLong(st.nextToken());
            cities[s][e] = Math.min(cities[s][e], w);
        }

        st = new StringTokenizer(br.readLine());
        A = Integer.parseInt(st.nextToken())-1;
        B = Integer.parseInt(st.nextToken())-1;


        dikjsta();
        bw.write(distance[B]+"\n");

        Stack<Integer> stack = new Stack<>();
        int cur = B;
        stack.push(cur);
        while(true){
            if(parent[cur]==A) break;
            cur = parent[cur];
            stack.push(cur);
        }
        if(stack.peek()!=A)
           stack.push(A);
        bw.write(stack.size()+"\n");
        while(!stack.isEmpty()){
            bw.write((stack.pop()+1)+" ");
        }


        br.close();
        bw.close();
    }
}

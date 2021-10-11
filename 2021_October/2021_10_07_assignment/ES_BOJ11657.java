package com.ssafy.algostudy;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main11657 {

    static int N, M;
    static final int INF = Integer.MAX_VALUE;
    static int[][] adj;
    static int[] source;
    static int[] destination;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        adj = new int[N+1][N+1];
        for(int i = 1; i < N+1; i++){
            Arrays.fill(adj[i], INF);
        }

        source = new int[M];
        destination = new int[M];
        for(int i = 0 ; i < M; i++){ // 간선 챙기기
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());
            adj[A][B] = Math.min(adj[A][B], C);
            source[i] = A;
            destination[i] = B;
        }

        // 벨만 포드 알고리즘
        long[] distance = new long[N+1];
        int[] parent = new int[N+1];
        Arrays.fill(distance, INF);
        distance[1] = 0;

        BELLMAN_FORD:
        for(int k = 1; k < N; k++){ // 정점 개수만큼 반복
            for(int v = 1; v < N+1; v++){ // 각 정점 갱신
                for(int w = 1; w < N+1; w++){
                    if(adj[w][v] != INF){ // 인접 노드에 대해서
                        if(distance[w]!=INF && distance[v] > distance[w] + adj[w][v]){ // 갱신
                            distance[v] = distance[w] + adj[w][v];
                        }
                    }
                }
            }
        }


//        long[] tmp = new long[N+1];
//        for(int i = 0; i < N+1; i++){
//            tmp[i] = distance[i];
//        }

//        for(int i = 0; i < N+1; i++){
//
//            System.out.println(distance[i]);
//        }


        boolean flag = true; // 일반 적인 음의 사이클 찾는 거
        for(int i = 0; i < M; i++){
            int a = source[i];
            int b = destination[i];
            int c = adj[a][b];
            if(distance[a] == INF) continue;
            if( distance[b] > distance[a] + c ){
                flag = false;
                break;
            }
        }


        if(flag){
            for (int i = 2; i < N+1; i++){
                if(distance[i]==INF)
                    bw.write(-1+"\n");
                else
                    bw.write(distance[i]+"\n");
            }
        }else{
            bw.write(-1+"\n");
        }

        br.close();
        bw.close();
    }
}

package com.ssafy.algostudy.bfs;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Array;
import java.util.*;

// 메모리 초과 오지게 난다.....
public class Main13913BFS {

    static int N, K;
    static int MAX = 100000;

    static int[] dp = new int[MAX+10];
    static int[] dten = {1, 1, 2};
    static int[] done = {-1, 1, 0};

    static int[] parent = new int[MAX+10];
    static Stack<Integer> stack = new Stack<>();

    private static void bfs(int cur){
        boolean[] visited = new boolean[MAX+1];
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(cur);
        visited[cur] = true;
        parent[cur]=cur;

        while (!queue.isEmpty()){
            int number = queue.poll();
            if(number==K){
                while(true){
                    if(parent[number]==N) break;
                    stack.push(parent[number]);
                    number = parent[number];
                }
                return;
            }

            for(int d = 0 ; d < 3; d++){
                int next = number * dten[d] + done[d];
                if(next < 0 || next > MAX) continue;
                if(!visited[next]){
                    parent[next] = number;
                    dp[next] = dp[number]+1;
                    visited[next] = true;
                    queue.offer(next);
                }
            }

        }

    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        if( K < N){
            bw.write((N-K)+"\n");
            for(int i = N; i > K; i--){
                bw.write(i+" ");
            }
            bw.write(K+"\n");
        }else if(N==K){
            bw.write(0+"\n");
            bw.write(N+"\n");
        }else{
            bfs(N);
            bw.write(dp[K]+"\n");
            bw.write(N+" ");
            while(!stack.isEmpty()){
                bw.write(stack.pop()+" ");
            }
            bw.write(K+"\n");
        }

        br.close();
        bw.close();
    }
}

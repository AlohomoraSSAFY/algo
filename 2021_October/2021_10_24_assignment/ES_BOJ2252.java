package com.ssafy.algostudy;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

// 줄 세우기
public class Main2252 {

    static int N, M;
    static ArrayList<Integer>[] indegree;
    static ArrayList<Integer>[] outdegree;
    static ArrayList<Integer> ret = new ArrayList<>();

    private static void topo(){
        int[] in = new int[N+1];
        Queue<Integer> queue = new LinkedList<>();
        for(int i = 1 ; i < N+1; i++){
            ArrayList<Integer> tmp = indegree[i];
            int cnt = tmp.size(); // indegree 개수
            in[i] = cnt;
            if(cnt==0) {
                queue.add(i);
                ret.add(i);
            }
        }

        while (!queue.isEmpty()){
            int cur = queue.poll();
            ArrayList<Integer> tmp = outdegree[cur];
            for(int e : tmp){
                in[e]--;
                if(in[e]==0){
                    queue.offer(e);
                    ret.add(e);
                }
            }
        }

    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        indegree = new ArrayList[N+1]; // indegree
        outdegree = new ArrayList[N+1]; // outdegree
        for(int i = 0 ; i < N+1; i++){
            indegree[i] = new ArrayList<>();
            outdegree[i] = new ArrayList<>();
        }
        for(int i = 0 ; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            indegree[b].add(a); // a가 b 앞에 와야 한다
            outdegree[a].add(b); // a는 b로 간다
        }

        topo();
        for( int e : ret){
            bw.write(e+" ");
        }

        br.close();
        bw.close();
    }
}

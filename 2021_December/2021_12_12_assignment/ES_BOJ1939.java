package com.ssafy.december.week3;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ1939 {

    static int N, M;
    static List<EDGE>[] list;
    static boolean[] check;
    static int ans;

    private static class EDGE{
        int to;
        int w;

        public EDGE(int to, int w) {
            this.to = to;
            this.w = w;
        }
    }

    private static void dfs(int pos, int target, int limit) {
        if(pos == target) {
            ans = pos;
            return;
        }

        for(EDGE e : list[pos]) {
            if(!check[e.to] && limit <= e.w ) {
                check[pos]= true;
                dfs(e.to, target, limit);
            }
        }
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        list = new ArrayList[N+1];
        for(int i = 1; i < N+1; i++) {
            list[i] = new ArrayList<>();
        }

        int left =0;
        int right =0;
        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            list[a].add(new EDGE(b,w));
            list[b].add(new EDGE(a,w));
            right = Math.max(right, w);
        }

        st = new StringTokenizer(br.readLine());
        int from = Integer.parseInt(st.nextToken());
        int to = Integer.parseInt(st.nextToken());

        while(left <= right) {
            int mid = (left+right)/2;
            ans = -1;
            check = new boolean[N+1];
            dfs(from, to, mid);

            if(ans != -1) {
                left = mid+1;
            }else {
                right = mid-1;
            }
        }

        bw.write(right+"\n");
        bw.close();
        br.close();

    }


}


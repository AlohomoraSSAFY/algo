package com.ssafy.algostudy;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class Main1043 {

    static int N, M;
    static int[] known;
    static ArrayList<Integer>[] party;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        known = new int[N+1];
        st = new StringTokenizer(br.readLine());
        int K = Integer.parseInt(st.nextToken());
        for(int i = 0; i < K; i++){
            int tmp = Integer.parseInt(st.nextToken());
            known[tmp] = 1;
        }

        party = new ArrayList[M];
        for(int i = 0; i< M; i++){
            party[i] = new ArrayList<>();
            st = new StringTokenizer(br.readLine());
            int tmp = Integer.parseInt(st.nextToken());
            boolean flag = false;
            for(int j = 0; j < tmp; j++){
                int p = Integer.parseInt(st.nextToken());
                if( known[p] == 1) {
                    flag = true;
                }
            }

            if(flag){
                for(Iterator<Integer> it = party[i].iterator(); it.hasNext(); ){
                    int e = it.next();
                    known[e] = 1;
                }
            }
        }

        for(int i = M-1; i > -1; i--){
            boolean flag = false;
            for(Iterator<Integer> it = party[i].iterator(); it.hasNext(); ){
                int e = it.next();
                if(known[e] == 1){
                    flag = true;
                    break;
                }
            }
            if(flag) {
                for(Iterator<Integer> it = party[i].iterator(); it.hasNext(); ){
                    int e = it.next();
                    known[e] = 1;
                }
            }
        }

        int cnt = 0;
        for(int i = M-1; i > -1; i--){
            boolean flag = false;
            for(Iterator<Integer> it = party[i].iterator(); it.hasNext(); ){
                int e = it.next();
                if(known[e] == 1){
                    flag = true;
                    break;
                }
            }
            if(!flag) cnt++;
        }



        bw.write(cnt+"\n");
        br.close();
        bw.close();
    }
}

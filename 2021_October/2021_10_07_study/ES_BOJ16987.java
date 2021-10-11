package com.ssafy.algostudy;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main16987 {

    static int N;
    static int[] S;
    static int[] W;
    static int MAX = 0;
    private static void dfs(int pos, int[] curS, int cnt, int level){
//        System.out.println(pos+" 개수 "+cnt+"   깊이"+level);
//        for(int i = 0 ; i < N; i++){
//            System.out.print(curS[i]+" ");
//        }
//        System.out.println();
//        System.out.println();

        if( pos == N || cnt == N){
            MAX = Math.max(MAX, cnt);
//            System.out.println("RETURN");
            return;
        }

        if(curS[pos] < 1){
            dfs(pos+1, curS, cnt, level+1);
            return;
        }

        boolean flag = false;
        for(int i = 0; i < N; i++) {
            if (i != pos && curS[i] > 0) { // 0 초과인 경우 선택 가능
                int desk = curS[i] - W[pos];
                int hand = curS[pos] - W[i];
                curS[i] = desk;
                curS[pos] = hand;
                int broken = 0;
                if(desk < 1){
                    flag = true;
                    broken++;
                }
                if(hand < 1) {
                    flag = true;
                    broken++;
                }
                dfs(pos+1, curS, cnt+broken, level+1);
//                System.out.println("AFTER DFS "+pos+" ");
//                for(int j = 0 ; j < N; j++){
//                    System.out.print(curS[j]+" ");
//                }
//                System.out.println();
//                System.out.println("***********");
                curS[i] = desk + W[pos];
                curS[pos] = hand + W[i];

            }
        }


//        System.out.println("OUT FOR "+pos+" ");
//        for(int j = 0 ; j < N; j++){
//            System.out.print(curS[j]+" ");
//        }
//        System.out.println();
//        System.out.println("***********");
        if(!flag){
            dfs(pos+1, curS, cnt, level+1);
        }

//        System.out.println("AFTER FOR "+pos+" ");
//        for(int j = 0 ; j < N; j++){
//            System.out.print(curS[j]+" ");
//        }
//        System.out.println();
//        System.out.println("***********");

//                for(int i = 0; i < N; i++){
//            if( i != pos && curS[i] > 0){ // 0 이상인 경우 선택 가능
//                int desk = curS[i] - W[pos];
//                int hand = curS[pos] - W[i];
//
//                if( desk > 0 && hand > 0){ // 둘다 못깬 경우
////                    dfs(pos+1, curS, cnt, level+1); // 다음 계란으로 넘어간다
//                    continue;
//                }
//                if(desk > 0 && hand < 1){ // 손에 든 계란이 깨진 경우
////                    curS[i] = desk;
////                    curS[pos] = hand; // 깨주고
////                    dfs(pos+1, curS, cnt+1, level+1); // 다음 계란 으로
////                    curS[i] = desk + W[pos];
////                    curS[pos] = hand + W[i];
////                    dfs(pos+1, curS, cnt, level+1);
//                    continue;
//                }
//                if(desk < 1 && hand > 0){ // 바닥 계란이 깨진 경우
//                    curS[i] = desk;
//                    curS[pos] = hand; // 깨주고
//                    if(i!=pos+1){
//                        dfs(pos+1, curS, cnt+1, level+1);
//                    }else{
//                        dfs(pos+2, curS, cnt+1, level+1);
//                    }
//                    curS[i] = desk + W[pos];
//                    curS[pos] = hand + W[i];
//                    continue;
//                }
//                if(desk < 1 && hand < 1){ // 둘 다 깨진 경우
////                    curS[i] = desk;
////                    curS[pos] = hand; // 깨주고
////                    if(i!=pos+1){
////                        dfs(pos+1, curS, cnt+2, level+1);
////                    }else{
////                        dfs(pos+2, curS, cnt+2, level+1);
////                    }
////                    curS[i] = desk + W[pos];
////                    curS[pos] = hand + W[i];
////                    dfs(pos+1, curS, cnt, level+1);
//                    continue;
//                }
//
//            }
//        }
//
//
//
//        if(pos==N-1){
//            MAX = Math.max(MAX, cnt);
//            return;
//        }
//



    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        S = new int[N];
        W = new int[N];
        for(int i = 0 ; i < N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            S[i] = Integer.parseInt(st.nextToken());
            W[i] = Integer.parseInt(st.nextToken());
        }

        dfs(0, S, 0, 0);
        bw.write(MAX+"\n");
        br.close();
        bw.close();
    }
}

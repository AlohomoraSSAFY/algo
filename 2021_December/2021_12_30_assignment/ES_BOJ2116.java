package com.ssafy.jan.week1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ2116 {

    static int N;
    static ArrayList<DICE> list;
    static int ans = 0;

    private static class DICE{
        int[] num;

        public DICE(int[] num) {
            this.num = num;
        }

    }

    private static void dfs(int idx, int prev, int sum){ // idx가 현재 주사위 번호
        if(idx == N){
            ans = Math.max(ans, sum);
            return;
        }

        DICE cur = list.get(idx);
        int  select = -1;
        for (int i = 0; i < 6; i++){
            if(prev == cur.num[i]){
                select = i;
                break;
            }
        }

        int max = -1;
        for(int j = 0; j < 6; j++){
            if(j != select && j != 5-select){
                max = Math.max(max, cur.num[j]);
            }
        }

//        System.out.println(max);
        dfs(idx+1, cur.num[5-select] , sum+max);

    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        list = new ArrayList<>();
        for(int i = 0 ; i < N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int[] tmp = new int[6];

            int a = Integer.parseInt(st.nextToken());
            tmp[0] = a;
            int b = Integer.parseInt(st.nextToken());
            tmp[1] = b;
            int c = Integer.parseInt(st.nextToken());
            tmp[2] = c;
            int d = Integer.parseInt(st.nextToken());
            tmp[4] = d;
            int e = Integer.parseInt(st.nextToken());
            tmp[3] = e;
            int f = Integer.parseInt(st.nextToken());
            tmp[5] = f;
            list.add(new DICE(tmp));
        }

        DICE first = list.get(0);
        for (int i = 0; i < 6; i++){
            int max = -1;
            for(int j = 0; j < 6; j++){
                if(j!=i && j != 5-i){
                    max = Math.max(max, first.num[j]);
                }
            }
//            System.out.println(max);
            dfs(1, first.num[5-i] , max);
//            System.out.println();
        }

        bw.write(ans+"\n");
        br.close();
        bw.close();
    }
}

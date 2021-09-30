package com.ssafy.algostudy.dfs;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main1062Comb {

    static int N, K;
    static int[] arr;
    static int max = 0;
    static int[] selected;

    private static void comb(int cnt, int start){
        if(cnt==K){
//            System.out.println("SELECTED "+ Integer.toBinaryString(selected[K-1]));
            int reading = 0;
            int number = 0;
            for(int i = 0 ; i < K; i++){
                number |= 1 << selected[i];
            }

            for(int i = 0 ; i < N; i++){
                if( (number & arr[i]) == arr[i]){
                    reading++;
                }
            }
            if(reading > max) max = reading;
            return;
        }

        for(int i = start; i < 27; i++){
            selected[cnt] = i;
            comb(cnt+1, i+1);
        }

    }


    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());


        arr = new int[N];
        selected = new int[K];
        for(int i = 0 ; i < N; i++){
            String tmp = br.readLine();
            int cur = 1;
            for(int j = 0 ; j < tmp.length(); j++){
                cur |= 1 << (tmp.charAt(j)-'a');
            }
            arr[i] = cur;
        }

        if(K<5){
            bw.write(0+"\n");
        }else {
            comb(0, 0);
            bw.write(max+"\n");
        }

        br.close();
        bw.close();
    }
}

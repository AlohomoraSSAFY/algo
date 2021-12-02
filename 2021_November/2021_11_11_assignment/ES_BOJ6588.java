package com.ssafy.algostudy;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main6588 {

    static int N;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        boolean[] number = new boolean[10000001];
        number[1] = true; //
        for(int i = 2; i <= 1000; i++){
            if(number[i]) continue;

            for(int j = 2*i; j < 10000001; j+= i){
                if(!number[j]) number[j] = true;
            }
        }


        while(true){
            String str = br.readLine();
            if(str.equals("0")) break;
            int n = Integer.parseInt(str);
            boolean flag = false;
            int a = 0, b= 0;
            for(int i = 1; i <= n/2; i++){
                a = i; b = n - i;
                if(!number[a] && !number[b]) {
                    flag = true;
                    break;
                }
            }

            if(flag){
                bw.write(String.format("%d = %d + %d\n", n ,a, b));
            }else{
                bw.write("Goldbach's conjecture is wrong.\n");
            }

        }
        br.close();
        bw.close();
    }
}

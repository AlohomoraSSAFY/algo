package com.ssafy.algostudy;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main4811Retry {

    static int N;
    static long[][] dp;

    // N = 3
    // 4개짜리 문자열은 이미 W >=  H
    // WHWH W   H
    // WWHH W   H

    // WHWW H   H
    // WWWH H   H
    // WWHW H   H
    // -> W가 3개인 것과 H가 2개인 겅의 문자열
    // W가 2개 H가 2개 W가 3개 H1개


    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        dp = new long[31][31];
        for(int h = 0 ; h < 31; h++){
            for(int w = 0; w < 31; w++){
                if(h > w) continue;
                if(h == 0){
                    dp[w][0] = 1;
                } else { // 1 1
                    // dp[0][1] + dp[1][0]
                    // W가 2이고 H 1일때
                    // W가 1개 H 1개 일때 + W
                    // W가 2개이고 H가 0개일때 + H
                    dp[w][h] = dp[w-1][h] + dp[w][h-1];
                }
            }
        }

        while(true){
            String input = br.readLine();
            if(input.equals("0")) break;
            int N = Integer.parseInt(input);
            bw.write(dp[N][N]+"\n");
        }


        br.close();
        bw.close();
    }
}

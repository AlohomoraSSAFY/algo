package com.ssafy.jan.week2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ20437 {

    static int T, K;
    static String W;
    static ArrayList<Integer>[] alpha;
    static int min = 10001, max = -1;

    private static void check(int idx){
        int left = 0, right = 0;
        while (right < alpha[idx].size()){
            if( right - left == K -1){
                int val = alpha[idx].get(right) - alpha[idx].get(left) +1;
                min = Math.min(min, val);
                max = Math.max(max, val);
            }
            right++;
            while (right - left > K-1) left++;
        }
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        T = Integer.parseInt(br.readLine());
        for(int cs = 0; cs < T; cs++){
            W = br.readLine();
            K = Integer.parseInt(br.readLine());

            alpha = new ArrayList[26];
            for (int i = 0 ; i < 26; i++){
                alpha[i] = new ArrayList<>();
            }
            for(int i = 0; i < W.length(); i++ ){
                alpha[W.charAt(i) - 'a'].add(i);
            }

            min = 10001; max = -1;
            for(int i = 0 ; i < 26; i++){
                if(alpha[i].size() > K-1){
                    check(i);
                }
            }

            if(min == 10001 || max ==-1) {
                bw.write("-1\n");
            } else {
                bw.write(String.format("%d %d\n", min, max));
            }

        }

        br.close();
        bw.close();
    }
}

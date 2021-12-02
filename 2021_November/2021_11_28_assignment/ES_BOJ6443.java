package com.ssafy.december.week1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main6443 {

    static int N;
    static char[] selected;

    private static boolean nextperm(int len){
        int i = len - 1;
        while(i > 0 && selected[i-1] >= selected[i]){
            --i;
        }

        if(i==0){
            return false;
        }

        int j = len - 1;
        while(selected[i-1] >= selected[j]){
            --j;
        }

        char tmp = selected[i-1];
        selected[i-1] = selected[j];
        selected[j] = tmp;

        int k = len -1;
        while(i < k){
            tmp = selected[i];
            selected[i] = selected[k];
            selected[k] = tmp;
            ++i;
            --k;
        }
        return true;
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        for (int i = 0 ; i < N; i++){
            char[] str = br.readLine().toCharArray();
            selected = str;
            Arrays.sort(selected);
            do {
                for(int idx = 0; idx < str.length; idx++){
                    bw.write(str[idx]);
                }
                bw.write("\n");
            }while (nextperm(str.length));

        }
        br.close();
        bw.close();
    }
}

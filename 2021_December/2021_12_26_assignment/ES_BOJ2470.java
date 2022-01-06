package com.ssafy.jan.week1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class BOJ2470 {

    static int N;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        ArrayList<Integer> list = new ArrayList<>();
        StringTokenizer st = new StringTokenizer(br.readLine());

        for(int i = 0; i < N; i++){
            list.add(Integer.parseInt(st.nextToken()));
        }

        Collections.sort(list);

        int s = 0, e = N-1;
        int diff = 2000000000;
        int a = -1, b = -1;

        while (s < e) {
            int tmp = list.get(s) + list.get(e);

            if (Math.abs(tmp) < diff) {
                a = list.get(s);
                b = list.get(e);
                diff = Math.abs(tmp);
            }

            if (tmp > 0) {
                e--;
            } else {
                s++;
            }
        }

        bw.write(a+" "+b+"\n");
        br.close();
        bw.close();
    }
}

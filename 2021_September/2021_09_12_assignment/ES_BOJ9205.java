package com.ssafy.algostudy.dfs;

import java.io.*;
import java.util.StringTokenizer;

public class Main9205 {

    static int T, N;
    static int[][] smap;
    static int[][] diff;
    static boolean[][] visited;
    static String answer="";

    private static void nextPosition(int cur, int next){
        if( next == N+1 ){
            if(diff[cur][next] != -1){
                answer="happy\n";
                return;
            }
        }

        for(int i = 1 ; i < N + 2; i++){
            if(!visited[next][i]){
                visited[next][i] = true;
                if(diff[next][i] != -1){
                    nextPosition(next, i);
                }
            }
        }

    }

    public static void main(String[] args) throws Exception{
        System.setIn(new FileInputStream("./res/9205.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        T = Integer.parseInt(br.readLine());
        for(int cs = 1; cs < T+1; cs++){
            answer = "";
            N = Integer.parseInt(br.readLine());
            // 집, 페스티벌, 편의점
            smap = new int[2][N+2];
            diff = new int[N+2][N+2];

            for(int i = 0 ; i < N + 2; i++){
                StringTokenizer st = new StringTokenizer(br.readLine());
                smap[0][i] = Integer.parseInt(st.nextToken());
                smap[1][i] = Integer.parseInt(st.nextToken());
            }

            // 서로 거리를 담은 diff
            for(int i = 0 ; i < N + 2; i++){
                for(int j = 0 ; j < N + 2; j++){
                    int len = Math.abs(smap[0][i]-smap[0][j])+Math.abs(smap[1][i]-smap[1][j]);
                    if( len <= 50*20){
                        diff[i][j] = 20 - (len/50);
                    }else {
                        diff[i][j] = -1;
                    }
                }
            }

            for(int i = 1; i < N + 2; i++){
                visited= new boolean[N+2][N+2];
                if(diff[0][i]!=-1){
                    nextPosition(0, i);
                }
            }

            if(!answer.equals("happy\n")){
                answer = "sad\n";
            }
            bw.write(answer);
        }

        bw.close();
        br.close();

    }
}

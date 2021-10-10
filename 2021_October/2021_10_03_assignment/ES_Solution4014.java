package com.ssafy.hw.expert;

import java.io.*;
import java.util.Stack;
import java.util.StringTokenizer;

public class Solution4014 {

    static int T;
    static int N, X;
    static int[][] map;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("./swres/input4014.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        T = Integer.parseInt(br.readLine());
//        T = 1;
        for(int cs = 1; cs < T + 1; cs++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            X = Integer.parseInt(st.nextToken());
            map = new int[N][N];

            for(int i = 0 ; i < N; i++){
                st = new StringTokenizer(br.readLine());
                for(int j = 0 ; j < N; j++){
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

//            if(cs!=16 ) continue;

            int count = 2*N;
            // 행 먼저
            for(int i = 0 ; i < N; i++){
                boolean[] build = new boolean[N];
                ROW:
                for(int j = 0 ; j < N-1; j++){
//                    System.out.println(i+" "+j);
                    if(map[i][j] != map[i][j+1]){ // 다른 경우
                        if (map[i][j] +1 == map[i][j+1]){ // 열이 작은 쪽이 작은 경우
                            for (int k = 0 ; k < X; k++){
                                if( j - k < 0){
                                    count--;
                                    break ROW;
                                }
                                if(build[j-k]){ // 이미 다리를 건설한 경우
                                    count--;
                                    break ROW;
                                }
                                if( map[i][j-k] != map[i][j] ){ // 높이가 다른 경우
                                    count--;
                                    break ROW;
                                }
                            }
                            for (int k = 0 ; k < X; k++){
                               build[j-k] = true; // 경사로 건설
                            }
                        }else if(map[i][j] == map[i][j+1] +1){ // 열이 큰 쪽이 작은 경우
                            for (int k = 0 ; k < X; k++){
                                if( j + 1 + k > N-1){
                                    count--;
                                    break ROW;
                                }
//                                System.out.println("TEST "+map[i][j+k]+" "+map[i][j+1]);
                                if(build[j+1+k]){ // 이미 다리를 건설한 경우
                                    count--;
                                    break ROW;
                                }
                                if( map[i][j+1+k] != map[i][j+1] ){ // 높이가 다른 경우
                                    count--;
                                    break ROW;
                                }
                            }
                            for (int k = 0 ; k < X; k++){
                                build[j+1+k] = true; // 경사로 건설
                            }
                        }else {
                            count--;
                            break ROW;
                        }
                    }
//                    System.out.println(i+" "+j);

                }
            }

            // 열 먼저
            for(int j = 0 ; j < N; j++){
                boolean[] build = new boolean[N];
                COL:
                for(int i = 0 ; i < N-1; i++){
//                    System.out.println(i+" "+j);
                    if(map[i][j] != map[i+1][j]){ // 다른 경우
//                        for(int m = 0 ; m < N; m++){
//                            System.out.printf(build[m]+" ");
//                        }
//                        System.out.println();
                        if (map[i][j] +1 == map[i+1][j]){ // 열이 작은 쪽이 작은 경우
                            for (int k = 0 ; k < X; k++){
                                if( i - k < 0){
                                    count--;
                                    break COL;
                                }
                                if(build[i-k]){ // 이미 다리를 건설한 경우
                                    count--;
                                    break COL;
                                }
                                if( map[i-k][j] != map[i][j] ){ // 높이가 다른 경우
                                    count--;
                                    break COL;
                                }
                            }
                            for (int k = 0 ; k < X; k++){
                                build[i-k] = true; // 경사로 건설
                            }
//                            System.out.println(j);

                        }else if(map[i][j] == map[i+1][j] +1){ // 열이 큰 쪽이 작은 경우
                            for (int k = 0 ; k < X ; k++){
                                if( i + 1+ k > N -1){
                                    count--;
                                    break COL;
                                }
                                if(build[i+1+k]){ // 이미 다리를 건설한 경우
                                    count--;
                                    break COL;
                                }
                                if( map[i+1+k][j] != map[i+1][j] ){ // 높이가 다른 경우
                                    count--;
                                    break COL;
                                }
                            }
//                            System.out.println(j);
                            for (int k = 0 ; k < X; k++){
                                build[i+1+k] = true; // 경사로 건설
                            }
                        }else {
                            count--;
                            break COL;
                        }
                    }
//                    System.out.println(i+" "+j);
                }
            }


            bw.write("#"+cs+" "+count+"\n");
        }
        bw.close();
        br.close();
    }
}

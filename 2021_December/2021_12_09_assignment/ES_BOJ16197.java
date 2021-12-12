package com.ssafy.december.week2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BOJ16197 {

    static int N, M, ans;
    static char[][] map;
    static boolean[][][] visited;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        ans = Integer.MAX_VALUE;
        map = new char[N][M];

        int cnt = 0;
        int start[][] = new int[2][2];

        for(int i = 0 ; i < N ; ++i) {
            char[] tmp = br.readLine().toCharArray();
            for(int j = 0 ; j < M ; ++j) {
                map[i][j] = tmp[j];
                if(tmp[j] == 'o') {
                    start[cnt][0] = i;
                    start[cnt++][1] = j;
                }
            }
        }

        dfs(0, start[0][0],  start[0][1],  start[1][0],  start[1][1]);

        if(ans == Integer.MAX_VALUE) ans = -1;

        bw.write(ans +"\n");
        br.close();
        bw.close();
    }
    private static void dfs(int depth, int ar, int ac, int br, int bc) {
//        System.out.println("dfs");
        if( depth >= ans || depth > 9) {
            return;
        }
//        System.out.println("for");
        for(int d = 0 ; d < 4 ; d++) {
            boolean aflag = false, bflag = false;
            // 첫 번째 동전 이동
            int anr = ar + dr[d];
            int anc = ac + dc[d];
            // 두 번째 동전 이동
            int bnr = br + dr[d];
            int bnc = bc + dc[d];

//            System.out.println(anr+" "+anc+" "+bnr+" "+bnc);
            if(anr > N-1 || anr < 0 || anc > M -1 || anc < 0) {
                aflag = true;
            }
            if(bnr > N -1 || bnr < 0 || bnc > M -1 || bnc < 0) {
                bflag = true;
            }

//            System.out.println(aflag +" "+bflag);
            // 둘 다 떨어진 경우
            if(aflag && bflag) continue;
            // 겹친 경우
            if ((anr == bnr) && (anc == bnc)) continue;

            // 하나 떨어진 경우
            if(aflag || bflag) {
                ans = Math.min( ans, depth+1);
                return;
            }

            // 벽인 경우
            if(!aflag && map[anr][anc] == '#') {
                anr = ar;
                anc = ac;
            }
            if(!bflag && map[bnr][bnc] == '#') {
                bnr = br;
                bnc = bc;
            }

            dfs(depth + 1, anr, anc, bnr, bnc);
        }
//        System.out.println("end");
    }
}

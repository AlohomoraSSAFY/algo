package com.ssafy.algostudy.implementation;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main14891 {
    static int K;
    static List<List<Integer>> gear= new ArrayList<>();

    private static boolean checkLeft(int base){
        boolean rotate = false;
        List<Integer> one = gear.get(base);
        List<Integer> left = gear.get(base-1);

        if(one.get(6)!=left.get(2)){
            rotate = true;
        }

        return rotate;
    }

    private static boolean checkRight(int base){
        boolean rotate = false;
        List<Integer> one = gear.get(base);
        List<Integer> right = gear.get(base+1);

        if(one.get(2)!=right.get(6)){
            rotate = true;
        }
        return rotate;
    }

    private static void rotateClock(int dir, int idx){
        if(dir == 1){
            gear.get(idx).add(0, gear.get(idx).get(7));
            gear.get(idx).remove(8);
        }
        else {
            gear.get(idx).add( gear.get(idx).get(0));
            gear.get(idx).remove(0);
        }
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("./res/14891_1.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        for(int i = 0 ; i < 4; i++){
            List<Integer> tmp = new ArrayList<>();
            String[] str = br.readLine().split("");
            for(int j = 0 ; j < 8 ; j++){
                tmp.add(Integer.parseInt(str[j]));
            }
            gear.add(tmp);
        }

        K = Integer.parseInt(br.readLine());
        for(int i = 0 ; i < K ; i++){
            String[] str = br.readLine().split(" ");
            int dir = Integer.parseInt(str[1]);
            int cdir = (dir==1) ? -1 : 1;
            boolean flag;
            System.out.println(str[0]+" "+str[1]);
            switch (Integer.parseInt(str[0])-1){
                case 0: // 1번째 톱니바퀴
                    if(checkRight(0)){
                        if(checkRight(1)){
                            if(checkRight(2)){
                                rotateClock(cdir, 3);
                            }
                            rotateClock(dir, 2);
                        }
                        rotateClock(cdir, 1);
                    }
                    rotateClock(dir, 0);
                    break;
                case 1:
                    flag = false;
                    if( (flag = checkLeft(1)) ){
                        rotateClock(cdir, 0);
                    }
                    if(checkRight(1)){
                        if(checkRight(2)){
                            rotateClock(dir, 3);
                        }
                        rotateClock(cdir, 2);
                        if(!flag) flag = true;
                    }
                    rotateClock(dir, 1);
                    break;
                case 2:
                    flag = false;
                    if( (flag = checkRight(2)) ){
                        rotateClock(cdir, 3);
                    }
                    if(checkLeft(2)){
                        if(checkLeft(1)){
                            rotateClock(dir, 0);
                        }
                        rotateClock(cdir, 1);
                        if(!flag) flag = true;
                    }
                    rotateClock(dir, 2);
                    break;
                case 3:
                    if(checkLeft(3)){
                        if(checkLeft(2)){
                            if(checkLeft(1)){
                                rotateClock(cdir, 0);
                            }
                            rotateClock(dir, 1);
                        }
                        rotateClock(cdir, 2);
                    }
                    rotateClock(dir, 3);
                    break;
            }
        }

        int score = 0;
        for(int i = 0 ; i < 4; i++){
            if(gear.get(i).get(0)==1){
                score += (Math.pow(2,i));
            }
        }

        bw.write(score+"\n");
        bw.close();
        br.close();
    }
}

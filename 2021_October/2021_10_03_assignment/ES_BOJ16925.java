package com.ssafy.algostudy;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class Main16925Retry {

    static int N;
    static HashMap<Integer, String> characters = new HashMap<>();

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        for(int i = 0 ; i < 2*N -2; i++){
            String tmp = br.readLine();
            characters.put(i, tmp);
        }

        List<Integer> keyPre = new ArrayList<>(characters.keySet());
        Collections.sort(keyPre, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return characters.get(o2).length() - (characters.get(o1).length());
            }
        });

        String first = characters.get(keyPre.get(0));
        String second = characters.get(keyPre.get(1));
        String end = characters.get(keyPre.get(2*N-3));
        String secondEnd = characters.get(keyPre.get(2*N-4));

        boolean flag = true;
        String pos = first+end;
        HashSet<String> set = new HashSet<>();
        StringBuilder sb = new StringBuilder();
        for(int i = 0 ; i < 2*N-2; i++){
            String tmp = characters.get(i);
            if(pos.startsWith(tmp)){
                if(!set.contains(tmp)){
                    sb.append("P");
                    set.add(characters.get(i));
                }else{
                    sb.append("S");
                }
            }else if(pos.endsWith(tmp)) {
                sb.append("S");
            }else {
                flag = false;
                break;
            }
        }

        if(flag){
            bw.write(pos+"\n");
            bw.write(sb.toString());
            bw.close();
            return;
        }

        flag = true;
        pos = first+secondEnd;
        set = new HashSet<>();
        sb = new StringBuilder();
        for(int i = 0 ; i < 2*N-2; i++){
            String tmp = characters.get(i);
            if(pos.startsWith(tmp)){
                if(!set.contains(tmp)){
                    sb.append("P");
                    set.add(characters.get(i));
                }else{
                    sb.append("S");
                }
            }else if(pos.endsWith(tmp)) {
                sb.append("S");
            }else {
                flag = false;
                break;
            }
        }

        if(flag){
            bw.write(pos+"\n");
            bw.write(sb.toString());
            bw.close();
            return;
        }

        flag = true;
        pos = second+end;
        set = new HashSet<>();
        sb = new StringBuilder();
        for(int i = 0 ; i < 2*N-2; i++){
            String tmp = characters.get(i);
            if(pos.startsWith(tmp)){
                if(!set.contains(tmp)){
                    sb.append("P");
                    set.add(characters.get(i));
                }else{
                    sb.append("S");
                }
            }else if(pos.endsWith(tmp)) {
                sb.append("S");
            }else {
                flag = false;
                break;
            }
        }

        if(flag){
            bw.write(pos+"\n");
            bw.write(sb.toString());
            bw.close();
            return;
        }

        flag = true;
        pos = second+secondEnd;
        set = new HashSet<>();
        sb = new StringBuilder();
        for(int i = 0 ; i < 2*N-2; i++){
            String tmp = characters.get(i);
            if(pos.startsWith(tmp)){
                if(!set.contains(tmp)){
                    sb.append("P");
                    set.add(characters.get(i));
                }else{
                    sb.append("S");
                }
            }else if(pos.endsWith(tmp)) {
                sb.append("S");
            }else {
                flag = false;
                break;
            }
        }

        bw.write(pos+"\n");
        bw.write(sb.toString());

        br.close();
        bw.close();
    }
}

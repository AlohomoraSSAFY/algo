package com.baekjoon.problem44;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class HN_베스트앨범 {
	    static Map<String, Integer> gcnt;
	    static Map<Integer, PriorityQueue<int []>> hmap;
	    public int[] solution(String[] genres, int[] plays) {
	        int len = genres.length;
	        gcnt = new HashMap<>();
	        hmap = new HashMap<>();
	        int no = 0, idx = 0;
	        int cnt[] = new int[100];
	        PriorityQueue<int []> pq;
	        for(int l = 0; l < len; l++){
	            if(!gcnt.containsKey(genres[l])){
	                gcnt.put(genres[l], idx);
	                cnt[idx] = plays[l]; // 재생수
	                pq = new PriorityQueue<>(new Comparator<int []>(){
	                    public int compare(int[] i, int[] j){
	                        if(i[1] == j[1]){
	                            return Integer.compare(i[0], j[0]);
	                        }
	                        return Integer.compare(j[1], i[1]);
	                    }
	                });
	                pq.add(new int[]{l, plays[l]});
	                hmap.put(idx++, pq);
	            }else{
	                no = gcnt.get(genres[l]);
	                pq = hmap.get(no);
	                pq.add(new int[]{l, plays[l]});
	                cnt[no] += plays[l];
	            }
	        }
	        
	        PriorityQueue<int []> pq2 = new PriorityQueue<>(new Comparator<int []>(){
	                    public int compare(int[] i, int[] j){
	                        if(i[0] == j[0]){
	                            return Integer.compare(i[1], j[1]);
	                        }
	                        return Integer.compare(j[0], i[0]);
	                    }
	        });
	        for(int i = 0; i < idx; i++){
	            pq2.add(new int[]{cnt[i], i});
	        }
	        
	        idx = 0;
	        List<Integer> arr = new ArrayList<>();
	        while(!pq2.isEmpty()){
	            no = pq2.poll()[1];
	            pq = hmap.get(no);
	            int temp = pq.size();
	            if(temp == 1){
	                arr.add(pq.poll()[0]);
	                continue;
	            }
	            int size = 2;
	            while(size-- > 0){
	                int pcnt = pq.poll()[0];
	                arr.add(pcnt);
	            }
	        }
	        int answer[] = new int[arr.size()];
	        idx = 0;
	        for(Integer i : arr){
	            answer[idx++] = i;
	        }
	        return answer;
	    }
}

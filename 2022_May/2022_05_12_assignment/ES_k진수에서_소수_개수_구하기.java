package net.acmicpc.may.week4;

import java.util.*;

public class ES_k진수에서_소수_개수_구하기 {

	    private boolean isPrime(long num){
	        if(num == 1)
	            return false;
	        for(int i = 2; i <= Math.sqrt(num); i++){
	            if(num %i ==0){
	                return false;
	            }
	        }
	        return true;
	    }
	    
	    public int solution(int n, int k) {
	        int answer = 0;
	        
	        // k진수
	        StringBuilder sb = new StringBuilder();
	        while(n > 0) {
	            sb.append(n%k);
	            n = n / k;
	        }
	        String num = sb.reverse().toString();
	        
	        // 소수
	        String[] list = num.split("0");
	        for(String str : list){
	            //System.out.println(str);
	            if(!str.equals("") && isPrime(Long.valueOf(str))){
	                answer++;
	            }
	        }
	        
	        return answer;
	    }
	}
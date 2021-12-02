package study1202;

import java.util.*;

public class SY_소수_찾기 {

    static int len;
    static int[] array;
    static boolean[] selected;
    static Set<Integer> set;
    static int count;
    static int max;
    static boolean[] isPrime;
    
    public int solution(String numbers) {
        len = numbers.length();
        array = new int[len];
        selected = new boolean[len];
        for (int i = 0; i < len; i++) {
            array[i] = numbers.charAt(i) - '0';
        }
        
        set = new HashSet<>();
        for (int i = 1; i < len+1; i++) {
            count = i;
            permutation(0, 0);
        }
        
        int answer = 0;
        isPrime = new boolean[max+1];
        for (int i = 2; i <= max; i++) {
            boolean flag = true;
            for (int j = 2; j < i; j++) {
                if (j * j > i) break;
                if (i % j == 0) {
                    flag = false;
                    break;
                }
            }
            
            if (flag) isPrime[i] = true;
        }
        
        for (Integer i : set) {
            if (isPrime[i]) {
                answer++;
            }
        }
        
        return answer;
    }
    
    public static void permutation(int cnt, int num) {
        if (cnt == count) {
            set.add(num);
            max = Math.max(max, num);
            return;
        }
        
        for (int i = 0; i < len; i++) {
            if (selected[i]) continue;
            
            selected[i] = true;
            permutation(cnt + 1, num * 10 + array[i]);
            selected[i] = false;
        }
    }
}

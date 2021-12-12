package study1212;

import java.util.*;

public class SY_가장_큰_수 {
    public String solution(int[] numbers) {
        String[] nArray = new String[numbers.length];
        for (int i = 0; i < numbers.length; i++) {
            nArray[i] = Integer.toString(numbers[i]);
        }
        
        Arrays.sort(nArray, new Comparator<String>() {
           @Override
            public int compare(String s1, String s2) {
                return (s1 + s2).compareTo(s2 + s1);
            }
        });
        
        if (nArray[nArray.length - 1].equals("0")) return "0";
        
        String answer = "";
        for (int i = nArray.length - 1; i >= 0; i--) {
            answer += nArray[i];
        }
        return answer;
    }
}

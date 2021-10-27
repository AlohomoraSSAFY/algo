package study1028;

import java.util.*;

public class SY_K번째수 {
	public int[] solution(int[] array, int[][] commands) {
        int[] answer = new int[commands.length];
        for (int i = 0; i < commands.length; i++) {
            int a = commands[i][0];
            int b = commands[i][1];
            int c = commands[i][2];
            
            int[] temp = new int[b-a+1];
            int t = 0;
            for (int j = a-1; j < b; j++) {
                temp[t++] = array[j];
            }
            
            Arrays.sort(temp);
            answer[i] = temp[c-1];
        }
        return answer;
    }
}

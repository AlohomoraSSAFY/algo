import java.util.*;

class Solution {
    public int solution(int[][] sizes) {
        int max = 0;
        for(int i = 0; i < sizes.length; i++){
            for(int j = 0; j < sizes[0].length; j++){
                max = Math.max(max, sizes[i][j]);
            }
        }
        
        int min = 0;
        int[] line = new int[sizes.length];
        for(int i = 0; i < sizes.length; i++){
            // int a = Math.max(sizes[i][0], sizes[i][1]);
            int b = Math.min(sizes[i][0], sizes[i][1]);
            // line[i] = b;
            min = Math.max(min, b);
        }
        
        return max*min;
    }
}
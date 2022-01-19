package study0120;

import java.util.*;

public class SY_단속카메라 {
    public int solution(int[][] routes) {
        Arrays.sort(routes, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1] - o2[1];
            }
        });
        
        int answer = 1;
        int pos = routes[0][1];
        for (int i = 1; i < routes.length; i++) {
            if (routes[i][0] <= pos) continue;
            answer++;
            pos = routes[i][1];
        }
        return answer;
    }
}

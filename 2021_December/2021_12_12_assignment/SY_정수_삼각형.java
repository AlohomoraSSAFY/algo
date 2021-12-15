package study1216;

public class SY_정수_삼각형 {
    public int solution(int[][] triangle) {
        for (int i = 1; i < triangle.length; i++) {
            for (int j = 0; j < triangle[i].length; j++) {
                if (j == 0) {
                    triangle[i][j] += triangle[i-1][j];
                } else if (j == triangle[i].length - 1) {
                    triangle[i][j] += triangle[i-1][triangle[i-1].length-1];
                } else {
                    triangle[i][j] += Math.max(triangle[i-1][j-1], triangle[i-1][j]);
                }
            }
        }
        
        int answer = 0;
        for (int i = 0; i < triangle[triangle.length-1].length; i++) {
            int temp = triangle[triangle.length-1][i];
            if (answer < temp) answer = temp;
        }
        return answer;
    }
}

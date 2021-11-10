package study1031;

public class SY_모의고사 {
	public int[] solution(int[] answers) {
        int[] one = {1, 2, 3, 4, 5};
        int[] two = {2, 1, 2, 3, 2, 4, 2, 5};
        int[] three = {3, 3, 1, 1, 2, 2, 4, 4, 5, 5};
        
        int[] array = new int[3];
        for (int i = 0; i < answers.length; i++) {
            if (answers[i] == one[i%5]) array[0]++;
            if (answers[i] == two[i%8]) array[1]++;
            if (answers[i] == three[i%10]) array[2]++;
        }
        
        int max = -1;
        int cnt = 0;
        for (int i = 0; i < 3; i++) {
            if (array[i] > max) {
                max = array[i];
                cnt = 1;
            } else if (array[i] == max) {
                cnt++;
            }
        }
        
        int[] answer = new int[cnt];
        int temp = 0;
        for (int i = 0; i < 3; i++) {
            if (array[i] == max) answer[temp++] = i+1;
        }
        return answer;
    }
}

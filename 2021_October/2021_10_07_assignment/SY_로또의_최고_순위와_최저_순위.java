package study1011;

public class SY_로또의_최고_순위와_최저_순위 {
	public int[] solution(int[] lottos, int[] win_nums) {
        int[] answer = new int[2];
        int correct = 0;
        int cntZero = 0;
        for (int i=0; i<6; i++) {
            if (lottos[i] == 0) {
                cntZero++;
                continue;
            } else {
                for (int j=0; j<6; j++) {
                    if (lottos[i] == win_nums[j]) {
                        correct++;
                        break;
                    }
                }
            }
        }
        
        answer[0] = cal(correct + cntZero);
        answer[1] = cal(correct);
        
        return answer;
    }
    
    public static int cal(int cnt) {
        if (cnt == 6) {
            return 1;
        } else if (cnt == 5) {
            return 2;
        } else if (cnt == 4) {
            return 3;
        } else if (cnt == 3) {
            return 4;
        } else if (cnt == 2) {
            return 5;
        } else {
            return 6;
        }
    }

}

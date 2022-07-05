package study0707;

public class SY_양궁대회 {
    
    int[] apeachArr;
    int[] ryanArr;
    int[] answer;
    int max = 0;
    
    public int[] solution(int n, int[] info) {
        apeachArr = info;
        ryanArr = new int[11];
        answer = new int[11];
        
        recursion(0, n);
        
        if (max == 0) {
            answer = new int[1];
            answer[0] = -1;
        }
        
        return answer;
    }
    
    private void recursion(int cnt, int arrow) {
        if (cnt == 10 || arrow == 0) {
            if (arrow > 0) ryanArr[10] = arrow;
            cal();
            ryanArr[10] = 0;
            return;
        }
        
        if (arrow > apeachArr[cnt]) {
            ryanArr[cnt] = apeachArr[cnt] + 1;
            recursion(cnt + 1, arrow - (apeachArr[cnt] + 1));
            ryanArr[cnt] = 0;
        }
        
        recursion(cnt + 1, arrow);
    }
    
    private void cal() {
        int ryanScore = 0;
        int apeachScore = 0;
        for (int i = 1; i <= 10; i++) {
            if (ryanArr[10 - i] == 0 && apeachArr[10 - i] == 0) continue;
            
            if (ryanArr[10 - i] > apeachArr[10 - i]) {
                ryanScore += i;
            } else {
                apeachScore += i;
            }
        }
        
        if (ryanScore > apeachScore && ryanScore - apeachScore > max) {
            max = ryanScore - apeachScore;
            for (int i = 0; i <= 10; i++) {
                answer[i] = ryanArr[i];
            }
        } else if (ryanScore > apeachScore && ryanScore - apeachScore == max) {
            for (int i = 10; i >= 0; i--) {
                if (ryanArr[i] < answer[i]) {
                    return;
                } else if (ryanArr[i] > answer[i]) {
                    for (int j = 0; j <= 10; j++) {
                        answer[j] = ryanArr[j];
                    }
                }
            }
        }
    }
}
